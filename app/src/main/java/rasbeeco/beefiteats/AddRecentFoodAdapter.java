package rasbeeco.beefiteats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rasb on 2018-12-05.
 */

public class AddRecentFoodAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private ArrayList<RecentFoods> mData = new ArrayList<RecentFoods>();

    public AddRecentFoodAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addItem(final RecentFoods item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddRecentFoodAdapter.ViewHolder holder = null;

        if (convertView == null) {
            holder = new AddRecentFoodAdapter.ViewHolder();

            convertView = mInflater.inflate(R.layout.recentfood_itemlist, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.RecentfoodName);
            holder.textView2 = (TextView) convertView.findViewById(R.id.RecentCarbs);
            holder.textView3 = (TextView) convertView.findViewById(R.id.RecentProts);
            holder.textView4 = (TextView) convertView.findViewById(R.id.RecentFats);

            convertView.setTag(holder);
        }else{
            holder = (AddRecentFoodAdapter.ViewHolder) convertView.getTag();
        }


        holder.textView1.setText(mData.get(position).fname);
        holder.textView2.setText("Carbs: " + mData.get(position).carbs + "g");
        holder.textView3.setText("Prots: " + mData.get(position).prots + "g");
        holder.textView4.setText("Fats: " + mData.get(position).fats + "g");

        convertView.setTag(holder);

        return convertView;
    }
    public static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
    }
    public RecentFoods getFood(int position){
        return mData.get(position);
    }
}
