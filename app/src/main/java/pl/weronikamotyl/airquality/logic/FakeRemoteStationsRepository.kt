package pl.weronikamotyl.airquality.logic

import pl.weronikamotyl.airquality.entity.AQStation

class FakeRemoteStationsRepository : RemoteStationsRepository {

	override suspend fun getAll(): List<AQStation> {
		return emptyList()
	}
}