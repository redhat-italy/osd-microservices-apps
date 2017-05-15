doccher.go: docker miniclone in Go
==================================

## Preparing your environment

This docker miniclone works only in Linux.
You have to prepare a chroot ready directory before running it. There is a prebuilt busy box image in the images directory.
https://github.com/jpetazzo/docker-busybox/raw/master/rootfs.tar

**All the following commands need to be executed as a privileged user (i.e. `root` or using `sudo`)**

```
mkdir /home/doccher
tar xvf images/rootfs.tar -C /home/doccher
```

## Usage (from source)

**All the following commands need to be executed as a privileged user (i.e. `root` or using `sudo`)**

```
export PATH=$PATH:/bin
go run doccher.go run bash
```

## Usage (from binary)

```
go build doccher.go
export PATH=$PATH:/bin
./doccher run bash
```

## Credits

Original code by Julian Friedman [1] and Liz Rice [2]

[1] https://www.infoq.com/articles/build-a-container-golang

[2] https://youtu.be/HPuvDm8IC-4?list=PLDWZ5uzn69eyh791ZTkEA9OaTxVpGY8_g
