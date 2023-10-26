/*
 * ISC License
 *
 * Copyright (c) 2022. Wolf-Martell Montwé (bitfunk)
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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.upwolf.mobile.blueprint.common.ui.system.PrimaryButton

@Composable
fun HomePage() {

    val sampleData = SampleData()
    val items = remember { sampleData.getSampleItems() }
    var selectedValueEnabled by remember { mutableStateOf(items[0]) }
    var selectedValueDisabled by remember { mutableStateOf(items[0]) }
    var selectedValueError by remember { mutableStateOf(items[0]) }
    var selectedValueWithPlaceholder by remember { mutableStateOf(items[0]) }
    var selectedValueWithoutPlaceholder by remember { mutableStateOf(items[0]) }

    var isMenuExpandedEnabled by remember { mutableStateOf(false) }
    var isMenuExpandedDisabled by remember { mutableStateOf(false) }
    var isMenuExpandedError by remember { mutableStateOf(false) }
    var isMenuExpandedWithPlaceholder by remember { mutableStateOf(false) }
    var isMenuExpandedWithoutPlaceholder by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dropdown", color = Color.White) },
                Modifier.padding(0.dp, 40.dp, 0.dp, 0.dp),
                backgroundColor = Color.Black,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            ) {
                // Enabled dropdown section
                DropdownSection(
                    title = "Enabled",
                    selectedValue = selectedValueEnabled,
                    onSelectedValueChange = { selectedValueEnabled = it },
                    isMenuExpanded = isMenuExpandedEnabled,
                    onMenuExpandedChange = { isMenuExpandedEnabled = it },
                    false,
                    false,
                    false,
                    items
                )

                // Disabled dropdown section
                DropdownSection(
                    title = "Disabled",
                    selectedValue = selectedValueDisabled,
                    onSelectedValueChange = { selectedValueDisabled = it },
                    isMenuExpanded = isMenuExpandedDisabled,
                    onMenuExpandedChange = { isMenuExpandedDisabled = it },
                    true,
                    false,
                    false,
                    items
                )

                // Error dropdown section
                DropdownSection(
                    title = "Error",
                    selectedValue = selectedValueError,
                    onSelectedValueChange = { selectedValueError = it },
                    isMenuExpanded = isMenuExpandedError,
                    onMenuExpandedChange = { isMenuExpandedError = it },
                    false,
                    true,
                    false,
                    items
                )

                // With Placeholder dropdown section
                DropdownSection(
                    title = "With Placeholder",
                    selectedValue = selectedValueWithPlaceholder,
                    onSelectedValueChange = { selectedValueWithPlaceholder = it },
                    isMenuExpanded = isMenuExpandedWithPlaceholder,
                    onMenuExpandedChange = { isMenuExpandedWithPlaceholder = it },
                    false,
                    false,
                    false,
                    items
                )

                // Without Placeholder dropdown section
                DropdownSection(
                    title = "Without Placeholder",
                    selectedValue = selectedValueWithoutPlaceholder,
                    onSelectedValueChange = { selectedValueWithoutPlaceholder = it },
                    isMenuExpanded = isMenuExpandedWithoutPlaceholder,
                    onMenuExpandedChange = { isMenuExpandedWithoutPlaceholder = it },
                    false,
                    false,
                    true,
                    items
                )
            }
        }
    )
}
