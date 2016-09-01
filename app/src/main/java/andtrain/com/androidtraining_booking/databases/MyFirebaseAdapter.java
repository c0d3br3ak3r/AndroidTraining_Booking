package andtrain.com.androidtraining_booking.databases;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ararmoha on 8/31/2016.
 */
public class MyFirebaseAdapter {
    private static Firebase mref;
    public static String remote_pass;
    public static String name;
    public static String phno;
    public static String email;
    public static HashMap<String,String> getUserDetails(String username, String password) {
        mref = new Firebase("https://bookingproject-83555.firebaseio.com/users/"+username+"/password");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterator itr = dataSnapshot.getChildren().iterator()
//                while(itr.hasNext()){
//
//                }
                String data = dataSnapshot.getValue(String.class);
                System.out.println(data); //got password yay!!
                remote_pass = data;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        if(!password.equals(remote_pass)) {
            return null;
        } else {
            HashMap<String,String> hmap = new HashMap<String,String>();
            hmap.put("username",username);
            hmap.put("phno","9876567890");
            hmap.put("email","testfirebase.com");
            hmap.put("name","testfirebase");
            return hmap;
        }
    }
}
