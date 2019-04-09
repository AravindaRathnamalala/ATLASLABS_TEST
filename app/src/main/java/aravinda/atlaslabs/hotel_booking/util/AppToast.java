package aravinda.atlaslabs.hotel_booking.util;

import android.content.Context;
import android.widget.Toast;



public class AppToast {

  /**
   * Show toast
   */
  public static void showToast(Context context, String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }
}
