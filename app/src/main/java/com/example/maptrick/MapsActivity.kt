package com.example.maptrick

import android.location.GnssAntennaInfo
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.maptrick.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val inKenya = LatLng(0.5143, 35.2698)
        mMap.addMarker(MarkerOptions().position(inKenya).title("Marker in Some Play in Kenya"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(inKenya))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(inKenya, 14f))

        var latlng1 = LatLng(-2.0, 38.0)
        var latlng2 = LatLng(3.0, 36.0)


        val polyline1 = googleMap.addPolyline(PolylineOptions()
            .clickable(true)
            .add(
                latlng1,
                inKenya
            )
        )
        var origin = MarkerOptions().position(latlng1)
        var destination = MarkerOptions().position(latlng2)
        mMap.addMarker(origin)
        mMap.addMarker(destination)
        var results = FloatArray(10)
        Location.distanceBetween(0.5143, 35.2698, 3.0,36.0, results)
        val s = String.format("%.1f", results[0]/ 1000)

        Toast.makeText(this, "The distance is ${s} Metres", Toast.LENGTH_LONG).show()
//        mMap.animateCamera()
        googleMap.setOnPolylineClickListener{this}
    }
}