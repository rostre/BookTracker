package ro.twodoors.booknotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.api.RetrofitFactory

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = host.navController
        NavigationUI.setupWithNavController(bottom_nav_view, navController)
        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.add -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav_view.setupWithNavController(navController)
    }

    private fun showBottomNav(){
        bottom_nav_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav(){
        bottom_nav_view.visibility = View.GONE
    }

//    private fun searchByTitle(title: String) {
//        val job = Job()
//        val coroutineScope = CoroutineScope(job + Dispatchers.Main)
//        coroutineScope.launch {
//            val resultList = RetrofitFactory().searchByTitle(title)
//            bookList.adapter = BookAdapter(resultList)
//            //val coverId = resultList.docs?.get(1)?.coverEditionKey
//            //Picasso.get().load("https://covers.openlibrary.org/b/olid/" + coverId +"-L.jpg?default=false").into(imageView)
//            Log.d(TAG, "$resultList")
//        }
//    }

    private fun getCoverUrl(coverId: String) : String{
        return "https://covers.openlibrary.org/b/olid/" + coverId +"-L.jpg?default=false"
    }


}
