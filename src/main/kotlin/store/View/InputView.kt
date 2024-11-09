package store.View

import camp.nextstep.edu.missionutils.Console
import store.Product.BuyProduct
import java.util.*

class InputView {

    private fun input(): String {
        return Console.readLine()
    }

    fun readItem(): List<BuyProduct> {
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        val input = input().split(',')

        return input.map {
            val (name, quantity) = it.split('-')
            BuyProduct(name, quantity.toInt())
        }
    }

    fun isReBuy(): Boolean {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        val input = input()

        return input.uppercase(Locale.getDefault()) == "Y"
    }


    fun notPromotion() : Boolean {
        println("현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        val input = input()
        return input.uppercase(Locale.getDefault()) == "Y"
    }
}