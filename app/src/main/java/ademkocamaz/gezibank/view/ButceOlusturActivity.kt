package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.databinding.ActivityButceOlusturBinding
import ademkocamaz.gezibank.util.OzelSharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ButceOlusturActivity : AppCompatActivity() {
    private lateinit var binding: ActivityButceOlusturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButceOlusturBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun butceOlustur(view: View) {
        if (binding.butceOlusturEditTextBakiye.text.toString() == "") {
            Toast.makeText(this, "Bütçe bilgisini giriniz.", Toast.LENGTH_LONG).show()
            return
        }
        val butce = binding.butceOlusturEditTextBakiye.text.toString().toFloat()
        OzelSharedPreferences(applicationContext).butceKaydet(butce)
        //MainActivity().butceGetir()
        finish()
    }
}