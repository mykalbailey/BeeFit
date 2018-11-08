package rasbeeco.beefiteats;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class diaryFragment extends android.support.v4.app.Fragment {

    DatabaseReference dref;

    private View myFragView;
    private diaryArrayAdapter mAdapter;
    String[] titles = {"Breakfast","Lunch","Dinner","Snacks"};
    String[] items ={"Eggs","Sandwich","Steak Dinner","Chocolate Chip Cookie","Chips"};
    String[] bfast = {"Eggs"};
    String[] lnch = {"Sandwich"};
    String[] dnnr = {"Steak Dinner"};
    String[] sncks = {"Chocolate Chip Cookie","Chips"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toolbar toolbar = (Toolbar) myFragView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        myFragView = inflater.inflate(R.layout.fragment_diary, container, false);

        ListView lv = (ListView) myFragView.findViewById(R.id.diaryListView);

        int headerCount = 0;

        mAdapter = new diaryArrayAdapter(getActivity());

        mAdapter.addSectionHeaderItem("Breakfast");
        for(int i = 0; i < bfast.length; i++){
            mAdapter.addItem(bfast[i]);
        }
        //mAdapter.addItem("Add Food");
        mAdapter.addSectionHeaderItem("Lunch");
        for(int i = 0; i < lnch.length; i++){
            mAdapter.addItem(lnch[i]);
        }
        //mAdapter.addItem("Add Food");
        mAdapter.addSectionHeaderItem("Dinner");
        for(int i = 0; i < dnnr.length; i++){
            mAdapter.addItem(dnnr[i]);
        }
        //mAdapter.addItem("Add Food");
        mAdapter.addSectionHeaderItem("Snacks");
        for(int i = 0; i < sncks.length; i++){
            mAdapter.addItem(sncks[i]);
        }
        //mAdapter.addItem("Add Food");

        /*
        for(int i = 0; i < titles.length; i++){
            for(int k = 0; k < bfast.length; k++){

            }
        }

        for (int i = 0; i < items.length; i++) {

            if (headerCount < 4) {
                mAdapter.addSectionHeaderItem(titles[headerCount]);

                headerCount++;
            }
            mAdapter.addItem(items[i]);
            if(i < items.length){
                mAdapter.addItem("Add Food");
            }
        }
        lv.setAdapter(mAdapter);
        */
        lv.setAdapter(mAdapter);

        dref= FirebaseDatabase.getInstance().getReference("User1");
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mAdapter.addItem(dataSnapshot.child("fname").getValue().toString());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return myFragView;
    }


    /*public void addTitles(ListView lv){
        for(int i=0;i<titles.length;i++){
            TableRow row = new TableRow(getActivity());
            Resources resource = getActivity().getResources();
            row.setBackgroundColor(Color.YELLOW);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            //lp.height = 100;
            //row.setMinimumHeight(30);
            //row.setLayoutParams(lp);


            TextView title = new TextView(getActivity());
            title.setText(titles[i]);
            title.setTextColor(Color.BLACK);
            title.setTextSize(24);

            row.addView(title);

            lv.addView(row);
        }

    }*/

}
