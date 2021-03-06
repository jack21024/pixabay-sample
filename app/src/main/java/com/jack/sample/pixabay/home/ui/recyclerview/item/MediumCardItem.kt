package com.jack.sample.pixabay.home.ui.recyclerview.item

import com.jack.baselibrary.recyclerview.item.BaseCardItem
import com.jack.sample.pixabay.home.data.entity.PixabayImage

data class MediumCardItem(
    override val id: Int,
    val userName: String? = "",
    val thumbnailUrl: String? = "",
    val thumbnailWidth: Int = 0,
    val thumbnailHeight: Int = 0,
    val imageUrl: String? = "",
    val imageWidth: Int = 0,
    val imageHeigh: Int = 0,
    val likes: Int = 0,
    val stars: Int = 0,
    val comments: Int = 0
): BaseCardItem(id) {

    constructor(image: PixabayImage): this(
        id = image.id,
        userName = image.user,
        thumbnailUrl = image.previewURL,
        thumbnailWidth = image.previewWidth,
        thumbnailHeight = image.previewHeight,
        imageUrl = image.webformatURL,
        imageWidth = image.webformatWidth,
        imageHeigh = image.webformatHeight,
        likes = image.likes,
        stars = image.favorites,
        comments = image.comments
    )

}