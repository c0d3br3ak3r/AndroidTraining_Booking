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

import andtrain.com.androidtraining_booking.R;
import andtrain.com.androidtraining_booking.adapter.AndroidTrainingAppDatabaseAdapter;
import andtrain.com.androidtraining_booking.databases.MyFirebaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SignUpFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        dbadapter = new AndroidTrainingAppDatabaseAdapter(rootView.getContext());
        btnClickListeners(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    public static Button submit_btn;
    private AndroidTrainingAppDatabaseAdapter dbadapter;

    public void btnClickListeners(final View rootView) {
        submit_btn = (Button) rootView.findViewById(R.id.button3);
        submit_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("andtrain.com.androidtraining_booking.activities.ResultActivity");
                        intent.putExtra("FromPage", "SignUpPage");
                        dbadapter.open();
                        String username = ((EditText)rootView.findViewById(R.id.editText7)).getText().toString();
                        String password = ((EditText)rootView.findViewById(R.id.editText6)).getText().toString();
                        String name = ((EditText)rootView.findViewById(R.id.editText3)).getText().toString();
                        String email = ((EditText)rootView.findViewById(R.id.editText4)).getText().toString();
                        String phno = ((EditText)rootView.findViewById(R.id.editText5)).getText().toString();
                        dbadapter.insertEntry(username,password,name,email,phno);
                        dbadapter.close();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
