package com.ph32395.bai1.service.boundService


import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class CalculationService : Service() {

    private val binder = CalculationBinder()

    inner class CalculationBinder : Binder() {
        fun getService(): CalculationService = this@CalculationService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun calculationSum(a: Int, b: Int): Int {
        return a + b
    }

    fun calculationProduct(a: Int, b: Int): Int {
        return a * b
    }
}