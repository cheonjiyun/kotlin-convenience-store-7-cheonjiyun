package store.Buy

import store.Product.BuyProduct
import store.Product.Product
import store.Promotion.Promotion
import store.View.InputView


val MEMBERSHIP_DISCOUNT_RATE = 0.3

class BuyController(private val promotions: List<Promotion>, private var products: List<Product>) {
    private val inputView = InputView()

    private lateinit var buyList: List<Product>
    private var totalPrice = 0
    private var promotionDiscount = 1000
    private var membershipDiscount = 0


    private fun findPromotion(promotionName: String): Promotion? {
        return promotions.find { it.getName() == promotionName }
    }

    private fun findProduct(productName: String): Product? {
        return products.find { it.getName() == productName }
    }

    private fun removeNoPromotion(buyProduct : BuyProduct){
        buyProduct.quantity
    }

    private fun checkRemoveNoPromotion(buyProduct : BuyProduct){
        if(inputView.notPromotion()){
            removeNoPromotion(buyProduct)
        }
    }

    private fun checkPromotion(buyProduct: BuyProduct) {
        val storeProduct = findProduct(buyProduct.name) ?: return
        val promotion = storeProduct.getPromotion()?.let { findPromotion(it.getName()) }

        // 해당 상품이 프로모션인가
        if (promotion == null) return


        println(storeProduct.canBuyQuantity(buyProduct.quantity))
        // 프로모션 재고가 있는가
        if(storeProduct.canBuyQuantity(buyProduct.quantity)){
            // 프로모션보다 작게 가져온 경우
        }else{
            // 못받아도 구매할건지
            checkRemoveNoPromotion(buyProduct)
        }

    }

    private fun checkPromotions(buyList: List<BuyProduct>) {
        buyList.forEach { buyProduct ->
            checkPromotion(buyProduct)
        }
    }

    private fun calculateTotalPrice() {
//        val prices =
//            buyList.map { buyProduct ->
//                val price = products.find { product -> buyProduct.name == product.getName() }?.getPrice()
//                price?.times(buyProduct.quantity) ?: 0
//            }
//
//        totalPrice = prices.reduce { acc, it -> acc + it }
    }

    private fun checkMemberShip() {
//        println(totalPrice - promotionDiscount)
//
//        membershipDiscount = ((totalPrice - promotionDiscount) * MEMBERSHIP_DISCOUNT_RATE).toInt()
    }

    fun buyStart() {
        val buyInputList = inputView.readItem()
        buyList = buyInputList.mapNotNull { buyInput ->
            findProduct(buyInput.name)
        }


        calculateTotalPrice()
//
//        checkPromotions(buyList)
//        checkMemberShip()
//        printReceipt()
    }
}