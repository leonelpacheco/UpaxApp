package com.leonel.upaxapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.leonel.upaxapp.R


class ChangeFragment {

    companion object{
    fun change(fragment: Fragment, fragmentManager: FragmentManager) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

        fun changewhitData(fragment: Fragment, fragmentManager: FragmentManager, bundle:Bundle) {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragment.arguments = bundle
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }

    }
}