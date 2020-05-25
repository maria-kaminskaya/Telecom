package com.telecom.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telecom.R
import com.telecom.authentication.Contract
import com.telecom.authentication.Service

class AdapterService(val context: Context?, private val arrayS: ArrayList<Service>): BaseAdapter() {

    val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var view: View = convertView
        //        if(view==null){
        val view: View = inflater.inflate(R.layout.item_service, parent, false)
//        }
        val service: Service = arrayS[position]

        view.findViewById<TextView>(R.id.cost1).text = "${service.cost}"
        view.findViewById<TextView>(R.id.tv1).text = "${service.tv}"
        view.findViewById<TextView>(R.id.sp1).text = "${service.speed}"
        view.findViewById<TextView>(R.id.tr1).text = "${service.internet}"
        view.findViewById<TextView>(R.id.nameS1).text = "${service.serviceName}"

        return view
    }

    override fun getItem(position: Int): Service {
        return arrayS[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayS.size
    }


}
