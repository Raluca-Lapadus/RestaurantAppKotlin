package com.example.navigation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigation.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var listAdapter: RestaurantListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        listAdapter = RestaurantListAdapter(mutableListOf(), requireContext())

        binding.rvRestaurantTitle.adapter = listAdapter
        binding.rvRestaurantTitle.layoutManager = LinearLayoutManager(requireContext())

        binding.btnToAddRestaurant.setOnClickListener {
            val restaurantTitle = binding.etRestaurantTitle.text.toString()
            if (restaurantTitle.isNotEmpty()) {
                val restaurantList = RestaurantItem(restaurantTitle)
                listAdapter.addRestaurant(restaurantList)
                binding.etRestaurantTitle.text.clear()
            }
        }

        binding.btnToDeleteRestaurant.setOnClickListener {
            listAdapter.deleteRestaurant()
        }
//        binding.searchView.isFocusable = true
//        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submit
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle query text change
                listAdapter.filterList(newText)
                return true
            }
        })

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}