package com.it10x.foodappgstav7_29.core

import com.it10x.foodappgstav7_29.data.online.repository.PosRenewalSyncRepository

class PosRenewalManager(
    private val repository: PosRenewalSyncRepository
) {

    suspend fun checkAtStartup(): Boolean {
        return repository.checkRenewalAtStartup()
    }
}