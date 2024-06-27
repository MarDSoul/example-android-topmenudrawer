package example.mardsoul.topmenudrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.mardsoul.topmenudrawer.ui.theme.TopMenuDrawerTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			TopMenuDrawerTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					TopMenuDrawerApp(
						modifier = Modifier
							.fillMaxSize()
							.padding(innerPadding)
					)
				}
			}
		}
	}
}

@Composable
fun TopMenuDrawerApp(modifier: Modifier = Modifier) {
	Box(modifier = modifier) {
		var menuExpanded by remember { mutableStateOf(false) }

		AnimatedVisibility(
			visible = menuExpanded,
			enter = slideInVertically(),
			exit = slideOutVertically()
		) {
			TopMenu(modifier = Modifier) { menuExpanded = false }
		}

		Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
			Button(onClick = { menuExpanded = !menuExpanded }) {
				Text(text = "Toggle Menu")
			}
		}

	}
}

@Composable
fun TopMenu(modifier: Modifier = Modifier, iconAction: () -> Unit) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.primaryContainer)
			.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(text = "Top menu", color = MaterialTheme.colorScheme.onPrimaryContainer)
		IconButton(onClick = { iconAction() }) {
			Icon(
				imageVector = Icons.Default.Menu,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.onPrimaryContainer
			)
		}
	}
}
