package andtrain.com.androidtraining_booking.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import andtrain.com.androidtraining_booking.R;

public class ResultActivity extends Activity {

    @Override
    public void onBackPressed() {
        //Go to loginActivity
        Intent intent = new Intent("andtrain.com.androidtraining_booking.activities.LoginActivity");
        startActivity(intent);
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int flag = 0;
        Intent fromIntent = getIntent();
        String frompage = fromIntent.getStringExtra("FromPage");
        String resString = "Hello....";

        if(frompage.equals("SignUpPage")) {
            resString = "Hello ! Successfully created an account. You can go back and now login to your account";
        } else if (frompage.equals("SignInPage")) {
            if("true".equals((fromIntent.getStringExtra("valid")))) {
                flag = 1;
                Intent intent = new Intent("andtrain.com.androidtraining_booking.activities.UserLandingHomePageActivity"); //Start landing home page activity
                intent.putExtra("username",fromIntent.getStringExtra("username"));
                intent.putExtra("password",fromIntent.getStringExtra("password"));
                startActivity(intent);
            } else {
                resString = "Your Credentials are invalid. Please try again.";
            }
        }

        //failure or signup case
        if(flag!=1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.resactivity);
            LinearLayout innerLayout = new LinearLayout(this);            //create a new layout and add that layout to relative layout ?
            innerLayout.setOrientation(LinearLayout.VERTICAL);

            TextView txview = new TextView(this);
            txview.setText(resString);
            innerLayout.addView(txview);
            /*if ("true".equals((fromIntent.getStringExtra("valid")))) {

                String name = fromIntent.getStringExtra("Name");
                String email = fromIntent.getStringExtra("Email");
                String phno = fromIntent.getStringExtra("phno");
                TextView name_widget = new TextView(this);
                name_widget.setText(name);
                innerLayout.addView(name_widget);
                TextView email_widget = new TextView(this);
                email_widget.setText(email);
                innerLayout.addView(email_widget);
                TextView phno_widget = new TextView(this);
                phno_widget.setText(phno);
                innerLayout.addView(phno_widget);


            }*/

            layout.addView(innerLayout);
        }
    }
}
