package com.iish.pulse.data.source.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageSettings(
    var id: String,
    val url: String,
    var width: Int,
    var height: Int
) : Parcelable {

//    fun getSize(): Int {
//        if (width == 0 && height == 0) {
//            return L
//        }
//        if (width <= 75 && height <= 75) {
//            return A
//        }
//        if ((width <= 480 && height < 480) || (width < 480 && height <= 480)) {
//            return S
//        }
//        if ((width <= 720 && height < 720) || (width < 720 && height <= 720)) {
//            return M
//        }
//        if ((width <= 1200 && height < 1200) || (width < 1200 && height <= 1200)) {
//            return M
//        }
//        return O
//    }
}