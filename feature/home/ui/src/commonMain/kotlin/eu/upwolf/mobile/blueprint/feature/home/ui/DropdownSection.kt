/*
 * ISC License
 *
 * Copyright (c) 2023. Wolf-Martell Montwé (bitfunk)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package eu.upwolf.mobile.blueprint.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropdownSection(
    title: String,
    selectedValue: String,
    onSelectedValueChange: (String) -> Unit,
    isMenuExpanded: Boolean,
    onMenuExpandedChange: (Boolean) -> Unit,
    isDisabled: Boolean = false,
    isError: Boolean = false,
    noPlaceHolder: Boolean? = false,
    items: List<String>
) {
    Column {
        Text(
            text = title,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.padding(0.dp, 10.dp)
        )

        // Box to display the selected item or error message
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isError) Color.Red else Color.Transparent, // Set border color based on 'isError'
                    shape = RectangleShape
                )
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = selectedValue)
                IconButton(
                    onClick = { onMenuExpandedChange(!isMenuExpanded) },
                    enabled = !isDisabled
                ) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        }

        // Helper text or error message
        Text(
            text = if (isError) {
                "Error Message"
            } else if (noPlaceHolder == true) {
                " "
            } else "Helper Text",
            color = if (isError) Color.Red else Color.Gray,
            fontSize = if (isError) 14.sp else 14.sp,
            modifier = Modifier.padding(0.dp, 4.dp)
        )

        // Dropdown menu positioned below the selected item box
        if (isMenuExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { onMenuExpandedChange(false) }
                ) {

                    items.subList(1, items.size).forEach { item ->
                        DropdownMenuItem(onClick = {
                            onSelectedValueChange(item)
                            onMenuExpandedChange(false)
                        }) {
                            Text(text = item)
                        }
                    }
                }
            }
        }
    }
}
