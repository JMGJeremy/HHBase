package com.jmg.baseproject.ui.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.text.textView.TV22B700


@Composable
fun TopBarDrawableText(
    modifier: Modifier,
    drawable: Int,
    text: String,
    btnClick: ()->Unit
){

    ConstraintLayout(
        modifier = modifier

    ) {
        val (imgView, textView) = createRefs()

        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(18.dp, 18.dp)
                .constrainAs(imgView) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable { btnClick.invoke() },
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.inverseOnSurface)
        )

        TV22B700(
            text = text,
            modifier = Modifier
                .constrainAs(textView){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                }
        )
    }

}

@Preview
@Composable
fun TopBarDrawableTextPrev(){
    HHBaseTheme {
        TopBarDrawableText(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colorScheme.background),
            drawable = R.drawable.chevron_left,
            text = "Tutors",
            btnClick = {}
        )
    }
}