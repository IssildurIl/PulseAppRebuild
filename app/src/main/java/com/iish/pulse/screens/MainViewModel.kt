package com.iish.pulse.screens

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.iish.pulse.data.features.user.UserRepository
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiHelper: ApiHelper,
    private val userRepository: UserRepository
) : ViewModel() {

}