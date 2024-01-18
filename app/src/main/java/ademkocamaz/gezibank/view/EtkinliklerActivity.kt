package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.adapter.RecyclerAdapter
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.model.Etkinlik
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_etkinlikler.*

class EtkinliklerActivity : AppCompatActivity() {

    private lateinit var etkinlikListesi: ArrayList<Etkinlik>
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlikler)


        etkinlikListesi = ArrayList<Etkinlik>()
        recyclerAdapter = RecyclerAdapter(etkinlikListesi)

        verileriGetir()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter


    }

    override fun onResume() {
        verileriGetir()
        super.onResume()
    }

    fun verileriGetir() {
        val liste = GeziBankDatabase(applicationContext).dao().getAll()
        etkinlikListesi.clear()
        for (item in liste) {
            etkinlikListesi.add(item)
        }
        recyclerAdapter.notifyDataSetChanged()
    }

}