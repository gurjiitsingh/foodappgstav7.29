package com.it10x.foodappgstav7_29.utils

import com.it10x.foodappgstav7_29.data.online.models.OrderMasterData

fun OrderMasterData.createdAtMillis(): Long {
    return createdAt?.toDate()?.time ?: createdAtMillis
}