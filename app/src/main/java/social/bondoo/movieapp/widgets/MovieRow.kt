package social.bondoo.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import social.bondoo.movieapp.model.Movie
import social.bondoo.movieapp.model.getMovies

@Preview(showBackground = true)
@Composable
fun MovieRow(
    movie: Movie = getMovies().first(),
    onItemClick: (String) -> Unit = { }
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        border = BorderStroke(1.dp, Color.Gray),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = CircleShape,
                shadowElevation = 4.dp,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images.first())
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                AnimatedVisibility(isExpanded) {
                    Column() {
                        HorizontalDivider(modifier = Modifier.padding(6.dp), color = MaterialTheme.colorScheme.onBackground)
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontSize = 13.sp
                                )
                            ) {
                                append("Plot: ")
                            }
                            withStyle(
                                style =SpanStyle(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))
                        HorizontalDivider(modifier = Modifier.padding(6.dp), color = MaterialTheme.colorScheme.onBackground)
                        Text(
                            text = "Genre: ${movie.genre}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable(
                            onClick = { isExpanded = !isExpanded },
                            indication = null, // 禁用点击后的涟漪效果
                            interactionSource = remember { MutableInteractionSource() } // 避免触发默认的点击交互反馈
                        ),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}