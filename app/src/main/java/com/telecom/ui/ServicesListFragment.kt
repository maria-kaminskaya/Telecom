package com.telecom.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.telecom.R
import com.telecom.authentication.DATA_SERVICES
import com.telecom.authentication.Service
import com.telecom.ui.adapters.AdapterService
import com.telecom.ui.main.MainFragmentDirections
import kotlinx.android.synthetic.main.fragment_main.*

class ServicesListFragment: Fragment() {

    lateinit var listSAll: ListView
    val nav = this

    lateinit var adapter: AdapterService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_servises_list, container, false)

        listSAll = root.findViewById(R.id.listSAll)

        return root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressLayout.setOnTouchListener { _, _ -> true}
        populateInfo()

    }

    private val serviceDatabase = FirebaseDatabase.getInstance().reference.child(DATA_SERVICES)

    var listServices = ArrayList<Service>()

    private fun populateInfo() {
        //SERVICE
        serviceDatabase.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(p0: DataSnapshot) {
                val snapshotIterator = p0.children
                Log.d("ServicesListFragment", "${p0.children}");
                val iterator = snapshotIterator.iterator()
                Log.d("ServicesListFragment", "${iterator}");
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    Log.d("ServicesListFragment", "Value = " + next.child("sid").value);
                    Log.d("ServicesListFragment", "${iterator}");
                    listServices.add(
                        Service(
                            "${next.child("sid").value}",
                            "${next.child("channels").value}",
                            "${next.child("console").value}",
                            "${next.child("cost").value}",
                            "${next.child("internet").value}",
                            "${next.child("modem").value}",
                            "${next.child("serviceName").value}",
                            "${next.child("speed").value}",
                            "${next.child("tv").value}"
                        )
                    )
                    Log.d("ServicesListFragment", "${listServices}");
                    progressLayout.visibility = View.GONE

                }
                adapter = AdapterService(context, listServices)
                listSAll.adapter = adapter

                listSAll.setOnItemClickListener { parent, view, position, id ->
                    nav.findNavController().navigate(ServicesListFragmentDirections.actionServicesListFragmentToServiceDetailFragment(listServices[position]))
                }
            }})

    }

    override fun onPause() {
        super.onPause()
        listServices.clear()
        listServices.clear()
        listSAll.adapter=null
        adapter.notifyDataSetChanged()
    }


}