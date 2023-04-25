package com.example.mapsbasics2.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapsbasics2.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class NewProcessorActivity : AppCompatActivity(), OnMapReadyCallback, OnMapsSdkInitializedCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_map)

        MapsInitializer.initialize(this,MapsInitializer.Renderer.LATEST, this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val galeria = LatLng(-18.00468345346427, -70.24431106333033)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(galeria, 12f))
        googleMap.addMarker(MarkerOptions().position(galeria).title("Centro Tupac Amaru"))
        //activar trafico de la zona
        googleMap.isTrafficEnabled = true
    }

    override fun onMapsSdkInitialized(p0: MapsInitializer.Renderer) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}