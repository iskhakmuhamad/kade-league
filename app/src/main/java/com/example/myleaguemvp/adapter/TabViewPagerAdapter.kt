@file:Suppress("DEPRECATION")

package com.example.myleaguemvp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private var listFragment = arrayListOf<Fragment>()
    private var listTitle = arrayListOf<String>()

    fun setData(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    override fun getItem(position: Int): Fragment = listFragment[position]
    override fun getCount(): Int = listFragment.size
    override fun getPageTitle(position: Int) = listTitle[position]
}