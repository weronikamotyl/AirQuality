package pl.weronikamotyl.airquality.logic

import pl.weronikamotyl.airquality.entity.AQStation

interface RemoteStationsRepository {

	suspend fun getAll(): List<AQStation>
}