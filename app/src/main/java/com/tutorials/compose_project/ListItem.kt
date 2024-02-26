import androidx.compose.runtime.Composable







@Composable
fun ListItemCard(item: ListItem){

}












data class ListItem(
    val title: String,
    val subtitle: String,
    val icon: Int // Resource ID for the icon
)
