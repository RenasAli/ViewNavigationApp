package com.example.viewnavigation

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.viewnavigation.fragments.AboutFragment
import com.example.viewnavigation.fragments.HomeFragment
import com.example.viewnavigation.fragments.SettingsFragment

import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    val metrics = Resources.getSystem().displayMetrics
    val screenWidth = metrics.widthPixels / metrics.density
    val screenHeight = metrics.heightPixels / metrics.density


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isTablet()
    }

    fun isTablet(){
        if (screenWidth >= 600 && screenHeight >= 600) {
            navigationViewForTablet()
        } else {
          drawerNavigation()
        }
    }


    fun drawerNavigation(){
        drawerLayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView()

    }

    fun navigationView(){
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        replaceFragment(HomeFragment(), "Home")
      navigationView.setNavigationItemSelectedListener {
            it.isChecked = true

            when(it.itemId) {
                R.id.home ->{
                    replaceFragment(HomeFragment(),it.title.toString())

                true
                }
                R.id.settings ->{
                    replaceFragment(SettingsFragment(),it.title.toString())
                true
                }
                R.id.about ->{
                    replaceFragment(AboutFragment(),it.title.toString())
                true
                }

                else -> {

                    true
                }
            }

        }
    }
    private fun replaceFragment(fragment : Fragment, title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    private fun replaceFragmentForTablet(fragment : Fragment, title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        setTitle(title)

    }
    fun navigationViewForTablet(){
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        replaceFragmentForTablet(HomeFragment(), "Home")
        navigationView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId) {
                R.id.home ->{
                    replaceFragmentForTablet(HomeFragment(),it.title.toString())
                    true
                }
                R.id.settings ->{
                    replaceFragmentForTablet(SettingsFragment(),it.title.toString())
                    true
                }
                R.id.about ->{
                    replaceFragmentForTablet(AboutFragment(),it.title.toString())
                    true
                }else -> true
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}