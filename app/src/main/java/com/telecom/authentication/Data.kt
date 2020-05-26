package com.telecom.authentication

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
data class Client(
    val uid: String? = "",
    val name: String? = "",
    val surname: String? = "",
    val dateOfBirth: String? = "",
    val balance: Long? = null,
    val phoneNumber: String? = "",
    val email: String? = ""
)
//{
//
//    // [START post_to_map]
//    @Exclude
//    fun toMap(): Map<String, Any?> {
//        return mapOf(
//            "uid" to uid,
//            "name" to name,
//            "surname" to surname,
//            "dateOfBirth" to dateOfBirth,
//            "balance" to balance,
//            "phoneNumber" to phoneNumber,
//            "email" to email
//
//        )
//    }
//    // [END post_to_map]
//}
// [END post_class]

@IgnoreExtraProperties
data class Contract(
    val idS: String? = "",
    val idc: String? = "",
    val idClient: String? = "",
    val dateOfConclusion: String? = ""
)

@Parcelize
@IgnoreExtraProperties
data class Service(
    val sid: String? = "",
    val channels: String? = "",
    val console: String? = "",
    val cost: String? = "",
    val internet: String? = "",
    val modem: String? = "",
    val serviceName: String? = "",
    val speed: String? = "",
    val tv:String? = ""
) : Parcelable

val DATA_CLIENTS = "Clients"
    val DATA_NAME = "name"
    val DATA_SURNAME = "surname"
    val DATA_DATEOFBIRTH = "dateOfBirth"
    val DATA_BALANCE = "balance"
    val DATA_PHONE = "phone"
    val DATA_EMAIL = "email"

val DATA_CONTRACTS = "Contracts"
    val DATA_IDC = "idC"
    val DATA_IDCLIENT = "idClient"
    val DATA_IDS = "idS"
    val DATA_DATEOFCONCLUSION = "dateOfConclusion"

val DATA_SERVICES = "Services"
//    val DATA_CHANNELS = ""
//    val DATA_CONSOLE = ""
//    val DATA_COST = ""
//    val DATA_INTERNET = ""
//    val DATA_MODEM = ""
//    val DATA_SERVICENAME = ""
//    val DATA_SPEED = ""
//    val DATA_TV = ""
