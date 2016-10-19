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

## Credits

Original code by Julian Friedman [1] and Liz Rice [2]

[1] https://www.infoq.com/articles/build-a-container-golang
[2] https://youtu.be/HPuvDm8IC-4?list=PLDWZ5uzn69eyh791ZTkEA9OaTxVpGY8_g
