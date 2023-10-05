package com.aktofredy.anilist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aktofredy.anilist.databinding.ActivityMainBinding
import com.aktofredy.anilist.home.HomeFragmentDirections
import com.aktofredy.anilist.settings.SettingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val navView: BottomNavigationView = binding.navBotView
        val navController = findNavController(R.id.nav_host_fragment_main)

        navController.addOnDestinationChangedListener { _, destinationRoute, _ ->
            if (destinationRoute.id == R.id.detailActivity || destinationRoute.id == R.id.searchFragment) {
                binding.navBotView.visibility = View.GONE
                state = false
            } else {
                state = true
                binding.navBotView.visibility = View.VISIBLE
            }
        }

        val appbarConf = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_favorite
            )
        )

        setupActionBarWithNavController(navController, appbarConf)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (state) {
            menuInflater.inflate(R.menu.home_app_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search_btn -> {
                val dir = HomeFragmentDirections.actionNavHomeToSearchFragment()
                findNavController(R.id.nav_host_fragment_main).navigate(dir)
                Toast.makeText(this, "WOWOW", Toast.LENGTH_SHORT).show()
            }
            R.id.settings_btn -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}