package com.example.compose_study.part2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactRow(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(false) }
    Row(modifier) {
        ContactDetail(contact = contact) /** Immutable 변수들로 인하여 Smart Recomposition의 대상 **/
        IconToggleButton(
            checked = selected,
            onCheckedChange = { selected = !selected }
        ) {}
    }
}

@Composable
fun ContactDetail(
    contact: Contact
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = contact.name)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Text(text = contact.number)
    }
}

/** Immutable 변수들 **/
/**
 * immutable 인 val이 아니라 mutable한 var일 경우 <- Compose에서 알 수가 없음 <- Unstable한 형태
 **/
data class Contact(
    val name: String,
    val number: String
)