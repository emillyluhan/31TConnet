package com.example.tutorconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.tutorconnect.fragments.AcademicProgressFragment
import com.example.tutorconnect.fragments.FavoritesFragment

class StudentProfileActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)

        // Configurar información del estudiante
        setupStudentInfo()

        // Configurar navegación
        setupNavigation()

        // Configurar botón de cerrar sesión
        findViewById<TextView>(R.id.btnLogout).setOnClickListener {
            // TODO: Implementar cierre de sesión
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setupStudentInfo() {
        findViewById<TextView>(R.id.txtAge).text = "22 años"
        findViewById<TextView>(R.id.txtProgram).text = "Ingeniería de Sistemas"
        findViewById<TextView>(R.id.txtFavoriteSubjects).text = "Materias favoritas: Álgebra Lineal, Diferencial"
        findViewById<TextView>(R.id.txtProgramming).text = "Programación: Java, Cálculo Diferencial"
    }

    private fun setupNavigation() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Configurar ViewPager
        viewPager.adapter = ProfilePagerAdapter(this)

        // Conectar TabLayout con ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Progreso académico"
                1 -> "Favoritos"
                else -> null
            }
        }.attach()
    }
}

class ProfilePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AcademicProgressFragment()
            1 -> FavoritesFragment()
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}
