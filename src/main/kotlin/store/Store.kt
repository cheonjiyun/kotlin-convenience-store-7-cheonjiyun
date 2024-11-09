package store

import store.Promotion.checkPromotion
import store.Promotion.Promotion
import store.View.InputView

class Store {
    private val inputView = InputView()
    private lateinit var products: List<Product>
    private lateinit var promotions: List<Promotion>

    private fun getProducts(): List<Product> {
        return readFile("products.md").map {
            val (name, price, quantity, promotion) = it
            Product(name, price.toInt(), quantity.toInt(), promotion)
        }
    }

    private fun getPromotion(): List<Promotion> {
        return readFile("promotions.md").map {
            val (name, buy, get, start_date, end_date) = it
            Promotion(name, buy.toInt(), get.toInt(), start_date, end_date)
        }
    }


    private fun saveStock() {
        products = getProducts()
        promotions = getPromotion()
    }

    private fun visitCustomer() {
        val buyList = inputView.readItem()
//        checkPromotion()
//        buyList.forEach {
//
//        }

    }

    fun open() {
        saveStock()

        while (true) {
            visitCustomer()
            if (!inputView.isReBuy()) break
        }
    }

}