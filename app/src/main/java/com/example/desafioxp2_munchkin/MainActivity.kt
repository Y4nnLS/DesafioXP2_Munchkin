package com.example.desafioxp2_munchkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.desafioxp2_munchkin.ui.theme.DesafioXP2_MunchkinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista6Jogadores() // Chama a tela com a lista de 6 jogadores
        }
    }
}

@Composable
fun Lista6Jogadores() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Usa o repeat para criar 6 jogadores, numerando cada um deles
        repeat(6) { index ->
            item {
                // Exibe o número do jogador
                Text(text = "Jogador ${index + 1}", modifier = Modifier.padding(bottom = 8.dp))

                // Exibe os controles de cada jogador
                TelaJogadores()

                // Espaçamento entre cada jogador
                Spacer(modifier = Modifier.height(10.dp))

                // Adiciona uma linha separadora entre os jogadores
                Divider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)
            }
        }
    }
}

@Composable
fun TelaJogadores() {
    var nome by remember { mutableStateOf("") }
    var level by remember { mutableIntStateOf(1) }
    var poder by remember { mutableIntStateOf(0) }
    var bonus by remember { mutableIntStateOf(0) }
    var modificadores by remember { mutableIntStateOf(0) }

    // Atualiza o poder total automaticamente
    poder = level + bonus + modificadores

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Campo para inserir o nome do jogador
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Jogador") })

        Spacer(modifier = Modifier.height(15.dp))

        // Controle do level
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { level = (level - 1).coerceAtLeast(1) }) {
                Text(text = "-")
            }
            Text(text = "Level: $level")
            Button(onClick = { level = (level + 1).coerceAtMost(10) }) {
                Text(text = "+")
            }
        }

        // Exibe o poder total do jogador
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Poder: $poder")
        }

        // Controle do bônus de equipamento
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { bonus = (bonus - 1).coerceAtLeast(0) }) {
                Text(text = "-")
            }
            Text(text = "Bônus: $bonus")
            Button(onClick = { bonus = (bonus + 1).coerceAtMost(99) }) {
                Text(text = "+")
            }
        }

        // Controle dos modificadores
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { modificadores = (modificadores - 1).coerceAtLeast(-10) }) {
                Text(text = "-")
            }
            Text(text = "Modificadores: $modificadores")
            Button(onClick = { modificadores = (modificadores + 1).coerceAtMost(10) }) {
                Text(text = "+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListaJogadores() {
    Lista6Jogadores() // Pré-visualiza a lista com 6 jogadores
}
