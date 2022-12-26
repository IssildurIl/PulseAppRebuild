package com.iish.pulse.screens.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.screens.registration.models.RegistrationEvent
import com.iish.pulse.screens.registration.models.RegistrationViewState
import com.iish.pulse.utils.Country
import com.iish.pulse.utils.getCountriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel(), EventHandler<RegistrationEvent> {


    var firstName by mutableStateOf("")
    var isNameValid by mutableStateOf(false)
    var firstNameFieldError by mutableStateOf("")


    var phone by mutableStateOf("")
    var isPhoneValid by mutableStateOf(false)
    var phoneFieldError by mutableStateOf(false)

    var isPasswordSame by mutableStateOf(false)

    var isEmailValid by mutableStateOf(false)

    var isEnabledRegBtn by mutableStateOf(false)


    var email by mutableStateOf("")

    var password by mutableStateOf("")
    var passwordReply by mutableStateOf("")
    val countriesList = getCountriesList()
    var mobileCountry by mutableStateOf(Country("ru", "7", "Russian Federation"))

    private val _registrationViewState: MutableLiveData<RegistrationViewState> = MutableLiveData(RegistrationViewState.WaitingForUser)
    val registrationViewState: LiveData<RegistrationViewState> = _registrationViewState

    override fun obtainEvent(event: RegistrationEvent) {
        TODO("Not yet implemented")
    }

}
