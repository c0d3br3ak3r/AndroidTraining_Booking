package andtrain.com.androidtraining_booking;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //To call back the main activity implementation of the function (IMPORTANT) - Added by me...
    CommInterface mCallback = null;

    //all Other operations on the View should be performed in another callback, onViewCreated
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String menuItems[] = getResources().getStringArray(R.array.menuItems);
        ListAdapter my_menu_list = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,menuItems);
        setListAdapter(my_menu_list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if(getActivity()!=null) {
            mCallback = (CommInterface) getActivity();
            System.out.println("getActivity() callback on attach called!! -- " + getActivity());
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (CommInterface) activity;
        System.out.println("callback defined using onAttach(Activity) method for API 22 -!! -- " + activity);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        String buttonClicked = getListView().getItemAtPosition(position).toString();
        Object frag = null;
        switch (buttonClicked) {
            case "About Us":
                frag = new AboutFragment();
                break;

            case "My Details":
                frag = new UserDetailsFragment();
                break;
        }

        if (mCallback != null && frag!=null) {
            mCallback.loadFragmentContent(frag, getListView().getItemAtPosition(position).toString());
        }

        //Toast.makeText(getActivity(),getListView().getItemAtPosition(position).toString() + " Loaded",Toast.LENGTH_LONG).show(); //show a toast message
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public interface CommInterface {
        public void loadFragmentContent(Object fg, String message);
    }

    public interface OnFragmentInteractionListner {
    }
}
