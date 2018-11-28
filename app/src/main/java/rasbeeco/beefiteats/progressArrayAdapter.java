package rasbeeco.beefiteats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by rasb on 2018-10-17.
 */

class progressArrayAdapter extends BaseAdapter { {

}
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<String[]> mData = new ArrayList<String[]>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public progressArrayAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addItem(final String[] item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void addSectionHeaderItem(final String[] item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String[] getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        progressArrayAdapter.ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new progressArrayAdapter.ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.progress_item_list, null);
                    holder.textView1 = (TextView) convertView.findViewById(R.id.macro);
                    holder.textView2 = (TextView) convertView.findViewById(R.id.currentp);
                    holder.textView3 = (TextView) convertView.findViewById(R.id.goalp);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.progress_item_header, null);
                    holder.textView1 = (TextView) convertView.findViewById(R.id.textView10);
                    holder.textView2 = (TextView) convertView.findViewById(R.id.currentt);
                    holder.textView3 = (TextView) convertView.findViewById(R.id.goalt);

                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (progressArrayAdapter.ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(mData.get(position)[0]);
        holder.textView2.setText(mData.get(position)[1]);
        holder.textView3.setText(mData.get(position)[2]);

        System.out.println(mData.size());
        for(int i = 0 ; i < 3; i++){
            System.out.println(mData.get(position)[i]);
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
    }
    public void clearAdapter()
    {
        mData.clear();
        notifyDataSetChanged();
    }

}
