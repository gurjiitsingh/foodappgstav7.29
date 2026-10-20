package com.it10x.foodappgstav7_29.ui.settings

import com.it10x.foodappgstav7_29.data.PrinterConfig
import com.it10x.foodappgstav7_29.data.PrinterRole

data class PrinterSettingsState(
    val printers: Map<PrinterRole, PrinterConfig> = emptyMap()
)
