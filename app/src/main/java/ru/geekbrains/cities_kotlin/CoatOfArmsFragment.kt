package ru.geekbrains.cities_kotlin

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class CoatOfArmsFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coatofarms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(this.arguments != null) {
            val index = this.arguments?.getInt(ARG_INDEX)
            val imageCoatOfArms: ImageView = view.findViewById(R.id.coat_of_arms_image_view)
            val images: TypedArray = resources.obtainTypedArray(R.array.coat_of_arms_imgs)
            imageCoatOfArms.setImageResource(images.getResourceId(index!!, 0))
            images.recycle()
        }
    }

    companion object {
        const val ARG_INDEX = "index"

        fun newInstance(index: Int) = CoatOfArmsFragment().apply{
            arguments = Bundle().apply {
                putInt(ARG_INDEX, index)
            }
        }
    }
}