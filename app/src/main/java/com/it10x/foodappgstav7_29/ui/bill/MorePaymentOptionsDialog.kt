package com.it10x.foodappgstav7_29.ui.bill

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MorePaymentOptionsDialog(
    onDismiss: () -> Unit,
    onCreditClick: () -> Unit,
    onPayLaterClick: () -> Unit,
    onComplimentaryClick: () -> Unit,
    showCreditInput: Boolean,
    onCreditConfirm: () -> Unit,
    onComplimentaryReasonClick: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onPhoneNumberConfirm: () -> Unit,
    creditAmount: String,
    onCreditAmountChange: (String) -> Unit,
    onCreditCancel: () -> Unit,
    showRemainingOptions: Boolean,
    remainingPaise: Long,
    onPayRemaining: (String, Long) -> Unit,
) {
    var activeKeypadInput by remember {
        mutableStateOf("PHONE")
    }

    var selectedOption by remember {
        mutableStateOf("CREDIT")
    }

    val options = listOf(
        "COMPLIMENTARY" to "Complimentary",
        "PAY_LATER" to "Pay Later",
        "CREDIT" to "Credit"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .fillMaxHeight(0.96f),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 12.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                // HEADER
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "More Payment Options",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202124)
                    )

                    Spacer(Modifier.weight(1f))

                    TextButton(onClick = onDismiss) {
                        Text(
                            text = "Close",
                            fontSize = 16.sp
                        )
                    }
                }

                Divider(color = Color(0xFFE0E0E0))

                // MAIN AREA: LEFT MENU + RIGHT CONTENT
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    // LEFT MENU
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.30f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        options.forEach { (key, label) ->

                            val selected = selectedOption == key

                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .clickable {
                                        selectedOption = key

                                        if (key == "CREDIT") {
                                            activeKeypadInput = "CREDIT"
                                            onCreditClick()
                                        } else {
                                            activeKeypadInput = "PHONE"
                                        }
                                    },
                                shape = RoundedCornerShape(10.dp),
                                color = if (selected) {
                                    Color(0xFF1976D2)
                                } else {
                                    Color(0xFFF1F3F5)
                                },
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (selected) {
                                        Color(0xFF1565C0)
                                    } else {
                                        Color(0xFFD6D9DD)
                                    }
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (selected) {
                                            Color.White
                                        } else {
                                            Color(0xFF30343B)
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                        color = Color(0xFFE0E0E0)
                    )

                    // RIGHT CONTENT
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.70f)
                            .padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        when (selectedOption) {

                            "COMPLIMENTARY" -> {
                                Text(
                                    text = "Complimentary",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF202124)
                                )

                                Text(
                                    text = "Select a reason for the complimentary order.",
                                    fontSize = 14.sp,
                                    color = Color(0xFF666666)
                                )

                                listOf(
                                    "Driver",
                                    "Friend",
                                    "Family"
                                ).forEach { reason ->
                                    OutlinedButton(
                                        onClick = {
                                            onComplimentaryReasonClick(reason)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(52.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp
                                        )
                                    ) {
                                        Text(
                                            text = reason,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            }

                            "PAY_LATER" -> {
                                Text(
                                    text = "Pay Later",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF202124)
                                )

                                Text(
                                    text = "Continue with the existing Pay Later option.",
                                    fontSize = 14.sp,
                                    color = Color(0xFF666666)
                                )

                                Button(
                                    onClick = onPayLaterClick,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(54.dp),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "Confirm Pay Later",
                                        fontSize = 16.sp
                                    )
                                }
                            }

                            "CREDIT" -> {
                                Text(
                                    text = "Credit",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF202124)
                                )

                                Text(
                                    text = "Enter the credit amount using the shared keypad.",
                                    fontSize = 14.sp,
                                    color = Color(0xFF666666)
                                )

                                OutlinedTextField(
                                    value = creditAmount,
                                    onValueChange = { value ->
                                        if (
                                            value.isEmpty() ||
                                            (
                                                    value.all {
                                                        it.isDigit() || it == '.'
                                                    } &&
                                                            value.count { it == '.' } <= 1
                                                    )
                                        ) {
                                            onCreditAmountChange(value)
                                        }

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged { focusState ->
                                            if (focusState.isFocused) {
                                                activeKeypadInput = "CREDIT"
                                            }
                                        },
                                    label = {
                                        Text("Credit Amount")
                                    },
                                    placeholder = {
                                        Text("Enter amount")
                                    },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Decimal
                                    ),
                                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color(0xFF212121),
                                        unfocusedTextColor = Color(0xFF212121),
                                        focusedLabelColor = Color(0xFF1976D2),
                                        unfocusedLabelColor = Color(0xFF555555),
                                        focusedBorderColor = Color(0xFF1976D2),
                                        unfocusedBorderColor = Color(0xFF888888),
                                        cursorColor = Color(0xFF1976D2)
                                    )
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = onCreditCancel,
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(50.dp)
                                    ) {
                                        Text("Cancel")
                                    }

                                    Button(
                                        onClick = onCreditConfirm,
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(50.dp)
                                    ) {
                                        Text("Confirm Credit")
                                    }

                                    // REMAINING PAYMENT OPTIONS
                                    if (showRemainingOptions && remainingPaise > 0L) {

                                        Spacer(Modifier.height(8.dp))

                                        val remainingRupees = remainingPaise / 100
                                        val remainingPaisa = remainingPaise % 100

                                        Surface(
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(10.dp),
                                            color = Color(0xFFFFF8E1),
                                            border = androidx.compose.foundation.BorderStroke(
                                                1.dp,
                                                Color(0xFFFFC107)
                                            )
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(12.dp),
                                                verticalArrangement = Arrangement.spacedBy(10.dp)
                                            ) {
                                                Text(
                                                    text = "Pay Remaining: ₹$remainingRupees.${
                                                        remainingPaisa.toString().padStart(2, '0')
                                                    }",
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF212121)
                                                )

                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                                ) {
                                                    Button(
                                                        onClick = {
                                                            onPayRemaining("CASH", remainingPaise)
                                                        },
                                                        modifier = Modifier
                                                            .weight(1f)
                                                            .height(44.dp),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = Color(0xFF4CAF50),
                                                            contentColor = Color.White
                                                        ),
                                                        contentPadding = PaddingValues(horizontal = 4.dp)
                                                    ) {
                                                        Text("💵 Cash", fontSize = 12.sp)
                                                    }

                                                    Button(
                                                        onClick = {
                                                            onPayRemaining("CARD", remainingPaise)
                                                        },
                                                        modifier = Modifier
                                                            .weight(1f)
                                                            .height(44.dp),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = Color(0xFF1976D2),
                                                            contentColor = Color.White
                                                        ),
                                                        contentPadding = PaddingValues(horizontal = 4.dp)
                                                    ) {
                                                        Text("💳 Card", fontSize = 12.sp)
                                                    }

                                                    Button(
                                                        onClick = {
                                                            onPayRemaining("UPI", remainingPaise)
                                                        },
                                                        modifier = Modifier
                                                            .weight(1f)
                                                            .height(44.dp),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = Color(0xFFFF9800),
                                                            contentColor = Color.Black
                                                        ),
                                                        contentPadding = PaddingValues(horizontal = 4.dp)
                                                    ) {
                                                        Text("📱 UPI", fontSize = 12.sp)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Divider(color = Color(0xFFE0E0E0))

                // COMMON PHONE NUMBER + KEYPAD AREA
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    // PHONE NUMBER: COMMON TO ALL OPTIONS
                    Column(
                        modifier = Modifier
                            .weight(1.1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Customer Details",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF202124)
                        )

                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { value ->
                                if (
                                    value.length <= 15 &&
                                    value.all { it.isDigit() }
                                ) {
                                    onPhoneNumberChange(value)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) {
                                        activeKeypadInput = "PHONE"
                                    }
                                },
                            label = {
                                Text("Customer Phone Number")
                            },
                            placeholder = {
                                Text("Enter phone number")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color(0xFF212121),
                                unfocusedTextColor = Color(0xFF212121),
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedLabelColor = Color(0xFF1976D2),
                                unfocusedLabelColor = Color(0xFF555555),
                                focusedBorderColor = Color(0xFF1976D2),
                                unfocusedBorderColor = Color(0xFF888888),
                                cursorColor = Color(0xFF1976D2)
                            )
                        )

                        Button(
                            onClick = onPhoneNumberConfirm,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Confirm Phone Number")
                        }
                    }

                    // ONE SHARED NUMERIC KEYPAD
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = if (activeKeypadInput == "PHONE") {
                                "Phone Keypad"
                            } else {
                                "Credit Keypad"
                            },
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF202124)
                        )

                        val keys = listOf(
                            listOf("1", "2", "3"),
                            listOf("4", "5", "6"),
                            listOf("7", "8", "9"),
                            listOf("⌫", "0", "C")
                        )

                        keys.forEach { rowKeys ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                rowKeys.forEach { key ->
                                    OutlinedButton(
                                        onClick = {
                                            when (activeKeypadInput) {
                                                "PHONE" -> {
                                                    when (key) {
                                                        "C" -> {
                                                            onPhoneNumberChange("")
                                                        }

                                                        "⌫" -> {
                                                            onPhoneNumberChange(
                                                                phoneNumber.dropLast(1)
                                                            )
                                                        }

                                                        else -> {
                                                            if (phoneNumber.length < 15) {
                                                                onPhoneNumberChange(
                                                                    phoneNumber + key
                                                                )
                                                            }
                                                        }
                                                    }
                                                }

                                                "CREDIT" -> {
                                                    when (key) {
                                                        "C" -> {
                                                            onCreditAmountChange("")
                                                        }

                                                        "⌫" -> {
                                                            if (creditAmount.isNotEmpty()) {
                                                                onCreditAmountChange(
                                                                    creditAmount.dropLast(1)
                                                                )
                                                            }
                                                        }

                                                        else -> {
                                                            if (
                                                                key.all { it.isDigit() } ||
                                                                (
                                                                        key == "." &&
                                                                                !creditAmount.contains(".")
                                                                        )
                                                            ) {
                                                                onCreditAmountChange(
                                                                    creditAmount + key
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(42.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            text = key,
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}