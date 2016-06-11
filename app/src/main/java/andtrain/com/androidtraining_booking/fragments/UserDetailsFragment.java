package andtrain.com.androidtraining_booking.fragments;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

import andtrain.com.androidtraining_booking.R;
import andtrain.com.androidtraining_booking.adapter.AndroidTrainingAppDatabaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    public static AndroidTrainingAppDatabaseAdapter dbadapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent fromIntent = getActivity().getIntent();
        final String username = fromIntent.getStringExtra("username");
//        if(getActivity().findViewById(R.id.userdetails_text) != null) {
//            TextView txtview =  (TextView)getActivity().findViewById(R.id.userdetails_text);
//            txtview.setText(username);

            final EditText editName = (EditText)getActivity().findViewById(R.id.editName);
            EditText editPhone = (EditText)getActivity().findViewById(R.id.editPhone);
            EditText editEmail = (EditText)getActivity().findViewById(R.id.editEmail);

            dbadapter.open();
            HashMap<String,String> details = dbadapter.getDetails(username);
            dbadapter.close();

            editName.setText(details.get("name"));
            editPhone.setText(details.get("phno"));
            editEmail.setText(details.get("email"));

            Button update_btn = (Button) getActivity().findViewById(R.id.updatedetails);
            update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbadapter.open();
                    String name = ((EditText)getActivity().findViewById(R.id.editName)).getText().toString();
                    String email = ((EditText)getActivity().findViewById(R.id.editEmail)).getText().toString();
                    String phno = ((EditText)getActivity().findViewById(R.id.editPhone)).getText().toString();
                    dbadapter.updateEntry(username,name,email,phno);
                    dbadapter.close();
                    Toast.makeText(getActivity(),"Updated details successful.",Toast.LENGTH_LONG).show();
                }
            });

//        } else {
//            System.out.println("Activity not found .... CHeck again!!");
//        }

    }

    MenuFragment.CommInterface mCallBack;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetailsFragment newInstance(String param1, String param2) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_user_details, container, false);
        dbadapter = new AndroidTrainingAppDatabaseAdapter(rootview.getContext());
        return rootview;
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
        mCallBack = (MenuFragment.CommInterface) getActivity();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (MenuFragment.CommInterface)getActivity();
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
