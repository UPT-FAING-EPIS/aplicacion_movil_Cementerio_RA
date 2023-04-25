package com.example.mapsbasics2.artistForm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.mapsbasics2.R
import com.example.mapsbasics2.common.Constant.BIRTH_LOCATION
import com.example.mapsbasics2.common.dataAccess.FakeDatabase
import com.example.mapsbasics2.common.entities.Artist
import com.example.mapsbasics2.common.entities.BirthLocation
import com.example.mapsbasics2.databinding.ActivityFormBinding
import com.google.gson.Gson

class FormActivity : AppCompatActivity(), AuxLocation {

    private lateinit var binding: ActivityFormBinding
    private lateinit var newLocation: BirthLocation

    private var artist:Artist? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getArtists()?.let{
            artist = it
            setupArtist(it)
        }

        binding.btnEditLocation.setOnClickListener{showMap()}
        binding.btnSave.setOnClickListener{uploadData()}
    }

    private fun setupArtist(artist: Artist) {
        binding.etName.setText(artist.name)
        binding.etSurnames.setText(artist.surnames)
        Glide.with(this)
            .load(artist.photoUrl)
            .circleCrop()
            .into(binding.imgPhoto)
        setBirthLocation(artist.birthLocation.latitude, artist.birthLocation.longitude,
            artist.birthLocation.placeDetails)
    }

    private fun getArtists():Artist? = FakeDatabase.getArtists(this)

    private fun showMap(){
        artist?.let {
            val dialogFragment = LocationFragment(this)
            val args = Bundle()
            args.putString(BIRTH_LOCATION, Gson().toJson(it.birthLocation))
            dialogFragment.arguments = args
            val transaction = supportFragmentManager.beginTransaction()
            dialogFragment.show(transaction,LocationFragment.TAG)
        }
    }

    private fun uploadData(){
        artist?.let {
            it.name = binding.etName.text.toString()
            it.surnames = binding.etSurnames.text.toString()
            it.birthLocation = newLocation

            binding.tvCloud.setText(it.toString()   )
        }
    }
    //AuxLocation
    override fun setBirthLocation(latitude: Double, longitude: Double, placeDetails: String) {
        newLocation = BirthLocation()
        newLocation.latitude = latitude
        newLocation.longitude = longitude
        newLocation.placeDetails = placeDetails
        binding.etLocation.setText(placeDetails)
    }

}
