package com.iish.pulse.screens.verification

import androidx.lifecycle.ViewModel
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.screens.verification.models.VerificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel(), EventHandler<VerificationEvent> {

    override fun obtainEvent(event: VerificationEvent) {
        TODO("Not yet implemented")
    }

}
