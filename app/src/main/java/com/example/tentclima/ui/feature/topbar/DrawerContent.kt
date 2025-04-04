package com.example.tentclima.ui.feature.topbar

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tentclima.data.citydatabase.CityEntity
import com.example.tentclima.ui.theme.AzulAcinzentadoClaro

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
            .fillMaxWidth(0.6f),
        drawerContainerColor = AzulAcinzentadoClaro
    ) {
        Text(
            text = "Cidades visitadas",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        HorizontalDivider(thickness = 1.dp, color = Color.DarkGray.copy(alpha = 0.5f))

        LazyColumn()
        {
            items(cities) { city ->
                val showDeleteButton = remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (selectedCity.value == city) Color.DarkGray.copy(alpha = 0.3f)
                            else Color.Transparent,
                            shape = RectangleShape
                        )
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

                        .padding(horizontal = 26.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = city.name + ", " + city.country,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    if (showDeleteButton.value){
                        IconButton(
                            onClick = {
                                onCityDeleted(city)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Excluir cidade",
                                tint = Color.Black
                            )
                        }
                    }
                }
                HorizontalDivider(thickness = 1.dp, color = Color.DarkGray.copy(alpha = 0.5f))
            }
        }
    }
}