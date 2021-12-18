package com.example.yarmarka.utils

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.yarmarka.R

lateinit var fm: FragmentManager
var bundle: Bundle? = Bundle()

//lateinit var skills: List<Int>

val consideration_state = R.drawable.state_consideration
val consideration_color = R.color.orange

val participation_state = R.drawable.state_participation
val participation_color = R.color.green

val finished_state = R.drawable.state_finished
val finished_color = R.color.light_grey

val declined_state = R.drawable.state_declined
val declined_color = R.color.black

val recalled_state = R.drawable.state_recalled
val recalled_color = R.color.main_blue

val excluded_state = R.drawable.state_excluded
val excluded_color = R.color.brown