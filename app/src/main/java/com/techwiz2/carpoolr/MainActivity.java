package com.techwiz2.carpoolr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techwiz2.carpoolr.activity.AccountActivity;
import com.techwiz2.carpoolr.activity.BookCarActivity;
import com.techwiz2.carpoolr.activity.HistoryActivity;
import com.techwiz2.carpoolr.activity.LoginActivity;
import com.techwiz2.carpoolr.activity.RegisterActivity;
import com.techwiz2.carpoolr.connectnetwork.ApiDirection;
import com.techwiz2.carpoolr.model.direction.DirectionResults;
import com.techwiz2.carpoolr.model.direction.Route;
import com.techwiz2.carpoolr.model.direction.RouteDecode;
import com.techwiz2.carpoolr.model.direction.Steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener{

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCAT = 44;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private SupportMapFragment mapFragment;
    private TextView ePointAway, eDestination, btnBookCar;
    private LatLng latLngAway, latLngDes;

    ImageView idAccount,idHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Places.initialize(getApplicationContext(), "AIzaSyAKpjDbGDSTJIjkkEyE8Cw2vnkhq6rcK-U");

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapMain);

        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }


    public void init() {
        ePointAway = findViewById(R.id.ePointAway);
        eDestination = findViewById(R.id.eDestination);
        btnBookCar = findViewById(R.id.btnBookCar);
        idAccount = findViewById(R.id.idAccount);
        idHistory = findViewById(R.id.idHistory);

        ePointAway.setOnClickListener(this);
        eDestination.setOnClickListener(this);
        btnBookCar.setOnClickListener(this);
        idAccount.setOnClickListener(this);
        idHistory.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocationPermission();
        getCurrentLocation();
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCAT);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                mapFragment.getMapAsync(this);
            }
        }
    }

    private void getCurrentLocation() {
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
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;
                            mMap.isMyLocationEnabled();
                            latLngAway = new LatLng(location.getLatitude(), location.getLongitude());

                            MarkerOptions options = new MarkerOptions().position(latLngAway).title("you are here");

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngAway, 15));

                            mMap.addMarker(options);

                        }
                    });

                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        ePointAway.setText(addresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

//    public void goToAccount(){
//        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void goToHistory(){
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
        finish();
    }


    public void goToAccount(){
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ePointAway:
                getPointAway();
                break;
            case R.id.eDestination:
                getDestination();
                break;
            case  R.id.btnBookCar:
                bookCar();
                break;
            case R.id.idHistory:
                goToHistory();
                break;

            case R.id.idAccount:
                goToAccount();
                break;

        }
    }

    public void bookCar() {
        if (ePointAway.getText().toString().isEmpty() || eDestination.getText().toString().isEmpty()) {
            Toast.makeText(this,"Please enter information",Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, BookCarActivity.class);
            intent.putExtra("fromLati", latLngAway.latitude);
            intent.putExtra("fromLongi", latLngAway.longitude);
            intent.putExtra("toLati", latLngDes.latitude);
            intent.putExtra("toLongi", latLngDes.longitude);
            intent.putExtra("fromAddress", ePointAway.getText().toString());
            intent.putExtra("toAddress", eDestination.getText().toString());
            startActivity(intent);
        }
    }

    public void getPointAway() {
        List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fieldList).build(this);
        startActivityForResult(intent, 100);
    }

    public void getDestination() {
        List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fieldList).build(this);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);

            ePointAway.setText(place.getAddress());

            mMap.clear();

            MarkerOptions options = new MarkerOptions();

            latLngAway = place.getLatLng();
            if (latLngDes == null) {

                options.position(latLngAway);
                options.title(place.getName());
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(options);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngAway, 12));
            } else {
                getDirections(latLngAway, latLngDes);
            }

        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);

            eDestination.setText(place.getAddress());

            mMap.clear();

            latLngDes = place.getLatLng();

            getDirections(latLngAway, latLngDes);

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);

            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getDirections(LatLng origin, LatLng destination) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiDirection.MAP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDirection apiDirection = retrofit.create(ApiDirection.class);
        apiDirection.getDirection("driving", "less_driving", origin.latitude + "," + origin.longitude, destination.latitude + "," + destination.longitude, getString(R.string.api_key_map))
                .enqueue(new Callback<DirectionResults>() {
                    @Override
                    public void onResponse(Call<DirectionResults> call, Response<DirectionResults> response) {
                        DirectionResults directionResults = response.body();
                        ArrayList<LatLng> routelist = new ArrayList<LatLng>();
                        if(directionResults.getRoutes().size()>0){
                            ArrayList<LatLng> decodelist;
                            Route routeA = directionResults.getRoutes().get(0);

                            if(routeA.getLegs().size()>0){
                                List<Steps> steps= routeA.getLegs().get(0).getSteps();
                                Steps step;
                                com.techwiz2.carpoolr.model.direction.Location location;
                                String polyline;
                                for(int i=0 ; i<steps.size();i++){
                                    step = steps.get(i);
                                    location = step.getStart_location();
                                    routelist.add(new LatLng(location.getLat(), location.getLng()));
                                    Log.i("zacharia", "Start Location :" + location.getLat() + ", " + location.getLng());
                                    polyline = step.getPolyline().getPoints();
                                    decodelist = RouteDecode.decodePoly(polyline);
                                    routelist.addAll(decodelist);
                                    location =step.getEnd_location();
                                    routelist.add(new LatLng(location.getLat() ,location.getLng()));
                                }
                            }
                        }

                        if(routelist.size()>0){
                            PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.BLUE);

                            for (int i = 0; i < routelist.size(); i++) {
                                rectLine.add(routelist.get(i));
                            }

                            MarkerOptions options = new MarkerOptions();

                            // Adding route on the map
                            mMap.addPolyline(rectLine);
                            options.position(destination);
                            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                            options.draggable(true);
                            mMap.addMarker(options);
                            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                            options.position(origin);
                            mMap.addMarker(options);
                        }
                    }

                    @Override
                    public void onFailure(Call<DirectionResults> call, Throwable t) {
                        System.out.println("Failure, retrofitError" + t);
                    }
                });

    }


}