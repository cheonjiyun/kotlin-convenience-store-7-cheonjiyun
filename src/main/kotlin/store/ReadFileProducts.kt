package store

import java.io.File
import java.nio.file.Paths

fun readFileProducts(path: String): MutableList<MutableList<String>> {
    val projectAbsolutePath = Paths.get("").toAbsolutePath().toString()
    val fileAbsolutePath = "${projectAbsolutePath}/src/main/resources/${path}"

    val products = mutableListOf<MutableList<String>>()
    File(fileAbsolutePath).forEachLine {
        val (name, price, quantity, promotion) = it.split(",")
        products.add(mutableListOf(name, price, quantity, promotion))
    }
    products.removeAt(0)

    return products
}