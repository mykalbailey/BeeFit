package rasbeeco.beefiteats;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasb on 2018-11-27.
 */

public class SignUpArrayAdapter extends ArrayAdapter<String> {

    private int resourceLayout;
    private Context mContext;

    public SignUpArrayAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        String p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.signUpItem);

            if (tt1 != null) {
                tt1.setText(p.toString());
            }
        }

            return v;
    }
}
