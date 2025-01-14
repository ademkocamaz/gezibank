package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.databinding.ActivityEtkinlikEkleBinding
import ademkocamaz.gezibank.model.Etkinlik
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.util.*

class EtkinlikEkleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEtkinlikEkleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtkinlikEkleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun etkinlikEkle(view: View) {

        if (binding.etkinlikEkleEditTextEtkinlikAdi.text.toString() == "") {
            Toast.makeText(this, "Etkinlik adını giriniz.", Toast.LENGTH_LONG).show()
            return
        }

        if (binding.etkinlikEkleEditTextEtkinlikTutar.text.toString() == "") {
            Toast.makeText(this, "Etkinlik tutarını giriniz.", Toast.LENGTH_LONG).show()
            return
        }

        val currentTime: Date = Calendar.getInstance().getTime()

        val etkinlik = Etkinlik(
            binding.etkinlikEkleEditTextEtkinlikAdi.text.toString(),
            binding.etkinlikEkleEditTextEtkinlikTutar.text.toString().toDouble(),
            currentTime
        )
        GeziBankDatabase(applicationContext).dao().insert(etkinlik)
        finish()
    }
}