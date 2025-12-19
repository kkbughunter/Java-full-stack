## Linux VPS (Systemd Service)
Edit your service file:
```
Environment="SPRING_PROFILES_ACTIVE=prod"
```
Or for **dev** testing:
```
Environment="SPRING_PROFILES_ACTIVE=dev"
```
Then: `sudo systemctl daemon-reload && sudo systemctl restart coreflow`[1]

## Windows (Command Line/IDE)
**Run command**:
```cmd
set SPRING_PROFILES_ACTIVE=dev
java -jar coreflow.jar
```

**Or Gradle** (`build.gradle` or IDE):
```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

**Or Maven**:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Cross-Platform Defaults
**application.yml** (auto-switches):
```yaml
spring:
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:${spring.profiles.active:dev}}  # dev on Windows if unset
```

**Priority**: Systemd/CMD var > JVM arg > YAML default > `dev`[2][1]

**Verify**: Startup logs show `"The following profiles are active: dev"`[3]

[1](https://www.danvega.dev/blog/spring-boot-environment-variables)
[2](https://docs.spring.io/spring-boot/reference/features/external-config.html)
[3](https://www.index.dev/blog/spring-boot-environment-variables)