package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.model.Etkinlik
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_etkinlik_ekle.*
import java.util.*

class EtkinlikEkleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_ekle)
    }

    fun etkinlikEkle(view: View) {

        if(etkinlik_ekle_editText_etkinlik_adi.text.toString()==""){
            Toast.makeText(this,"Etkinlik adını giriniz.",Toast.LENGTH_LONG).show()
            return
        }

        if(etkinlik_ekle_editText_etkinlik_tutar.text.toString()==""){
            Toast.makeText(this,"Etkinlik tutarını giriniz.",Toast.LENGTH_LONG).show()
            return
        }

        val currentTime: Date = Calendar.getInstance().getTime()

        val etkinlik = Etkinlik(
            etkinlik_ekle_editText_etkinlik_adi.text.toString(),
            etkinlik_ekle_editText_etkinlik_tutar.text.toString().toDouble(),
            currentTime
        )
        GeziBankDatabase(applicationContext).dao().insert(etkinlik)
        finish()
    }
}