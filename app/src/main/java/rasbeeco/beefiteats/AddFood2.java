package rasbeeco.beefiteats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddFood2 extends AppCompatActivity {

    DatabaseReference dref;
    DatabaseReference Ref;
    TabHost host;
    private AddRecentFoodAdapter ARFAdapter;
    String fname;
    boolean doesExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food2);

        host = (TabHost)findViewById(R.id.TabHost);

        host.setup();


        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Recent");
        spec.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                View wv = getLayoutInflater().inflate(R.layout.activity_add_food_recent, host.getTabContentView(), false);
                return wv;
            }
        });
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("New Food");
        spec.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                View wv = getLayoutInflater().inflate(R.layout.activity_add_food, host.getTabContentView(), false);
                Button addFoodButton = wv.findViewById(R.id.AddFoodNew);
                addFoodButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddNewFood();
                    }
                });
                return wv;
            }
        });
        host.addTab(spec);

        ListView lv = findViewById(R.id.addRecentFood);

        ARFAdapter = new AddRecentFoodAdapter(getBaseContext());

        lv.setAdapter(ARFAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Recent Food Clicked: " + position);
                RecentFoods rf = ARFAdapter.getFood(position);
                System.out.println(rf.toString());
                addRecentFood(rf);
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        dref= FirebaseDatabase.getInstance().getReference(currentUser.getUid()).child("preload");

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                RecentFoods RF = new RecentFoods(dataSnapshot);
                ARFAdapter.addItem(RF);
                ARFAdapter.notifyDataSetChanged();
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

    }

    private void AddNewFood(){
        EditText[] fields = getFields();
        if(Verify(fields)){


            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            Ref = database.getReference(currentUser.getUid());
            DatabaseReference myRef = Ref.child("foods");

            String[] date = getDate();
            String mType= getIntent().getStringExtra("mtype");
            System.out.println("Meal type: " + mType);

            DatabaseReference preload = Ref.child("preload");

            doesExist = false;


            this.fname = fields[0].getText().toString();

            preload.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.toString().equals(fname.toString())){
                        doesExist = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if(doesExist){
                Toast.makeText(this, "Food name already exists",
                        Toast.LENGTH_LONG).show();
                return;
            }
            else{
                String time = getTime();

                DatabaseReference myRef2 =  myRef.child(fields[0].getText().toString()+"@"+time);

                Map<String,Object> taskMap = new HashMap<>();
                taskMap.put("fname", fields[0].getText().toString());
                taskMap.put("ssize", fields[1].getText().toString());
                taskMap.put("nserv", fields[2].getText().toString());
                taskMap.put("fats", fields[3].getText().toString());
                taskMap.put("prots", fields[4].getText().toString());
                taskMap.put("carbs", fields[5].getText().toString());
                taskMap.put("date", getDate2());
                taskMap.put("mtype", mType.toString());
                taskMap.put("time",time);

                myRef2.updateChildren(taskMap);

                Map<String,Object> taskMap2 = new HashMap<>();
                taskMap2.put("fname", fields[0].getText().toString());
                taskMap2.put("ssize", fields[1].getText().toString());
                taskMap2.put("nserv", fields[2].getText().toString());
                taskMap2.put("fats", fields[3].getText().toString());
                taskMap2.put("prots", fields[4].getText().toString());
                taskMap2.put("carbs", fields[5].getText().toString());

                preload.child(taskMap2.get("fname").toString()).updateChildren(taskMap);

                finish();
            }

        }
    }
    //makes sure form is filled out
    boolean Verify(EditText[] fields){
        for(int i = 0; i < fields.length; i++){
            String text = fields[i].getText().toString();
            if(text.matches("")){
                Toast.makeText(this, "Missing Info", Toast.LENGTH_SHORT).show();
                return false;
            }
            Log.d("test",text);
        }
        return true;
    }
    EditText[] getFields(){
        EditText fname = findViewById(R.id.nfood_fname);
        EditText ssize = findViewById(R.id.nfood_ssize);
        EditText nserv = findViewById(R.id.nfood_nserv);
        EditText fats = findViewById(R.id.nfood_fats);
        EditText prots = findViewById(R.id.nfood_prots);
        EditText carbs = findViewById(R.id.nfood_carbs);
        EditText notes = findViewById(R.id.nfood_notes);


        EditText[] fields = {fname,ssize,nserv,fats,prots,carbs};

        return fields;
    }

    public class newFood{
        String fname,ssize,nserv,fats,prots,carbs,notes;

        public void main(String[] args){
            EditText[] fields = getFields();

            this.fname = fields[0].getText().toString();
            this.ssize = fields[1].getText().toString();
            this.nserv = fields[2].getText().toString();
            this.fats = fields[3].getText().toString();
            this.prots = fields[4].getText().toString();
            this.carbs = fields[5].getText().toString();
            this.notes = fields[6].getText().toString();
        }
    }
    private String[] getDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(date);

        String[] dp = formattedDate.split("-");

        return dp;
    }
    private String getDate2(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(date);

        return formattedDate;
    }

    private void addRecentFood(final RecentFoods RF){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Number of servings of " + RF.fname + ":");
        //builder.setMessage("Are you sure you want to add " + RF.fname + " ");
        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // add a button
        builder.setPositiveButton("Log Food", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                Ref = database.getReference(currentUser.getUid());
                DatabaseReference myRef = Ref.child("foods");

                String mType= getIntent().getStringExtra("mtype");

                String time = getTime();
                DatabaseReference myRef2 =  myRef.child(RF.fname.toString()+"@"+time);

                Map<String,Object> taskMap = new HashMap<>();

                taskMap.put("fname", RF.fname);
                taskMap.put("ssize", RF.sSize);
                taskMap.put("nserv", (Integer.parseInt(input.getText().toString())));
                taskMap.put("fats", RF.fats);
                taskMap.put("prots", RF.prots);
                taskMap.put("carbs", RF.carbs);
                taskMap.put("date", getDate2());
                taskMap.put("mtype", mType.toString());
                taskMap.put("time",time);

                myRef2.updateChildren(taskMap);

                finish();
            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private String getTime(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(date);

        System.out.println(formattedDate);

        return formattedDate;
    }
}
