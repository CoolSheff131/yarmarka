package com.example.yarmarka.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(view: View) {

        binding.btnAccountClose.setOnClickListener {
            view.findNavController().popBackStack()
        }
        binding.btnRequests.setOnClickListener {
            sendNotification()
            view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    private val CHANNEL_ID = "channel_id"
    private val notificationId = 101
    private fun sendNotification(){
        val builder = context?.let {
            NotificationCompat.Builder(it,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_account)
                .setContentTitle("Test title")
                .setContentText("Test description")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        if (builder != null) {
            with(context?.let { NotificationManagerCompat.from(it) }){
                this?.notify(notificationId,builder.build())
            }
        }


    }
}