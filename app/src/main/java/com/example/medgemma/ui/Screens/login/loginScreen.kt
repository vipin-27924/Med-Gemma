import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val MintGreen = Color(0xFF6DE8C3)
private val LightBlueBg = Color(0xFFC3E8E8)
private val TextDark = Color(0xFF4A4A4A)
private val InputBg = Color(0xFFF0F0F0)
private val LinkBlue = Color(0xFF4285F4)
@Composable
fun LoginScreen() {
    // Full screen background
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightBlueBg // Use the light blue as the top background color for visual continuity
    ) {
        // The form content container, allowing scrolling on small screens
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🧬\nMEDI-AI",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp)
                    .padding(horizontal = 16.dp), // Padding on the sides
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                LoginForm(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp) // Adjusted inner padding
                )
            }
            // Add a small spacer at the bottom for better visual spacing below the card
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

/**
 * Panel containing the login form elements.
 * Accepts a modifier to control internal padding.
 */
@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Header ---
        Text("WELCOME BACK", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = TextDark)
        Spacer(Modifier.height(8.dp))
        Text(
            "Sign in with your social account or enter your details",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Spacer(Modifier.height(40.dp))

        // --- Email Field ---
        Text("Email Address", modifier = Modifier.fillMaxWidth().align(Alignment.Start), fontWeight = FontWeight.Medium, color = TextDark)
        Spacer(Modifier.height(8.dp))
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Enter your email",
            keyboardType = KeyboardType.Email
        )

        Spacer(Modifier.height(20.dp))

        // --- Password Field ---
        Text("Password", modifier = Modifier.fillMaxWidth().align(Alignment.Start), fontWeight = FontWeight.Medium, color = TextDark)
        Spacer(Modifier.height(8.dp))
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "••••••••••",
            trailingIcon = Icons.Outlined.Lock,
            keyboardType = KeyboardType.Password,
            isPassword = true
        )

        Spacer(Modifier.height(16.dp))

        // --- Remember Me & Forgot Password ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(checkedColor = MintGreen, uncheckedColor = Color.Gray)
                )
                Text("Remember me", color = TextDark, fontSize = 14.sp)
            }
            Text(
                "Forgot Password ?",
                color = LinkBlue,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier.clickable {  }
            )
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { /* Handle Login */ },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MintGreen),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text("Log In", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(28.dp))

        Text("or", color = Color.Gray, fontSize = 14.sp)
        Spacer(Modifier.height(28.dp))

        OutlinedButton(
            onClick = { /* Handle Google Sign In */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextDark)
        ) {
            // Placeholder for the Google G logo
            Text("G", fontWeight = FontWeight.Bold, color = LinkBlue, fontSize = 20.sp)
            Spacer(Modifier.width(12.dp))
            Text("Google", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }

        Spacer(Modifier.height(48.dp))

        // --- Sign Up Link ---
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account? ", color = TextDark, fontSize = 14.sp)
            Text(
                "Sign up",
                color = LinkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { /* Handle Sign up click */ }
            )
        }
    }
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = modifier.height(56.dp),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = InputBg,
            focusedContainerColor = InputBg,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = MintGreen,
            cursorColor = MintGreen
        ),
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    trailingIcon,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
