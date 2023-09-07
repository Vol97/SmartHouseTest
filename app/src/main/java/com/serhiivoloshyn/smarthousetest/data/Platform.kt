package com.serhiivoloshyn.smarthousetest.data

import com.google.gson.annotations.SerializedName
import com.serhiivoloshyn.smarthousetest.R

enum class Platform(val platformName: String) {
    @SerializedName("Sercomm G450")
    SERCOMM_G450("Sercomm G450"),
    @SerializedName("Sercomm G550")
    SERCOMM_G550("Sercomm G550"),
    @SerializedName("MiCasaVerde VeraLite")
    MICASAVERDE_VERALITE("MiCasaVerde VeraLite"),
    @SerializedName("Sercomm NA900")
    SERCOMM_NA900("Sercomm NA900"),
    @SerializedName("Sercomm NA301")
    SERCOMM_NA301("Sercomm NA301"),
    @SerializedName("Sercomm NA930")
    SERCOMM_NA930("Sercomm NA930"),
    @SerializedName("")
    NONE("None");

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

        fun getRequiredIcon(string: String?): Int {
            return when (string) {
                "SERCOMM_G450" -> R.drawable.vera_plus_big
                "SERCOMM_G550" -> R.drawable.vera_secure_big
                "MICASAVERDE_VERALITE",
                "SERCOMM_NA900",
                "SERCOMM_NA301",
                "SERCOMM_NA930",
                "NONE" -> R.drawable.vera_edge_big
                else -> R.drawable.vera_edge_big
            }
        }
    }
}
