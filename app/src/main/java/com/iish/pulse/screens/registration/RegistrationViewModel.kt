package com.iish.pulse.screens.registration

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.screens.registration.models.RegistrationEvent
import com.iish.pulse.screens.registration.models.RegistrationViewState
import com.iish.pulse.utils.Country
import com.iish.pulse.utils.getCountriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel(), EventHandler<RegistrationEvent> {


    var name by mutableStateOf("")
    var isPublicNameFieldError by mutableStateOf(true)

    var email by mutableStateOf("")
    var isEmailFieldError by mutableStateOf(true)
    private val emailRegEx = "^(.+)@(\\S+)$".toRegex()

    var phone by mutableStateOf("")

    var password by mutableStateOf("")
    var passwordReply by mutableStateOf("")
    var isPasswordsSame by mutableStateOf(true)

    val pickedImage = mutableStateOf<Uri?>(null)


    val countriesList = getCountriesList()
    var mobileCountry by mutableStateOf(Country("ru", "7", "Russian Federation"))

    private val _registrationViewState: MutableLiveData<RegistrationViewState> = MutableLiveData(RegistrationViewState.WaitingForUser)
    val registrationViewState: LiveData<RegistrationViewState> = _registrationViewState


    override fun obtainEvent(event: RegistrationEvent) {
        when (val currentState = _registrationViewState.value) {
            is RegistrationViewState.WaitingForUser -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: RegistrationEvent, currentState: RegistrationViewState.WaitingForUser) {
        when (event) {
            RegistrationEvent.onRegistrationClicked -> registerUser()
        }
    }

    private fun registerUser() {
//        _registrationViewState.postValue(RegistrationViewState.Loading)
        checkFields()
        viewModelScope.launch {

        }
    }

    private fun checkFields() {
        isPublicNameFieldError = name.isNotEmpty()
        isEmailFieldError = email.isNotEmpty() && email.matches(emailRegEx)
            isPasswordsSame = password.isNotEmpty()&&passwordReply.isNotEmpty()&&(password == passwordReply)

    }

}
