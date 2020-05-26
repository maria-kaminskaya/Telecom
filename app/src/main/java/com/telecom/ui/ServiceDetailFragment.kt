package com.telecom.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.telecom.R
import com.telecom.authentication.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ServiceDetailFragment: Fragment() {
    lateinit var service: Service
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val root = inflater.inflate(R.layout.fragment_service_detal, container, false)
        service = ServiceDetailFragmentArgs.fromBundle(arguments!!).selectedServiceAll

        root.findViewById<TextView>(R.id.costd).text = "${service.cost}"
        root.findViewById<TextView>(R.id.consoled).text = "${service.console}"
        root.findViewById<TextView>(R.id.channelsd).text = "${service.channels}"
        root.findViewById<TextView>(R.id.tvd).text = "${service.tv}"
        root.findViewById<TextView>(R.id.modemd).text = "${service.modem}"
        root.findViewById<TextView>(R.id.spd).text = "${service.speed}"
        root.findViewById<TextView>(R.id.trd).text = "${service.internet}"
        root.findViewById<TextView>(R.id.named).text = "${service.serviceName}"

        root.findViewById<Button>(R.id.podkl).setOnClickListener {
            paid()
            Log.d("ServiceDetailFragmentP", "onClick")

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        progressLayout.setOnTouchListener { _, _ -> true}


    }

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userId = firebaseAuth.currentUser?.uid
    private val clientDatabase = FirebaseDatabase.getInstance().reference.child(DATA_CLIENTS).child(userId!!)
    private val contractDatabase = FirebaseDatabase.getInstance().reference.child(DATA_CONTRACTS)


    @RequiresApi(Build.VERSION_CODES.O)
    fun paid(){

        val costService = service.cost!!.toLong()
       val  idG = (Math.random()*10000).toInt().toString()

        val idS = service.sid
        val idc = (Math.random()*100).toInt().toString()
        val idClient = userId
        val dateOfConclusion = LocalDateTime.now().toString()

        val contract = Contract(idS, idc, idClient, dateOfConclusion)

        Log.d("ServiceDetailFragment", "paid: ${contract}")
        contractDatabase.child(idG).setValue(contract)
        Log.d("ServiceDetailFragment", "paid: ${contractDatabase.child(idG)}")

        clientDatabase.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(isAdded) {
                    val user = p0.getValue(Client::class.java)
//                  val b = user?.balance!!.toInt()

                      val balanceUser = user?.balance
                    Log.d("ServiceDetailFragment", "paid: ${balanceUser}")
                    Log.d("ServiceDetailFragment", "paid: ${costService}")
                    val currentBalance = balanceUser!! - costService
                    Log.d("ServiceDetailFragment", "paid: ${currentBalance}")
                    clientDatabase.child(DATA_BALANCE).setValue(currentBalance)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("Populate User", "Populate cancelled")
            }
        })


        Log.d("ServiceDetailFragment", "paid: ${clientDatabase.child("${userId}").child(DATA_BALANCE)}")
//        clientDatabase.child("${userId}").child(DATA_BALANCE).setValue(currentBalance)

    }


}