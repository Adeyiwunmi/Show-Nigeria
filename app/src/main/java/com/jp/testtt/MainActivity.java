package com.jp.testtt;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.api.zzl;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    GoogleMap googleMap;
    Toolbar toolbar;
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    1);
        }
        googleApiClient = new GoogleApiClient.Builder(this, this,  this).addApi(LocationServices.API).build();

        //initialiseMap();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onConnected(Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

            double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
            initialiseMap(lat,lon, "Your Location");

        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.id_Abia) {

            initialiseMap(5.41667, 7.5, getResources().getString(R.string.abia_state));
            // Handle the camera action
        } else if (id == R.id.id_Adamawa) {

            initialiseMap(9.33333, 12.5, getResources().getString(R.string.Adamawa_state));

        } else if (id == R.id.id_AkwaIbom) {

            initialiseMap(5.0, 7.83333, getString(R.string.akwa_ibom_state));

        } else if (id == R.id.id_Anambra) {

            initialiseMap(6.2758, 7.0068, getResources().getString(R.string.anambra_state));

        } else if (id == R.id.id_Bauchi) {

            initialiseMap(10.5, 10.0, getString(R.string.bauchi_state));

        } else if (id == R.id.id_Bayelsa) {

            initialiseMap(4.75, 6.08333, getString(R.string.bayelsa_state));

        } else if (id == R.id.id_Benue) {

            initialiseMap(7.33333, 8.75, getString(R.string.benue_state));

        } else if (id == R.id.id_Borno) {

            initialiseMap(11.5, 13.0, getString(R.string.borno_state));

        } else if (id == R.id.id_crossRiver) {

            initialiseMap(5.75, 8.5, getString(R.string.cross_river_state));

        } else if (id == R.id.id_Delta) {

            initialiseMap(5.5, 6.0, getString(R.string.delta_state));

        } else if (id == R.id.id_Ebonyi) {

            initialiseMap(6.25, 8.08333, getString(R.string.ebonyi_state));

        } else if (id == R.id.id_Edo) {

            initialiseMap(6.5, 6.0, getString(R.string.edo_state));

        } else if (id == R.id.id_Ekiti) {

            initialiseMap(7.66667, 5.25, getString(R.string.ekiti_state));

        } else if (id == R.id.id_Enugu) {

            initialiseMap(6.5, 7.5, getString(R.string.enugu_state));

        } else if (id == R.id.id_Gombe) {

            initialiseMap(10.25, 11.16667, getString(R.string.gombe_state));

        } else if (id == R.id.id_Imo) {

            initialiseMap(5.5, 7.16667, getString(R.string.imo_state));

        } else if (id == R.id.id_Jigawa) {

            initialiseMap(12.1, 9.56, getString(R.string.jigawa_state));

        } else if (id == R.id.id_Kaduna) {

            initialiseMap(10.33333, 7.75, getString(R.string.kaduna_state));

        } else if (id == R.id.id_kano) {

            initialiseMap(11.5, 8.5, getString(R.string.kano_state));

        } else if (id == R.id.id_Katsina) {

            initialiseMap(12.25, 7.5, getString(R.string.katsina_state));

        } else if (id == R.id.id_kebbi) {

            initialiseMap(11.5, 4.0, getString(R.string.kebbi_state));

        } else if (id == R.id.id_Kogi) {

            initialiseMap(7.75, 6.75, getString(R.string.Kogi_state));

        } else if (id == R.id.id_Kwara) {

            initialiseMap(8.5, 5.0, getString(R.string.kwara_state));

        } else if (id == R.id.id_Lagos) {

            initialiseMap(6.53774, 3.3522, getString(R.string.lagos_state));

        } else if (id == R.id.id_Nasarawa) {

            initialiseMap(8.5, 8.25, getString(R.string.nasarawa_state));

        } else if (id == R.id.id_Niger) {

            initialiseMap(10.0, 6.0, getString(R.string.niger_state));

        } else if (id == R.id.id_Ogun) {

            initialiseMap(7.0, 3.58333, getString(R.string.ogun_state));

        } else if (id == R.id.id_Ondo) {

            initialiseMap(7.16667, 5.08333, getString(R.string.ondo_state));

        } else if (id == R.id.id_Osun) {

            initialiseMap(7.5, 4.5, getString(R.string.osun_state));

        } else if (id == R.id.id_Oyo) {

            initialiseMap(8.0, 4.0, getString(R.string.oyo_state));

        } else if (id == R.id.id_Plateau) {

            initialiseMap(9.16667, 9.75, getString(R.string.plateau_state));

        } else if (id == R.id.id_Rivers) {

            initialiseMap(4.74974, 6.82766, getString(R.string.rivers_state));

        } else if (id == R.id.id_Sokoto) {

            initialiseMap(13.08333, 5.25, getString(R.string.sokoto_state));

        } else if (id == R.id.id_Taraba) {

            initialiseMap(8.0, 10.5, getString(R.string.taraba_state));

        } else if (id == R.id.id_Yobe) {

            initialiseMap(12.0, 11.5, getString(R.string.yobe_state));

        } else if (id == R.id.id_Zamfara) {

            initialiseMap(12.16667, 6.25, getString(R.string.zamfara_state));

        } else if (id == R.id.id_FCT) {

            initialiseMap(8.83333, 7.16667, getString(R.string.fct_state));

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initialiseMap() {
        googleApiClient = new GoogleApiClient.Builder(this, this,  this).addApi(LocationServices.API).build();

        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapfrag)).getMap();
            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(6.53774, 3.3522)).title("lagos");
            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(6.53774, 3.3522)).zoom(8).build();

            googleMap.addMarker(markerOptions);
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            if (googleMap == null) {
                Toast.makeText(getApplicationContext(), "sorry unable to create ", Toast.LENGTH_SHORT).show();
            }

        }


    }

    public void initialiseMap(double lat, double longt, String title) {
        //googleMap.clear();
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapfrag)).getMap();
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(lat, longt)).title(title);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat, longt)).zoom(12).build();

        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        toolbar.setTitle(title);

        if (googleMap == null) {
            Toast.makeText(getApplicationContext(), "sorry unable to create ", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

                    double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
                    initialiseMap(lat,lon, "Your Location");

                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}