package com.telecom.authentication

import com.google.firebase.database.IgnoreExtraProperties

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
    val dateOfConclusion: String? = "",
    val idc: Long? = null,
    val idS: String? = "",
    val idClient: String? = ""
)

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
)

val DATA_CLIENTS = "Clients"
    val DATA_NAME = "Name"
    val DATA_SURNAME = "Surname"
    val DATA_DATEOFBIRTH = "DateOfBirth"
    val DATA_BALANCE = "Balance"
    val DATA_PHONE = "Phone"
    val DATA_EMAIL = "Email"

val DATA_CONTRACTS = "Contracts"
    val DATA_IDC = "IdC"
    val DATA_IDCLIENT = "IdClient"
    val DATA_IDS = "IdS"
    val DATA_DATEOFCONCLUSION = "DateOfConclusion"

val DATA_SERVICES = "Services"
//    val DATA_CHANNELS = ""
//    val DATA_CONSOLE = ""
//    val DATA_COST = ""
//    val DATA_INTERNET = ""
//    val DATA_MODEM = ""
//    val DATA_SERVICENAME = ""
//    val DATA_SPEED = ""
//    val DATA_TV = ""
