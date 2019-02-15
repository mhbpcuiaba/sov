package br.com.mhbpit.sov.activemodel.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.mhbpit.sov.MainActivity
import br.com.mhbpit.sov.R
import br.com.mhbpit.sov.activemodel.AboutActivity
import br.com.mhbpit.sov.activemodel.profile.ProfileActivity
import br.com.mhbpit.sov.activemodel.resume.ResumeActivity
import br.com.mhbpit.sov.util.SaveSharedPreference
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by suporte on 13/01/2018.
 */
//https://github.com/googlesamples/android-architecture-components/tree/master/PagingWithNetworkSample
//class HomeActivity : AppCompatActivity() {
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawer_layout.addDrawerListener(toggle)
        drawer_layout.nav_view.setNavigationItemSelectedListener(this)
        toggle.syncState()

        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .add(R.id.screen_container, ListFragment())
//                    .commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        Log.i("nav", "AKi ESTOU")
        Log.d("nav", "AKi ESTOU")
        if (item.itemId == R.id.menu_resume) {
            val intent = Intent(this, ResumeActivity::class.java)
            startActivity(intent)

        } else if (item.itemId == R.id.menu_resume) {
            val intent = Intent(this, ResumeActivity::class.java)
            startActivity(intent)

        } else if (item.itemId == R.id.menu_starred) {
            Toast.makeText(this, "menu favorito", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this, "clico no item", Toast.LENGTH_SHORT).show()
        return true;
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }
    //
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.resume) {
            val intent = Intent(this, ResumeActivity::class.java)
            startActivity(intent)
        }

        if(item?.itemId == R.id.profile_menu) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }

        if(item?.itemId == R.id.about_menu) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)

        }

        if (item?.itemId == R.id.menu_resume) {
            val intent = Intent(this, ResumeActivity::class.java)
            startActivity(intent)
        }

        if (item?.itemId == R.id.logout_menu) {
            mAuth.signOut()
            SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        return super.onOptionsItemSelected(item)
    }

}