package com.ph32395.bai1.fragment


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ph32395.bai1.R


class ContainerViewActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = MaterialTheme.colors.background
            ) {
                // Add FragmentContainerView
//                AndroidView dung de nhung 1 View Android vao giao dien Jetpack Compose
                androidx.compose.ui.viewinterop.AndroidView({ context ->
                    androidx.fragment.app.FragmentContainerView(context).apply {
//                        Thiet lap id de tham chieu den no tu FragmentManager
                        id = R.id.fragment_container_view
                    }
                })

                // Add Fragment A
//                supportFragmentManager.commit quan ly cac giao dich Fragment
                supportFragmentManager.commit {
//                    Sap xep lai cac giao dich Fragment de cai tien hieu suat
                    setReorderingAllowed(true)
//                    Thay the bat ky Fragment nao hien co trong FragmentContainerView bang FragmentA
                    replace<FragmentA>(R.id.fragment_container_view)
                }
            }
        }
    }
}