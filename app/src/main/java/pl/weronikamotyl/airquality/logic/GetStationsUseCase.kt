package pl.weronikamotyl.airquality.logic

import pl.weronikamotyl.airquality.entity.AQStation
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
	private val remoteStationsRepository: RemoteStationsRepository) {

	suspend fun execute(): List<AQStation> {
		return remoteStationsRepository.getAll()
	}
}