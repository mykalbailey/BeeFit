package rasbeeco.beefiteats;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class diaryFragment extends android.support.v4.app.Fragment {

    DatabaseReference dref;

    private View myFragView;
    private diaryArrayAdapter mAdapter;

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

        //TODO:Following line creates diary listview navigator:
        /*
        LayoutInflater li = getLayoutInflater();
        ViewGroup myHeader = (ViewGroup)li.inflate(R.layout.diaryheader, lv, false);
        lv.addHeaderView(myHeader, null, false);
        */
        int headerCount = 0;

        mAdapter = new diaryArrayAdapter(getActivity());
        mAdapter.OragnizeData();

        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mAdapter.getItemViewType(position) == 0){

                }else if(mAdapter.getItemViewType(position) == 2){
                    addFood(mAdapter.getItem(position).organize);
                }else{
                    return;
                }
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        dref= FirebaseDatabase.getInstance().getReference(currentUser.getUid()).child("foods");
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.toString());
                DiaryObject DO = new DiaryObject(dataSnapshot);
                mAdapter.addItem(DO);
                mAdapter.OragnizeData();
                //mAdapter.notifyDataSetChanged();
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
    protected void addFood(String mtype){
        Intent intent = new Intent(myFragView.getContext(), AddFood.class);
        intent.putExtra("mtype", mtype);
        startActivity(intent);
    }




}
