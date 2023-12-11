package ademkocamaz.gezibank.dataaccess

import ademkocamaz.gezibank.model.Etkinlik
import ademkocamaz.gezibank.util.Converters
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Etkinlik::class), version = 1)
@TypeConverters(Converters::class)
abstract class GeziBankDatabase : RoomDatabase() {

    abstract fun dao(): IEtkinlikDao

    companion object {
        @Volatile
        private var instance: GeziBankDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseOlustur(context).also { geziBankDatabase ->
                instance = geziBankDatabase
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GeziBankDatabase::class.java,
            "database"
        )
            .allowMainThreadQueries()
            .build()
    }

}