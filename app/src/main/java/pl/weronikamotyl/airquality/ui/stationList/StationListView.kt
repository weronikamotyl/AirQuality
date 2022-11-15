package pl.weronikamotyl.airquality.ui.stationList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest

//class StationListView {
//}
@Composable
fun StationListScreen() {
	val viewModel: StationListViewModel = hiltViewModel()
	val state = viewModel.state

	SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
		onRefresh = { viewModel.onPullToRefresh() }) {
		LazyColumn() {
			items(state.stations) {
				Card(
					modifier = Modifier
						.padding(10.dp)
						.fillMaxWidth()
						.wrapContentHeight(),
					shape = MaterialTheme.shapes.medium,
					colors = CardDefaults.cardColors(containerColor = Color.Yellow.copy(alpha = 0.23f))
				) {
					Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
						AsyncImage(
							model = ImageRequest.Builder(LocalContext.current)
								.data(it.imageUrl).build(), contentDescription = null,
							modifier = Modifier.size(80.dp)
						)
						Column(
							modifier = Modifier
								.padding(5.dp)
						) {
							Text(text = it.title, style = MaterialTheme.typography.headlineLarge)
							Text(text = it.subtitle, style = MaterialTheme.typography.titleMedium)
							Text(text = it.label, style = MaterialTheme.typography.bodySmall)
						}
					}
				}
			}
		}
	}
}

