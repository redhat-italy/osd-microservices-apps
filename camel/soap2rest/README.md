# Fuse Integration Services Demo
This demo is based on [quickstart](http://fabric8.io/guide/quickstarts/running.html) archetype project and the aim is to explain how to reproduce a small demo on each of the two build workflos available for now in Fuse Integration Services:
- Build the image outside Openshift then push it
- Push the code then let Openshift build the image
Fuse Integration Services is a collection of tools shipped by Red Hat to develop and run JBoss Fuse application on Openshift.

## What you need
To be able to demo the 2 scenarios an Openshift environment is needed, one of the easies way to achive that is by using a virtual machine provisioned via vagrant; there are many option to do so and many kind of images (demobuilder, fabric8v2 etc.) here are some caveats for the CDK environment by Red Hat:

- Be sure to install virtualbox and vagrantand be able to run vagrant without root access (if on linux)
- Download virtualbox vm image and cdkv2 from https://access.redhat.com/downloads/content/293/ver=2/rhel---7/2.0.0/x86_64/product-software
- Unzip `cdk-2.0.0-beta4.zip` and go to `cdk/components/rhel/rhel-ose`
- Modify `Vagrant` file replacing `OPTINS` variable with:
  - `OPTIONS='--selinux-enabled -H tcp://0.0.0.0:2376 -H unix:///var/run/docker.sock --tlscacert=/etc/docker/ca.pem --tlscert=/etc/docker/server-cert.pem --tlskey=/etc/docker/server-key.pem --tlsverify --storage-opt dm.no_warn_on_loop_devices=true'
`
  - Set memory and cpu to something reasonable for your hardware a minimum would be `v.memory = 4096` and `v.cpus   = 2`
  - Configure landrush plugin for DNS: `config.landrush.enabled = true`, `config.landrush.tld = 'cdk.vm'`, `config.landrush.host 'cdk.vm', "#{PUBLIC_ADDRESS}"`
- vagrant plugin install vagrant-registration vagrant-service-manager landrush
- vagrant box add --name cdkv2 path/to/rhel-cdk-kubernetes-7.2-19.x86_64.vagrant-virtualbox.box
- Configure dnsmasq:
  - Be sure to turn off libvirt and kill dnsmasq
  - configure dnsmasd by creating ``/etc/dnsmasq.d/vagrant-landrush` with this content `server=/.vm/127.0.0.1#10053`
  - change your `/etc/resolv.conf` to contain as first line `nameserver 127.0.0.1`
  - more info and full explanation here: http://nts.strzibny.name/dns-for-your-vagrant-needs-with-landrush-libvirt-and-dnsmasq/
- vagrant up (fill in the required username and password with a valid RH account)

> To run command as system:admin in cdk once you have sshed in to it with `vagrant ssh` you can do `sudo oc get nodes --config=/var/lib/origin/openshift.local.config/master/openshift-master.kubeconfig`

## Build then push demo
To showcase this scenario you can follow these steps:
- Get an example project by cloning this one `git clone https://github.com/valdar/fis-example` or by using a maven artifact `mvn archetype:generate -DarchetypeCatalog=https://repo.fusesource.com/nexus/content/groups/public/io/fabric8/archetypes/archetypes-catalog/2.2.0.redhat-079/archetypes-catalog-2.2.0.redhat-079-archetype-catalog.xml`
- `export KUBERNETES_MASTER=https://10.1.2.2:8443`
- `export KUBERNETES_DOMAIN=cdk.vm`
- log in to Openshift cluster from your machine `oc login test-admin`
- create a new project (namespace), `oc new-project buildpush`
- import s2i images in the project: `oc create -n buildpush -f https://raw.githubusercontent.com/jboss-fuse/application-templates/master/fis-image-streams.json`
- build the code, build doker image put it in Openshift docker registry, generate `.json` Openshift configuration files, push (apply) them to Openshift: `mvn -Pf8-local-deploy`

> Be sure to have these repo configured in your maven `setting.xml`:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <localRepository/>
    <profiles>
    <profile>
            <id>fusesource</id>
            <repositories>
                <repository>
                    <id>fusesource</id>
                    <url>http://repo.fusesource.com/nexus/content/groups/public/</url>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                </repository>
               <repository>
                    <id>fusesource.snapshot</id>
                    <url>http://repo.fusesource.com/nexus/content/groups/public-snapshots/</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                    <releases>
                        <enabled>false</enabled>
                    </releases>
                </repository>
                <repository>
                    <id>apache-public</id>
                    <url>https://repository.apache.org/content/groups/public/</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                </repository>
            </repositories>
         <pluginRepositories>
            <pluginRepository>
	            <id>fusesource-plugin</id>
                    <url>http://repo.fusesource.com/nexus/content/groups/public/</url>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
	    </pluginRepository>
      </pluginRepositories>
  </profile>

	<profile>
	  <id>jboss-ga-repository</id>
    <repositories>
	    <repository>
	      <id>jboss-ga-repository</id>
	      <url>http://maven.repository.redhat.com/techpreview/all</url>
	      <releases>
		        <enabled>true</enabled>
	      </releases>
	      <snapshots>
		        <enabled>false</enabled>
	      </snapshots>
	    </repository>
      <repository>
        <id>jboss-groups-ea-repository</id>
        <url>https://repository.jboss.org/nexus/content/groups/ea</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
      </repository>
	  </repositories>
	  <pluginRepositories>
      <pluginRepository>
        <id>jboss-groups-ea-plugin-repository</id>
        <url>https://repository.jboss.org/nexus/content/groups/ea</url>
        <releases>
          <enabled>true</enabled>
        </releases>
        <snapshots>
          <enabled>false</enabled>
        </snapshots>
      </pluginRepository>
	    <pluginRepository>
	      <id>jboss-ga-plugin-repository</id>
	      <url>http://maven.repository.redhat.com/techpreview/all</url>
	      <releases>
		      <enabled>true</enabled>
	      </releases>
	      <snapshots>
		      <enabled>false</enabled>
	      </snapshots>
	    </pluginRepository>
	  </pluginRepositories>
	</profile>
  </profiles>

  <activeProfiles>
	 <activeProfile>jboss-ga-repository</activeProfile>
	 <activeProfile>fusesource</activeProfile>
  </activeProfiles>
</settings>
```

## Push then build demo
To showcase this scenario you can follow these steps:
- `export KUBERNETES_MASTER=https://10.1.2.2:8443`
- `export KUBERNETES_DOMAIN=cdk.vm`
- log in to Openshift cluster from your machine `oc login test-admin`
- create a new project (namespace), `oc new-project pushbuild`
- import s2i images in the project: `oc create -n pushbuild -f https://raw.githubusercontent.com/jboss-fuse/application-templates/master/fis-image-streams.json`
- import this example application template in the project: `oc create -n pushbuild -f https://raw.githubusercontent.com/valdar/fis-example/master/quickstart-template.json`
- use the just created template to instantiate an application pointing it to the git sourcecode repo `https://github.com/valdar/fis-example`

Now Openshift should grab the code for you, build the docker image containing your code and spin the application up. 
