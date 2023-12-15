package com.sendiko.daggerhiltretrofitcompose.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sendiko.daggerhiltretrofitcompose.helper.FailedState
import com.sendiko.daggerhiltretrofitcompose.network.ApiService
import com.sendiko.daggerhiltretrofitcompose.network.responses.GetConcertsResponse
import com.sendiko.daggerhiltretrofitcompose.network.responses.GetTicketsResponse
import com.sendiko.daggerhiltretrofitcompose.network.responses.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private fun getTickets(){
        _state.update { it.copy(isLoading = true) }
        val request = apiService.getTickets()
        request.enqueue(
            object : Callback<GetTicketsResponse>{
                override fun onResponse(
                    call: Call<GetTicketsResponse>,
                    response: Response<GetTicketsResponse>
                ) {
                    Log.i("ISLOADING", "isLoading: ${state.value.isLoading}")
                    _state.update { it.copy(isLoading = false) }
                    when(response.code()){
                        200 -> {
                            response.body()?.data.let { tickets ->
                                _state.update {
                                    it.copy(tickets = tickets!!)
                                }
                            }
                        }
                        else -> _state.update {
                            it.copy(
                                failedState = FailedState(
                                    isFailed = false,
                                    failedMessage = "Server error."
                                )
                            )
                        }
                    }
                }

                override fun onFailure(
                    call: Call<GetTicketsResponse>,
                    t: Throwable
                ) {
                    _state.update {
                        it.copy(
                            failedState = FailedState(
                                isFailed = false,
                                failedMessage = t.message.toString()
                            ),
                            isLoading = false
                        )
                    }
                }

            }
        )
    }

    private fun getConcerts(){
        _state.update { it.copy(isLoading = true) }
        val request = apiService.getConcerts()
        request.enqueue(
            object : Callback<GetConcertsResponse>{
                override fun onResponse(
                    call: Call<GetConcertsResponse>,
                    response: Response<GetConcertsResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when(response.code()){
                        200 -> {
                            response.body()?.data.let { concerts ->
                                _state.update {
                                    it.copy(concerts = concerts!!)
                                }
                            }
                        }
                        else -> _state.update {
                            it.copy(
                                failedState = FailedState(
                                    isFailed = false,
                                    failedMessage = "Server error."
                                )
                            )
                        }
                    }
                }

                override fun onFailure(
                    call: Call<GetConcertsResponse>,
                    t: Throwable
                ) {
                    _state.update {
                        it.copy(
                            failedState = FailedState(
                                isFailed = false,
                                failedMessage = t.message.toString()
                            )
                        )
                    }
                }

            }
        )
    }

    fun onEvent(event: HomeScreenEvents){
        when(event){
            HomeScreenEvents.OnConcertLoad -> getConcerts()
            HomeScreenEvents.OnTicketsLoad -> getTickets()
        }
    }
}