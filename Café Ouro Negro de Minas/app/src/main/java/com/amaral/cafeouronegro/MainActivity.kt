package com.amaral.cafeouronegro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.*
import androidx.navigation.compose.*
import com.amaral.cafeouronegro.ui.theme.CafeOuroNegroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeOuroNegroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("clientes") { ClientesScreen(navController,ClientesDAO()) }
        composable("produtos") { ProdutosScreen(navController,ProdutosDAO()) }
        composable("pedidos") { PedidosScreen(navController,PedidosDAO(),ClientesDAO(),ProdutosDAO()) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar( // Use CenterAlignedTopAppBar
                title = {
                    Text(
                        text = "Café Ouro Negro de Minas",
                        color = MaterialTheme.colorScheme.onPrimary // Cor do texto do título
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Blue // Cor da barra superior
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Button(
                    onClick = { navController.navigate("clientes") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green
                    )
                ) {
                    Text(text = "Clientes", color = Color.Black)
                }
                Button(
                    onClick = { navController.navigate("produtos") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )
                ) {
                    Text(text = "Produtos", color = Color.Black)
                }
                Button(
                    onClick = { navController.navigate("pedidos") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow
                    )
                ) {
                    Text(text = "Pedidos", color = Color.Black)
                }
            }
        }
    )
}

