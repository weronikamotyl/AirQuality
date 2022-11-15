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
		val localStation = localStationsRepository.getAll()
		if (localStation.isEmpty()) {
			val remoteStations = remoteStationsRepository.getAll()
			localStationsRepository.save(remoteStations)
			return remoteStations
		}
		return localStation
//		return if (localStationsRepository.getAll()
//				.isEmpty()
//		) remoteStationsRepository.getAll() else localStationsRepository.getAll()
	}
}