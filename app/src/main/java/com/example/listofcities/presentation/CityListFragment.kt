package com.example.listofcities.presentation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.repository.CityRepositoryImpl
import com.example.listofcities.databinding.FragmentListBinding
import com.example.listofcities.domain.usecases.GetCitiesForListUseCase
import com.example.listofcities.presentation.adapters.CityAdapter
import com.example.listofcities.presentation.adapters.ItemTouchHelperCallback
import com.example.listofcities.presentation.viewmodels.CityListViewModel
import com.example.listofcities.presentation.viewmodels.CityListViewModelFactory

//Показывает список городов выбранного типа
class CityListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: CityAdapter
    private lateinit var viewModel: CityListViewModel
    private val repository by lazy {
        CityRepositoryImpl(
            CitiesDatabase.getInstance(requireContext()).cityDao(),
            CitiesDatabase.getInstance(requireContext()).cityListDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater,container,false)
        setupAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observeViewModel()
    }

    private fun setupAdapter(){
        listAdapter = CityAdapter()
        binding.rvList.adapter = listAdapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())

        val callback = ItemTouchHelperCallback(listAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvList)
    }

    private fun initViewModel(){
        val factory = CityListViewModelFactory(GetCitiesForListUseCase(repository))
        viewModel = ViewModelProvider(this, factory)[CityListViewModel::class.java]
    }

    private fun observeViewModel(){
        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            listAdapter.submitList(cities)
        }
    }

}