package pl.weronikamotyl.airquality.logic.usecase

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.assertEquals
import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.RemoteStationsRepository


class GetStationsUseCaseTest {

	@Test
	fun init_DoesNotMakeAnyRemoteOrLocalCalls() {
		val remote = MockRemoteStationsRepository()
		val sut = GetStationsUseCase(remoteStationsRepository = remote)
		assertEquals(false, remote.getAllCalled)
	}

	@Test
	fun executeMakeOneCallToRemote() = runBlocking {
		val remote = MockRemoteStationsRepository()
		val sut = GetStationsUseCase(remoteStationsRepository = remote)
		sut.execute()
		assertEquals(1, remote.getAllCalledCount)
	}

}

class MockRemoteStationsRepository : RemoteStationsRepository {
	val getAllCalled: Boolean
		get() {
			return getAllCalledCount > 0
		}
	var getAllCalledCount: Int = 0
	override suspend fun getAll(): List<AQStation> {
		getAllCalledCount++
		return emptyList()
	}
}