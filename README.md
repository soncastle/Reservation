# 마이리솔(My Little Sol)
- 주점과 영화 좌석 예매 기능을 결합한 좌석 예약 서비스

# 🛠️ 기술 스택
| 구분         | 스택                                                                                                                                                                                                                  |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Backend**  | ![Java](https://img.shields.io/badge/Java_21-007396?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) ![RESTful API](https://img.shields.io/badge/RESTful_API-6DB33F?style=for-the-badge&logo=rest&logoColor=white) ![JPA](https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=hibernate&logoColor=white)|
| **Frontend** | ![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white) ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-06B6D4?style=for-the-badge&logo=tailwind-css&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
| **Database** | ![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white) |
| **Tool**     | ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)


## ✨ 주요 기능

- 🛒 **영화 확인**
  - themoviedb 영화 상영 API를 활용한 실시간 영화정보 확인

- ➕  **좌석 예약**
  - 영화별 좌석 예약 및 현황 확인

- 📬 **회원 시스템**
  - 회원 가입 및 로그인
  - 영화 및 좌석 예약 현황 확인
  - 예약 취소


## 🔧 핵심 기술 및 구현 내용
- 아키텍처
  - 도메인별 구성을 통한 유지보수성 및 응집도 향상
  - mvc패턴으로 역할분리를 통한 유지보수성 및 개발 효율성 증대
- Front
  - 코드안정성
    - TypeScript를 사용하여 데이터 타입을 명확히 지정하고, 런타임 오류를 사전에 방지
  - 유지보수성
    - 컴포넌트 기반 구조로 중복 코드를 최소화하고 재사용성 향상
    - Tailwind CSS와 일반 CSS를 혼용하여 가독성과 수정 편의성 증대
    - 반응형 이미지 처리를 적용하여 디바이스별 최적화된 이미지 제공
- Back
  - 보안
    - MVC 패턴을 적용하여 기능별 역할을 명확히 분리하고 데이터 전달의 안정성 확보
    - Spring Security를 활용하여 사용자 인증 및 데이터 접근 권한을 안전하게 관리
  - DB
    - JPA를 활용한 효율적인 데이터 관리 및 트랜잭션 처리 구현
    - 좌석 중복 예약 방지를 위한 동시성 해소

## 적용한 세부 기능 및 디자인

- favicon 및 로고 제작
- React의 조건부 렌더링(Conditional JSX)을 통해 페이지별로 동일 기능의 표시 상태를 동적으로 변경
- 무한 스크롤 이미지 적용

-----------------
### 수정 및 개발중인 기능
- 유효성 처리

### 고도화 예정인 기능
- Test : Junit5를 활용한 유지보수 안정성 확보
- Security : jwt를 활용한 사용자 데이터 안정성 증대



-----------------
#### 페이지 및 기능별 수정현황
#5. 예외처리 및 SRP 확보
- back
  - 예외처리
    - 전역(Global) 예외 처리와 도메인별(회원/예약 등) 예외 클래스를 분리하여, 오류 대응을 체계화하고 서비스 안정성을 강화
    - ErrorCode, ErrorResponse, BusinessException을 중심으로 일관된 에러 응답 구조 구현
    - Repository·Service 등 기능별로 세부 예외를 분리해 문제 지점을 명확히 파악 가능하도록 개선
  - SRP
    - 전역 예외처리와 도메인별 세부 예외 분리를 통해 오류 발생 지점을 명확히 하고, UserService와 AuthService의 책임을 분리하여 SRP를 완전히 준수하는 구조로 개선
      
#4.
- back(좌석 예약 및 취소)
  - 동시성 해소
    - 좌석 중복 예약 방지를 위한 DB 제약조건 및 JPA 연관관계 활용
    - Reservation–SeatReservation 구조 개선으로 레이스 컨디션 최소화
    
  - 리팩토링
    - Stream 기반 데이터 처리로 코드 가독성 및 유지보수성 향상
    - JPA : CRUD 중심의 자동 쿼리 기반 단순화를 통한 복잡도 및 의존성 최소화

#3.
- back
  - 예약 취소시 예약됐던 좌석을 예약 가능 좌석으로 복구
  - 예약 취소 후 동일 영화가 예약 안되는 오류 수정

#2.
- front / back
  - myPage
    - 예약 취소 기능 완료
    - 예약 취소 관련 중복 코드 통합 및 구조 개선

#1.
- front
  - myPage : 사용자 예약 정보 조회 기능 추가
  - movieList : 개봉일 기준 내림차순 정렬 적용 및 포스터 크기 통일화
  - 로그아웃 : 새로고침 대신 SPA 특성 활용하여 페이지 이동 방식으로 개선 
  - 전체 : 페이지별 레이아웃 일관성 확보

- back
  - stream을 활용하여 코드 가독성과 유지보수성 향상
  - JPA : @ElementCollection 적용을 통한 데이터 구조 단순화 및 DB 효율성 개선
