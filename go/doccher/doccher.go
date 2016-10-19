// +build linux

// original code by Julian Friedman [1] and Liz Rice [2]
// [1] https://www.infoq.com/articles/build-a-container-golang
// [2] https://youtu.be/HPuvDm8IC-4?list=PLDWZ5uzn69eyh791ZTkEA9OaTxVpGY8_g

package main

import (
	"fmt"
	"os"
	"os/exec"
	"syscall"
)

func main() {

	if len(os.Args) < 3 {
		fmt.Println("usage: doccher run command")
		os.Exit(1)
	}
	switch os.Args[1] {
	case "run":
		parent()
	case "child":
		child()
	default:
		panic("wat?")
	}
}

func parent() {
	cmd := exec.Command("/proc/self/exe", append([]string{"child"}, os.Args[2:]...)...)
	cmd.SysProcAttr = &syscall.SysProcAttr{
		Cloneflags: syscall.CLONE_NEWUTS | syscall.CLONE_NEWPID | syscall.CLONE_NEWNS,
	}
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	must(cmd.Run())

}

func child() {

	fmt.Printf("running %v as pid %d\n", os.Args[2:], os.Getpid())
	cmd := exec.Command(os.Args[2], os.Args[3:]...)
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	must(syscall.Chroot("/home/doccher"))
	must(syscall.Chdir("/"))
	must(syscall.Mount("proc", "proc", "proc", 0, ""))
	must(cmd.Run())
}


func must(err error) {
	if err != nil {
		panic(err)
	}
}
