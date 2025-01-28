package com.example.a8_176.ui.view.kamar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a8_176.model.Bangunan
import com.example.a8_176.ui.custumwidget.CustumeTopAppBar
import com.example.a8_176.ui.custumwidget.DropDownAsrama
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiEvent
import com.example.a8_176.ui.viewmodel.kamar.InsertKmrUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertKmrUiEvent: InsertKmrUiEvent = InsertKmrUiEvent(),
    modifier: Modifier = Modifier,
    onValueChange: (InsertKmrUiEvent) -> Unit = {},
    enabled: Boolean = true,
    idbangunanList: List<Bangunan>,
) {
    val status_kamar = listOf("Terisi", "Kosong")

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val inputFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF42A5F5),
            unfocusedBorderColor = Color(0xFF90CAF9),
            cursorColor = Color(0xFF42A5F5),
            focusedLabelColor = Color(0xFF42A5F5),
            unfocusedLabelColor = Color(0xFF1976D2)
        )

        OutlinedTextField(
            value = insertKmrUiEvent.id_kamar,
            onValueChange = {onValueChange(insertKmrUiEvent.copy(id_kamar = it))},
            label = { Text("ID Kamar") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )
        OutlinedTextField(
            value = insertKmrUiEvent.nomor_kamar,
            onValueChange = {onValueChange(insertKmrUiEvent.copy(nomor_kamar = it))},
            label = { Text("Nomor Kamar") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )

        DropDownAsrama(
            label = "ID Bangunan",
            options = idbangunanList.map { it.id_bangunan },
            selectedOption = idbangunanList.find { it.id_bangunan == insertKmrUiEvent.id_bangunan }?.id_bangunan.orEmpty(),
            onOptionSelected = { selected ->
                val selectedBangunan = idbangunanList.find { it.id_bangunan == selected }
                onValueChange(insertKmrUiEvent.copy(id_bangunan = selectedBangunan?.id_bangunan ?: ""))
            },
            colors = inputFieldColors
        )

        OutlinedTextField(
            value = insertKmrUiEvent.kapasitas,
            onValueChange = {onValueChange(insertKmrUiEvent.copy(kapasitas = it))},
            label = { Text("Kapasitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = inputFieldColors
        )

        Text(text = "Status Kamar", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            status_kamar.forEach { st ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    RadioButton(
                        selected = insertKmrUiEvent.status_kamar == st,
                        onClick = {
                            onValueChange(insertKmrUiEvent.copy(status_kamar = st))
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF42A5F5))
                    )
                    Text(
                        text = st,
                        color = Color(0xFF1976D2),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
        if(enabled) {
            Text("Isi Semua Data!",
                modifier = Modifier.padding(12.dp),
                color = Color.Red
            )
        }
        Divider(
            thickness = 8.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(12.dp)
        )
    }
}