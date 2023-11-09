package com.example.infobyteassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        supportActionBar?.hide()

        val bottomView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        //by default home page should be visible
        replaceWithFragment(WatchListFragment())

        bottomView.setOnItemReselectedListener{

            when(it.itemId){

                R.id.watchlist -> replaceWithFragment(WatchListFragment())


//                else ->{
//                    Toast.makeText(this,"Sorry for error", Toast.LENGTH_SHORT).show()
//                }

            }
            true
        }
    }

    private fun replaceWithFragment(fragment: Fragment) {

        val fragmentManger = supportFragmentManager
        val fragmentTransaction = fragmentManger.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}