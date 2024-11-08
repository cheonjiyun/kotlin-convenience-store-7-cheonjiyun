package store

// 상품
class Product(private var name: String, private var quantity: Int, private val price: Int) {

    fun getName(): String {
        return name
    }

    fun getPrice(): Int {
        return price
    }

    fun getQuantity(): Int {
        return quantity
    }

    fun buy(quantity: Int) {
        checkCanBuyQuantity(quantity)
        this.quantity -= quantity
    }

    fun canBuyQuantity(quantity: Int): Boolean {
        return quantity > this.quantity
    }

    private fun checkCanBuyQuantity(quantity: Int) {
        if (canBuyQuantity(quantity)) {
            throw IllegalStateException("재고 수량을 넘어갔습니다")
        }
    }
}