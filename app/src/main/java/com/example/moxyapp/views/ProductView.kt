package com.example.moxyapp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.moxyapp.models.ProductsList
import retrofit2.Response

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ProductView: MvpView {
    fun retrofitCreate()
    fun setAdapter()
    fun loadList()
    fun forEach(response: Response<ProductsList>)
    fun showError(message: String)
    fun showError(message: Int)
    fun showSuccess()
}