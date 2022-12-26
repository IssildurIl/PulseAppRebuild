package com.iish.pulse.screens.main_screen.model

sealed class MainEvent {
    object LoadMap: MainEvent()
    object Searching: MainEvent()
    object MapPointClicked: MainEvent()
}