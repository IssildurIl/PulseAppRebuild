package com.iish.pulse.screens.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.screens.registration.models.RegistrationEvent
import com.iish.pulse.screens.registration.models.RegistrationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel(), EventHandler<RegistrationEvent> {

    private val _registrationViewState: MutableLiveData<RegistrationViewState> = MutableLiveData(RegistrationViewState.WaitingForUser)
    val registrationViewState: LiveData<RegistrationViewState> = _registrationViewState

    override fun obtainEvent(event: RegistrationEvent) {
        TODO("Not yet implemented")
    }

}
