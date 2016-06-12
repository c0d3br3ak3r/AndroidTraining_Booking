package andtrain.com.androidtraining_booking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.HashSet;
import java.util.Set;

import andtrain.com.androidtraining_booking.R;

/**
 * Created by ararmoha on 6/4/2016.
 */
public class CheckboxAdapter extends BaseAdapter {
    private Context mCtx;
    public CheckBox[] slots;
    public static Set<String> checkedList;
    private void initializeSlots() {
        slots = new CheckBox[48];
    }
    public CheckboxAdapter(Context ctx) {
        initializeSlots();
        checkedList = new HashSet<String>();
        mCtx = ctx;
    }

    @Override
    public int getCount() {
        return slots.length;
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return slots[position];
    }

    public static Set<String> getCheckedList(){
        return checkedList;
    }

    public static void setCheckedList(Set<String> checkedList) {
        CheckboxAdapter.checkedList = checkedList;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckBox chkbox;
        if(convertView==null) {
            chkbox = new CheckBox(mCtx);
            chkbox.setButtonDrawable(R.drawable.customcheckbox);
            String txt = "" + ((position%24))/2;
            if(position==24 || position==25) {
                txt = "12";
            }
            if(position%2==0) {
                txt += ":00 ";
            } else {
                txt += ":30 ";
            }
            if(position>=24) {
                txt += "PM";
            } else {
                txt += "AM";
            }
            chkbox.setText(txt);
            if(!getCheckedList().isEmpty()) {
                for (String slot : getCheckedList()) {
                    if (txt.equals(slot)) {
                        //System.out.println("Match found");
                        //chkbox.setEnabled(false);
                        chkbox.setClickable(false);
                        chkbox.setButtonDrawable(R.drawable.ic_slot_red);
                        break;
                    }
                }
            }


        } else {
            chkbox = (CheckBox) convertView;
        }

        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkedList.add(buttonView.getText().toString());
                } else {
                    checkedList.remove(buttonView.getText().toString());
                }
            }
        });


        return chkbox;
    }
}
