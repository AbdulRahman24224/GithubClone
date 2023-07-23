package com.example.app.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.GithubCloneTheme
import com.example.common.ui.CircleImage
import com.example.common.ui.sH
import com.example.common.ui.sV
import com.example.common.ui.shapes.CircleDot
import com.example.common.ui.shimmerPlaceHolder
import com.example.domain_models.repos.Repo
import com.example.githubClone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<ReposListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            GithubCloneTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val reposList = viewModel.reposList.collectAsState()
                    val viewState = viewModel.viewState.collectAsState()

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        itemsIndexed(reposList.value) { index, repo -> RepoItem(repo) }

                        item {

                            LaunchedEffect(true) { viewModel.loadRepos() }

                            if (viewState.value.isLoading) {
                                repeat(3) { PlaceHolderItem() }

                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RepoItem(repo: Repo) {

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp)
            ) {

                CircleImage(path = repo.ownerAvatarUrl ?: "")
                sH(w = 10)

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {

                    Text(text = repo.name ?: "")

                    Text(text = repo.ownerName ?: "" , style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))

                    sV(h = 4)
                    Text(
                        text = repo.description ?: "",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    sV(h = 8)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {

                        if (repo.language.isNullOrBlank().not()) {
                            CircleDot(color = MaterialTheme.colors.primary, size = 12)

                            Text(text = repo.language!!)
                        }
                        sH(w = 20)

                        if (repo.stars!! > 0) {
                            Image(
                                modifier =
                                Modifier
                                    .size(18.dp),
                                painter = painterResource(R.drawable.star),
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant),
                                contentDescription = "",
                            )
                            Text(text = repo.stars.toString())
                        }

                    }

                }
                Spacer(modifier = Modifier.padding(8.dp))

            }
        }
    }

    @Composable
    private fun PlaceHolderItem() {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                CircleImage(modifier = Modifier.shimmerPlaceHolder(true), path = "")
                sH(w = 8)

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        modifier = Modifier.shimmerPlaceHolder(true),
                        text = "Dummy textDummy text"
                    )
                    sV(h = 4)
                    Text(
                        modifier = Modifier.shimmerPlaceHolder(true),
                        text = "Dummy textDummy textDum  text",
                    )

                }
                Spacer(modifier = Modifier.padding(8.dp))

            }
        }
    }
}
