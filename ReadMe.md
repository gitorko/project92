# Chat Application

SpringBoot + WebSocket + STOMP + Angular + Clarity

## Setup

Check the version of java, node & yarn.

```bash
npm i -g yarn
$java -version
openjdk version "11.0.7" 2020-04-14 LTS
$node --version
v14.17.0
$npm --version
6.14.13
$yarn --version
1.22.17
$docker --version
Docker version 19.03.5, build 633a0ea
```

### Dev

To Run UI in DEV mode

```bash
cd project92/ui
yarn install
yarn build
yarn start
```

To Run backend in DEV mode

```bash
cd project92
./gradlew bootRun
```

Open [http://localhost:4200](http://localhost:4200) to view it in the browser.


### Prod
To run as a single jar, both UI and backend are bundled to single uber jar.

```bash
./gradlew cleanBuild
cd project92/build/libs
java -jar project92-1.0.0.jar
```

Open [http://localhost:8080](http://localhost:8080) to view it in the browser.

