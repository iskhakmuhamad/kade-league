package com.example.myleaguemvp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.adapter.TabViewPagerAdapter
import com.example.myleaguemvp.view.activity.SearchActivity
import com.google.android.material.tabs.TabLayout

class MatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.action_to_search, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs_match)

        val adapter = TabViewPagerAdapter(childFragmentManager)
        adapter.setData(PastMatchFragment(), "PAST MATCH")
        adapter.setData(NextMatchFragment(), "NEXT MATCH")


        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_to_search) {
            startActivity(Intent(activity, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


}
