# kotlin-convenience-store-precourse

## 기능목록

- 재고관리
- 상품
    - 가격
    - 수량

- 프로모션 및 멤버십

- 영수증
    - 추가구매
    - 종료

### TDD

- 상품
    - [x] 구매한 개수만큼 상품의 수량이 줄어든다


- [ ] 재고 수량을 고려하여 결제 가능 여부를 확인한다
- [ ] 상품을 구매할 때마다, 결제된 수량만큼 상품의 재고에서 차감한다.
- [ ] 구매할 때 정확한 재고 정보를 제공한다.

- [ ] 프로모션 기간인경우 할인을 적용한다.
- [ ] 프로모션 n개 구매시 1개 무료 증정
- [ ] 동일 상품에는 한번만 적용된다.
- [ ] (예외) 프로모션을 제공할정도로 재고가 충분하지 않은 경우
- [ ] 프로모션 기간 중에는 프로모션 재고를 우선적으로 차감한다.
- [ ] 없으면 일반 재고를 사용한다.
- [ ] 고객이 해당 수량보다 적게 가져온 경우, 혜택을 받을 수 있다고 안내한다. -> 그럼 다시 물어야곘지
- [ ] 재고가 부족하여 혜택없이 결제해야하는 경우, 일부 수량에 대해 정가로 결제하게 됨

- [ ] 멤버쉽 회원은 프로모션 미적용 금액의 30% 할인
- [ ] 프로모션 후 남은 금액에 대해 멤버십 할인
- [ ] 멤버십 할인 최대 한도는 8000

- [ ] 구매내역과 할인 요약하여 출력
- [ ] 구매한 상품명, 수량, 가격
- [ ] 무료 증성 상품
- [ ] 총 구매액, 행사할인금액, 멤버십 할인금액, 최종 내야할 금액

## TDD

이번엔 TDD로 진행해보며 테스트를 익혀보기로 했다.