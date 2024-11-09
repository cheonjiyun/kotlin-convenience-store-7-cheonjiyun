package store.Buy

import store.Product.BuyProduct
import store.View.InputView

class BuyController {
    private val inputView = InputView()

    private fun checkPromotion(product: String) {

    }

    private fun checkPromotions(buyList: List<BuyProduct>) {
        buyList.forEach { product ->
            checkPromotion(product.name)
        }
    }

    fun buyStart() {
        val buyList = inputView.readItem()
        checkPromotions(buyList)
//        checkMemberShip()
//        printReceipt()
    }
}