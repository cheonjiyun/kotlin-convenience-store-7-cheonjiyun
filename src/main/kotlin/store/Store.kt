package store

import store.Buy.BuyController
import store.Product.Product
import store.Promotion.Promotion
import store.Util.readFile
import store.View.InputView

class Store {
    private val inputView = InputView()
    private lateinit var products: List<Product>
    private lateinit var promotions: List<Promotion>

    private fun getProducts(): List<Product> {
        return readFile("products.md").map {
            val (name, price, quantity, promotionInput) = it

            Product(name, price.toInt(), quantity.toInt(), promotions.find { promotionItem ->
                promotionItem.getName() == promotionInput
            })
        }
    }

    private fun getPromotion(): List<Promotion> {
        return readFile("promotions.md").map {
            val (name, buy, get, start_date, end_date) = it
            Promotion(name, buy.toInt(), get.toInt(), start_date, end_date)
        }
    }


    private fun saveStock() {
        promotions = getPromotion()
        products = getProducts()
    }


    fun open() {
        saveStock()

        while (true) {
            val buyController = BuyController()
            buyController.buyStart()
            if (!inputView.isReBuy()) break
        }
    }

}