package com.exorticait.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Location (

    @SerializedName("name"            ) var name           : String? = null,
    @SerializedName("region"          ) var region         : String? = null,
    @SerializedName("country"         ) var country        : String? = null,
    @SerializedName("lat"             ) var lat            : String? = null,
    @SerializedName("lon"             ) var lon            : String? = null,
    @SerializedName("tz_id"           ) var tzId           : String? = null,
    @SerializedName("localtime_epoch" ) var localtimeEpoch : String? = null,
    @SerializedName("localtime"       ) var localtime      : String? = null

)