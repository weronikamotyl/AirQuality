package pl.weronikamotyl.airquality.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import pl.weronikamotyl.airquality.data.AirlyStationDataSource
import pl.weronikamotyl.airquality.data.airly.AirlyConstants
import pl.weronikamotyl.airquality.data.airly.AirlyService
import pl.weronikamotyl.airquality.logic.repository.RemoteStationsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AirQualityProvider {

	@Provides
	@Singleton
	fun provideRemoteStationsRepository(airlyService: AirlyService): RemoteStationsRepository {
		return AirlyStationDataSource(airlyService)

	}


	@Provides
	@Singleton
	fun provideAirlyAuthInterceptor(): AirlyAuthInterceptor {
		return AirlyAuthInterceptor()
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(airlyAuthInterceptor: AirlyAuthInterceptor): OkHttpClient {
		return OkHttpClient
			.Builder()
			.addInterceptor(airlyAuthInterceptor)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit
			.Builder()
			.baseUrl(AirlyConstants.HOST)
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
	}

	@Provides
	@Singleton
	fun provideAirlyService(retrofit: Retrofit): AirlyService {
		return retrofit.create(AirlyService::class.java)
//		return Retrofit
//			.Builder()
//			.baseUrl(AirlyStationDataSource.HOST)
//			.addConverterFactory(GsonConverterFactory.create())
//			.build()
//			.create(AirlyStationDataSource.AirlyService::class.java)
	}

}

class AirlyAuthInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val buildRequest = chain.request().newBuilder()
		buildRequest.addHeader("apiKey", "TyTorshbUiy4YTFsv8A12Y5TdrixUt1w")
		return chain.proceed(buildRequest.build())
	}

}