package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.adapter.RecyclerAdapter
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.model.Etkinlik
import ademkocamaz.gezibank.util.OzelSharedPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var butce: Double = 0.0
    private lateinit var etkinlikListesi: ArrayList<Etkinlik>
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)

        reklamYenile()

        butceGetir()
        kalaniBul()

//        val currentTime:Date=Calendar.getInstance().time
//        val etkinlik=Etkinlik("Tantuni",80.85,currentTime)
//        GeziBankDatabase(applicationContext).dao().insert(etkinlik)

        //GeziBankDatabase(applicationContext).dao().deleteAll()


        etkinlikListesi = ArrayList<Etkinlik>()
        recyclerAdapter = RecyclerAdapter(etkinlikListesi)

        verileriGetir()

        val layoutManager = LinearLayoutManager(this)
        main_recyclerView.layoutManager = layoutManager
        main_recyclerView.adapter = recyclerAdapter


    }

    fun reklamYenile(){
        var adRequest = AdRequest.Builder().build()
        main_adView.loadAd(adRequest)
    }

    fun verileriGetir() {
        val liste = GeziBankDatabase(applicationContext).dao().getAll()
        etkinlikListesi.clear()
        for (item in liste) {
            etkinlikListesi.add(item)
        }
        recyclerAdapter.notifyDataSetChanged()
    }

    fun butceGetir() {
        val alinanButce = OzelSharedPreferences(applicationContext).butceAl()
        if (alinanButce != null) {
            butce = alinanButce.toDouble()
        } else {
            butce = 0.0
        }
        main_textView_butce_bakiye.text = "Bütçe :    " + butce.toString()+"₺"
    }

    fun kalaniBul() {
        val total = GeziBankDatabase(applicationContext).dao().total()
        val kalan = butce - total
        main_textView_butce_kalan.text = "Kalan :    " + kalan.toString()+"₺"
        main_textView_etkinlik_toplam.text = "Toplam :    " + total.toString()+"₺"
    }

    override fun onResume() {
        butceGetir()
        kalaniBul()
        verileriGetir()
        reklamYenile()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.main_menu_tum_etkinlikler) {
            val intent = Intent(this, EtkinliklerActivity::class.java)
            startActivity(intent)
        }

        if (item.itemId == R.id.main_menu_etkinlik_ekle) {
            val intent = Intent(this, EtkinlikEkleActivity::class.java)
            startActivity(intent)
        }

        if (item.itemId == R.id.main_menu_butce_olustur) {
            val intent = Intent(this, ButceOlusturActivity::class.java)
            startActivity(intent)
        }

        if(item.itemId==R.id.main_menu_herseyi_sil){
            GeziBankDatabase(applicationContext).dao().deleteAll()
            OzelSharedPreferences(applicationContext).butceKaydet(0f)
            onResume()
        }

        return super.onOptionsItemSelected(item)
    }
}