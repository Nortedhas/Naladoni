package com.ageone.naladoni.Internal.Utilities

import com.ageone.naladoni.R

fun getIdCategoryIcon(serialNum: Int): Int =  when(serialNum) {
    0 -> R.drawable.ic_category_0
    1 -> R.drawable.ic_category_1
    2 -> R.drawable.ic_category_2
    3 -> R.drawable.ic_category_3
    4 -> R.drawable.ic_category_4
    5 -> R.drawable.ic_category_5
    6 -> R.drawable.ic_category_6
    7 -> R.drawable.ic_category_7
    8 -> R.drawable.ic_category_8
    9 -> R.drawable.ic_category_9
    10 -> R.drawable.ic_category_10
    11 -> R.drawable.ic_category_11
    else -> R.drawable.ic_category_12
}

fun getIdCategoryFlag(serialNum: Int, isSelested: Boolean = false): Int = if (isSelested) {
    when(serialNum) {
        0 -> R.drawable.pic_flag_0
        1 -> R.drawable.pic_flag_1
        2 -> R.drawable.pic_flag_2
        3 -> R.drawable.pic_flag_3
        4 -> R.drawable.pic_flag_4
        5 -> R.drawable.pic_flag_5
        6 -> R.drawable.pic_flag_6
        7 -> R.drawable.pic_flag_7
        8 -> R.drawable.pic_flag_8
        9 -> R.drawable.pic_flag_9
        10 -> R.drawable.pic_flag_10
        11 -> R.drawable.pic_flag_11
        else -> R.drawable.pic_flag_12
    }
} else {
    when(serialNum) {
        0 -> R.drawable.pic_flag_selected_0
        1 -> R.drawable.pic_flag_selected_1
        2 -> R.drawable.pic_flag_selected_2
        3 -> R.drawable.pic_flag_selected_3
        4 -> R.drawable.pic_flag_selected_4
        5 -> R.drawable.pic_flag_selected_5
        6 -> R.drawable.pic_flag_selected_6
        7 -> R.drawable.pic_flag_selected_7
        8 -> R.drawable.pic_flag_selected_8
        9 -> R.drawable.pic_flag_selected_9
        10 -> R.drawable.pic_flag_selected_10
        11 -> R.drawable.pic_flag_selected_11
        else -> R.drawable.pic_flag_selected_12
    }
}