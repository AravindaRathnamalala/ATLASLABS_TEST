package aravinda.atlaslabs.hotel_booking.ui.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import aravinda.atlaslabs.hotel_booking.data.local.DBHelper;
import aravinda.atlaslabs.hotel_booking.data.remote.HotelSync;
import aravinda.atlaslabs.hotel_booking.data.remote.HotelSyncIn;
import aravinda.atlaslabs.hotel_booking.injection.ApplicationContext;
import aravinda.atlaslabs.hotel_booking.model.Data;
import aravinda.atlaslabs.hotel_booking.model.Details;
import aravinda.atlaslabs.hotel_booking.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainView> {
    private HotelSync hotelSync;
    private Context context;
    private final DBHelper mydb;

    @Inject
    public MainPresenter(@ApplicationContext Context context, HotelSync hotelSync) {
        this.hotelSync = hotelSync;
        this.context = context;
        mydb = new DBHelper(context);
    }

    public void getHotels() {
        getView().showProgressDialog();
        hotelSync.getHotels(new HotelSyncIn.OnHotelListener() {
            @Override
            public void onHotelReceivedFailed(String msg) {
                getView().hideProgressDialog();
                getView().showErrorMessage(msg);
            }

            @Override
            public void onHotelReceivedSuccess(Data hotels) {
                getView().hideProgressDialog();
                getView().onHotelReceived(hotels);
            }
        });
    }

    public void getLocationList(){
        List<String> location = new ArrayList<>();
        location =  mydb.getLocations();
        getView().onGetLocations(location);
    }

    public void getHotelList(String location){
        List<String> hotels = new ArrayList<>();
        hotels =  mydb.getHotels(location);
        getView().onGetHotelsByLocation(hotels);
    }

    public void getDetails(String hotel){
        Details details = new Details();
        details = mydb.getHotelDetails(hotel);
        getView().getHotelDetails(details);
    }

    public void getHotelsByPrice(String price){
        List<String> hotelsByPrice = new ArrayList<>();
        hotelsByPrice =  mydb.getHotelsByPrice(price);
        getView().getHotelsByPrice(hotelsByPrice);

    }



    @Override
    public void attachView(MainView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
