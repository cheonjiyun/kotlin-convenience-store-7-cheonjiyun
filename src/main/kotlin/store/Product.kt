package store

// 상품
class Product(private var name: String, private val price: Int, private var quantity: Int, private var promotion: String) {

    fun getName(): String {
        return name
    }

    fun getPrice(): Int {
        return price
    }

    fun getPromotion() : String{
        return promotion
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