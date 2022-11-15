package pl.weronikamotyl.airquality.logic.repository

import pl.weronikamotyl.airquality.entity.AQStation

interface RemoteStationsRepository {

	suspend fun getAll(): List<AQStation>
}