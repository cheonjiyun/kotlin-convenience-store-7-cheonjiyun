package store.View

import store.Product.BuyProduct
import store.Product.Product
import java.text.DecimalFormat

class OutputView {
    val dec = DecimalFormat("#,###")

    fun printProducts(products: List<Product>) {
        products.forEach {
            val price = if(it.getQuantity() == 0) "재고 없음" else "${it.getQuantity()}개"
            println("- ${it.getName()} ${dec.format(it.getPrice())}원 $price ${it.getPromotion()?.getName() ?: ""}")
        }
        println("- 콜라 1,000원 10개 탄산2+1")
    }

    fun printReceipt(buyList : List<BuyProduct>, promotionList : MutableList<BuyProduct>, totalPrice: Int, promotionDiscount: Int, membershipDiscount: Int) {
        println("==============W 편의점================")
        println("상품명\t\t수량\t금액")
        buyList.forEach {
            println("${it.name}\t\t${it.quantity} \t${dec.format(it.price * it.quantity)}")
        }
        println("=============증\t정===============")
        promotionList.forEach {
            println("${it.name}\t\t${it.quantity}")
        }
        println("====================================")
        println("총구매액\t\t2\t${dec.format(totalPrice)}")
        println("행사할인\t\t\t-${dec.format(promotionDiscount)}")
        println("멤버십할인\t\t\t-${dec.format(membershipDiscount)}")
        println("내실돈\t\t\t ${dec.format(totalPrice - promotionDiscount - membershipDiscount)}")
        println()
    }

}