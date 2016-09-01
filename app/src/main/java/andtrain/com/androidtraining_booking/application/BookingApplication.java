package andtrain.com.androidtraining_booking.application;

import com.firebase.client.Firebase;

/**
 * Created by ararmoha on 8/31/2016.
 */
public class BookingApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
