package com.example.mapsbasics2.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapsbasics2.R
import com.example.mapsbasics2.common.Locations.ovalo
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.StreetViewSource

class PanoramaActivity : AppCompatActivity(), OnStreetViewPanoramaReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panorama)

        val panoramaFragment = supportFragmentManager.findFragmentById(R.id.streetViewPanorama) as SupportStreetViewPanoramaFragment
        panoramaFragment.getStreetViewPanoramaAsync(this)
    }

    override fun onStreetViewPanoramaReady(streetViewPanorama: StreetViewPanorama) {
        streetViewPanorama.apply {
            setPosition(ovalo, 2000, StreetViewSource.OUTDOOR)

            //isPanningGesturesEnabled = true
            //isUserNavigationEnabled = true
            //isZoomGesturesEnabled = true
            isStreetNamesEnabled = true // no funciona con radios pequeños
        }
    }
}