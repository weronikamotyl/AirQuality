package pl.weronikamotyl.airquality.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.weronikamotyl.airquality.logic.FakeRemoteStationsRepository
import pl.weronikamotyl.airquality.logic.RemoteStationsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AirQualityProvider {

	@Provides
	@Singleton
	fun provideRemoteStationRepository(): RemoteStationsRepository {
		return FakeRemoteStationsRepository()
	}
}