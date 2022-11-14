package pl.weronikamotyl.airquality.ui.stationList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

//class StationListView {
//}
@Composable
fun StationListScreen() {
	val viewModel: StationListViewModel = hiltViewModel()
	LazyColumn() {
		items(viewModel.state.stations) {
			Text(text = it)
		}
	}
}