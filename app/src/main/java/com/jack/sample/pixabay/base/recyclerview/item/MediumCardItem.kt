package com.jack.sample.pixabay.base.recyclerview.item

import com.jack.baselibrary.recyclerview.item.BaseCardItem
import com.jack.sample.pixabay.home.data.entity.PixabayImage

data class MediumCardItem(
    override val id: Int,
    val userName: String? = "",
    val imageUrl: String? = "",
    val likes: Int = 0,
    val stars: Int = 0,
    val comments: Int = 0
): BaseCardItem(id) {

    constructor(image: PixabayImage): this(
        id = image.id,
        userName = image.user,
        imageUrl = image.webformatURL,
        likes = image.likes,
        stars = image.favorites,
        comments = image.comments
    )

}