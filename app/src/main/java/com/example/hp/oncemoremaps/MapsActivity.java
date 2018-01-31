package com.example.hp.oncemoremaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{


    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        LatLng rv =new LatLng(29.8645, 77.8928);
        LatLng rajb =new LatLng(29.8698, 77.8949);
        LatLng rkb =new LatLng(29.8711, 77.8954);
        LatLng gb =new LatLng(29.8715, 77.8944);
        LatLng cb =new LatLng(29.8704, 77.8955);
        LatLng sarb =new LatLng(29.8651, 77.9001);
        LatLng kasb =new LatLng(29.8673, 77.9011);
        LatLng lbss =new LatLng(29.867320,77.895675 );
        LatLng abn =new LatLng(29.869328,77.896211);
        LatLng mgl =new LatLng(29.865278,77.894782);
        LatLng jtb =new LatLng(29.864860,77.896579);
        LatLng mech =new LatLng(29.862662,77.897317);
        LatLng cse =new LatLng(29.863728,77.895760);
        LatLng ele =new LatLng(29.863175,77.897344);
        LatLng chem =new LatLng(29.864860,77.896579);
        LatLng meta =new LatLng(29.867213,77.893082);
        LatLng ece =new LatLng(29.863722,77.895753);
        LatLng civ =new LatLng(29.862781,77.898484);
        mMap.addMarker(new MarkerOptions().position(rv).title("Ravinder Bhawan").snippet("")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(rajb).title("Rajiv Bhawan").snippet("Rajiv Bhawan is the newest jewel among the 19 hostels of IIT Roorkee. It is named on the 6th prime minister of India, Rajiv Gandhi. It was on 27 October, 2008 that the founding stone of the Bhawan was laid by the then director of IITR , Dr. S.C. Saxena. The construction was completed on 12 November 2010.The Bhawan came to be known in its present inhabitable form on 17 July, 2011, when the students first walked in.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(rkb).title("Radha krishna Bhawan").snippet("Named after the legendary teacher, visionary, philosopher, humanist and former President of India, Dr. S. Radhakrishnan, the Radhakrishnan Bhawan is the latest bhawan of IIT Roorkee. It is the first of its kind in more respects than one, the first multi-storied hostel comprising 8 storeys including the ground floor, the first Bhawan to have Lift facility and also the first bhawan that has outsourced all its services including the Mess and House-keeping. Its massive structure resembles the New Taj of Mumbai and is everybody's delight in its full view at night. This is the first bhawan to have 24 hour electric supply with the facility of state of the art generator.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(gb).title("Ganga Bhawan").snippet("Ganga Bhawan, one of the oldest in IIT Roorkee, was named after Sir Ganga Ram, one of the greatest products of the erstwhile Thomason College of Engineering. The foundation stone of D.S. hostel D Block (presently 'A' Block) was laid by Sri B.R. Sabbarwal, Retired Chief Engineer, Bengal on Nov. 26,1956. This wing was opened by Ch.Girdhari Lal, Minister for Irrigation, Power and P.W.D., U.P. Government on Nov. 27 1960. Ganga Bhawan, inspite of being the oldest in the institute provides the modern amenities and facilities to its residents and caters well to the needs of the 650 odd residents of the bhawan.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(cb).title("Cautley Bhawan").snippet("Cautley Bhawan being one of the oldest structure, has always been reckoning for its construction plan and strong healthy principled environment. This Bhawan with its infrastructure and facilities like Gym, SBI-ATM, T.V. Hall. Lipton, a huge canteen and above the beautiful greenery around, make the Bhawan a prefect place in IIT Roorkee to live in. Recently, a new block of 201 seat in year 2001 was established. Now a new building (72 double seated room) is under construction, making a total strength of Cautley Bhawan around 657 seats.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(sarb).title("Sarojini Bhawan").snippet("Sarojini Bhawan, the first Girls Hostel in IIT Roorkee, was established in the year 1966-67 by Smt. Lakshi Kanntamma Reddy.As the time passed,it was extended four times. The wing named Krishna was constructed in year 1985-86 and the second extension,which resulted in the Disang wing ,was build in year 1988-89.The Alaknanda wing was constructed in 2000-2001. The Newest wing,Sabarmati was built in 2002-2003. The name of the bhawan is dedicated to the Nightangle of India, Sarojini Naidu, who is an inspiration for all girls that sky is the limit.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(kasb).title("Kasturba Bhawan").snippet("Kasturba Bhawan, the second Girls Hostel in IIT Roorkee, was established initially as a two-floor building in the year 2006-07. As the time passed and the need to house the increasing number of girl entrants to the instituion increased, it was extended four times and now in its present form is seen as a vibrant seven floored, two winged multi-coloured building inaugrated in 12 November 2010. The name of the bhawan is dedicated to Kasturba Ji, who is a inspiration for all girl residents.The Bhawan has every facility that one needs to live a comfortable life.
It has a state of art Cyber Cafe, managed by students. The place where every girl resident finds her foot is the Bhawan Canteen which serves delicious food at all times. The Bhawan Club has a 21 inch Television with chairs,matresses and tables.It also has a music system. The Bhawan also provides the facility to play various sports such as badminton, table tennis, throw ball etc. by providing various sports equipments free of cost. Along with it, the bhawan also has a seperate washing machine room to help girls meet the daily requirements. We LIVE here, not just to survive but to provide an expression to our lives and this Bhawan is the foundation of it all. It is this place where more than half of our time is spent, and it is this place that serves us as a home, away from our homes.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions().position(lbss).title("Lal Bahadur Shastri Stadium,Sports Ground").snippet("LBS is accredited as our institute’s only helipad cum athletic track cum cricket field cum jogging track. It shares a boundary with the basketball court and witnesses a surge in the number of students on the ground at about 6:30 every evening- which incidentally is just about when the basketball girls come for practice. It is widely believed that early in the morning NSO proficiency holders can be seen sweating it out on the LBS ground.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(new MarkerOptions().position(abn).title("ABN Ground,Sports Ground").snippet("It is the playing ground for students and all the festivities are held here.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(new MarkerOptions().position(mgl).title("Mahatma Gandhi Central Library").snippet("The Mahatma Gandhi Central Library finds a unique place in the academic spectrum of the Institute. Started in 1848 with a few hundred donated books, its collection has grown to more than 3,50,000 documents in all media. Providing information through e-resources is the main focus of the Library. It has around 90,000 sq ft of fully airconditioned space. It can accommodate more than 500 readers at any point of time. The library building is WiFi enabled and contains a total 75 user terminals for dedicated readers. It also contains an 80-seater open reading room.")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mMap.addMarker(new MarkerOptions().position(jtb).title("James Thomson Building").snippet("The institution has its origins in a class started in 1845 to train local youth in engineering to assist in public works then beginning. In 1847 it was officially established. It was renamed as the Thomason College of Civil Engineering in 1854 in honour of its founder, Sir James Thomason, lieutenant governor 1843–53. The first Indian to graduate from the Roorkee college was Rai Bahadur Kanhaiya Lal in 1852")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        mMap.addMarker(new MarkerOptions().position(mech).title("Mechanical Engineering Department,IITR").snippet(" At present it offers both undergraduate and postgraduate courses in various facets of Mechanical and Industrial Engineering. The department offers Master of Technology courses in Machine Design Engineering, Production and Industrial Systems Engineering, Thermal System Engineering, Welding Engineering and CAD, CAM and Robotics. The department has laboratory and workshop facilities with modern sophisticated equipment to carry out research in all areas related to Mechanical and Production & Industrial Engineering. The faculty actively participates in sponsored research and consultancy work."));
        mMap.addMarker(new MarkerOptions().position(cse).title("Computer Science Engineering Department,IITR").snippet("The Department has successfully completed a large number of sponsored research projects funded by DRDO, DST, UGC, ISRO, DOE, AICTE, MCIT etc. Besides, the Department provides R &D and consultancy services to various industries.");
        mMap.addMarker(new MarkerOptions().position(ele).title("Electrical Engineering Department,IITR").snippet("The Department has specialization in research areas such as: ANN and fuzzy logic applications in robotics and control, Distribution system planning and operation, Economic dispatch and planning, Flexible AC transmission system, Optimal system operation, Power system protection, monitoring, control and simulation, Power quality, Relay coordination, Power system automation, Artificial intelligence applications and Voltage stability of power system, Telemedicine, ECG signal analysis and classification, Digital signal and image processing, Intelligent instrumentation, Industrial instrumentation, Medical system modeling, Instrumentation and bio-informatics, Process instrumentation and control, System analysis and optimization, Data base management, FPGA based control, System automation and monitoring, Reliability  engineering, Robotics, System modeling, Computer controlled system including process control, Computer controlled multi-quadrant solid-state converters, Condition monitoring of electrical machines/drives, High performance computer controlled DC and AC drives, Modeling and simulation of electric machines."));
        mMap.addMarker(new MarkerOptions().position(chem).title("Chemical Engineering Department,IITR").snippet("The Department of Chemical Engineering was established in 1963 with an Undergraduate programme in Chemical Engineering. A Master’s course in Equipment and Plant Design was initiated in 1970. Later on, two new Master’s courses namely Industrial Pollution Abatement and Advanced Transfer Processes were inducted. Subsequently, the specialisation of Equipment and Plant Design was redesigned, updated and re-christened as Computer Aided Process Plant Design. At present, the Department is running three post-graduate programmes leading to M.Tech. (Chem.) degree with specialization in Computer Aided Process Plant Design, Industrial Pollution Abatement and Industrial Safety & Hazards Management."));
        mMap.addMarker(new MarkerOptions().position(meta).title("Metallurgy Department,IITR").snippet("METES' the Metallurgical Engineering Society, is a student organization of the Department of Metallurgical and Materials Engineering, IIT Roorkee. It was the year 1967 when this organization came in existence and since then it has been playing a significant role in the department. It acts as an interface between student and the faculty, thereby, removing the gap between them."));
        mMap.addMarker(new MarkerOptions().position(ece).title("Electronics and Communication Engineering Department,IITR").snippet("In view of the increasing importance of the Electronics Engineering discipline, a separate Department of Electronics & Communication Engineering was established in 1964 to offer Bachelor of engineering degree in Electronics & Communication and Master of Engineering degrees in (a) Advanced Electronics and (b) Applied Electronics & Servomechanisms."));
        mMap.addMarker(new MarkerOptions().position(civ).title("Civil Engineering Department,IITR").snippet("The Department of Civil Engineering at the Indian Institute of Technology, Roorkee is the oldest and the largest in the country. It was established on November 25, 1847 as Roorkee Civil Engineering College and renamed as Thomason College of Civil Engineering in 1854. The department has produced several eminent engineers who have made significant contributions in the planning and execution of Civil Engineering projects in India as well as abroad."));
    }


    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null)
        {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }
    public  void changeType(View view)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch(v.getId())
        {
            case R.id.B_search:
                EditText tf_location =  findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList;


                if(!location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                        if(addressList != null)
                        {
                            for(int i = 0;i<addressList.size();i++)
                            {
                                LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.B_hospital:
                mMap.clear();
                String hospital = "hospital";
                String url = getUrl(latitude, longitude, hospital);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();
                break;


            case R.id.B_school:
                mMap.clear();
                String school = "school";
                url = getUrl(latitude, longitude, school);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Schools", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_restaurant:
                mMap.clear();
                String resturant = "restuarant";
                url = getUrl(latitude, longitude, resturant);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Restaurants", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
