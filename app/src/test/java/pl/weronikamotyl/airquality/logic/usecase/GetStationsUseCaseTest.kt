package pl.weronikamotyl.airquality.logic.usecase

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.LocalStationsRepository
import pl.weronikamotyl.airquality.logic.repository.RemoteStationsRepository


class GetStationsUseCaseTest {


	lateinit var sut: GetStationsUseCase
	lateinit var remote: MockRemoteStationsRepository
	lateinit var local: MockLocalStationsRepository

	@Before
	fun setUp() {
		remote = MockRemoteStationsRepository()
		local = MockLocalStationsRepository()
		sut = GetStationsUseCase(remoteStationsRepository = remote, localStationsRepository = local)
	}

	@Test
	fun init_DoesNotMakeAnyRemoteOrLocalCalls() {
		assertEquals(false, remote.getAllCalled)
	}

	@Test
	fun executeMakeOneCallToLocal() = runBlocking {
		sut.execute()
		assertEquals(1, local.getAllCalledCount)
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

//testowanie local powinno być w innym pliku
class MockLocalStationsRepository : LocalStationsRepository {
	val getAllCalled: Boolean
		get() {
			return getAllCalledCount > 0
		}
	var getAllCalledCount: Int = 0

	val getSaveCalled: Boolean
		get() {
			return getAllCalledCount > 0
		}
	var getSaveCalledCount: Int = 0

	override suspend fun getAll(): List<AQStation> {
		getAllCalledCount++
		return emptyList()
	}

	override suspend fun save(stations: List<AQStation>) {
		getSaveCalledCount++
	}
}