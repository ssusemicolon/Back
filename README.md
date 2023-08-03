# Back
2023년 숭실대 소프트웨어학부 공모전 '세미콜론'팀의 작품 '자리있슈' 서버

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
