package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_etkinlik_detay.*

class EtkinlikDetayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_detay)
        val etkinlik =
            GeziBankDatabase(applicationContext).dao().getById(intent.getIntExtra("id", 0))
        etkinlik_detay_textView_etkinlik_adi.text=etkinlik.adi
        etkinlik_detay_textView_etkinlik_tutar.text=etkinlik.tutar.toString()+"₺"
    }


    fun sil(view: View) {
        GeziBankDatabase(applicationContext).dao().deleteById(intent.getIntExtra("id", 0))
        finish()
    }
}