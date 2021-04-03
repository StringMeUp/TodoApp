package com.string.me.up.todohiltassignment.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.string.me.up.todohiltassignment.ui.details.DetailsFragment

class PagerAdapter(detailsFragment: DetailsFragment) : FragmentStateAdapter(detailsFragment) {

    private val arrayList: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addFragment(fragment: Fragment) {
        arrayList.add(fragment)
    }

    override fun createFragment(position: Int): Fragment {
        return arrayList[position]
    }
}