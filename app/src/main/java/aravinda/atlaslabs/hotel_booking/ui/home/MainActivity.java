package aravinda.atlaslabs.hotel_booking.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import aravinda.atlaslabs.hotel_booking.R;
import aravinda.atlaslabs.hotel_booking.data.local.DBHelper;
import aravinda.atlaslabs.hotel_booking.model.Data;
import aravinda.atlaslabs.hotel_booking.model.Details;
import aravinda.atlaslabs.hotel_booking.model.Hotel;
import aravinda.atlaslabs.hotel_booking.model.LocationDetails;
import aravinda.atlaslabs.hotel_booking.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    @BindView(R.id.locationsSpinner)
    Spinner spinnerLocations;
    @BindView(R.id.hotelsSpinner)
    Spinner spinnerHotels;

    @Inject
    MainPresenter presenter;
    ArrayList<Hotel> hotellList;
    @BindView(R.id.hotelsByAmountSpinner)
    Spinner amountSpinner;
    @BindView(R.id.tvHotelName)
    TextView tvHotelName;
    @BindView(R.id.tvAmount)
    TextView tvAmount;
    @BindView(R.id.tvContact)
    TextView tvContact;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.searchBtn)
    Button btnSearch;
    @BindView(R.id.amountEdt)
    EditText edtAmount;
    private DBHelper mydb;
    private GoogleMap mMap;
    private Marker mLocation = null;
    double lngitude;
    double latitude;
    LocationDetails locationDetails = new LocationDetails();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        activityComponent().inject(this);
        presenter.attachView(this);
        presenter.getHotels();
        hotellList = new ArrayList<>();
        mydb = new DBHelper(this);
        presenter.getLocationList();

        spinnerLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getHotelList(spinnerLocations.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerHotels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getDetails(spinnerHotels.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        amountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getDetails(amountSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onHotelReceived(Data hotels) {
        hotellList.addAll(hotels.getHotel());
        mydb.deleteAllRaws();
        for (int i = 0; i < hotellList.size(); i++) {
            mydb.insertHotel(hotellList.get(i).getHotel(), String.valueOf(hotellList.get(i).getLongitude()), String.valueOf(hotellList.get(i).getLatitude()),
                    String.valueOf(hotellList.get(i).getContact()), hotellList.get(i).getEmail(), hotellList.get(i).getLocation(), String.valueOf(hotellList.get(i).getAmount()));
        }
    }

    public void setDataToSpinner(Spinner spinner, List list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void showErrorMessage(String msg) {
        super.showErrorMessage(msg);
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        super.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        super.hideProgressDialog();
    }

    @Override
    public void onGetHotelsByLocation(List<String> hotel) {
        setDataToSpinner(spinnerHotels, hotel);
    }

    @Override
    public void onGetLocations(List<String> location) {
        setDataToSpinner(spinnerLocations, location);
    }

    @Override
    public void getHotelDetails(Details details) {
        if (details != null) {
            tvHotelName.setText(details.getName());
            tvAmount.setText(details.getAmount());
            tvContact.setText(details.getContact());
            tvEmail.setText(details.getEmail());
            setMarker(details);

        }
    }

    @Override
    public void getHotelsByPrice(List<String> hotel) {
        setDataToSpinner(amountSpinner, hotel);
    }

    public void setMarker(Details details) {
        double lat = Double.parseDouble(details.getLatitude());
        double lng = Double.parseDouble(details.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lng)) // Sets the center of the map to location user
                .zoom(15)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        if (mLocation != null) {
            mLocation.remove();
            mLocation = null;
        }
        mLocation = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(details.getName()));
    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @OnClick(R.id.searchBtn)
    public void clickSearchBtn(){
        presenter.getHotelsByPrice(edtAmount.getText().toString());
    }
}


