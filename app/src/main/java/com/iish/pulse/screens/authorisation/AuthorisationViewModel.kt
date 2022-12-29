package com.iish.pulse.screens.authorisation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.data.features.user.UserRepository
import com.iish.pulse.data.source.request.AuthUser
import com.iish.pulse.data.source.responses.UserAuth
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.domain.repository.UserHelper
import com.iish.pulse.screens.authorisation.model.AuthorisationEvent
import com.iish.pulse.screens.authorisation.model.AuthorisationViewState
import com.iish.pulse.utils.Country
import com.iish.pulse.utils.Resource
import com.iish.pulse.utils.Utils.handler
import com.iish.pulse.utils.Utils.md5
import com.iish.pulse.utils.getCountriesList
import com.iish.pulseapprebuild.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorisationViewModel @Inject constructor(
    private val userHelper: UserHelper,
    private val userRepository: UserRepository
) : ViewModel(), EventHandler<AuthorisationEvent> {

    var phone by mutableStateOf("")
    var password by mutableStateOf("")
    val countriesList = getCountriesList()
    var mobileCountry by mutableStateOf(Country("ru", "7", "Russian Federation"))

    private val _authorisationViewState: MutableLiveData<AuthorisationViewState> = MutableLiveData(AuthorisationViewState.WaitingForUser)
    val authorisationViewState: LiveData<AuthorisationViewState> = _authorisationViewState

    private val _user: MutableLiveData<Resource<UserAuth>> = MutableLiveData()
    val user: LiveData<Resource<UserAuth>> = _user

    init {
        isUserRegistered()
    }


    override fun obtainEvent(event: AuthorisationEvent) {
        when (val currentState = _authorisationViewState.value) {
            is AuthorisationViewState.WaitingForUser -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: AuthorisationEvent, currentState: AuthorisationViewState.WaitingForUser) {
        when (event) {
            AuthorisationEvent.OnAuthClickedScreen -> authoriseUser()
        }
    }



    private fun authoriseUser() {
        _authorisationViewState.postValue(AuthorisationViewState.Loading)
        viewModelScope.launch {
            val login = "+${mobileCountry.code + phone}"
            val password = md5(password)
            val userData = userHelper.authUser(AuthUser(login = login, password = password))
            if (userData.isSuccessful) {
                _user.postValue(handler(userData))
                loadDataToDB(login, password)
                _authorisationViewState.postValue(AuthorisationViewState.Success)
            } else {
                _authorisationViewState.postValue(AuthorisationViewState.Error)
            }
            this.cancel()
        }
    }

    private suspend fun loadDataToDB(login: String, password: String) {
        val user = userRepository.fetchUserData().first()
        if (user.login == login) {
            userRepository.addOrUpdate(login = "+${mobileCountry.code + phone}", password = md5(password))
        }
    }

    private fun isUserRegistered(){
        viewModelScope.launch {
            val users = userRepository.fetchUserData()
            if(users.isNotEmpty()){
                _authorisationViewState.postValue(AuthorisationViewState.Success)
            }
        }
    }
}