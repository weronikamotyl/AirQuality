package pl.weronikamotyl.airquality.logic.repository

import pl.weronikamotyl.airquality.entity.AQStation

interface LocalStationsRepository {
	suspend fun getAll(): List<AQStation>
	suspend fun save(stations: List<AQStation>)
}