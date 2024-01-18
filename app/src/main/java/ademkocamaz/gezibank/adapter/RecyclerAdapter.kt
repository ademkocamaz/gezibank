package ademkocamaz.gezibank.adapter

import ademkocamaz.gezibank.R
import ademkocamaz.gezibank.model.Etkinlik
import ademkocamaz.gezibank.view.EtkinlikDetayActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class RecyclerAdapter(val etkinlikListesi: ArrayList<Etkinlik>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    class RecyclerHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent, false)
        return RecyclerHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val df: DateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
        val tarih = df.format(etkinlikListesi.get(position).tarih)
        holder.view.recycler_row_etkinlik_tarih.text = tarih.toString()
        holder.view.recycler_row_etkinlik_adi.text = etkinlikListesi.get(position).adi
        holder.view.recycler_row_etkinlik_tutar.text =
            etkinlikListesi.get(position).tutar.toString() + "₺"

        holder.view.setOnClickListener { view ->
            val intent=Intent(view.context,EtkinlikDetayActivity::class.java)
            intent.putExtra("id",etkinlikListesi.get(position).id)

            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return etkinlikListesi.size
    }
}