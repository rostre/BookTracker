package ro.twodoors.booknotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = host.navController
        NavigationUI.setupWithNavController(bottom_nav_view, navController)
        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.add,
                R.id.addBookNotesFragment,
                R.id.bookNotesFragment,
                R.id.viewNotesFragment,
                R.id.search-> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav_view.itemIconTintList = null
        bottom_nav_view.setupWithNavController(navController)
    }

    private fun showBottomNav(){
        bottom_nav_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav(){
        bottom_nav_view.visibility = View.GONE
    }

}
