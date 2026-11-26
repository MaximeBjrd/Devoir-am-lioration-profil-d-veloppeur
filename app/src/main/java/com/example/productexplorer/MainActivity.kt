package com.example.productexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productexplorer.ui.theme.ProductExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductExplorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        developer = sampleDeveloper(),
                        skills = sampleSkills(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class DeveloperUi(
    val name: String,
    val role: String,
    val description: String,
    val email: String,
    val location: String,
    val availability: String
)

data class SkillUi(
    val id: Int,
    val name: String
)

fun sampleDeveloper(): DeveloperUi {
    return DeveloperUi(
        name = "Maxime BEAUJARD",
        role = "Alternant en Architecture Logicielle",
        description = "En alternance, je cherche à renforcer mes bases en développement mobile avec Jetpack Compose.",
        email = "maxime@exemple.com",
        location = "Tours",
        availability = "Disponible pour une alternance"
    )
}

fun sampleSkills(): List<SkillUi> {
    return listOf(
        SkillUi(1, "Kotlin"),
        SkillUi(2, "Jetpack Compose"),
        SkillUi(3, "Git"),
        SkillUi(4, "Material 3"),
        SkillUi(5, "Java")
    )
}

@Composable
fun ProfileScreen(
    developer: DeveloperUi,
    skills: List<SkillUi>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileHeader(developer = developer)
        }
        item {
            SkillsSection(skills = skills)
        }
    }
}

@Composable
fun AvatarWithBadge(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondary
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Avatar du développeur",
                modifier = Modifier.padding(20.dp)
            )
        }
        Surface(
            modifier = Modifier.align(Alignment.BottomEnd).size(20.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary
        ) {}
    }
}

@Composable
fun ProfileHeader(developer: DeveloperUi, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarWithBadge()
                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = developer.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = developer.role,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = developer.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SkillChip(skill: SkillUi, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(
            text = skill.name,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SkillsSection(skills: List<SkillUi>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Compétences",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = skills, key = { skill -> skill.id }) { skill ->
                SkillChip(skill = skill)
            }
        }
    }
}

@Preview(showBackground = true, name = "Profil - Light Theme", heightDp = 1100)
@Composable
fun ProfileScreenLightPreview() {
    ProductExplorerTheme(darkTheme = false) {
        ProfileScreen(
            developer = sampleDeveloper(),
            skills = sampleSkills()
        )
    }
}
