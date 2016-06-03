package andtrain.com.androidtraining_booking;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements MenuFragment.CommInterface, AboutFragment.OnFragmentInteractionListener, UserDetailsFragment.OnFragmentInteractionListener {
    @Override
    public void loadFragmentContent(Object fg, String message) {
        if(fg!=null) {
            if(getFragmentManager().getBackStackEntryCount() > 0) {
                System.out.println("BackStack is not null.. Popping elements");
                getFragmentManager().popBackStackImmediate();
            }

            FragmentTransaction txn = getSupportFragmentManager().beginTransaction();
            txn.add(R.id.contentFragmentContainer, (Fragment) fg);
            txn.addToBackStack("ContentFrag");
            txn.commit();

            Toast.makeText(this, message + " Loaded",Toast.LENGTH_LONG).show(); //show a toast message
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.contentFragmentContainer) != null) {
            MenuFragment menu_frag = new MenuFragment();
            android.app.FragmentTransaction txn = getFragmentManager().beginTransaction();
            txn.add(R.id.contentFragmentContainer, menu_frag);
            txn.addToBackStack("MenuFragment");
            txn.commit();
        }
        btnClickListeners();
    }

    protected void btnClickListeners() {
        Button btn = (Button) findViewById(R.id.MenuButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().getBackStackEntryCount()>0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }

                MenuFragment menu_frag = new MenuFragment();
                // Loading ListFragment - MenuFragment - Therefore, Use getFragmentManager, not the usual getSupportFragmentManager
                //when using ListFragment use, getFragmentManager() instead of getSupportFragmentManager()
                android.app.FragmentTransaction txn = getFragmentManager().beginTransaction();
                txn.replace(R.id.contentFragmentContainer, menu_frag);
                txn.addToBackStack("MenuFragment");
                txn.commit();


                Toast.makeText(getBaseContext(),"Menu Loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
