doccher.go: docker miniclone in Go
==================================

## Preparing your environment

This docker miniclone works only in Linux.
You have to prepare a chroot ready directory before running it. There is a prebuilt busy box image in the images directory.
https://github.com/jpetazzo/docker-busybox/raw/master/rootfs.tar

```
mkdir /home/doccher
tar xvf images/rootfs.tar /home/docker
```

## Usage (from source)

```
go run doccher.go run bash
```

## Usage (from binary)

```
go build doccher.go
./doccher run bash
```
