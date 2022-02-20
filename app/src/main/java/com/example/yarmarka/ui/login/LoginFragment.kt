package com.example.yarmarka.ui.login

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentLoginBinding
import android.webkit.JavascriptInterface
import android.webkit.WebView

import android.webkit.WebViewClient
import com.example.yarmarka.MainActivity
import android.content.SharedPreferences

class LoginFragment : Fragment() {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences: SharedPreferences =
            activity?.getSharedPreferences("pref", Context.MODE_PRIVATE) as SharedPreferences
        val token = preferences.getString("token","")
        if(token != ""){
            view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            return
        }

        val USER_AGENT_FAKE =
            "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"
        binding.webView.getSettings().setUserAgentString(USER_AGENT_FAKE);
        binding.webView.loadUrl("http://projects.tw1.ru/campus_auth");

        binding.webView.getSettings().setSupportZoom(true);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.addJavascriptInterface(object : Any(){
            @JavascriptInterface
            fun showHTML(token: String?) {
                Log.d("AUTH", "TOKEN "+token)
                val preferences: SharedPreferences =
                    (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: null) as SharedPreferences
                if(preferences != null){
                    var editor = preferences.edit()
                    editor.putString("token",token)
                    editor.commit()

                }


                val handler = Handler(Looper.getMainLooper())
                handler.post(Thread {
                    (activity as MainActivity).runOnUiThread {
                        view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }
                })
            }
        }, "HtmlViewer")
        binding.webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                binding.webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('login-text login-item')[0].style.display='none'; })()");
                if (url != null && url.startsWith("http://projects.tw1.ru/campus_auth?code=")) {
                    binding.webView.loadUrl("javascript:HtmlViewer.showHTML(document.getElementsByTagName('body')[0].innerHTML.match(/(?<={\"token\":\")(.*?)(?=\"})/)[0])")
                }
            }
//            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
//                if (url != null && url.startsWith("http://projects.tw1.ru/campus_auth?code=")) {
//                    binding.webView.loadUrl("javascript:HtmlViewer.showHTML(document.getElementsByTagName('body')[0].innerHTML.match(/(?<={\"token\":\")(.*?)(?=\"})/)[0])")
//                }
//                super.doUpdateVisitedHistory(view, url, isReload)
//            }
        })
        initListeners(view)
    }

    private fun initListeners(view: View) {

//        binding.btnAccountClose.setOnClickListener {
//            view.findNavController().popBackStack()
//        }
//        binding.btnRequests.setOnClickListener {
            //view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)


            //context?.sendBroadcast(Intent(context,MyReceiver::class.java))
            //view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//        }
    }

}
