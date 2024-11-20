# 대전 5반 Spring 관통 프로젝트

---

## Member

- 이하영 
- 강성엽 

---

## ERD

<img src="/uploads/73bcec3be36254d7938335fb75bc2dc3/image.png" width="50%" align="center">

## Class Diagram

회원 관리 클래스 다이어그램

<img src="/uploads/0dbcf3c134b6797570cee0eb32e1e61b/member_classDiagram.jpg" width="100%" align="center">

관광지 정보 클래스 다이어그램

<img src="/uploads/b784ac9969084c00e29c8a2d203ab283/trip_classDiagram.jpg" width="70%" align="center">

---

## UseCase Diagram

<img src="/uploads/b3e7b6f7e42edbd769fe7c7236c01f43/image.png" width="50%">

<img src="/uploads/0a5b96334791c978b7203826b330bc0a/image.png" width="50%">

---

## 구현한 기능

<aside>
💡

Spring Legacy Project를 Spring Boot & MyBatis FrameWork로 변경

Model, View, Controller 구조분리
Model : Trip, Member별 Mapper 및 Service 구현

- Mapper : Database 연동  (TripMapper.java MemberMapper.java)
- Service : business login 및 Mapper 호출 (TripService.java, MemberService.java)

View : 조회한 data 실제 화면에 표현

Controller : Service 호출 및 Rest Controller로 json 반환 (TripController.java, MemberController.java)

</aside>

---

## 세부사항 및 결과

1. 메인 페이지

 <img src="/uploads/80d38f6ecb68cb218d291d819868637d/image.png" width="50%">

2. 지역별 관광지 검색 기능
    1. 전체 장소 조회

        <img src="/uploads/3452da6a78aea98aa7202dda470af7c2/image.png" width="50%">
    
    2. 검색어, 지역, 시설유형 필터에 따른 검색
    - 일부 입력에 대해서도 검색 가능

        <img src="/uploads/6ffb03093356112a8f6825fd1bf75e6d/image.png" width="50%">     
        

3. 회원 정보 등록, 수정, 삭제, 조회 기능
    1. 회원 가입

        <img src="/uploads/f3bebe640ad378df2e4e575adeb4fa0e/image.png" width="50%">
    2. 회원 정보 조회 및 수정, 탈퇴

        <img src="/uploads/2ab7250e16a4d676f41079e21bb1a541/image.png" width="50%">

4. 로그인 / 로그아웃 기능 구현
    - 로그인 / 아이디 저장 / 로그아웃 / 비밀번호 찾기
    1. 로그인 / 아이디 저장

        <img src="/uploads/7a0680c278d8dcf7d912622d94bcfac5/image.png" width="50%">

    2. 비밀번호 찾기
    
        <img src="/uploads/5b12da76fbe6530f9008d3917771d576/image.png" width="50%">

