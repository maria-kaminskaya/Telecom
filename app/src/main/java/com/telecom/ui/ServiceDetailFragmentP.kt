package com.telecom.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.telecom.R
import com.telecom.authentication.*
import kotlinx.android.synthetic.main.fragment_main.*

class ServiceDetailFragmentP: Fragment(){

    lateinit var service: Service
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

    val root = inflater.inflate(R.layout.fragment_service_detal_p, container, false)
       service = ServiceDetailFragmentPArgs.fromBundle(arguments!!).selectedServices

        root.findViewById<TextView>(R.id.costdp).text = "${service.cost}"
        root.findViewById<TextView>(R.id.consoledp).text = "${service.console}"
        root.findViewById<TextView>(R.id.channelsdp).text = "${service.channels}"
        root.findViewById<TextView>(R.id.tvdp).text = "${service.tv}"
        root.findViewById<TextView>(R.id.modemdp).text = "${service.modem}"
        root.findViewById<TextView>(R.id.spdp).text = "${service.speed}"
        root.findViewById<TextView>(R.id.trdp).text = "${service.internet}"
        root.findViewById<TextView>(R.id.namedp).text = "${service.serviceName}"

        root.findViewById<Button>(R.id.okl).setOnClickListener {
            Log.d("ServiceDetailFragmentP", "onClick")
            canceled()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        progressLayout.setOnTouchListener { _, _ -> true}


    }
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userId = firebaseAuth.currentUser?.uid
    private val serviceDatabase = FirebaseDatabase.getInstance().reference.child(DATA_SERVICES)
    private val contractDatabase = FirebaseDatabase.getInstance().reference.child(DATA_CONTRACTS)

    var listIdService = mutableListOf<String>()
    var listServices = ArrayList<Service>()


    fun canceled(){

        contractDatabase.child(DATA_IDS)//null
        Log.d("ServiceDetailFragmentP", "canceled: ${contractDatabase.child(DATA_IDS)}")

        val query: Query = contractDatabase.orderByKey()

        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(p0: DataSnapshot) {
                val snapshotIterator = p0.children
                Log.d("Iterator", "${p0.children}");
                val iterator = snapshotIterator.iterator()
                Log.d("Iterator", "${iterator}");
                while(iterator.hasNext()){
                    val next = iterator.next()
                    Log.d("Iterator", "Value = " + next.child("idClient").value);
                    Log.d("Iterator", "Next: ${next}");
                    Log.d("Iterator", "${iterator}");
                    if(userId == next.child("idClient").value){
                        val contract = p0.getValue(Contract::class.java)
//                        num.setText("${next.child("idc").value}", TextView.BufferType.NORMAL)
                        if(next.child("idS").value==service.sid){
//                        listIdService.add(next.child("idS").value as String)

                            val path = next.key
                            Log.d("ServiceDetailFragmentP", "Next: ${next.key}");
                            next.child("idS").value
                            Log.d("ServiceDetailFragmentP", "Next: ${contractDatabase.child("${next.key}").child(DATA_IDS)}");
                            contractDatabase.child("${next.key}").child(DATA_IDS).setValue("null")
                        Log.d("ServiceDetailFragmentP", "canceled: ${listIdService}");}
                    }
                }
            }

        }
        )


    }

}