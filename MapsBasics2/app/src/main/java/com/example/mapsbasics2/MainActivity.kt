package com.example.mapsbasics2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.mapsbasics2.artistForm.FormActivity
import com.example.mapsbasics2.artistMap.ArtistMapActivity
import com.example.mapsbasics2.databinding.ActivityMainBinding
import com.example.mapsbasics2.topics.*

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnFirstMap.setOnClickListener(this@MainActivity)
            btnNewProcessor.setOnClickListener(this@MainActivity)
            btnPanorama.setOnClickListener(this@MainActivity)
            btnCameraView.setOnClickListener(this@MainActivity)
            btnHistoria.setOnClickListener(this@MainActivity)
            btnInfo.setOnClickListener(this@MainActivity)
            btnArtistMap.setOnClickListener(this@MainActivity)
            btnLiteList.setOnClickListener(this@MainActivity)
            btnFormMap.setOnClickListener(this@MainActivity)

        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when(it.id){
                R.id.btnFirstMap -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnNewProcessor -> startActivity(Intent(this, NewProcessorActivity::class.java))
                R.id.btnPanorama -> startActivity(Intent(this, PanoramaActivity::class.java))
                R.id.btnCameraView -> startActivity(Intent(this, CameraViewActivity::class.java))
                R.id.btnHistoria -> startActivity(Intent(this, IlustresActivity::class.java))
                R.id.btnInfo -> startActivity(Intent(this, InfoAppActivity::class.java))
                R.id.btnArtistMap -> startActivity(Intent(this, ArtistMapActivity::class.java))
                R.id.btnLiteList -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnFormMap -> startActivity(Intent(this, FormActivity::class.java))
            }
        }
    }

}