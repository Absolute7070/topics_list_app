package com.example.listofcourses.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceIdTopicName: Int,
    val numberOfCourse: Int,
    @DrawableRes val imageResourceId: Int
)
