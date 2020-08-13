package com.jack.sample.pixabay.home.ui.viewcontroller

import android.widget.ImageView
import com.jack.baselibrary.viewcontroller.BaseViewController
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle

class MediumSwitchController(
    private val imageView: ImageView,
    initialStyle: MediumLayoutStyle = MediumLayoutStyle.LIST,
    private val onClicked: ((status: MediumLayoutStyle) -> Unit)? = null
) : BaseViewController<MediumLayoutStyle>(imageView) {

    private val layoutStyle: MediumLayoutStyle
        get() = if (imageView.isSelected) {
            MediumLayoutStyle.GRID
        } else {
            MediumLayoutStyle.LIST
        }

    init {
        imageView.setOnClickListener {
            it.isSelected = !it.isSelected
            onClicked?.invoke(layoutStyle)
        }
        update(initialStyle)
    }

    override fun update(data: MediumLayoutStyle) {
        if(data != layoutStyle) {
            imageView.performClick()
        }
    }
}