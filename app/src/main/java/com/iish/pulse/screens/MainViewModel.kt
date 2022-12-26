package com.iish.pulse.screens

import androidx.lifecycle.ViewModel
import com.iish.pulse.domain.repository.ApiHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel() {


}