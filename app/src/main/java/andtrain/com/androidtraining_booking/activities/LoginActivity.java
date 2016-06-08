package andtrain.com.androidtraining_booking.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import andtrain.com.androidtraining_booking.R;
import andtrain.com.androidtraining_booking.fragments.LoginFragment;
import andtrain.com.androidtraining_booking.fragments.SignUpFragment;

public class LoginActivity extends FragmentActivity implements LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(findViewById(R.id.MyFragmentContainer)!=null) {
            LoginFragment default_login_frag = new LoginFragment();
            default_login_frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.MyFragmentContainer,default_login_frag).commit();
        }
        onSwitchListeners();
    }

    @Override
    public void onBackPressed() {
        //Disabling back button action on LoginActivity
        Toast.makeText(this,"Cannot go back.",Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
    }

    public void onFragmentInteraction(Uri uri) {
        //Nothing to do.
    }

    public void onSwitchListeners() {
        Switch switch_widget = (Switch)findViewById(R.id.switch1);
        switch_widget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {  //Load signup fragment
                    SignUpFragment signup_frag = new SignUpFragment();
                    FragmentTransaction txn = getSupportFragmentManager().beginTransaction();
                    txn.replace(R.id.MyFragmentContainer, signup_frag);
                    txn.addToBackStack("SignUpFragment");
                    txn.commit();
                } else {  //Load login fragment
                    LoginFragment login_frag = new LoginFragment();
                    FragmentTransaction txn = getSupportFragmentManager().beginTransaction();
                    txn.replace(R.id.MyFragmentContainer, login_frag);
                    txn.addToBackStack("LoginFragment");
                    txn.commit();
                }
            }
        });
    }
}
