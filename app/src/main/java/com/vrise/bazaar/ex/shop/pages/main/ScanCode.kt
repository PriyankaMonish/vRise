package com.vrise.bazaar.ex.shop.pages.main

import android.Manifest.permission.CAMERA
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*

import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.RegistrationViewModel
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.fragment_scan.*
import kotlinx.android.synthetic.main.registration_fragment.*


class ScanCode : Fragment() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var viewModel: RegistrationViewModel


    val neededPermissions = arrayOf(
        android.Manifest.permission.CAMERA
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(RegistrationViewModel::class.java)
        codeScanner = CodeScanner(activity, scannerView)


        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
                var newtext = it.text
                        viewModel.addTaag(
                            Request(
                                utoken =Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                                seller_id="",
                                sellerref =  newtext
                            )
                        )?.observe(viewLifecycleOwner, Observer {
                            val tabs = this.parentFragment as TabFragment
                                tabs.gotoHomeFragment()

                        })
                    }
        }
        }


    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(requireContext(),
                CAMERA) == PackageManager.PERMISSION_GRANTED) {
            codeScanner.startPreview()
        } else {
            requestStoragePermission();
        }




    }

     fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
               CAMERA
            )
        ) {
            AlertDialog.Builder(activity)
                .setTitle("Permission needed")
                .setMessage("Enable camera to open QR Code")
                .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(CAMERA),
                            100
                        )
                    }
                })
                .setNegativeButton("cancel", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        dialog.dismiss()
                    }
                })
                .create().show()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(CAMERA),
                100
            )
        }
    }

     fun onRequestPermissionsResult(requestCode: Int,  @NonNull grantResults: IntArray) {
        if (requestCode == 100) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission GRANTED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


}