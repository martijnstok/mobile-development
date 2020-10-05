package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portal.*

class PortalFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
//        rvPortals.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvPortals.layoutManager = GridLayoutManager(context, 2)
        rvPortals.adapter = portalAdapter
        rvPortals.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        observeAddPortalResult()
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getString(BUNDLE_PORTAL_KEY_TITLE)?.let {
                val portal = Portal(it)

                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalFragment", "Request triggered, but empty portal title and/or url!")
        }
    }

//    private fun observeAddPortalResult() {
//        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
//            bundle.getStringArray(BUNDLE_PORTAL_KEY_TITLE)?.let {
//                val portal = Portal(it[0], it[1])
//
//                portals.add(portal)
//                portalAdapter.notifyDataSetChanged()
//            } ?: Log.e("PortalFragment", "Request triggered, but empty portal title and/or url!")
//        }
//    }

}