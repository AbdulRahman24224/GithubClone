package com.example.app.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.common.ui.AppColors
import com.example.common.ui.theme.GithubCloneTheme
import com.example.common.ui.CircleImage
import com.example.common.ui.bars.AppToolbar
import com.example.common.ui.bars.ChangeStatusBarColor
import com.example.common.ui.errors.ConnectionErrorView
import com.example.common.ui.errors.MessageBar
import com.example.common.ui.sH
import com.example.common.ui.sV
import com.example.common.ui.shapes.CircleDot
import com.example.common.ui.shimmerPlaceHolder
import com.example.domain_models.repos.Repo
import com.example.githubClone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposListActivity : ComponentActivity() {

    val viewModel by viewModels<ReposListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            GithubCloneTheme {

                ReposListScreen(onRetryLoading = { viewModel.loadRepos() })
            }
        }
    }

    @Composable
    private fun ReposListScreen(
        onRetryLoading: () -> Unit
    ) {

        ChangeStatusBarColor()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            val reposList = viewModel.reposList.collectAsState()
            val viewState = viewModel.viewState.collectAsState()

            Box (modifier = Modifier.fillMaxSize()){

                Column(Modifier.fillMaxSize()) {

                    Toolbar()

                    if (viewState.value.isApiUnreachable && viewState.value.isLoading.not())
                        ConnectionError(onRetryLoading)
                    else
                        ReposList(reposList.value, viewState.value)
                }

                SnackBar(viewState.value)
            }

        }
    }

    @Composable
    private fun Toolbar() {
        AppToolbar(
            title = stringResource(R.string.trending),
            optionsIcon = {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { },
                    contentDescription = "Menu",
                )
            },
        )
    }

    @Composable
    private fun ConnectionError(onRetryLoading: () -> Unit) {
        ConnectionErrorView(
            extraContent = {
                OutlinedButton(
                    onClick = onRetryLoading,
                    modifier = Modifier.padding(16.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
                    content = {
                        Text(
                            text = stringResource(R.string.lis_btn_retry),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                        )
                    },
                )
            }
        )
    }

    @Composable
    private fun SnackBar(viewState: ReposListViewState) {
        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = viewState.snackBarMessage != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                MessageBar(
                    modifier = Modifier.padding( 10.dp),
                    message = viewState.snackBarMessage?:"",
                )
            }
        }

    }

    @Composable
    private fun ReposList(
        reposList: List<Repo>,
        viewState: ReposListViewState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            itemsIndexed(reposList) { _, repo -> RepoItem(repo) }

            item {

                LaunchedEffect(true) { viewModel.loadRepos() }

                if (viewState.isLoading)
                    repeat(3) { PlaceHolderItem() }
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

                    Text(
                        text = repo.ownerName ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )

                    sV(h = 4)
                    Text(
                        text = repo.description ?: "",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start )
                    )
                    sV(h = 8)
                    Row(
                        modifier = Modifier.align(Alignment.Start) ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {

                        if (repo.language.isNullOrBlank().not()) {
                            CircleDot(color = MaterialTheme.colorScheme.onPrimary, size = 12)

                            Text(text = repo.language!!)

                            sH(w = 20)
                        }


                        if (repo.stars!! > 0) {
                            Image(
                                modifier =
                                Modifier
                                    .size(18.dp),
                                painter = painterResource(R.drawable.star),
                                colorFilter = ColorFilter.tint(AppColors.Yellow4),
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
