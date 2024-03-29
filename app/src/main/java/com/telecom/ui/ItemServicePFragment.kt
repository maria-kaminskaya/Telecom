package com.telecom.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.telecom.R
import com.telecom.authentication.Service

class ItemServicePFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.item_service_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        progressLayout.setOnTouchListener { _, _ -> true}


    }


    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userId = firebaseAuth.currentUser?.uid

}
//
//class ItemServicePViewModel: ViewModel(){
//
//    var listener : ItemListener? = null
//    lateinit var service: Service
//
//    fun bind(service: Service, listener : ItemListener?){
//        this.service = service
//        this.listener = listener
//
//    }
//
//    fun onClickItem(){
//        listener?.onSubUserItemClick(service)
//    }
//
//}
//
//
//interface ItemListener {
//    fun onSubUserItemClick(s: Service)
//}