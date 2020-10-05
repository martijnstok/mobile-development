package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY_TITLE = "bundle_portal_title"
const val BUNDLE_PORTAL_KEY_URL = "bundle_portal_url"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddReminder.setOnClickListener {
            onAddPortal()
        }

    }

    private fun onAddPortal() {
        val portalTitle = etPortalName.text.toString()

        if (portalTitle.isNotBlank()) {
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY_TITLE, portalTitle)))
            findNavController().popBackStack()

        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_portal, Toast.LENGTH_SHORT
            ).show()
        }
    }

//    private fun onAddPortal() {
//        val portalTitle = etPortalName.text.toString()
//        val portalUrl = etPortalUrl.text.toString()
//
//        val portalArray = listOf(portalTitle, portalUrl)
//
//        if (portalTitle.isNotBlank() && portalUrl.isNotBlank()) {
//            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY_TITLE, portalArray)))
//            findNavController().popBackStack()
//
//        } else {
//            Toast.makeText(
//                activity,
//                R.string.not_valid_portal, Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
}