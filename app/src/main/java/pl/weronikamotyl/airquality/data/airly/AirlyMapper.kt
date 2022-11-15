package pl.weronikamotyl.airquality.data.airly

import pl.weronikamotyl.airquality.entity.AQStation

class AirlyMapper {
	fun mapInstallation(installation: AirlyDTO.Installation): AQStation {
		return AQStation(
				id = installation.id.toString(),
				name = installation.address?.displayAddress2 ?: "Unknown",
				city = installation.address?.city ?: "Unkonwn",
				sponsor = installation.sponsor?.displayName ?: "Unknown",
				sponsorImage = installation.sponsor?.logo
			)


	}
}