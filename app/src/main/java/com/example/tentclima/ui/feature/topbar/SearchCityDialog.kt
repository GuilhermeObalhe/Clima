package com.example.tentclima.ui.feature.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tentclima.ui.feature.WeatherViewModel

@Composable
fun SearchCityButton(
    onCityAdded: (String) -> Unit,
    viewModel: WeatherViewModel
) {
    var isSearchOpen by remember { mutableStateOf(false) }
    var cityText by remember { mutableStateOf("") }

    IconButton(onClick = { isSearchOpen = true }) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
    }

    if (isSearchOpen) {
        SearchCityDialog(
            onDismiss = { isSearchOpen = false },
            onCityAdded = { city ->
                cityText = ""
                onCityAdded(city)
                isSearchOpen = false
            },
            viewModel = viewModel,
            onTextChange = { cityText = it },
            currentText = cityText
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchCityDialog(
    onDismiss: () -> Unit,
    onCityAdded: (String) -> Unit,
    viewModel: WeatherViewModel,
    onTextChange: (String) -> Unit,
    currentText: String
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = currentText, // <-- Usa o parâmetro recebido
                onValueChange = {newText ->
                    onTextChange(newText) // Atualiza o texto
                    viewModel.searchCities(newText) // Dispara a busca
                },
                label = { Text("Digite o nome da cidade") },
                modifier = Modifier.fillMaxWidth()
            )

            // Lista de sugestões
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(viewModel.searchResults) { city ->
                    val localNamePt = city.localNames?.get("pt") ?: city.name
                    Text(
                        text = "$localNamePt, ${city.state}, ${city.country}",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                onCityAdded(city.name) // Adiciona a cidade
                                onDismiss() // Fecha o diálogo
                            }
                    )
                }
            }
        }
    }
}

