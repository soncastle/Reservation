# 영화관
- 현재 상영중인 영화를 확인하고 예약할 수 있는 플렛폼

# 🛠️ 기술 스택
| 구분         | 스택                                                                                                                                                                                                                  |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Backend**  | ![Java](https://img.shields.io/badge/Java_21-007396?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) ![RESTful API](https://img.shields.io/badge/RESTful_API-6DB33F?style=for-the-badge&logo=rest&logoColor=white) |
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


## 🔧 핵심 기술 및 구현 내용
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

-----------------
### 적용한 세부 기능 및 디자인

- favicon 및 로고 제작
- React의 조건부 렌더링(Conditional JSX)을 통해 페이지별로 동일 기능의 표시 상태를 동적으로 변경
- 무한 스크롤 이미지 적용

-----------------
### 고도화중인 기능
- Security - jwt를 활용한 사용자 데이터 안정성 증대
