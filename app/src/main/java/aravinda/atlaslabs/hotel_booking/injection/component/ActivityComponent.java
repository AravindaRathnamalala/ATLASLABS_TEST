package aravinda.atlaslabs.hotel_booking.injection.component;

import dagger.Subcomponent;
import aravinda.atlaslabs.hotel_booking.ui.home.MainActivity;
import aravinda.atlaslabs.hotel_booking.injection.PerActivity;
import aravinda.atlaslabs.hotel_booking.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity mainActivity);
}
