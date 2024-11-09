package store.View

import camp.nextstep.edu.missionutils.Console
import store.Product.BuyInputProduct
import java.util.*

class InputView {

    private fun input(): String {
        return Console.readLine()
    }

    fun readItem(): List<BuyInputProduct> {
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        val input = input().split(',')

        val regex = Regex("\\[(.*?)]")

        return input.map {
            val produceInput = regex.find(it)?.groupValues?.get(1) ?: throw IllegalArgumentException("상품형식에 맞게 입력해주세요")
            val (name, quantity) = produceInput.split('-')
            BuyInputProduct(name, quantity.toInt())
        }
    }

    fun isMembership(): Boolean  {
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        val input = input()

        return input.uppercase(Locale.getDefault()) == "Y"
    }

    fun isReBuy(): Boolean {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        val input = input()

        return input.uppercase(Locale.getDefault()) == "Y"
    }


    fun isBuyNotPromotion(): Boolean {
        println("현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        val input = input()
        return input.uppercase(Locale.getDefault()) == "Y"
    }
}