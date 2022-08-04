package com.leonel.upaxapp.ui.datanegocio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.leonel.upaxapp.R

class NegocioMapsFragment : Fragment() {
    var datosRecuperados=arguments

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val yucatan: LatLng
        //val sydney = LatLng( 20.9803289, -89.7730065)
        if(datosRecuperados!=null)
            yucatan = LatLng( datosRecuperados?.get("latitud").toString().toDouble(), datosRecuperados?.get("longitud").toString().toDouble())
        else
         yucatan = LatLng( 24.9803289, -88.7730065)
        googleMap.addMarker(MarkerOptions().position(yucatan).title("Ubicación del negocio"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(yucatan))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_negocio_maps, container, false)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datosRecuperados =arguments

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

}