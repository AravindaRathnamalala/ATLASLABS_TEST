package aravinda.atlaslabs.hotel_booking.ui.home;

import java.util.List;

import aravinda.atlaslabs.hotel_booking.model.Data;
import aravinda.atlaslabs.hotel_booking.model.Details;
import aravinda.atlaslabs.hotel_booking.ui.base.BaseView;

public interface MainView extends BaseView {
  void onHotelReceived(Data hotels);

  void showErrorMessage(String msg);

  void showToast(String msg);

  void showProgressDialog();

  void hideProgressDialog();

  void onGetHotelsByLocation(List<String> hotel);

  void onGetLocations(List<String> location);

  void getHotelDetails(Details details);

  void getHotelsByPrice(List<String> hotel);
}
