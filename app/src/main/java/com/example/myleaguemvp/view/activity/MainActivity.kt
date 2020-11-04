package com.example.myleaguemvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.FragmentUtil
import com.example.myleaguemvp.view.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        setSupportActionBar(toolbar)
        tv_title.text = getString(R.string.txt_list_of_leagues)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_league -> {
                    tv_title.text = getString(R.string.txt_list_of_leagues)
                    FragmentUtil().loadFragment(LeaguesFragment(), supportFragmentManager)
                }
                R.id.menu_team -> {
                    tv_title.text = getString(R.string.txt_list_of_team)
                    FragmentUtil().loadFragment(TeamFragment(), supportFragmentManager)
                }
                R.id.menu_standing -> {
                    tv_title.text = getString(R.string.txt_klasemen)
                    FragmentUtil().loadFragment(StandingsFragment(), supportFragmentManager)
                }
                R.id.menu_match -> {
                    tv_title.text = getString(R.string.txt_list_of_leagues_event)
                    FragmentUtil().loadFragment(MatchFragment(), supportFragmentManager)
                }
                R.id.menu_favorite -> {
                    tv_title.text = getString(R.string.txt_btm_favorite_title)
                    FragmentUtil().loadFragment(FavoriteFragment(), supportFragmentManager)
                }
            }
            true
        }
        bottomNavigation.selectedItemId = R.id.menu_league
    }
}
