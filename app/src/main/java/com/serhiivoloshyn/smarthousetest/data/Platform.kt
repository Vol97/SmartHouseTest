package com.serhiivoloshyn.smarthousetest.data

import com.google.gson.annotations.SerializedName
import com.serhiivoloshyn.smarthousetest.R

enum class Platform {
    @SerializedName("Sercomm G450")
    SERCOMM_G450,
    @SerializedName("Sercomm G550")
    SERCOMM_G550,
    @SerializedName("MiCasaVerde VeraLite")
    MICASAVERDE_VERALITE,
    @SerializedName("Sercomm NA900")
    SERCOMM_NA900,
    @SerializedName("Sercomm NA301")
    SERCOMM_NA301,
    @SerializedName("Sercomm NA930")
    SERCOMM_NA930,
    @SerializedName("")
    NONE;

    companion object {
        fun getRequiredIcon(platform: Platform): Int {
            return when (platform) {
                SERCOMM_G450 -> R.drawable.vera_plus_big
                SERCOMM_G550 -> R.drawable.vera_secure_big
                MICASAVERDE_VERALITE,
                SERCOMM_NA900,
                SERCOMM_NA301,
                SERCOMM_NA930,
                NONE -> R.drawable.vera_edge_big
            }
        }
    }
}
