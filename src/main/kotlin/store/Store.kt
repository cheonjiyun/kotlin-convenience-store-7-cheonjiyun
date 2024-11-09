package store

import store.Controller.BuyController
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
        while (true) {
            inputView.readItem()
            val buyController = BuyController()
            buyController.start()

            if (!inputView.isReBuy()) break
        }
    }

    fun open() {
        saveStock()
        visitCustomer()
    }

}