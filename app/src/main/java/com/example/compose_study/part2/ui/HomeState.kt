package com.example.compose_study.part2.ui

import android.content.Intent
import com.example.compose_study.ui.ContentActivity
import com.example.compose_study.ui.MainActivity

class HomeState(val activity: MainActivity) {
    fun showContent(index: Int) {
        activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
            putExtra("id", index)
        })
    }
}