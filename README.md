## Excel to DTO Generator
Excel 파일에서 Java DTO(Data Transfer Object) 클래스를 자동으로 생성합니다.
이 프로젝트는 반복적인 DTO 클래스 작성 작업을 자동화하여 개발 생산성을 향상시킵니다.

카드사 송수신 인터페이스 DTO 생성을 반복적으로 처리하는 작업을 효율적으로 처리하고자 제작하게 되었습니다.

### 주요 기능
* Excel 파일 기반의 DTO 클래스 자동 생성 
* 여러 타입의 DTO 지원 (START, HEADER, DATA, TAILER, END)
* 필드별 주석 자동 생성 
* Lombok을 활용한 Getter/Setter 자동 구현 
* String과 Integer 타입 자동 감지 및 적용

### 시스템 요구사항
* JAVA 8 이상
* MAVEN 또는 Gradle

### 의존성 추가
**MAVEN 사용하는 경우**
```xml
<dependencies>
    <!-- Apache POI - Excel 파일 처리용 -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
    </dependency>
    
    <!-- Lombok - Getter/Setter 자동 생성용 -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.26</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

**Gradle을 사용하는 경우**
```groovy
dependencies {
    // Apache POI - Excel 파일 처리용
    implementation 'org.apache.poi:poi-ooxml:5.2.3'
    
    // Lombok - Getter/Setter 자동 생성용
    compileOnly 'org.projectlombok:lombok:1.18.26'
    annotationProcessor 'org.projectlombok:lombok:1.18.26'
}
```