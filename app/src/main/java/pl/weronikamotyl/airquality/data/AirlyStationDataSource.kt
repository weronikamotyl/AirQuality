package pl.weronikamotyl.airquality.data

import pl.weronikamotyl.airquality.data.airly.AirlyMapper
import pl.weronikamotyl.airquality.data.airly.AirlyService
import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.RemoteStationsRepository
import javax.inject.Inject

class AirlyStationDataSource @Inject constructor(
	private val airlyService: AirlyService
) : RemoteStationsRepository {

	override suspend fun getAll(): List<AQStation> {
		val installations = airlyService.getInstallations()
		return installations.map { AirlyMapper().mapInstallation(it) }
	}

}