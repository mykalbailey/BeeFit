package rasbeeco.beefiteats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity2 extends AppCompatActivity {

    Map<String, String> creds2 = new HashMap<String, String>();
    Map<String, Object> profile = new HashMap<String, Object>();
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();

        Button signUp = findViewById(R.id.SignUpSU);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        Spinner dd = findViewById(R.id.SignUpDD);
        String[] goals = {"Gain","Maintain","Lose"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, goals);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dd.setAdapter(adapter);

    }
    private void signUp(){
        if(getCreds()){
            Bundle extras = getIntent().getExtras();
            //Sign up to firebase
            String[] creds= {"First Name","Last Name","Email","Password","Birth Date"};

            for(Map.Entry<String, String> cred : creds2.entrySet()){
                profile.put(cred.getKey(),cred.getValue());
            }
            for(int i = 0;i<creds.length;i++){
                profile.put(creds[i].toString(),extras.getString(creds[i].toString()));
            }
            profile.put("Start Weight",creds2.get("Current Weight"));
            profile.put("Progress","0");


            mAuth.createUserWithEmailAndPassword(profile.get("Email").toString(),profile.get("Password").toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        System.out.println("Successful Sign up");
                        mAuth.signInWithEmailAndPassword(profile.get("Email").toString(),profile.get("Password").toString());
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                        myRef.child("profile").updateChildren(profile);


                        DatabaseReference myRef2 = myRef.child("preload");
                        preloadFoods(myRef2);

                        Done();
                    }
                    if(!task.isSuccessful()){
                        return;
                    }
                }
            });
        }
        if(mAuth.getCurrentUser() != null){
            Done();
        }

    }
    private void Done(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        if(SignUpActivity.instance != null){
            SignUpActivity.instance.finish();

        }
        finish();
    }
    private boolean getCreds(){
        boolean comp = true;

        EditText gweight = findViewById(R.id.gweight);
        EditText cweight = findViewById(R.id.cweight);
        Spinner dd = findViewById(R.id.SignUpDD);

        String goal = String.valueOf(dd.getSelectedItem());

        List<EditText> input = new ArrayList<EditText>();
        input.add(cweight);
        input.add(gweight);

        String[] creds = {"Current Weight","Goal Weight","Goal"};


        for(int i =0;i<input.size();i++){
            if(input.get(i).getText().toString().equals("")){
                Toast.makeText(this, (String)creds[i] + " field is empty",
                        Toast.LENGTH_LONG).show();
                comp=false;
                continue;
            }
            else{
                creds2.put(creds[i],input.get(i).getText().toString());
            }
        }
        creds2.put("Goal",goal);

        return comp;
    }
    private void preloadFoods(DatabaseReference myRef2){



        Map<String,Object> taskMap1 = new HashMap<>();
        taskMap1.put("fname", "StrawBerry");
        taskMap1.put("ssize", "100");
        taskMap1.put("nserv", "1");
        taskMap1.put("fats", "0");
        taskMap1.put("prots", "1");
        taskMap1.put("carbs", "12");

        Map<String,Object> taskMap2 = new HashMap<>();
        taskMap2.put("fname", "Bread");
        taskMap2.put("ssize", "28");
        taskMap2.put("nserv", "1");
        taskMap2.put("fats", "1");
        taskMap2.put("prots", "4");
        taskMap2.put("carbs", "12");

        Map<String,Object> taskMap3 = new HashMap<>();
        taskMap3.put("fname", "Chicken Breast");
        taskMap3.put("ssize", "28");
        taskMap3.put("nserv", "1");
        taskMap3.put("fats", "2");
        taskMap3.put("prots", "19");
        taskMap3.put("carbs", "0");

        myRef2.child(taskMap1.get("fname").toString()).updateChildren(taskMap1);
        myRef2.child(taskMap2.get("fname").toString()).updateChildren(taskMap2);
        myRef2.child(taskMap3.get("fname").toString()).updateChildren(taskMap3);
    }
}
