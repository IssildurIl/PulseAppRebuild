package com.iish.pulse.screens.main_screen

import androidx.lifecycle.ViewModel
import com.iish.pulse.data.base.EventHandler
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.screens.main_screen.model.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    apiHelper: ApiHelper
):ViewModel(),EventHandler<MainEvent>{

    override fun obtainEvent(event: MainEvent) {
        TODO("Not yet implemented")
    }

}