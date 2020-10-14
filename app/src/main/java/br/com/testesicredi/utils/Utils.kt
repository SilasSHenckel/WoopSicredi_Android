package br.com.testesicredi.utils

import java.text.DecimalFormat

object Utils {
    private val formatter = DecimalFormat("R$ #.00")

    fun formatCurrency(value: Double): String {
        return formatter.format(value).replace(".", ",")
    }
}