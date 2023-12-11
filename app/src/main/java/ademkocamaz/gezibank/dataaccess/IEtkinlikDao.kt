package ademkocamaz.gezibank.dataaccess

import ademkocamaz.gezibank.model.Etkinlik
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IEtkinlikDao {

    @Query("SELECT * FROM etkinlik WHERE id=:id")
    fun getById(id:Int):Etkinlik

    @Query("SELECT * FROM etkinlik")
    fun getAll():List<Etkinlik>

    @Insert
    fun insert(etkinlik:Etkinlik)

    @Delete
    fun delete(etkinlik: Etkinlik)

    @Query("DELETE FROM etkinlik")
    fun deleteAll()

    @Query("SELECT SUM(tutar) FROM etkinlik")
    fun total():Double

    @Query("DELETE FROM etkinlik WHERE id=:id")
    fun deleteById(id:Int)
}