package com.jmg.baseproject.ui.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.text.textView.TV22B700


@Composable
fun TopBarDrawableProfile(
    modifier: Modifier,
    drawable: Int,
    text: String,
    btnClick: ()->Unit,
    img: String
){

    ConstraintLayout(
        modifier = modifier
    ) {
        val (imgView, textView, image) = createRefs()

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
                .clickable { btnClick.invoke() }
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

        Row(
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(image) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .border(width = 1.dp, shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.primary)
                .padding(8.dp)
        ) {
            Image(
                painter = if (img.isNotEmpty()) {
                    rememberAsyncImagePainter(model = img)
                } else {
                    painterResource(id = R.drawable.user)
                },
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp, 22.dp),
                contentScale = ContentScale.Fit
            )
        }
        
    }

}

@Preview
@Composable
fun TopBarDrawableProfilePrev(){
    HHBaseTheme {
        TopBarDrawableProfile(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colorScheme.background),
            drawable = R.drawable.chevron_left,
            text = "Tutors",
            btnClick = {},
            img = ""
        )
    }
}