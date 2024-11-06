package store

class Product(private var quantity : Int) {

    fun getQuantity() : Int{
        return quantity
    }
    fun buy(quantity : Int){
        this.quantity -= quantity
    }
}