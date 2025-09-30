# Public WiFi Service

서울시 공공 와이파이 Open API를 활용한 위치 기반 웹서비스

## 📋 프로젝트 개요

이 프로젝트는 서울시 공공 와이파이 Open API를 활용하여 사용자의 위치 기반으로 근처 와이파이 정보를 제공하는 웹 애플리케이션입니다.

## 🚀 주요 기능

- **와이파이 데이터 가져오기**: 서울시 공공 와이파이 Open API를 통해 와이파이 정보를 데이터베이스에 저장
- **근처 와이파이 검색**: 사용자의 현재 위치(위도, 경도)를 기반으로 가장 가까운 20개의 와이파이 정보 제공
- **위치 히스토리**: 사용자의 검색 위치를 히스토리로 저장 및 조회
- **웹 인터페이스**: JSP를 활용한 사용자 친화적인 웹 인터페이스

## 🛠 기술 스택

- **Backend**: Spring Boot 3.5.5, Java 17
- **Database**: SQLite
- **ORM**: Spring Data JPA, Hibernate
- **Frontend**: JSP, JSTL
- **HTTP Client**: OkHttp3
- **JSON Processing**: Gson
- **Build Tool**: Gradle

## 📁 프로젝트 구조

```
src/main/java/com/jinsehyun/wifi/
├── controller/          # 웹 컨트롤러
│   ├── ViewController.java      # 메인 페이지 라우팅
│   ├── NearbyController.java    # 근처 와이파이 검색
│   ├── ImportController.java    # 와이파이 데이터 가져오기
│   └── HistoryController.java   # 위치 히스토리 관리
├── domain/             # 엔티티 모델
│   ├── WifiAp.java            # 와이파이 정보 엔티티
│   └── LocationHistory.java   # 위치 히스토리 엔티티
├── dto/                # 데이터 전송 객체
│   ├── NearbyWifiView.java     # 근처 와이파이 뷰 모델
│   └── WifiApiEnvelope.java    # API 응답 래퍼
├── repository/         # 데이터 접근 계층
│   ├── WifiApRepository.java
│   └── LocationHistoryRepository.java
├── service/            # 비즈니스 로직
│   ├── NearbyService.java      # 근처 와이파이 검색 서비스
│   └── WifiImportService.java  # 와이파이 데이터 가져오기 서비스
└── WifiApplication.java # 메인 애플리케이션 클래스
```

## 🚀 실행 방법

### 1. 환경 설정

1. Java 17 이상 설치
2. Gradle 설치 (또는 Gradle Wrapper 사용)

### 2. API 키 설정

`application.yml` 파일에서 서울시 Open API 키를 설정하거나 환경변수로 설정:

```yaml
seoul:
  api-key: ${SEOUL_API_KEY:your_api_key_here}
```

### 3. 애플리케이션 실행

```bash
# Gradle Wrapper 사용
./gradlew bootRun

# 또는 JAR 파일 빌드 후 실행
./gradlew build
java -jar build/libs/wifi-0.0.1-SNAPSHOT.jar
```

### 4. 웹 브라우저 접속

- 메인 페이지: `http://localhost:8080`
- 와이파이 데이터 가져오기: `http://localhost:8080/import/wifi`

## 📊 API 엔드포인트

| 메서드 | 경로 | 설명 |
|--------|------|------|
| GET | `/` | 메인 페이지 |
| GET | `/nearby` | 근처 와이파이 검색 (lat, lnt 파라미터 필요) |
| GET | `/import/wifi` | 와이파이 데이터 가져오기 |
| GET | `/history` | 위치 히스토리 조회 |

## 🗄 데이터베이스 스키마

### WIFI_AP 테이블
- `mgr_no`: 관리번호 (Primary Key)
- `wrdofc`: 자치구/설치기관
- `main_nm`: 와이파이명
- `adres1`, `adres2`: 주소
- `lat`, `lnt`: 위도, 경도
- `work_dttm`: 데이터 갱신일시

### HISTORY 테이블
- `id`: 히스토리 ID (Primary Key)
- `x`: 경도
- `y`: 위도
- `query_dttm`: 검색 일시

## 🔧 설정 파일

### application.yml
```yaml
spring:
  datasource:
    url: jdbc:sqlite:./wifi.db
    driver-class-name: org.sqlite.JDBC
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

seoul:
  api-key: ${SEOUL_API_KEY:your_api_key}
  host: openapi.seoul.go.kr
  port: 8088
  dataset: TbPublicWifiInfo
```

## 📝 사용법

1. **와이파이 데이터 가져오기**: `/import/wifi` 페이지에서 서울시 공공 와이파이 데이터를 데이터베이스에 저장
2. **근처 와이파이 검색**: 메인 페이지에서 현재 위치(위도, 경도)를 입력하여 근처 와이파이 검색
3. **위치 히스토리 조회**: 이전에 검색한 위치들을 히스토리에서 확인

## 🚨 주의사항

- 서울시 Open API 키가 필요합니다
- 대용량 데이터 가져오기 시 시간이 오래 걸릴 수 있습니다
- SQLite 데이터베이스를 사용하므로 동시 접속자 수에 제한이 있을 수 있습니다

## 📄 라이선스

이 프로젝트는 교육 목적으로 개발되었습니다.
