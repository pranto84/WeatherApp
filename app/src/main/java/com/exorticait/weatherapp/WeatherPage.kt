package com.exorticait.weatherapp

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.exorticait.weatherapp.data.NetworkResponse
import com.exorticait.weatherapp.data.WeatherModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    
    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherLiveData.observeAsState()

    var keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = { newCity ->
                    city = newCity
                },
                modifier = Modifier.weight(1f),
                label = {
                    Text(text = "Search for any location")
                }
            )

            IconButton(onClick = {
                viewModel.getData(city = city)
                keyboardController?.hide()
            }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search for any location"
                )
            }
        }

        when(val result = weatherResult.value){
            is NetworkResponse.Failure -> {
                Text(text = result.message)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(result.data)
            }
            null -> {}
        }

    }
}

@Composable
fun WeatherDetails(data: WeatherModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector =  Icons.Default.LocationOn,
                contentDescription = "Location Icon",
            )
            Text(
                text = data.location.name.toString(),
                fontSize = 38.sp,
                color = colorResource(id = R.color.gray),

            )
            Spacer(modifier = Modifier
                .width(8.dp)
            )

            /**
             * Using dimensionResource will not scale properly
             * This scaling value must be applied to the dimension that is fetched, to return something that can be used as sp:
             *
             * val scaledSize = with(LocalContext.current.resources) {
             *      (getDimension(R.dimen.sp_size) / displayMetrics.scaledDensity).sp
             *    }
             *
             *    Warning: this will only work correctly for dimensions defined as sp!
             *    Source: Stackoverflow: 67522145
             */
            Text(
                text = data.location.country.toString(),
                fontSize = 17.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = data.current.tempC.toString() + "°C" ,
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition?.icon }".replace("64x64", "128x128"),
            contentDescription = "Condition Icon"
        )

        Text(
            text = data.current.condition?.text.toString(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        WeatherKeyValue(key = "Humidity", value = data.current.humidity.toString())
                    }
                    Column {
                        WeatherKeyValue(key = "Wind Speed", value = data.current.windKph.toString() + "km/h")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        WeatherKeyValue(key = "UV", value = data.current.uv.toString())
                    }
                    Column {
                        WeatherKeyValue(key = "Feels °C", value = data.current.feelslikeC.toString())
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        WeatherKeyValue(key = "Local Time", value = data.location.localtime.split(" ")[1])
                    }
                    Column {
                        WeatherKeyValue(key = "Local Date", value =data.location.localtime.split(" ")[0])
                    }
                }

            }
        }

    }
}

@Composable
fun WeatherKeyValue(key: String, value: String){
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value)
        Text(text = key)
    }

}

