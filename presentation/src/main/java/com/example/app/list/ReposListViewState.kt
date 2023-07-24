package com.example.app.list

data class ReposListViewState(
    val isLoading : Boolean  = false,
    val hasNoMoreLocaleData : Boolean  = false,
    val page : Int = 1 ,
    val snackBarMessage : String? = null,
    val hasLoadedAllData : Boolean = false,
    val isApiUnreachable : Boolean = false,
)
