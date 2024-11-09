package store

import store.Controller.BuyController
import store.View.InputView

class Store {
    private val inputView = InputView()

    private fun saveStock() {
        val products = readFile("products.md")
        val promotions = readFile("promotions.md")
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