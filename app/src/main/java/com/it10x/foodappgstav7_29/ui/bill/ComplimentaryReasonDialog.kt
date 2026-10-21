package com.it10x.foodappgstav7_29.ui.bill

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComplimentaryReasonDialog(
    onDismiss: () -> Unit,
    onDriverClick: () -> Unit,
    onFamilyClick: () -> Unit,
    onFriendClick: () -> Unit
) {
    val reasons = listOf(
        Triple("🚚  Driver", Color(0xFF1976D2), onDriverClick),
        Triple("👨‍👩‍👧  Family", Color(0xFF9C27B0), onFamilyClick),
        Triple("🤝  Friend", Color(0xFF4CAF50), onFriendClick)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "🎁 Complimentary Order",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Select reason for free order")

                reasons.forEach { (title, color, onClick) ->
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = color
                        )
                    ) {
                        Text(
                            text = title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        },
        confirmButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}