package pl.weronikamotyl.airquality.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import pl.weronikamotyl.airquality.entity.AQStation
import pl.weronikamotyl.airquality.logic.repository.LocalStationsRepository
import javax.inject.Inject

class DatabaseStationRepository @Inject constructor(private val database: AppDatabase) :
	LocalStationsRepository {


	override suspend fun getAll(): List<AQStation> {
		val stationEntities = database.stationsDao().getAll()
		return stationEntities.map {
			AQStation(
				it.id,
				it.name,
				it.city,
				it.sponsor,
				it.sponsorImage
			)
		}
	}

	override suspend fun save(stations: List<AQStation>) {
		database.stationsDao().insert(stations.map {
			StationEntity(
				it.id,
				it.name,
				it.city,
				it.sponsor,
				it.sponsorImage
			)
		})
	}
}

@Entity
data class StationEntity(
	@PrimaryKey() val id: String,
	@ColumnInfo(name = "name") val name: String,
	@ColumnInfo(name = "city") val city: String,
	@ColumnInfo(name = "sponsor") val sponsor: String,
	@ColumnInfo(name = "sponsor_image") val sponsorImage: String?
)

@Dao
interface StationsDao {

	@Query("SELECT * FROM stationEntity")
	suspend fun getAll(): List<StationEntity>

	@Insert
	suspend fun insert(stations: List<StationEntity>)

}

@Database(entities = [StationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun stationsDao(): StationsDao
}