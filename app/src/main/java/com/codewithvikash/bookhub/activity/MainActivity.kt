package com.codewithvikash.bookhub.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.codewithvikash.bookhub.R
import com.codewithvikash.bookhub.fragment.DashboardFragment
import com.codewithvikash.bookhub.fragment.AboutAppFragment
import com.codewithvikash.bookhub.fragment.FavouritesFragment
import com.codewithvikash.bookhub.fragment.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
   // private lateinit var toolbar: Toolbar
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView
    var previousMenuItem:MenuItem?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
//        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationViwe)
        //setUpToolbar()

        openDashboard()

        /*val actionBarDrawerToggle = ActionBarDrawerToggle{
            ativity: this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        }*/
        //drawerLayout.addDrawerListener(actionBarDrawerToggle)
        //actionBarDrawerToggle.syncState()
       navigationView.setNavigationItemSelectedListener {
           if(previousMenuItem!=null){
               previousMenuItem?.isChecked=false
           }
           it.isCheckable =true
           it.isChecked=true
           previousMenuItem = it

            when(it.itemId){
                R.id.dashboard ->{
                     supportFragmentManager.beginTransaction()
                         .replace(R.id.frame, DashboardFragment())
                         .addToBackStack("Dashboard")
                         .commit()
                    supportActionBar?.title ="Dashboard"
                         drawerLayout.closeDrawers()
                }
                R.id.favourites ->{

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FavouritesFragment())
                        .addToBackStack("Favourites")
                        .commit()
                    supportActionBar?.title = "Faourites"
                    drawerLayout.closeDrawers()
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
                        .addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()

                }
                R.id.about_app ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutAppFragment())
                        .addToBackStack("AboutApp")
                        .commit()
                    supportActionBar?.title ="About App"
                    drawerLayout.closeDrawers()

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolBar(){
        var toolbar = null
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        val suppportAction = null
        suppportAction?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)

    }
  fun openDashboard(){
      val fragment = DashboardFragment()
      val transaction = supportFragmentManager.beginTransaction()
          .replace(R.id.frame, DashboardFragment())
          .addToBackStack("Dashboard")
          .commit()
      supportActionBar?.title ="Dashboard"
      navigationView.setCheckedItem(R.id.dashboard)
  }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is DashboardFragment ->openDashboard()
            else ->super.onBackPressed()

        }
    }

}

private fun Nothing?.setHomeButtonEnabled(b: Boolean) {


}
