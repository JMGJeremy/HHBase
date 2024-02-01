package com.jmg.baseproject.ui.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.buttons.B18PrimaryBodyMedRound
import com.jmg.baseproject.ui.text.textFields.cardFields.TextFieldCVV
import com.jmg.baseproject.ui.text.textFields.cardFields.TextFieldCardNumber
import com.jmg.baseproject.ui.text.textFields.cardFields.TextFieldExpDate
import com.jmg.baseproject.ui.text.textFields.cardFields.TextFieldZip

@Composable
fun PaymentScreen(
    addCard:(String, String, String, String)->Unit,
    skippable: Boolean = false,
    skip: ()->Unit,
    setError: (String?)->Unit
){

    val cardNumber = remember { mutableStateOf<String?>(null)}
    val date = remember { mutableStateOf<String?>(null)}
    val cvv = remember { mutableStateOf<String?>(null)}
    val zip = remember { mutableStateOf<String?>(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                painter = painterResource(id = R.drawable.xmark),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.background)
            )

            if(skippable){
                Text(
                    text = "skip",
                    modifier = Modifier
                        .clickable{
                            skip.invoke()
                        }
                    )
            }
        }

        Column(
            modifier = Modifier
        ){
            TextFieldCardNumber(
                cardNumber = cardNumber,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ){
                TextFieldExpDate(
                    date = date,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .weight(1f)
                )

                TextFieldCVV(
                    cvv = cvv,
                    modifier = Modifier
                        .weight(1f)
                )

                TextFieldZip(
                    zip = zip,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f)
                )
            }
        }

        B18PrimaryBodyMedRound(
            click = {
                when (true){
                    cardNumber.value.isNullOrEmpty() -> setError.invoke("Enter your card number")
                    date.value.isNullOrEmpty() -> setError.invoke("Enter your expiration date")
                    cvv.value.isNullOrEmpty() -> setError.invoke("Enter your CVV")
                    zip.value.isNullOrEmpty() -> setError.invoke("Enter your zip code")
                    else -> addCard.invoke(cardNumber.value ?: "", cvv.value ?: "", date.value ?: "", zip.value ?: "")
                }

                    },
            text = "Add Card",
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .padding(24.dp)
        )
    }

}

@Preview(device = Devices.PIXEL_3A)
@Composable
fun PaymentPrev(){
    HHBaseTheme {
        PaymentScreen(
            addCard = { cardNum, cvv, date, zip->

            },
            skippable = true,
            skip = {},
            setError = {}
        )
    }
}