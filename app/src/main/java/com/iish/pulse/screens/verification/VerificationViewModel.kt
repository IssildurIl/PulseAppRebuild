package com.iish.pulse.screens.verification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.screens.registration.models.RegistrationViewState
import com.iish.pulse.screens.verification.models.VerificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel(), EventHandler<VerificationEvent> {

    var secondOtpCode by mutableStateOf("")
    var thirdOtpCode by mutableStateOf("")
    var fourthOtpCode by mutableStateOf("")

    private val _verificationViewState: MutableLiveData<RegistrationViewState> = MutableLiveData(RegistrationViewState.WaitingForUser)
    val verificationViewState: LiveData<RegistrationViewState> = _verificationViewState

    override fun obtainEvent(event: VerificationEvent) {
        TODO("Not yet implemented")
    }

}
