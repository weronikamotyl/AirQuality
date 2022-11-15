package pl.weronikamotyl.airquality.data.local

import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.LocalStationsRepository

class InMemoryStationsRepository: LocalStationsRepository {

	companion object {
		private var stations: List<AQStation> = emptyList()
	}

	override suspend fun getAll(): List<AQStation> {
		return stations
	}

	override suspend fun save(stations: List<AQStation>) {
		InMemoryStationsRepository.stations = stations
	}
}