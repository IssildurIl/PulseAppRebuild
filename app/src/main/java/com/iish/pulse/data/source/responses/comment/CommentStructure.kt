package com.iish.pulse.data.source.responses.comment

data class CommentStructure(
    var id: String,
    var countLikes: Int = 0,
    var userId: String,
    var description: String,
    var datePublication: String,
    var publicationId: String?,
    var publicationTypeId: String?,
)