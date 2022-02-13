package ru.geekbrains.cities_kotlin

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.cities_kotlin.CoatOfArmsFragment.Companion.ARG_INDEX

class CoatOfArmsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coatofarms)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.coat_of_arms_fragment_container, CoatOfArmsFragment.newInstance(intent.extras!!.getInt(ARG_INDEX)))
                .commit()
    }
}