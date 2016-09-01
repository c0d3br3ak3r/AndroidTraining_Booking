package andtrain.com.androidtraining_booking.databases;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Created by ararmoha on 8/31/2016.
 */
public class MyFirebaseAdapter {
    private static Firebase mref;
    public static Map<String,String> hmap;
    public static HashMap<String,String> getUserDetails(String username, String password) {
        mref = new Firebase("https://bookingproject-83555.firebaseio.com/users/"+username);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hmap = dataSnapshot.getValue(Map.class);
                System.out.println("Got the data..." + hmap.get("password"));
                System.out.println("finished!");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        if(hmap!=null && password.equals(hmap.get("password"))) {
            return new HashMap<String,String>(hmap);
        } else {
            return null;
        }
    }
}
