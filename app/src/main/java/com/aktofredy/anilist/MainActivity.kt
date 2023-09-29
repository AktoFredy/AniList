package com.aktofredy.anilist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aktofredy.anilist.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navBotView
        val navController = findNavController(R.id.nav_host_fragment_main)

        navController.addOnDestinationChangedListener { _, destinationRoute, _ ->
            if (destinationRoute.id == R.id.nav_home || destinationRoute.id == R.id.nav_favorite) {
                binding.navBotView.visibility = View.VISIBLE
            } else {
                binding.navBotView.visibility = View.GONE
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
}