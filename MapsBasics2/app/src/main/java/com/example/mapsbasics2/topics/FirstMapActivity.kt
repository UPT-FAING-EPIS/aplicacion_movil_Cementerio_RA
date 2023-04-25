package com.example.mapsbasics2.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapsbasics2.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FirstMapActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val tupac = LatLng(-18.00372649936725, -70.24304796144774)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tupac, 20f))
        googleMap.addMarker(MarkerOptions().position(tupac).title("Centro Tupac Amaru"))
        //activar trafico de la zona
        googleMap.isTrafficEnabled = true
    }
}