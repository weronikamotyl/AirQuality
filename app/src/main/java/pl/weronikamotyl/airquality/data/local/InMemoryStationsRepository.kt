package pl.weronikamotyl.airquality.data.local

import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.LocalStationsRepository

class InMemoryStationsRepository: LocalStationsRepository {

	override suspend fun getAll(): List<AQStation> {
		TODO("Not yet implemented")
	}

	override suspend fun save(stations: List<AQStation>) {
		TODO("Not yet implemented")
	}
}