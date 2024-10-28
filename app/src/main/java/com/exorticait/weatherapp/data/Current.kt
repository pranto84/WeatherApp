package com.exorticait.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Current (

    @SerializedName("last_updated_epoch" ) var lastUpdatedEpoch : String?       = null,
    @SerializedName("last_updated"       ) var lastUpdated      : String?    = null,
    @SerializedName("temp_c"             ) var tempC            : String?       = null,
    @SerializedName("temp_f"             ) var tempF            : String?    = null,
    @SerializedName("is_day"             ) var isDay            : String?       = null,
    @SerializedName("condition"          ) var condition        : Condition? = Condition(),
    @SerializedName("wind_mph"           ) var windMph          : String?    = null,
    @SerializedName("wind_kph"           ) var windKph          : String?    = null,
    @SerializedName("wind_degree"        ) var windDegree       : String?       = null,
    @SerializedName("wind_dir"           ) var windDir          : String?    = null,
    @SerializedName("pressure_mb"        ) var pressureMb       : String?       = null,
    @SerializedName("pressure_in"        ) var pressureIn       : String?    = null,
    @SerializedName("precip_mm"          ) var precipMm         : String?       = null,
    @SerializedName("precip_in"          ) var precipIn         : String?       = null,
    @SerializedName("humidity"           ) var humidity         : String?       = null,
    @SerializedName("cloud"              ) var cloud            : String?       = null,
    @SerializedName("feelslike_c"        ) var feelslikeC       : String?    = null,
    @SerializedName("feelslike_f"        ) var feelslikeF       : String?    = null,
    @SerializedName("windchill_c"        ) var windchillC       : String?    = null,
    @SerializedName("windchill_f"        ) var windchillF       : String?    = null,
    @SerializedName("heatindex_c"        ) var heatindexC       : String?    = null,
    @SerializedName("heatindex_f"        ) var heatindexF       : String?    = null,
    @SerializedName("dewpoint_c"         ) var dewpointC        : String?    = null,
    @SerializedName("dewpoint_f"         ) var dewpointF        : String?    = null,
    @SerializedName("vis_km"             ) var visKm            : String?       = null,
    @SerializedName("vis_miles"          ) var visMiles         : String?       = null,
    @SerializedName("uv"                 ) var uv               : String?       = null,
    @SerializedName("gust_mph"           ) var gustMph          : String?    = null,
    @SerializedName("gust_kph"           ) var gustKph          : String?    = null

)
