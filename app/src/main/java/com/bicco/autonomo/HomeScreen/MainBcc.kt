package com.example.abalateral

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.bicco.autonomo.Form.FormLogin
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.HomeScreen.HomeFragment
import com.bicco.autonomo.HomeScreen.Identificacao
import com.bicco.autonomo.R
import com.bicco.autonomo.R.id.*
import com.google.android.material.navigation.NavigationView


class MainBcc : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawer: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainbcc)
        val toolbar = findViewById<Toolbar>(toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(drawer_layout)
        val navigationView = findViewById<NavigationView>(nav_view)
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
                fragment_container,
                HomeFragment()
            ).commit()
            navigationView.setCheckedItem(nav_home)
        }

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            nav_home -> supportFragmentManager.beginTransaction().replace(
                fragment_container,
                HomeFragment()
            ).commit()
            nav_perfil -> supportFragmentManager.beginTransaction().replace(
                fragment_container,
                PerfilFragment()
            ).commit()
            nav_anuncios -> supportFragmentManager.beginTransaction().replace(
                fragment_container,
                AnunciosFragment()
            ).commit()
            nav_assinatura -> supportFragmentManager.beginTransaction().replace(
                fragment_container,
                AssinaturaFragment()
            ).commit()
            nav_notificacoes -> supportFragmentManager.beginTransaction().replace(
                fragment_container,
                NotificacoesFragment()
            ).commit()


        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        //  store the menu to var when creating options menu
        var optionsMenu = menu;
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("RestrictedApi")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.excluir -> {
                val policy = ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                Biccorequests.deletar(
                    "https://bicco-api.herokuapp.com/deletar/autonomo",
                    Identificacao.identificacao
                )
                val launchNewIntent = Intent(this, FormLogin::class.java)
                startActivityForResult(launchNewIntent, 0)
                true
            }


            else -> false
        }

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
