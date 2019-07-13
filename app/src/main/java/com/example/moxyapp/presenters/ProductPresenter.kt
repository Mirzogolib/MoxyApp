package com.example.moxyapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.moxyapp.models.ProductsList
import com.example.moxyapp.views.ProductView
import retrofit2.Response

@InjectViewState
class ProductPresenter : MvpPresenter<ProductView>() {
    fun creatingRetrofitAndAdapter() {
        viewState.retrofitCreate()
        viewState.setAdapter()
    }

    fun loadingList() {
        viewState.loadList()
    }

    fun onResponse(response: Response<ProductsList>) {
        if (response.code() == 200) {
            viewState.forEach(response)
            viewState.showSuccess()
        } else {
            viewState.showError("Empty list of Product")
        }
    }

    fun onFailure(t: Throwable) {
        viewState.showError(t.message.toString())
    }




}