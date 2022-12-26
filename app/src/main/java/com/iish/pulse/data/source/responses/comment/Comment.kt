package com.iish.pulse.data.source.responses.comment

import com.iish.pulse.data.source.responses.UserAbility
import com.iish.pulse.data.source.responses.UserEntityProfile

data class Comment(
    val user: UserEntityProfile,
    val comment: CommentStructure,
    val userAbility: UserAbility
) {
    var animation = true
}

data class CommentList(
    val commentList:MutableList<Comment>
)