package store

import org.junit.jupiter.api.Test

class ProductTest {
    @Test
    fun `상품을 구매하면, 구매한 수량만큼 재고가 줄어든다`()  {
        val product = Product(10)
        product.buy(3)

        assert(product.getQuantity() == 10 - 3)
    }
}