🌟 CheerPing에 오신 것을 환영합니다! 🌟
# 실행 방법
## 목차
- [파일 tree 구조](#파일-tree-구조)
- [패키지 목록 업데이트](#패키지-목록-업데이트)
- [Git 설치](#git-설치)
- [Git 설치 확인](#git-설치-확인)
- [Git 기본 설정](#git-기본-설정)
- [Git 설정 확인 및 clone](#git-설정-확인-및-clone)
- [Java 및 Gradle 설치](#java-및-gradle-설치)
- [의존성 캐시 제거 및 설치](#의존성-캐시-제거-및-설치)
- [애플리케이션 실행](#애플리케이션-실행)


## 0. 파일 tree 구조
```
backend
└── backend
    ├── README.md
    └── chatBot_server
        ├── build
        │   ├── classes
        │   │   └── java
        │   │       ├── main
        │   │       │   └── com
        │   │       │       └── capstone
        │   │       │           └── chatBotServer
        │   │       │               ├── App
        │   │       │               │   ├── Controller
        │   │       │               │   │   └── chatBotController.class
        │   │       │               │   └── Dto
        │   │       │               │       ├── CMRespDto.class
        │   │       │               │       └── ChatMessage.class
        │   │       │               ├── Chat
        │   │       │               │   ├── Chat$ChatBuilder.class
        │   │       │               │   ├── Chat.class
        │   │       │               │   └── ChatMessageRepository.class
        │   │       │               ├── ChatBotServerApplication.class
        │   │       │               ├── Config
        │   │       │               │   └── CorsConfig.class
        │   │       │               └── Service
        │   │       │                   ├── ChatBotserviceImpl.class
        │   │       │                   └── chatBotservice.class
        │   │       └── test
        │   │           └── com
        │   │               └── capstone
        │   │                   └── chatBotServer
        │   │                       └── ChatBotServerApplicationTests.class
        │   ├── generated
        │   │   └── sources
        │   │       ├── annotationProcessor
        │   │       │   └── java
        │   │       │       ├── main
        │   │       │       └── test
        │   │       └── headers
        │   │           └── java
        │   │               ├── main
        │   │               └── test
        │   ├── libs
        │   │   ├── chatBot_server-0.0.1-SNAPSHOT-plain.jar
        │   │   └── chatBot_server-0.0.1-SNAPSHOT.jar
        │   ├── reports
        │   │   └── tests
        │   │       └── test
        │   │           ├── classes
        │   │           │   └── com.capstone.chatBotServer.ChatBotServerApplicationTests.html
        │   │           ├── css
        │   │           │   ├── base-style.css
        │   │           │   └── style.css
        │   │           ├── index.html
        │   │           ├── js
        │   │           │   └── report.js
        │   │           └── packages
        │   │               └── com.capstone.chatBotServer.html
        │   ├── resolvedMainClassName
        │   ├── resources
        │   │   └── main
        │   │       └── application.properties
        │   ├── test-results
        │   │   └── test
        │   │       ├── TEST-com.capstone.chatBotServer.ChatBotServerApplicationTests.xml
        │   │       └── binary
        │   │           ├── output.bin
        │   │           ├── output.bin.idx
        │   │           └── results.bin
        │   └── tmp
        │       ├── bootJar
        │       │   └── MANIFEST.MF
        │       ├── compileJava
        │       │   └── previous-compilation-data.bin
        │       ├── compileTestJava
        │       │   └── previous-compilation-data.bin
        │       ├── jar
        │       │   └── MANIFEST.MF
        │       └── test
        ├── build.gradle
        ├── gradle
        │   └── wrapper
        │       ├── gradle-wrapper.jar
        │       └── gradle-wrapper.properties
        ├── gradlew
        ├── gradlew.bat
        ├── nohup.out
        ├── settings.gradle
        └── src
            ├── main
            │   ├── java
            │   │   └── com
            │   │       └── capstone
            │   │           └── chatBotServer
            │   │               ├── App
            │   │               │   ├── Controller
            │   │               │   │   └── chatBotController.java
            │   │               │   └── Dto
            │   │               │       ├── CMRespDto.java
            │   │               │       └── ChatMessage.java
            │   │               ├── Chat
            │   │               │   ├── Chat.java
            │   │               │   └── ChatMessageRepository.java
            │   │               ├── ChatBotServerApplication.java
            │   │               ├── Config
            │   │               │   └── CorsConfig.java
            │   │               └── Service
            │   │                   ├── ChatBotserviceImpl.java
            │   │                   └── chatBotservice.java
            │   └── resources
            │       └── application.properties
            └── test
                └── java
                    └── com
                        └── capstone
                            └── chatBotServer
                                └── ChatBotServerApplicationTests.java
```

## 1. 패키지 목록 업데이트
```
sudo apt update
```
## 2. Git 설치
```
sudo apt install git
```
## 3. Git 설치 확인
```
git --version
```
## 4. Git 기본 설정
```
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```
## 5. Git 설정 확인 및 clone
```
git config --list
```
```
git clone https://github.com/cheerPing-capstone/backend.git
```
## 6. Java, Gradle 설치 및 설치 확인
#### 6.1 Java, Gradle 설치
```
sudo apt install openjdk-11-jdk
sudo apt install gradle
```
#### 6.2 Java, Gradle 설치 확인
```
java -version
gradle -v
```
## 7. 의존성 캐시 제거 및 설치
```
./gradlew clean
./gradlew build
```
## 8. 애플리케이션 실행
```
java -jar chatBot_server-0.0.1-SNAPSHOT.jar # 위 tree 구조를 보고 위치 참고
./gradlew bootRun
```
>>>>>>> teamCheerping/main
