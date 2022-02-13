package ru.geekbrains.cities_kotlin

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ru.geekbrains.cities_kotlin.CoatOfArmsFragment.Companion.ARG_INDEX

class CitiesFragment: Fragment() {

    private val CURRENT_CITY = "CurrentCity"
    private var currentPosition = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null)
            currentPosition = savedInstanceState.getInt(CURRENT_CITY, 0)
        initList(view)
        if (isLandScape())
            showLandCoatOfArms(currentPosition)
    }

    fun initList(view: View){
        val layout = view as LinearLayout
        val cities = resources.getStringArray(R.array.cities)
        for(element in cities) {
            val tvCity = TextView(context)
            tvCity.textSize = 20f
            tvCity.text = "$element"
            layout.addView(tvCity)
            val position: Int = cities.indexOf(element)
            tvCity.setOnClickListener {
                currentPosition = position
                showCoatOfArms(position)
            }
        }
    }

    fun showCoatOfArms(index: Int) {
        if (isLandScape())
            showLandCoatOfArms(index)
        else
            showPortCoatOfArms(index)
    }


    fun showPortCoatOfArms(index: Int) {
        val activity: Activity = requireActivity()
        val intent = Intent(activity, CoatOfArmsActivity::class.java)
        intent.putExtra(ARG_INDEX, index)
        activity.startActivity(intent)
    }

    fun showLandCoatOfArms(index: Int) {
        val detail = CoatOfArmsFragment.newInstance(index)
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.coat_of_arms_container, detail)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_CITY, currentPosition)
        super.onSaveInstanceState(outState)
    }

    private fun isLandScape(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    companion object {
        fun newInstance() = CitiesFragment()
    }
}