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