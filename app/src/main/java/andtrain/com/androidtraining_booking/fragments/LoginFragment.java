package andtrain.com.androidtraining_booking.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import andtrain.com.androidtraining_booking.R;
import andtrain.com.androidtraining_booking.adapter.AndroidTrainingAppDatabaseAdapter;
import andtrain.com.androidtraining_booking.databases.MyFirebaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        dbadapter = new AndroidTrainingAppDatabaseAdapter(rootView.getContext()); //initializing database
        buttonEventListeners(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    public static Button login_btn;
    private static AndroidTrainingAppDatabaseAdapter dbadapter;

    public void buttonEventListeners(final View rootView) {
        login_btn = (Button)rootView.findViewById(R.id.button);
        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("andtrain.com.androidtraining_booking.activities.ResultActivity");
                        intent.putExtra("FromPage", "SignInPage");
                        String username = ((EditText)rootView.findViewById(R.id.editText)).getText().toString();
                        String password = ((EditText)rootView.findViewById(R.id.editText2)).getText().toString();
                        //dbadapter.open();
                        //HashMap<String,String> hmap = dbadapter.getLoginCredentials(((EditText)rootView.findViewById(R.id.editText)).getText().toString(), ((EditText)rootView.findViewById(R.id.editText2)).getText().toString());
                        //dbadapter.close();
                        HashMap<String,String> hmap = null;
                        hmap = MyFirebaseAdapter.getUserDetails(username,password);
                        if(hmap!=null) {
                            intent.putExtra("valid","true");
                            intent.putExtra("Name", hmap.get("name"));
                            intent.putExtra("Email", hmap.get("email"));
                            intent.putExtra("phno", hmap.get("phno"));
                            intent.putExtra("username", hmap.get("username"));
                        } else {
                            intent.putExtra("valid","false");
                        }
                        startActivity(intent);
                    }
                }
        );
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        comm = (OnSwitchListenerCommInterface) context;

//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
