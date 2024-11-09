package store.Buy

import store.Product.BuyProduct
import store.Product.Product
import store.Promotion.Promotion
import store.View.InputView


val MEMBERSHIP_DISCOUNT_RATE = 0.3

class BuyController(private val promotions: List<Promotion>, private var products: List<Product>) {
    private val inputView = InputView()

    private lateinit var buyList: List<BuyProduct>
    private var totalPrice = 0
    private var promotionDiscount = 1000
    private var membershipDiscount = 0

    private fun checkPromotion(product: String) {

    }

    private fun checkPromotions(buyList: List<BuyProduct>) {
        buyList.forEach { product ->
            checkPromotion(product.name)
        }
    }

    private fun calculateTotalPrice() {
        val prices =
            buyList.map { buyProduct ->
                val price = products.find { product -> buyProduct.name == product.getName() }?.getPrice()
                price?.times(buyProduct.quantity) ?: 0
            }

        totalPrice = prices.reduce { acc, it -> acc + it }
    }

    private fun checkMemberShip() {
//        println(totalPrice - promotionDiscount)
//
//        membershipDiscount = ((totalPrice - promotionDiscount) * MEMBERSHIP_DISCOUNT_RATE).toInt()
    }

    fun buyStart() {
        buyList = inputView.readItem()
        calculateTotalPrice()

        checkPromotions(buyList)
        checkMemberShip()
//        printReceipt()
    }
}