# java-convenience-store-precourse
## 상품
- [x] 상품을 생성한다
  - [x] 상품은 이름, 가격, 수량, 프로모션 값을 가지고 있다.
  - [x] 동일 상품에 여러 프로모션은 적용할 수 없다.
- [x] 생성한 상품은 편의점에 적재한다.

## 주문
- [x] 주문을 생성한다.
- [x] 상품을 구매한다.
  - [x] 상품이 판매되면 수량이 1개 줄어든다.
- [x] 상품의 재고가 없으면 에러 메세지를 출력한다.

## 프로모션
- 프로모션 정보를 생성한다.
- 구매 시 프로모션 재고를 우선적으로 차감한다.
- 오늘 날짜가 프로모션 기간이 지났다면, 적용하지 않는다.
- 프로모션 상품에 대해 몇 개를 더 가져오면 혜택을 받을 수 있는지 개수를 반환한다.
- 구매하고자 하는 제품의 개수보다 프로모션 제품의 재고의 개수가 적다면 프로모션이 적용되지 않는 개수에 대해 반환한다.
- 상품의 프로모션 기간이 끝났다면 프로모션을 해제한다.

## 계산
- 구매한 상품의 총 가격을 구한다.
- 행사할인된 가격을 구한다.
- 멤버십할인된 가격을 구한다.