package rasbeeco.beefiteats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Button addFoodButton = findViewById(R.id.AddFoodNew);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewFood();
            }
        });


    }
    protected void AddNewFood(){
        EditText[] fields = getFields();
        if(Verify(fields)){

            //TODO: Find better way to organize user data

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            //newFood food = new newFood();

            Toast.makeText(this, fields[0].getText().toString(), Toast.LENGTH_SHORT).show();
            //Log.d("fname",food.fname);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(currentUser.getUid()).child("foods");

            String[] date = getDate();
            String mType= getIntent().getStringExtra("mtype");
            System.out.println("Meal type: " + mType);

            DatabaseReference myRef2 =  myRef.child(fields[0].getText().toString());

            Map<String,Object> taskMap = new HashMap<>();
            taskMap.put("fname", fields[0].getText().toString());
            taskMap.put("ssize", fields[1].getText().toString());
            taskMap.put("nserv", fields[1].getText().toString());
            taskMap.put("fats", fields[1].getText().toString());
            taskMap.put("prots", fields[1].getText().toString());
            taskMap.put("carbs", fields[1].getText().toString());
            taskMap.put("date", getDate2());
            taskMap.put("mtype", mType.toString());

            myRef2.updateChildren(taskMap);
            finish();

            //taskMap.put("notes", fields[6].getText().toString());
            //myRef.setValue(fields[0].getText().toString());
            //DatabaseReference myRef2 =  myRef.child(date[0]).child(date[1]).child(date[2]).child(fields[0].getText().toString());

            /*DatabaseReference myRef2 =  myRef.child(fields[0].getText().toString());
            myRef2.child("fname").setValue(fields[0].getText().toString());
            myRef2.child("ssize").setValue(fields[1].getText().toString());
            myRef2.child("nserv").setValue(fields[2].getText().toString());
            myRef2.child("fats").setValue(fields[3].getText().toString());
            myRef2.child("prots").setValue(fields[4].getText().toString());
            myRef2.child("carbs").setValue(fields[5].getText().toString());
            myRef2.child("date").setValue(getDate2());
            myRef2.child("mtype").setValue(mType.toString());
            //myRef.child("notes").setValue(fields[6].getText().toString());
            finish();*/
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
}
