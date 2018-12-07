package rasbeeco.beefiteats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableRow;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class progressFragment extends android.support.v4.app.Fragment {

    private View myFragView;
    private progressArrayAdapter mAdapter;
    private ListView lv;

    String[] titles = {" ","Current","Goal"};

    float pCarbs = 0;
    float pFats = 0;
    float pProts = 0;

    private String[] fats = {"Fats","25%","25%"};
    private String[] carbs = {"Carbs","25%","30%"};
    private String[] prots = {"Protein","50%","45%"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myFragView = inflater.inflate(R.layout.fragment_progress, container, false);
        lv = (ListView) myFragView.findViewById(R.id.progressListView);

        int headerCount = 0;

        mAdapter = new progressArrayAdapter(getActivity());
        mAdapter.addSectionHeaderItem(titles);
        mAdapter.addItem(fats);
        mAdapter.addItem(carbs);
        mAdapter.addItem(prots);
        lv.setAdapter(mAdapter);

        setUpPie();

        TableRow navigator = myFragView.findViewById(R.id.ProgressNavigate);
        navigator.setVisibility(View.GONE);

        return myFragView;
    }

    private void setUpPie(){

        //PieChart pChart = (PieChart) myFragView.findViewById(R.id.pChart);
        //List<PieEntry> entries = new ArrayList<>();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        FirebaseDatabase.getInstance().getReference(currentUser.getUid()).child("foods").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                pCarbs = pCarbs + Float.parseFloat(dataSnapshot.child("carbs").getValue().toString());
                pFats = pFats + Float.parseFloat(dataSnapshot.child("fats").getValue().toString());
                pProts = pProts + Float.parseFloat(dataSnapshot.child("prots").getValue().toString());
                setData();
                mAdapter.clearAdapter();
                Float total = pCarbs + pFats + pProts;
                int ppCarbs = Math.round(pCarbs/total*100);
                int ppFats = Math.round(pFats/total*100);
                int ppProts = Math.round(pProts/total*100);

                mAdapter.addSectionHeaderItem(titles);

                //String pfats = Math.round(pFats);

                String[] fats = {"Fats",Integer.toString(ppFats)+"%","25%"};
                String[] carbs = {"Carbs",Integer.toString(ppCarbs)+"%","30%"};
                String[] prots = {"Protein",Integer.toString(ppProts)+"%","45%"};

                mAdapter.addItem(fats);
                mAdapter.addItem(carbs);
                mAdapter.addItem(prots);

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //pCarbs = pCarbs + 2;
        //pFats = pFats + 2;
        //pProts = pProts + 3;
        setData();/*
        entries.add(new PieEntry(pFats, "Fats"));
        entries.add(new PieEntry(pCarbs, "Carbs"));
        entries.add(new PieEntry(pProts, "Protein"));

        pChart.setUsePercentValues(false);
        pChart.setClickable(false);
        pChart.setDescription(null);

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);

        Legend legend = pChart.getLegend();
        //legend.setEnabled(false);

        PieData data = new PieData(set);
        pChart.setData(data);
        pChart.invalidate();
        */
    }
    private void setData(){
        PieChart pChart = (PieChart) myFragView.findViewById(R.id.pChart);
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(pFats, "Fats"));
        entries.add(new PieEntry(pCarbs, "Carbs"));
        entries.add(new PieEntry(pProts, "Protein"));

        pChart.setUsePercentValues(false);
        pChart.setClickable(false);
        pChart.setDescription(null);

        pChart.setNoDataText("No Data");

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setColors(ColorTemplate.MATERIAL_COLORS);

        Legend legend = pChart.getLegend();
        //legend.setEnabled(false);

        PieData data = new PieData(set);
        pChart.setData(data);
        pChart.invalidate();
    }
}
