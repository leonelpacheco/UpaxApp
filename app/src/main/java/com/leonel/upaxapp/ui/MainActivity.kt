package com.leonel.upaxapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.leonel.upaxapp.R
import com.leonel.upaxapp.databinding.ActivityMainBinding
import com.leonel.upaxapp.ui.dataempleado.EmpleadoFragment
import com.leonel.upaxapp.ui.datanegocio.DataNegocioFragment
import com.leonel.upaxapp.ui.imagestore.ImageStoreFragment
import com.leonel.upaxapp.ui.imagestorelist.ImageStoreListFragment
import com.leonel.upaxapp.utils.ChangeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.appBarMain.toolbar
        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout
        //val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navView.setNavigationItemSelectedListener(this)
       /* val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val fragmentapp: Fragment =
            ImageStoreListFragment()
        ChangeFragment.change(fragmentapp, supportFragmentManager)
    }
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


            R.id.nav_imagestore -> {
                val fragmentapp: Fragment =
                    ImageStoreListFragment()
                ChangeFragment.change(ImageStoreListFragment(), supportFragmentManager)
            }
            R.id.nav_dataempleado -> {
                val fragmentapp: Fragment =
                    EmpleadoFragment()
                ChangeFragment.change(EmpleadoFragment(), supportFragmentManager)
            }
            R.id.nav_negocio -> {
                val fragmentapp: Fragment =
                    DataNegocioFragment()
                ChangeFragment.change(DataNegocioFragment(), supportFragmentManager)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}