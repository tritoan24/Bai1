package com.ph32395.bai1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ph32395.bai1.R


class FragmentA : Fragment() {
    //    onCreateView duoc goi de tao va tra ve giao dien nguoi dung lien ket voi Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FragmentAContent()
            }
        }
    }

    @Composable
    fun FragmentAContent() {
        Button(onClick = {
            // Replace FragmentA with FragmentB
//            Bat dau 1 giao dich Fragment
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<FragmentB>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        },
            modifier = Modifier.width(100.dp).height(80.dp)
        ) {
            Text("Go to Fragment B")
        }
    }
}