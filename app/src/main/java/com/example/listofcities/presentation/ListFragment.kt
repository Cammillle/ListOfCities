package com.example.listofcities.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listofcities.databinding.FragmentListBinding
import com.example.listofcities.presentation.adapters.CityAdapter
import com.example.listofcities.presentation.adapters.ItemTouchHelperCallback
import com.example.listofcities.presentation.viewmodels.MainViewModel

private const val ID = "LIST_ID"

class ListFragment : Fragment(), CityAdapter.OnStartDragListener {
    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: CityAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var viewModel: MainViewModel

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupDragAndDrop()
    }

    init {
        val cities = viewModel.getCitiesFromCityList(id = 1).value?.cities
        listAdapter.submitList(cities)
    }

    companion object {
        @JvmStatic
        fun newInstance(listId:Int) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID,listId)
                }
            }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private fun setupAdapter(){
        listAdapter = CityAdapter(this)
        binding.rvList.adapter = listAdapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupDragAndDrop(){
        val callback = ItemTouchHelperCallback(listAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvList)
    }

    private fun observeViewModel(){
        viewModel.cityLists.observe(viewLifecycleOwner){

        }

    }

}