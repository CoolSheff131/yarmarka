package com.example.yarmarka.utils

fun convertDate(rawDate: String?): String {
    if (rawDate == null) return ""
    return rawDate.split("-")[2] + "." +
            rawDate.split("-")[1] + "." +
            rawDate.split("-")[0]
}

fun addZeroToDate(rawDate: Int): String {
    return if (rawDate / 10 == 0) {
        "0$rawDate"
    } else {
        "$rawDate"
    }
}