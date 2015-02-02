Sample play app
=============
Getting started
--------------
* Install docker (https://www.docker.com/) or install mongodb manually (http://www.mongodb.org/)
* Install sbt (http://www.scala-sbt.org/)

```
docker pull dockerfile/mongodb
docker run -d -p 27017:27017 --name mongodb dockerfile/mongodb
```

Creating and running the docker image
-------------------------------------

```
sbt docker:publishLocal
docker run -p 9000:9000 sample-play-app:{versionName}
```
