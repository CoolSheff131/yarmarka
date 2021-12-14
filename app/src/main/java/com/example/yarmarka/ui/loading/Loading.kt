package com.example.yarmarka.ui.loading

import android.app.Activity
import android.app.Dialog
import android.os.AsyncTask
import android.view.View
import com.example.yarmarka.MainActivity
import com.example.yarmarka.R
import com.example.yarmarka.ui.my_projects.MyProjectsFragment

class Loading(var activity: Activity) : AsyncTask<Void, Void, Void>() {

    var dialog: Dialog = Dialog(activity, R.style.Theme_Yarmarka)

    override fun onPreExecute() {
        val view: View = activity.layoutInflater.inflate(R.layout.fragment_loading, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()
    }

    override fun doInBackground(vararg result: Void?): Void? {
        Thread.sleep(3000)
        //val mp : MyProjectsFragment
        //mp.loadData()
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        dialog.dismiss()
    }
}