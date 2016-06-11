package andtrain.com.androidtraining_booking.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import andtrain.com.androidtraining_booking.R;
import andtrain.com.androidtraining_booking.adapter.AndroidTrainingBookingDBAdapter;
import andtrain.com.androidtraining_booking.adapter.CheckboxAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root_view = inflater.inflate(R.layout.fragment_booking, container, false);
        dbAdapter = new AndroidTrainingBookingDBAdapter(root_view.getContext());
        return root_view;
    }


//    String[] slots;
//    String[] dates;

    /*public void initializeSlots() {
        slots = new String[48];
        for(int i=0,j=0;i<24;i++,j=j+2) {
            slots[j] = i + ":00 - " + i + ":30";
            slots[j+1] = i + ":30 - " + (i+1) + ":00";
        }
    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //added by me -- Code here..
//        GridView date_grid_view = (GridView) getActivity().findViewById(R.id.myDateGrid);
//        initializeDates();
//        ArrayAdapter<String> arr_dates_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,dates);
//        date_grid_view.setAdapter(arr_dates_adapter);

        final GridView grid_view = (GridView) getActivity().findViewById(R.id.myGridView);
//        initializeSlots();
//        ArrayAdapter<String> arr_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,slots);
        //grid_view.setAdapter(new CheckboxAdapter(getActivity()));


        final TextView txview = (TextView)getActivity().findViewById(R.id.reservationDate);
        final Calendar mycal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd ''yy", Locale.US);
        txview.setText(sdf.format(mycal.getTime()));

        CheckboxAdapter chkboxadapter_1 = new CheckboxAdapter(getActivity());
        ArrayList<String> checked_slots_1 = dbAdapter.getReservedSlotList(sdf.format(mycal.getTime()));
        Set<String> slot_set_1 = new HashSet<>();
        if(checked_slots_1!=null && !checked_slots_1.isEmpty()) {
            for (String str : checked_slots_1) {
                slot_set_1.add(str);
            }
            chkboxadapter_1.setCheckedList(slot_set_1);
        }

        grid_view.setAdapter(chkboxadapter_1);

        final DatePickerDialog.OnDateSetListener date_set_callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mycal.set(Calendar.YEAR, year);
                mycal.set(Calendar.MONTH, monthOfYear);
                mycal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                txview.setText(sdf.format(mycal.getTime()));

                //reset grid ?

                Set<String> slot_set_2 = new HashSet<>();
                ArrayList<String> checked_slots_2 = dbAdapter.getReservedSlotList(sdf.format(mycal.getTime()));
                CheckboxAdapter chkboxadapter = new CheckboxAdapter(getActivity());
                if(checked_slots_2!=null && !checked_slots_2.isEmpty()) {
                    for (String str : checked_slots_2) {
                        slot_set_2.add(str);
                    }
                    chkboxadapter.setCheckedList(null);
                    chkboxadapter.setCheckedList(slot_set_2);
                }
                grid_view.setAdapter(chkboxadapter);


            }
        };
        txview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date_set_callback,2016,mycal.get(Calendar.MONTH),1).show();
            }
        });

        Button done_btn = (Button)getActivity().findViewById(R.id.doneBtn);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = ((TextView)getActivity().findViewById(R.id.reservationDate)).getText().toString();
                Intent fromIntent = getActivity().getIntent();
                String username = fromIntent.getStringExtra("username");
                Set<String> slots = CheckboxAdapter.getCheckedList();
                dbAdapter.insertReservedSlot(username, date, slots);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public static AndroidTrainingBookingDBAdapter dbAdapter;

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
