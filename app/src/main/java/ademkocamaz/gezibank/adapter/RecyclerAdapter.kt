package ademkocamaz.gezibank.adapter

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.databinding.RecyclerRowBinding
import ademkocamaz.gezibank.model.Etkinlik
import ademkocamaz.gezibank.view.EtkinlikDetayActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat

class RecyclerAdapter(val etkinlikListesi: ArrayList<Etkinlik>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
    private lateinit var binding: RecyclerRowBinding

    class RecyclerHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return RecyclerHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val df: DateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
        val tarih = df.format(etkinlikListesi.get(position).tarih)
        binding.recyclerRowEtkinlikTarih.text = tarih.toString()
        binding.recyclerRowEtkinlikAdi.text = etkinlikListesi.get(position).adi
        binding.recyclerRowEtkinlikTutar.text =
            etkinlikListesi.get(position).tutar.toString() + "₺"

        holder.view.setOnClickListener { view ->
            val intent = Intent(view.context, EtkinlikDetayActivity::class.java)
            intent.putExtra("id", etkinlikListesi.get(position).id)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return etkinlikListesi.size
    }
}