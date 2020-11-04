package com.example.myleaguemvp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.adapter.TabViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager_fav)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs_fav)

        val adapter = TabViewPagerAdapter(childFragmentManager)
        adapter.setData(FavPastFragment(), "PAST MATCH")
        adapter.setData(FavNextFragment(), "NEXT MATCH")
        adapter.setData(FavTeamFragment(), "TEAM")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


}
