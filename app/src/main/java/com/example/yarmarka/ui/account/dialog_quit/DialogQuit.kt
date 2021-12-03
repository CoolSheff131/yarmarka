package com.example.yarmarka.ui.account.dialog_quit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.DialogAccountQuitBinding

class DialogQuit(private val onDialogClickedListener: OnQuitDialogClickedListener): DialogFragment() {

    private val binding by viewBinding(DialogAccountQuitBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_account_quit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYes.setOnClickListener {
            onDialogClickedListener.onYesClicked()
            dismiss()
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }
}