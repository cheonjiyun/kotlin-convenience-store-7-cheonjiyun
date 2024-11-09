package store.View

import store.Product.BuyProduct
import java.text.DecimalFormat

class OutputView {
    val dec = DecimalFormat("#,###")

    fun printProducts() {
        println("- 콜라 1,000원 10개 탄산2+1")
    }

    fun printReceipt(buyList : List<BuyProduct>, totalPrice: Int, promotionDiscount: Int, membershipDiscount: Int) {
        println("==============W 편의점================")
        println("상품명\t\t수량\t금액")
        buyList.forEach {
            println("${it.name}\t\t${it.quantity} \t${dec.format(it.price)}")
        }
        println("=============증\t정===============")
//        println("오렌지주스\t\t1")
        println("====================================")
        println("총구매액\t\t2\t${dec.format(totalPrice)}")
        println("행사할인\t\t\t-${dec.format(promotionDiscount)}")
        println("멤버십할인\t\t\t-${dec.format(membershipDiscount)}")
        println("내실돈\t\t\t ${dec.format(totalPrice - promotionDiscount - membershipDiscount)}")
        println()
    }

}