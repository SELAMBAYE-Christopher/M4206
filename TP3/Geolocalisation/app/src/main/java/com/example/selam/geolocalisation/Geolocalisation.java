package com.example.selam.geolocalisation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Geolocalisation extends AppCompatActivity {

    LocationManager mLocationManager;
    LocationListener mLocationListtener;
    TextView mLongitude, mLatitude, mAltitude, mVitesse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);

        mLongitude = (TextView) findViewById(R.id.longitude);
        mLatitude = (TextView) findViewById(R.id.latitude);
        mAltitude = (TextView) findViewById(R.id.altitude);
        mVitesse = (TextView) findViewById(R.id.vitesse);

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        mLocationListtener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mLongitude.setText("Longitude : " + location.getLongitude());
                mLatitude.setText("Latitude : " + location.getLatitude());
                mAltitude.setText("Altitude " + location.getAltitude());
                mVitesse.setText("Vitesse : " + location.getSpeed());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 10, mLocationListtener);
    }
}
