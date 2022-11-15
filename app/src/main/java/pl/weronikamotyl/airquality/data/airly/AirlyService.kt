package pl.weronikamotyl.airquality.data.airly

import retrofit2.http.GET


interface AirlyService {
	@GET(AirlyConstants.INSTALLATIONS)
	suspend fun getInstallations(): List<AirlyDTO.Installation>
}