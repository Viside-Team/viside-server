## 📚 도서 큐레이팅 서비스, VSIDE 📚

### 📍 Contributors

👩🏻‍💻 [이유영](https://github.com/dldbdud314)

  - 로그인/회원가입/프로필 조회
  - 컨텐츠 리스트, 스크랩 리스트
  - 스크랩 기능

🧑🏻‍💻 [박준수](https://github.com/JJONSOO)
  
  - 로그아웃/회원탈퇴
  - 키워드 리스트, 검색 기능
  - 컨텐츠 상세 페이지
  
### 📍 dependencies

```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation group: 'org.springframework.boot', name: 'spring-boot-starter-data-redis', version: '2.4.10'
	implementation 'io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64'
	runtimeOnly 'mysql:mysql-connector-java'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	implementation 'org.springframework.boot:spring-boot-starter-webflux'
	implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
	implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
	implementation group: 'org.apache.commons', name: 'commons-lang3', version: '3.0'
	implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.5'
	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.5'
	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.5'
	implementation 'com.github.maricn:logback-slack-appender:1.4.0'
}
```

### 📍 APIs

👉 [Swagger](http://43.200.221.188:8080/swagger-ui.html#/)

### 📍 ERD

![image](https://user-images.githubusercontent.com/57944099/234151789-28ebeadf-5995-43f5-83f0-75bb00f7acd3.png)

### 📍 배포 Vr.1

✔️ [iOS](https://apps.apple.com/kr/app/vside/id1672915122)

✔️ [Android](https://play.google.com/store/apps/details?id=com.vside.app)
