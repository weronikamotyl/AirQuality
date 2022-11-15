package pl.weronikamotyl.airquality.logic.usecase

import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.RemoteStationsRepository
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
	private val remoteStationsRepository: RemoteStationsRepository
) {

	suspend fun execute(): List<AQStation> {
		remoteStationsRepository.getAll()
		remoteStationsRepository.getAll()
		remoteStationsRepository.getAll()
		return remoteStationsRepository.getAll()
	}
}