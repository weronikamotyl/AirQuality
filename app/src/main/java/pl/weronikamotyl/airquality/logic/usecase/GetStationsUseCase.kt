package pl.weronikamotyl.airquality.logic.usecase

import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.LocalStationsRepository
import pl.weronikamotyl.airquality.logic.repository.RemoteStationsRepository
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
	private val remoteStationsRepository: RemoteStationsRepository,
	private val localStationsRepository: LocalStationsRepository
) {

	suspend fun execute(): List<AQStation> {
		return localStationsRepository.getAll()
	}
}