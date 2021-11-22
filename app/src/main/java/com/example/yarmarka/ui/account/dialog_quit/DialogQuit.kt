package com.example.yarmarka.ui.account.dialog_quit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.yarmarka.R
import android.content.SharedPreferences

import android.preference.PreferenceManager




class DialogQuit(private val onDialogClickedListener: OnDialogClickedListener): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.dialog_account_quit, container)

        rootView.findViewById<Button>(R.id.btnYes).setOnClickListener {


            onDialogClickedListener.onYesClicked()
            dismiss()
        }

        rootView.findViewById<Button>(R.id.btnNo).setOnClickListener {
            dismiss()
        }

        return rootView
    }
}