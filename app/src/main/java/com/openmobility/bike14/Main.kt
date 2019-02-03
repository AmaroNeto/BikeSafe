package com.openmobility.bike14

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import kotlinx.android.synthetic.main.main_activity.*
import android.support.v4.app.Fragment


class Main : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.navigation_1)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when (p0.getItemId()) {
            R.id.navigation_1 -> {
                openFragment(MapFragment.newInstance())
            }
            R.id.navigation_2 -> {
                openFragment(Fragment1.newInstance())
            }
            R.id.navigation_3 -> {
                openFragment(Fragment2.newInstance())
            }
        }
        return true
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}