package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.adapter.RecyclerAdapter
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.databinding.ActivityMainBinding
import ademkocamaz.gezibank.model.Etkinlik
import ademkocamaz.gezibank.util.OzelSharedPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.util.*

class MainActivity : AppCompatActivity() {
    private var butce: Double = 0.0
    private lateinit var etkinlikListesi: ArrayList<Etkinlik>
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root


        InterstitialAd.load(
            this,
            "ca-app-pub-5764318432941968/3246390729",
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    interstitialAd.show(this@MainActivity)
                }
            })

        binding.mainAdView.loadAd(AdRequest.Builder().build())


        setContentView(view)

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
        binding.mainRecyclerView.layoutManager = layoutManager
        binding.mainRecyclerView.adapter = recyclerAdapter

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
        binding.mainTextViewButceBakiye.text = "Bütçe :    " + butce.toString() + "₺"
    }

    fun kalaniBul() {
        val total = GeziBankDatabase(applicationContext).dao().total()
        val kalan = butce - total
        binding.mainTextViewButceKalan.text = "Kalan :    " + kalan.toString() + "₺"
        binding.mainTextViewEtkinlikToplam.text = "Toplam :    " + total.toString() + "₺"
    }

    override fun onResume() {
        butceGetir()
        kalaniBul()
        verileriGetir()
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

        if (item.itemId == R.id.main_menu_herseyi_sil) {
            GeziBankDatabase(applicationContext).dao().deleteAll()
            OzelSharedPreferences(applicationContext).butceKaydet(0f)
            onResume()
        }

        return super.onOptionsItemSelected(item)
    }
}