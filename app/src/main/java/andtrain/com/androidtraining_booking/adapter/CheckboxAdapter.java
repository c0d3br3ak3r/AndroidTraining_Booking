package andtrain.com.androidtraining_booking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

/**
 * Created by ararmoha on 6/4/2016.
 */
public class CheckboxAdapter extends BaseAdapter {
    private Context mCtx;
    public CheckBox[] chkbox_matrix = new CheckBox[7];

    //constructor
    public CheckboxAdapter(Context ctx) {
        mCtx = ctx;
    }

    @Override
    public int getCount() {
        return chkbox_matrix.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckBox chkbox = new CheckBox(mCtx);
        return chkbox;
    }
}
