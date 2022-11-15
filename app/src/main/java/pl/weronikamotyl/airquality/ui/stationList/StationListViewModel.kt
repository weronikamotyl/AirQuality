package pl.weronikamotyl.airquality.ui.stationList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.weronikamotyl.airquality.logic.usecase.GetStationsUseCase
import javax.inject.Inject

@HiltViewModel
class StationListViewModel @Inject constructor(
	private val getStationsUseCase: GetStationsUseCase
) : ViewModel() {

	var state by mutableStateOf(
		State(stations = listOf())
	)

	init {
		viewModelScope.launch {
			loadStations()
		}
	}

	suspend private fun loadStations() {
		val stations = getStationsUseCase.execute()
		state = State(stations.map { aqStation -> aqStation.name})

//		state = State(stations = listOf("weronika", "jarek", "darek"))
	}

	data class State(
		val stations: List<String> = listOf()
	)
}