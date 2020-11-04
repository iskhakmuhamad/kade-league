package com.example.myleaguemvp.Util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myleaguemvp.R

class FragmentUtil {

    fun loadFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        val frTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        frTransaction.replace(R.id.container_layout, fragment)
        frTransaction.addToBackStack(null)
        frTransaction.commit()
    }
}