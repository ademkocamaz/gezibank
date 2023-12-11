package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.util.OzelSharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_butce_olustur.*

class ButceOlusturActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butce_olustur)
    }

    fun butceOlustur(view: View){
        if(butce_olustur_editText_bakiye.text.toString()==""){
            Toast.makeText(this,"Bütçe bilgisini giriniz.",Toast.LENGTH_LONG).show()
            return
        }
        val butce=butce_olustur_editText_bakiye.text.toString().toFloat()
        OzelSharedPreferences(applicationContext).butceKaydet(butce)
        //MainActivity().butceGetir()
        finish()
    }
}