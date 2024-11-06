package store

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductTest {
    @Test
    fun `상품을 구매하면, 구매한 수량만큼 재고가 줄어든다`() {
        val product = Product(10)
        product.buy(3)

        assert(product.getQuantity() == 10 - 3)
    }

    @Test
    fun `재고 수량보다 많은 수량을 구매하면, 예외가 발생한다`() {
        assertThrows<IllegalStateException> {
            val product = Product(10)
            product.buy(11)
        }
    }
}