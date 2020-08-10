package com.jack.sample.pixabay.home.data


import com.google.gson.annotations.SerializedName

data class PixabayImagesData(
    @SerializedName("total")
    var total: Int?,
    @SerializedName("totalHits")
    var totalHits: Int?,
    @SerializedName("hits")
    var hits: List<ImageData>?
)

data class ImageData(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("pageURL")
    var pageURL: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("tags")
    var tags: String?,
    @SerializedName("previewURL")
    var previewURL: String?,
    @SerializedName("previewWidth")
    var previewWidth: Int?,
    @SerializedName("previewHeight")
    var previewHeight: Int?,
    @SerializedName("webformatURL")
    var webformatURL: String?,
    @SerializedName("webformatWidth")
    var webformatWidth: Int?,
    @SerializedName("webformatHeight")
    var webformatHeight: Int?,
    @SerializedName("largeImageURL")
    var largeImageURL: String?,
    @SerializedName("imageWidth")
    var imageWidth: Int?,
    @SerializedName("imageHeight")
    var imageHeight: Int?,
    @SerializedName("imageSize")
    var imageSize: Int?,
    @SerializedName("views")
    var views: Int?,
    @SerializedName("downloads")
    var downloads: Int?,
    @SerializedName("favorites")
    var favorites: Int?,
    @SerializedName("likes")
    var likes: Int?,
    @SerializedName("comments")
    var comments: Int?,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("user")
    var user: String?,
    @SerializedName("userImageURL")
    var userImageURL: String?
)