package com.example.app.list

data class ReposListViewState(
    val isLoading : Boolean  = false,
    val isLoadingLocal : Boolean  = false,
    val hasNoMoreLocaleData : Boolean  = false,
    val page : Int = 1 ,
    val snackBarMessage : Any? = null,
    val hasLoadedAllData : Boolean = false,
    val isApiUnreachable : Boolean = false,
)
