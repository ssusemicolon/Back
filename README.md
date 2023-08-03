<div align="center">
  <img width="25%" height="25%" alt="공모전 로고" src="https://github.com/ssusemicolon/Back/assets/67828333/c6c4ef5d-2db1-46d7-a8f6-e121473509ba">
  <h1>'자리 있슈'</h1>
  <p align="center">
    2023년 숭실대학교 소프트웨어학부 여름방학 공모전에 공모한 작품 '자리있슈'의 서버(back end)입니다.
    <br />
    <a href="https://github.com/Instagram-Clone-Coding"><strong>클라이언트(front end) 저장소</strong></a><br>
    <a href="https://github.com/Instagram-Clone-Coding/React_instagram_clone"><strong>웹 서비스 구경하기(서비스 링크)</strong></a>
    <br />
    <div style="background-color: #ffdce0;">주의. AWS 프리티어를 이용해 배포한 웹 서비스로, 서버를 내릴 경우 웹 서비스 제공이 어려운 점에 대해 미리 사과드리며, 양해를 구하는 바입니다.</div>
    <br />
  </p>
</div>

<ul>
  <li><a href="#문제-상황과-서비스의-목적">문제 상황과 서비스의 목적</a></li>
  <li><a href="#REST-API-명세">REST API 명세</a></li>
  <li><a href="#package structure">Package Structure</a></li>
  <li><a href="#commit-convention">Commit Convention</a></li>
  <li><a href="#erd">ERD</a></li>
</ul>

## 문제 상황과 서비스의 목적
인기 있는 카페 및 식당을 가고 싶을 때, 매장의 자리가 없어 계획을 변경한 경험이 있다는 걸 파악함
해당 문제 상황의 근본적인 원인이 무엇일까?
<br />

> <h3 style="color:red;">매장에 직접 방문해야 해당 매장의 여유 좌석 수를 확인할 수 있는 점</h3>

<br />
이를 해결하기 위해, 매장 내 CCTV 이미지와 Obejct Detection 모델을 이용하여 매장 내 밀집도를 제공하는 웹 서비스를 구현하였다. 

## REST API 명세

|URI|HTTP METHOD|기능|
|------|---|---|
|/stores|GET|전체 매장 정보 조회|
|/stores|POST|새로운 매장 등록|
|/stores/:storeId|GET|특정 매장 정보 조회|
|/stores/:storeId|PATCH|매장 정보 변경|
|/stores/:storeId|PUT|매장 삭제|
|/stores?radius=number&latitude=number&longitude=number|GET|위,경도 기반 사용자 근처 매장 조회|
|/stores/search?query=”검색문자열”|GET|매장명 검색을 통한 매장 조회|
|/stores/:storeId/density|POST|특정 매장의 밀집도 갱신|
|/stores/:storeId/density/now|GET|특정 매장의 현재 밀집도 조회|
|/stores/:storeId/density?specificDate=YYYY-mm-dd|GET|특정 매장의 특정 날짜 밀집도 조회|
|/stores/:storeId/density/pastWeek|GET|특정 매장의 최근 일주일 밀집도 조회|
