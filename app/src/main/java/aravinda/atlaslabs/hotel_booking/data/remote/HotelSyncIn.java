package aravinda.atlaslabs.hotel_booking.data.remote;

import aravinda.atlaslabs.hotel_booking.model.Data;

public interface HotelSyncIn {

  interface OnHotelListener {
    void onHotelReceivedFailed(String msg);

    void onHotelReceivedSuccess(Data hotels);
  }
}
