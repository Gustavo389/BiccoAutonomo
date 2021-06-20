package com.example.abalateral

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.bicco.autonomo.HomeScreen.HomeFragment
import com.bicco.autonomo.R
import com.google.android.material.navigation.NavigationView

class MainBcc : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawer: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainbcc)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close

        )
        with(drawer) {
            this?.addDrawerListener(toggle)
        }

        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                PerfilFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_perfil)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_perfil -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                PerfilFragment()
            ).commit()
            R.id.nav_anuncios -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AnunciosFragment()
            ).commit()
            R.id.nav_assinatura -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AssinaturaFragment()
            ).commit()
            R.id.nav_notificacoes -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                NotificacoesFragment()
            ).commit()
            R.id.nav_home-> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                HomeFragment()
            ).commit()
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

private fun FragmentTransaction.replace(
    fragmentContainer: Int,
    mainBcc: MainBcc
): FragmentTransaction {
    TODO("Not yet implemented")
}
