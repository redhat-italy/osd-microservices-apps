package main

import (
	"fmt"
	"os"
	"os/exec"
	"syscall"
	"path/filepath"
)

func main() {
	switch os.Args[1] {
	case "run":
		parent()
	case "child":
		child()
	default:
		panic("wat should I do")
	}
}

func parent() {
	cmd := exec.Command("/proc/self/exe", append([]string{"child"}, os.Args[2:]...)...)
	//cmd := exec.Command(os.Args[2], os.Args[3:]...)
	cmd.SysProcAttr = &syscall.SysProcAttr{
		Cloneflags: syscall.CLONE_NEWUTS | syscall.CLONE_NEWPID | syscall.CLONE_NEWNS,
	}
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	if err := cmd.Run(); err != nil {
		fmt.Println("ERROR", err)
		os.Exit(1)
	}
}

func child() {

	wd, err := os.Getwd()
	if err != nil {
		fmt.Errorf("Error get working dir: %v", err)
	} else {
		fmt.Println(wd)
	}
	if err := chRoot("/path/to/busybox"); err != nil {
		fmt.Println("ERROR", err)
		os.Exit(1)
	}
	cmd := exec.Command(os.Args[2], os.Args[3:]...)
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	if err := cmd.Run(); err != nil {
		fmt.Println("ERROR", err)
		os.Exit(1)
	}
}

func chRoot(root string) error {

	err:= syscall.Mount(filepath.Join(root, "/proc"), filepath.Join(root, "/proc"), "proc", syscall.MS_NOEXEC | syscall.MS_NOSUID | syscall.MS_NODEV, "")
	if err != nil {
		return fmt.Errorf("mount proc error %v", err)
	}

	err = syscall.Chroot(root)
	if err != nil {
		return fmt.Errorf("chroot error %v", err)
	}

	err = syscall.Chdir("/");

	return err
}

func pivotRoot(root string) error {
	// we need this to satisfy restriction:
	// "new_root and put_old must not be on the same filesystem as the current root"
	if err := syscall.Mount(root, root, "bind", syscall.MS_BIND|syscall.MS_REC, ""); err != nil {
		return fmt.Errorf("Mount rootfs to itself error: %v", err)
	}
	// create rootfs/.pivot_root as path for old_root
	pivotDir := filepath.Join(root, ".pivot_root")
	os.Remove(pivotDir)
	if err := os.Mkdir(pivotDir, 0777); err != nil {
		return err
	}
	// pivot_root to rootfs, now old_root is mounted in rootfs/.pivot_root
	// mounts from it still can be seen in `mount`
	if err := syscall.PivotRoot(root, pivotDir); err != nil {
		return fmt.Errorf("pivot_root %v", err)
	}
	// change working directory to /
	// it is recommendation from man-page
	//if err := syscall.Chdir("/"); err != nil {
	//	return fmt.Errorf("chdir / %v", err)
	//}
	// path to pivot root now changed, update
	pivotDir = filepath.Join("/", ".pivot_root")
	// umount rootfs/.pivot_root(which is now /.pivot_root) with all submounts
	// now we have only mounts that we mounted ourselves in `mount`
	if err := syscall.Unmount(pivotDir, syscall.MNT_DETACH); err != nil {
		return fmt.Errorf("unmount pivot_root dir %v", err)
	}
	// remove temporary directory
	return os.Remove(pivotDir)
}
