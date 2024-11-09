package store.Buy

import store.Product.BuyInputProduct
import store.Product.BuyProduct
import store.Product.Product
import store.Promotion.Promotion
import store.View.InputView
import store.View.OutputView


val MEMBERSHIP_DISCOUNT_RATE = 0.3

class BuyController(private val promotions: List<Promotion>, private var products: List<Product>) {
    private val inputView = InputView()
    private val outputView = OutputView()

    private lateinit var buyList: List<BuyProduct>
    private var promotionList = mutableListOf<BuyProduct>()
    private var totalPrice = 0
    private var promotionDiscount = 0
    private var membershipDiscount = 0


    private fun findPromotion(promotionName: String): Promotion? {
        return promotions.find { it.getName() == promotionName }
    }

    private fun findProduct(productName: String): Product? {
        return products.find { it.getName() == productName }
    }

    private fun removeNoPromotion(buyProduct: BuyProduct) {
        val product = findProduct(buyProduct.name) ?: throw IllegalStateException("해당 상품이 없습니다")
        buyProduct.quantity -= product.getQuantity()
    }

    private fun checkRemoveNoPromotion(buyProduct: BuyProduct) {
        if (!inputView.isBuyNotPromotion()) {
            removeNoPromotion(buyProduct)
        }
    }

    private fun checkAddPromotion(): Boolean {

        return false
    }

    private fun calculatePromotion() {
        val discountPrice = promotionList.map { it.quantity * it.price }.reduceOrNull { acc, it -> acc + it }
        if (discountPrice != null) promotionDiscount = discountPrice
    }

    private fun applyPromotion(buyProduct: BuyProduct) {
        // 몇개 증정하는지
        val promotion = promotions.find { buyProduct.promotion?.getName() == it.getName() }
        val product = findProduct(buyProduct.name) ?: throw IllegalStateException("해당 상품이 없습니다")
        val canPromotionQuantity = if(product.getQuantity() > buyProduct.quantity) buyProduct.quantity else product.getQuantity()

        if (promotion?.canApplyPromotion() == true) {
            promotionList.addLast(
                BuyProduct(
                    buyProduct.name,
                    buyProduct.price,
                    promotion.howGetQuantity(canPromotionQuantity),
                    promotion
                )
            )
        }
    }

    private fun checkPromotion(buyProduct: BuyProduct) {
        val storeProduct = findProduct(buyProduct.name) ?: return
        val promotion = storeProduct.getPromotion()?.let { findPromotion(it.getName()) }

        // 해당 상품이 프로모션인가
        if (promotion == null) return

        // 프로모션 재고가 있는가
        if (storeProduct.canBuyQuantity(buyProduct.quantity)) {
//            // 프로모션보다 작게 가져온 경우
//            if(checkAddPromotion()){
//
//            }


        } else {
            // 못받아도 구매할건지
            checkRemoveNoPromotion(buyProduct)
        }

        applyPromotion(buyProduct)

    }

    private fun checkPromotions() {
        buyList.forEach { buyProduct ->
            checkPromotion(buyProduct)
        }
    }

    private fun calculateTotalPrice() {
        totalPrice = buyList.map { buyProduct ->
            buyProduct.price * buyProduct.quantity
        }.reduce { acc, it -> acc + it }
    }

    private fun checkMemberShip() {
        val notPromotionPrice =
            buyList.filter { it.promotion == null }.map { it.quantity * it.price }.reduceOrNull { acc, it -> acc + it }

        if (notPromotionPrice == null) return

        membershipDiscount = (notPromotionPrice * MEMBERSHIP_DISCOUNT_RATE).toInt()
    }

    private fun buyProducts() {
        try {
            buyList.forEach {
                findProduct(it.name)?.buy(it.quantity)
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    private fun inputBuyList() {
        buyList = inputView.readItem().mapNotNull { buyInput ->
            val product = findProduct(buyInput.name) ?: return@mapNotNull null
            BuyProduct(buyInput.name, product.getPrice(), buyInput.quantity, product.getPromotion())
        }
    }

    fun buyStart() {
        outputView.printProducts(products)

        inputBuyList()
        calculateTotalPrice()

        checkPromotions()
        calculatePromotion()

        if (inputView.isMembership()) {
            checkMemberShip()
        }

        buyProducts()

        outputView.printReceipt(buyList, promotionList, totalPrice, promotionDiscount, membershipDiscount)
    }
}