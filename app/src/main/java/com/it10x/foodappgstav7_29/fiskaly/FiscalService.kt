package com.it10x.foodappgstav7_29.fiscal

import com.it10x.foodappgstav7_29.data.pos.entities.PosKotItemEntity
import com.it10x.foodappgstav7_29.ui.payment.PaymentInput

data class FiscalContext(
    val txId: String?,
    val clientId: String?
)

interface FiscalService {

    suspend fun start(): FiscalContext

    suspend fun finish(
        context: FiscalContext,
        payments: List<PaymentInput>,
        items: List<PosKotItemEntity>
    )

    suspend fun cancel(context: FiscalContext)
}