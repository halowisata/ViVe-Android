package academy.bangkit.jetvive.ui.components

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUI(
    label: String,
    listItems: Array<String>,
    modifier: Modifier = Modifier
) {
    val contextForToast = LocalContext.current.applicationContext

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        // text field
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(
                    text =  {
                        Text(text = selectedOption)
                    },
                    onClick = {
                        selectedItem = selectedOption
                        Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyUIPreview() {
    JetViVeTheme {
        MyUI(
            label = stringResource(R.string.input_label),
            listItems = arrayOf("Favorites", "Options", "Settings", "Share")
        )
    }
}