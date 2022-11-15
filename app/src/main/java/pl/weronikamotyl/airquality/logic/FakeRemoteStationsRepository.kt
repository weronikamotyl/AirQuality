package pl.weronikamotyl.airquality.logic

import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.RemoteStationsRepository

class FakeRemoteStationsRepository : RemoteStationsRepository {

	override suspend fun getAll(): List<AQStation> {
		return emptyList()
	}
}