package com.string.me.up.todohiltassignment.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.adapters.PagerAdapter
import com.string.me.up.todohiltassignment.databinding.FragmentDetailsBinding
import com.string.me.up.todohiltassignment.helper.Helper
import com.string.me.up.todohiltassignment.ui.pagerfragments.CompletedTodoFragment
import com.string.me.up.todohiltassignment.ui.pagerfragments.IncompleteTodoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    lateinit var detailsViewModel: DetailsViewModel
    lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        setLifecycle(binding, detailsViewModel)
        detailsViewModel.getSingleUserTodo(args.userId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let { Helper.displayDialog(it, requireContext()) }
        })

        val pagerAdapter = PagerAdapter(this).also {
            it.addFragment(CompletedTodoFragment())
            it.addFragment(IncompleteTodoFragment())
        }

        binding.todoViewPager.adapter = pagerAdapter
        binding.todoViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(
            binding.tabLayout,
            binding.todoViewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.completed_tab)
                }
                1 -> {
                    tab.text = getString(R.string.incomplete_tab)
                }
            }
        }.attach()
    }

    private fun setLifecycle(
        binding: FragmentDetailsBinding,
        todoViewModel: DetailsViewModel
    ) {
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = todoViewModel
        }
    }
}