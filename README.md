# Project 92

Chat Server

[https://gitorko.github.io/chat-server/](https://gitorko.github.io/chat-server/)


### Version

Check version

```bash
$java -version
openjdk version "17.0.3" 2022-04-19 LTS

$node --version
v16.16.0

$yarn --version
1.22.18
```

### Dev

To Run backend in DEV mode

```bash
./gradlew clean build
./gradlew bootRun
```

To Run UI in DEV mode

```bash
cd ui
yarn install
yarn build
yarn start
```

Open [http://localhost:4200](http://localhost:4200)

### Prod

To run as a single jar, both UI and backend are bundled to single uber jar.

```bash
./gradlew cleanBuild
cd project92/build/libs
java -jar project92-1.0.0.jar
```

Open [http://localhost:8080](http://localhost:8080)

### Docker

```bash
./gradlew cleanBuild
docker build -f docker/Dockerfile --force-rm -t project92:1.0.0 .
docker images |grep project92
docker tag project92:1.0.0 gitorko/project92:1.0.0
docker push gitorko/project92:1.0.0
docker-compose -f docker/docker-compose.yml up 
```
