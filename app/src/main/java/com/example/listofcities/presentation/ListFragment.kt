package com.example.listofcities.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listofcities.databinding.FragmentListBinding
import com.example.listofcities.presentation.adapters.CityAdapter
import com.example.listofcities.presentation.adapters.ItemTouchHelperCallback
import com.example.listofcities.presentation.viewmodels.MainViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment(), CityAdapter.OnStartDragListener {
    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: CityAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var viewModel: MainViewModel

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
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
        viewModel.cityList.observe(viewLifecycleOwner){
            it.let {
                listAdapter.submitList(it)
            }
        }

    }

}