## 프로젝트 개요

1. 물류 시스템의 상품-상품위치 정보를 통하여, 실무자들이 보다 편하게 업무를 진행할 수 있도록 하기 위함

2. Used Skills:
 1.Spring Boot
 2. Spring Data JPA
 3. AWS EC2 / GitHub Actions (자동배포 완료)
 4. 데이터베이스: MySQL (mysql workbench) 

3. Tools
1.ERD Cloud
  • Figma UI

 환경변수 설정 :
 application-dev.yml

 프로젝트 API 문서 :
 Swagger UI

 *정책 :
 하나의 상품이 여러 위치에 있을 수 있으며,
 하나의 위치에 여러 상품이 올 수도 있다.
 (product : location = m:n)


 ## 로컬 셋팅

1. 로컬 테스트 DB 셋팅
    1. docker 설치
    2. mysql 8.0 image 설치
       ```
       docker pull mysql:8.0
       ```
    3. docker mysql container 백그라운드 실행
       ```
       docker run --name feed_my_sheep -e MYSQL_ROOT_PASSWORD=feedmysheep -e MYSQL_DATABASE=feed_my_sheep -d -p 3306:3306 mysql:8.0
       ```

2. 서버 구동
    1. profile 분리하여 실행 (intelliJ)
       ```
       // application-devel.yml 파일을 읽게 설정합니다.   
       active profiles >> devel
       ```
3. 초기 Data seeding
    - ~~기본 데이터가 셋팅 초기작업을 위해서 API call로 간단하게 구현했습니다.~~
    - ~~/data-seeding **"한번"** 만 호출하시면 됩니다. (자세한 내용은 swagger에 나와있습니다.)~~
    - 시드 데이터를 넣어놓았습니다. 데이터 초기화가 필요하면 말씀해주세요.
---

 
