package rasbeeco.beefiteats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;


class diaryArrayAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_AFOOD = 2;

    private ArrayList<DiaryObject> mData = new ArrayList<DiaryObject>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();
    private TreeSet<Integer> aFoodButton = new TreeSet<Integer>();

    String[] titles = {"Breakfast","Lunch","Dinner","Snacks"};

    ArrayList<DiaryObject> bfst = new ArrayList<DiaryObject>();
    ArrayList<DiaryObject> lnch = new ArrayList<DiaryObject>();
    ArrayList<DiaryObject> dnnr = new ArrayList<DiaryObject>();
    ArrayList<DiaryObject> snck = new ArrayList<DiaryObject>();


    private LayoutInflater mInflater;

    public diaryArrayAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addItem(final DiaryObject item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void addSectionHeaderItem(final DiaryObject item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }
    public void addFoodButtonItem(final DiaryObject item){
        //item.organize = "button";
        mData.add(item);
        aFoodButton.add(mData.size() - 1);
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        if(sectionHeader.contains(position)){
            return TYPE_SEPARATOR;
        }
        else if(aFoodButton.contains(position)){
            return TYPE_AFOOD;
        }
        else{
            return TYPE_ITEM;
        }
        //return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public DiaryObject getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.diary_item_list, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.diaryItem);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.diary_item_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.diaryHeader);
                    break;
                case TYPE_AFOOD:
                    convertView = mInflater.inflate(R.layout.diary_item_afood, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.diaryAFood);
                    break;

            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mData.get(position).fname);
        //System.out.println(position + ": " +mData.get(position).organize.toString());

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
    public void OragnizeData(){
        System.out.println("Organize");
        bfst.clear();
        lnch.clear();
        dnnr.clear();
        snck.clear();
        //ArrayList<DiaryObject> newData = new ArrayList<DiaryObject>();
        for(int i =0;i<mData.size();i++){
            if(mData.get(i).mType != null){
                if(mData.get(i).mType.equals("Breakfast")){
                    bfst.add(mData.get(i));
                }
                if(mData.get(i).mType.equals("Lunch")){
                    lnch.add(mData.get(i));
                }
                if(mData.get(i).mType.equals("Dinner")){
                    dnnr.add(mData.get(i));
                }
                if(mData.get(i).mType.equals("Snacks")){
                    snck.add(mData.get(i));
                }
            }
        }

        ArrayList<ArrayList<DiaryObject>> lists = new ArrayList<ArrayList<DiaryObject>>();
        lists.clear();
        lists.add(bfst);
        lists.add(lnch);
        lists.add(dnnr);
        lists.add(snck);

        ArrayList<DiaryObject> headers = new ArrayList<DiaryObject>();
        headers.clear();
        for(int k = 0;k<titles.length;k++){
            headers.add(new DiaryObject(String.valueOf(titles[k])));
            //System.out.println(headers.toString());
        }

        clearAdapter();
        System.out.println(mData.toString());
        //Data is being set hear
        for(int j = 0;j<lists.size();j++){
            addSectionHeaderItem(headers.get(j));
            System.out.println("j:"+ j +": " + headers.get(j).fname.toString());

            for(int l=0;l<lists.get(j).size();l++){
                addItem(lists.get(j).get(l));
                System.out.println("l: " + l + ": " + lists.get(j).get(l).toString());
            }

            DiaryObject aaFood = new DiaryObject(String.valueOf("AddFood"));
            aaFood.organize=(headers.get(j).fname.toString());
            addFoodButtonItem(aaFood);
            //System.out.println("ad mytype = " + aaFood);
        }
        //mData = newData;
        //for(int i = 0;i<mData.size();i++){
        //    System.out.println(mData.get(i).toString());
        //}
        notifyDataSetChanged();

    }
    public void clearAdapter()
    {
        mData.clear();
        notifyDataSetChanged();
        sectionHeader.clear();
        aFoodButton.clear();
    }
    public DiaryObject getDataObj(int position){
        return mData.get(position);
    }

}
