package ademkocamaz.gezibank.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity
class Etkinlik(
    @ColumnInfo(name = "adi")
    var adi: String,

    @ColumnInfo(name = "tutar")
    var tutar: Double,

    @ColumnInfo(name = "tarih")
    var tarih: Date
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int=0
}