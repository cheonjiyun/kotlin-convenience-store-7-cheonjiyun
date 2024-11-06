package store

class Product(private var quantity : Int) {

    fun getQuantity() : Int{
        return quantity
    }
    fun buy(quantity : Int){
        checkCanBuyQuantity(quantity)
        this.quantity -= quantity
    }

    fun canBuyQuantity(quantity: Int): Boolean{
        return quantity > this.quantity
    }

    private fun checkCanBuyQuantity(quantity : Int){
        if (canBuyQuantity(quantity)){
            throw IllegalStateException("재고 수량을 넘어갔습니다")
        }
    }
}