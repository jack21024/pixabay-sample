package com.jack.sample.pixabay.home.ui.viewcontroller

import android.widget.ImageView
import com.jack.baselibrary.viewcontroller.BaseViewController
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle

class MediumSwitchController(
    private val imageView: ImageView,
    private var status: MediumLayoutStyle = MediumLayoutStyle.LIST,
    private val onClicked: ((status: MediumLayoutStyle) -> Unit)? = null
) : BaseViewController<MediumLayoutStyle>(imageView) {

    init {
        imageView.setOnClickListener {
            status = if(status == MediumLayoutStyle.LIST) {
                MediumLayoutStyle.GRID
            } else {
                MediumLayoutStyle.LIST
            }
            imageView.setImageResource(status.iconRes)
            onClicked?.invoke(status)
        }
    }

    override fun update(data: MediumLayoutStyle) {
        status = data
    }

}