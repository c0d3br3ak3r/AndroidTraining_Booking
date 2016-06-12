package andtrain.com.androidtraining_booking.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;

import andtrain.com.androidtraining_booking.fragments.AboutFragment;
import andtrain.com.androidtraining_booking.fragments.BookingFragment;
import andtrain.com.androidtraining_booking.fragments.MenuFragment;
import andtrain.com.androidtraining_booking.R;
import andtrain.com.androidtraining_booking.fragments.UserDetailsFragment;

public class UserLandingHomePageActivity extends FragmentActivity implements MenuFragment.CommInterface, AboutFragment.OnFragmentInteractionListener, UserDetailsFragment.OnFragmentInteractionListener, BookingFragment.OnFragmentInteractionListener {
    @Override
    public void loadFragmentContent(Object fg, String message) {
        if (fg != null) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                System.out.println("BackStack is not null.. Popping elements");
                getFragmentManager().popBackStackImmediate();
            } else {
                getSupportFragmentManager().popBackStackImmediate();
            }

            FragmentTransaction txn = getSupportFragmentManager().beginTransaction();
            txn.add(R.id.contentFragmentContainer, (Fragment) fg);
            txn.addToBackStack("ContentFrag");
            txn.commit();

            Toast.makeText(this, message + " Loaded", Toast.LENGTH_LONG).show(); //show a toast message
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu_header, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Cannot go back. Use menu to navigate.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Object frag = null;
        switch (id) {
            case R.id.aboutus_option:
                frag = new AboutFragment();
                loadFragmentContent(frag, "About us");
                break;
            case R.id.booking_option:
                frag = new BookingFragment();
                loadFragmentContent(frag, "Booking");
                break;
            case R.id.mydetails_option:
                frag = new UserDetailsFragment();
                loadFragmentContent(frag, "My Details");
                break;
            case R.id.history_option:
                Toast.makeText(this, "History Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_option:
                Toast.makeText(this, "LogOut Clicked", Toast.LENGTH_SHORT).show();
                //this.finish(); //closing the activity
                Intent intent = new Intent("andtrain.com.androidtraining_booking.activities.LoginActivity");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Nothing to do here.
    }

    public void userHomeClickEvent(View view) {
        if (findViewById(R.id.contentFragmentContainer) != null) {
            //popping already added fragments
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                System.out.println("BackStack is not null.. Popping elements");
                getFragmentManager().popBackStackImmediate();
            } else {
                getSupportFragmentManager().popBackStackImmediate();
            }

            MenuFragment menu_frag = new MenuFragment();
            android.app.FragmentTransaction txn = getFragmentManager().beginTransaction();
            txn.add(R.id.contentFragmentContainer, menu_frag);
            txn.addToBackStack("MenuFragment");
            txn.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_home_page);
        if (findViewById(R.id.contentFragmentContainer) != null) {
            MenuFragment menu_frag = new MenuFragment();
            android.app.FragmentTransaction txn = getFragmentManager().beginTransaction();
            txn.add(R.id.contentFragmentContainer, menu_frag);
            txn.addToBackStack("MenuFragment");
            txn.commit();
        }
    }

    public void callNumber(View view) {
        TextView phno = (TextView) findViewById(R.id.contactNo);
        System.out.println("CAlling number..." + phno.getText());
        Intent call_intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phno.getText()));
        //checking permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            System.out.println("NO PERMISSION TO CALL....");
            return;
        } else {
            startActivity(call_intent);
        }
    }
}


//Not needed because menu action bar
    /*protected void btnClickListeners() {
        Button btn = (Button) findViewById(R.id.MenuButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }

                MenuFragment menu_frag = new MenuFragment();
                // Loading ListFragment - MenuFragment - Therefore, Use getFragmentManager, not the usual getSupportFragmentManager
                //when using ListFragment use, getFragmentManager() instead of getSupportFragmentManager()
                android.app.FragmentTransaction txn = getFragmentManager().beginTransaction();
                txn.replace(R.id.contentFragmentContainer, menu_frag);
                txn.addToBackStack("MenuFragment");
                txn.commit();


                Toast.makeText(getBaseContext(), "Menu Loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }*/