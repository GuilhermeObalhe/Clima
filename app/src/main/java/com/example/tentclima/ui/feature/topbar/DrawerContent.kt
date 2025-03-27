package com.example.tentclima.ui.feature.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tentclima.data.citydatabase.CityEntity

@Composable
fun DrawerContent(
    cities: List<CityEntity>,
    onClose: () -> Unit,
    onCitySelected: (CityEntity) -> Unit,
    onCityDeleted: (CityEntity) -> Unit
) {
    val selectedCity = remember { mutableStateOf<CityEntity?>(null) }
    ModalDrawerSheet(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Cidades visitadas",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        LazyColumn {
            items(cities) { city ->
                val showDeleteButton = remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    showDeleteButton.value = !showDeleteButton.value
                                    selectedCity.value = city
                                },
                                onTap = {
                                    showDeleteButton.value = false
                                    selectedCity.value = city
                                    onCitySelected(city)
                                    onClose()
                                },

                            )
                        }
                        .background(
                            if (selectedCity.value == city) Color.LightGray.copy(alpha = 0.3f)
                            else Color.Transparent
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nome da cidade
                    Text(
                        text = city.name,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    // Botão de exclusão
                    if (showDeleteButton.value){
                        IconButton(
                            onClick = {
                                onCityDeleted(city)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Excluir cidade",
                                tint = Color.Red
                            )
                        }
                    }
                }
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }
}