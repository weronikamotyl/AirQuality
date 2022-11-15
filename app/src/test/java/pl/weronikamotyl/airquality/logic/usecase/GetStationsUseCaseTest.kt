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

	@Test
	fun executeMakesCallToRemoteWhenLocalDataIsEmpty() = runBlocking {
		local.getAllResult = emptyList()//given

		sut.execute()//when

		assertEquals(true, remote.getAllCalled)//then
	}

	@Test
	fun executeMakesNoCallToRemoteWhenLocalDataIsNotEmpty() = runBlocking {
		local.getAllResult = listOf(sampleAQStation1)//given

		sut.execute()//when

		assertEquals(true, remote.getAllCalled)//then
	}

	@Test
	fun executeDoesMakesOneCallToLocal() = runBlocking {
		sut.execute()

		assertEquals(1, local.getAllCalledCount)//then
	}

	@Test
	fun executeDoesMakesOneCallToLocalForNonEmptyData() = runBlocking {
		local.getAllResult = listOf(sampleAQStation1)

		sut.execute()

		assertEquals(1, local.getAllCalledCount)//then
	}

	@Test
	fun executeReturnsRemoteStationsWhenRemoteStationsRepositoryIsCalled() = runBlocking {
		local.getAllResult = listOf()
		remote.getAllResult = listOf(sampleAQStation1)

		val actual = sut.execute()

		assertEquals("1", actual.first().id)//then
	}

	@Test
	fun executeReturnsLocalStationsWhenLocalStationsRepositoryIsCalled() = runBlocking {
		local.getAllResult = listOf(sampleAQStation1)

		val actual = sut.execute()

		assertEquals("1", actual.first().id)//then
	}

	@Test
	fun executeSavesStationsToLocalWhenRemoteIsNonEmpty() = runBlocking {
		local.getAllResult = emptyList()
		remote.getAllResult = listOf(sampleAQStation1)

		sut.execute()

		assertEquals(true, local.getSaveCalled)
		assertEquals("1", local.saveReceivedArguments.first().id)
	}

	@Test
	fun executeReturnsValidLocalListStations() = runBlocking {
		val sampleAQStation2 = AQStation("2","Spalska","Tomaszow","Wistom",null)
		local.getAllResult = listOf(sampleAQStation1, sampleAQStation2)

		val actual = sut.execute()

		assertEquals("1", actual.first().id )
		assertEquals("2", actual[1].id )
	}

	var sampleAQStation1 = AQStation("1","Spalska","Tomaszow","Wistom",null)

}

class MockRemoteStationsRepository : RemoteStationsRepository {
	val getAllCalled: Boolean
		get() {
			return getAllCalledCount > 0
		}
	var getAllCalledCount: Int = 0

	var getAllResult:List<AQStation> = emptyList()

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

	var getAllResult:List<AQStation> = emptyList()

	val getSaveCalled: Boolean
		get() {
			return getAllCalledCount > 0
		}
	var saveReceivedArguments: List<AQStation> = emptyList()
	var getSaveCalledCount: Int = 0

	override suspend fun getAll(): List<AQStation> {
		getAllCalledCount++
		return getAllResult
	}

	override suspend fun save(stations: List<AQStation>) {
		saveReceivedArguments = stations
		getSaveCalledCount++
	}
}