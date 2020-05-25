package com.telecom.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.telecom.R
import com.telecom.authentication.*
import com.telecom.ui.adapters.AdapterServiceP
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

    lateinit var listS: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
//        list.add(Service(
//            "qwe",
//            "qwe",
//            "qwe",
//            "qwe",
//            "qwe",
//            "qwe",
//            "qwe",
//            "qwe",
//            "qwe"
//        ))
        listS = root.findViewById<ListView>(R.id.listS)

        root.listButton.setOnClickListener{
            this.findNavController().navigate(R.id.action_mainFragment_to_servicesListFragment)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressLayout.setOnTouchListener { _, _ -> true}

        populateInfo()

    }

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userId = firebaseAuth.currentUser?.uid
    private val clientDatabase = FirebaseDatabase.getInstance().reference.child(DATA_CLIENTS).child(userId!!)
    private val contractDatabase = FirebaseDatabase.getInstance().reference.child(DATA_CONTRACTS)
    private val serviceDatabase = FirebaseDatabase.getInstance().reference.child(DATA_SERVICES)

    var listIdService = mutableListOf<String>()
    var listServices = ArrayList<Service>()

    var list = ArrayList<Service>()
//    var listS = mutableListOf<String>()

    private fun populateInfo(){
        //CLIENTS
        Log.d("populateInfo", "populateInfo $userId")
        Log.d("populateInfo", "populateInfo $clientDatabase")
        clientDatabase.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("onDataChange", "onDataChange")
                if(isAdded) {
                    val user = p0.getValue(Client::class.java)
                    Log.d("onDataChange", "onDataChange $user")
//                    val name = p0.getValue<Name>()
                    name.setText(user?.name, TextView.BufferType.NORMAL)
                    Log.d("onDataChange", "onDataChange ${user?.name}")
                    surname.setText(user?.surname, TextView.BufferType.EDITABLE)
                    Log.d("onDataChange", "onDataChange ${user?.surname}")
                    balance.setText(user?.balance.toString(), TextView.BufferType.EDITABLE)
                    Log.d("onDataChange", "onDataChange ${user?.balance}")
                    progressLayout.visibility = View.GONE
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("Populate User", "Populate cancelled")
            }
        })


//        contractDatabase.addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                Log.d("Populate User", "Populate cancelled")
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                Log.d("onDataChange", "Contract")
//                Log.d("populateInfo", "populateInfo $contractDatabase")
//                if(isAdded) {
//                    val contract = p0.getValue(Contract::class.java)
//                    Log.d("Contract", "Contract $contract")
////                    num.setText(contract?.idc, TextView.BufferType.EDITABLE)
//                    Log.d("Contract", "onDataChange ${contract?.idc}")
//                    progressLayout.visibility = View.GONE
//                }
//            }
//    })

//        val cDatabase = FirebaseDatabase.getInstance().reference.child(DATA_CONTRACTS)

        //CONTRACTS
        val query: Query = contractDatabase.orderByKey()

        query.addListenerForSingleValueEvent(object: ValueEventListener{
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
                    Log.d("Iterator", "${iterator}");
                    if(userId == next.child("idClient").value){
                        val contract = p0.getValue(Contract::class.java)
                        num.setText("${next.child("idc").value}", TextView.BufferType.NORMAL)
                        listIdService.add(next.child("idS").value as String)
                        Log.d("onDataChange", "listIdService: ${listIdService}");
                    }
                }
            }

        }
        )

        //SERVICE
        serviceDatabase.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(p0: DataSnapshot) {
                val snapshotIterator = p0.children
                Log.d("SERVICE", "${p0.children}");
                val iterator = snapshotIterator.iterator()
                Log.d("SERVICE", "${iterator}");
                while(iterator.hasNext()){
                    val next = iterator.next()
                    Log.d("SERVICE", "Value = " + next.child("sid").value);
                    Log.d("SERVICE", "${iterator}");
//                    listS.addAll(listOf(next.child("sid").value as String))
                    // not idClient - idService
                    Log.d("SERVICE", "$listS");

                    listIdService.forEach{
                        if(it == next.child("sid").value){
                            val service = p0.getValue(Service::class.java)
                            Log.d("SERVICE_n", "${next.child("serviceName").value}");

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
                                    "${next.child("tv").value}")
//
                            )
                            Log.d("LIST_SERVICES", "${listServices}");
                        }
                    }
                    val adapter = AdapterServiceP(context, listServices)
                    listS.adapter = adapter
//                    if(listIdService == next.child("sid").value){
////                        val contract = p0.getValue(Contract::class.java)
////                        num.setText("${next.child("idc").value}", TextView.BufferType.NORMAL)
//                        val service = p0.getValue(Service::class.java)
//                        Log.d("SERVICE", "${service}");
//
////                        nameS.setText("${next.child("serviceName").value}", TextView.BufferType.NORMAL)
////                        tr.setText("${next.child("internet").value}", TextView.BufferType.NORMAL)
////                        sp.setText("${next.child("speed").value}", TextView.BufferType.NORMAL)
////                        tv.setText("${next.child("tv").value}", TextView.BufferType.NORMAL)
//
//                        Log.d("SERVICE", "${next.child("serviceName").value}");
//                        Log.d("SERVICE", "${service?.serviceName}");
//
////                        listServices.value = mapOf(
////                            "serviceName" to "${service?.serviceName}",
////                            "sid" to "${ next.child("serviceName").value}",
////                            "channels" to "${ next.child("serviceName").value}",
////                            "console" to "${ next.child("serviceName").value}",
////                            "cost" to "${ next.child("serviceName").value}",
////                            "internet" to "${ next.child("serviceName").value}",
////                            "modem" to "${ next.child("serviceName").value}",
////                            "speed" to "${ next.child("serviceName").value}",
////                            "tv" to "${ next.child("serviceName").value}"
////                        )
////                        Log.d("LIST_SERVICES", "${listServices}");
//
////                    }
//                    else
//                        Log.d("LIST_SERVICES", "NOT");
                }
            }

        })
    }

////    fun onClickButton() {
//        Log.d("MainFragment", "ServicesListFragment")
//        val intent = Intent(activity, ServicesListFragment::class.java)
//        startActivity(intent)
//
//    }

}

