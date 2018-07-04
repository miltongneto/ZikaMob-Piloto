package com.zika.lika.zikamob;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalizationActivity extends Activity {

    private Button btn_get_localizacao;

    private LocationManager lManager;
    private Location location;
    private LocationListener locationListener;

    private static final int ID_PERMISSION_REQUEST = 2505;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, netPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}), ID_PERMISSION_REQUEST);
        }


        btn_get_localizacao = (Button) findViewById(R.id.localization_btn_get);

        lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("LocalizationActivity", "Location Changed");
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.i("LocalizationActivity", "Status Changed");                        }

            @Override
            public void onProviderEnabled(String s) {
                Log.i("LocalizationActivity", "Provider Enabled");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.i("LocalizationActivity", "Provider Disabled");
            }
        };

        btn_get_localizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Log.i("LocalizationActivity", "Permission OK");

                    lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                    location = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = new ArrayList<Address>();

                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String address = addresses.get(0).getAddressLine(0);
                    Toast.makeText(getApplicationContext(), "Sua localização é: " + address, Toast.LENGTH_LONG).show();
                } else {
                    Log.i("LocalizationActivity", "Permission fail");
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if  (requestCode == ID_PERMISSION_REQUEST) {
            if (hasAllPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION})) {
                Toast.makeText(this, "Permissao concedida!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Permissao negada!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean hasAllPermissions(String[] perms) {
        for (String perm : perms) {
            if (!hasPermission(perm)) {
                return(false);
            }
        }

        return(true);
    }

    protected boolean hasPermission(String perm) {
        return(ContextCompat.checkSelfPermission(this, perm)== PackageManager.PERMISSION_GRANTED);
    }

    private String[] netPermissions(String[] wanted) {
        ArrayList<String> result=new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return(result.toArray(new String[result.size()]));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lManager.removeUpdates(locationListener);
        }
    }
}
