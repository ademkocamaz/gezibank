package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.databinding.ActivityEtkinlikDetayBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EtkinlikDetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEtkinlikDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtkinlikDetayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val etkinlik =
            GeziBankDatabase(applicationContext).dao().getById(intent.getIntExtra("id", 0))
        binding.etkinlikDetayTextViewEtkinlikAdi.text = etkinlik.adi
        binding.etkinlikDetayTextViewEtkinlikTutar.text = etkinlik.tutar.toString() + "₺"
    }


    fun sil(view: View) {
        GeziBankDatabase(applicationContext).dao().deleteById(intent.getIntExtra("id", 0))
        finish()
    }
}