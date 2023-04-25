package com.example.mapsbasics2.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.mapsbasics2.R
import com.example.mapsbasics2.common.Locations.heladeria
import com.example.mapsbasics2.common.Locations.ovalo
import com.example.mapsbasics2.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CameraViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.addMarker(MarkerOptions().position(heladeria).title("Frogames"))
        //map.moveCamera(CameraUpdateFactory.newLatLng(heladeria))

        /*val reformaCamera = CameraPosition.Builder()
            .target(plaza)
            .bearing(60f)
            .tilt(0f)
            .zoom(10f)
            .build()
        map.moveCamera(CameraUpdateFactory.newCameraPosition(reformaCamera))
        map.apply {
            setMinZoomPreference(16f)
            setMaxZoomPreference(20f)
        }*/

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(heladeria,10f))
        lifecycleScope.launch {
            delay(5_000)
            map.addMarker(MarkerOptions().position(ovalo))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ovalo, 10f))
        }
    }

}