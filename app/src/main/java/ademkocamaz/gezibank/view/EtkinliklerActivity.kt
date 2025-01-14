package ademkocamaz.gezibank.view

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.adapter.RecyclerAdapter
import ademkocamaz.gezibank.dataaccess.GeziBankDatabase
import ademkocamaz.gezibank.databinding.ActivityEtkinliklerBinding
import ademkocamaz.gezibank.databinding.ActivityMainBinding
import ademkocamaz.gezibank.model.Etkinlik
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class EtkinliklerActivity : AppCompatActivity() {

    private lateinit var etkinlikListesi: ArrayList<Etkinlik>
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: ActivityEtkinliklerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtkinliklerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        etkinlikListesi = ArrayList<Etkinlik>()
        recyclerAdapter = RecyclerAdapter(etkinlikListesi)

        verileriGetir()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = recyclerAdapter


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