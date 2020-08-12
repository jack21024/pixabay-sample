package com.jack.sample.pixabay.home.enums

import androidx.annotation.DrawableRes
import com.jack.sample.pixabay.R

enum class MediumLayoutStyle(@DrawableRes val iconRes: Int) {
    LIST(R.drawable.ic_view_headline_black_24dp),
    GRID(R.drawable.ic_grid_on_black_24dp)
}