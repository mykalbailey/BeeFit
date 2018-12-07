package rasbeeco.beefiteats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText fname,lname,dob,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(mAuth.getCurrentUser().getUid());

        fname = findViewById(R.id.sfname);
        lname = findViewById(R.id.slname);
        dob = findViewById(R.id.sdob);
        email = findViewById(R.id.semail);


        populateSettings();

    }

    public void populateSettings(){
        String[] settings = {"Name","Details","Email","Meals","Goals","Notifications","About","Logout"};
        ArrayList<String> list = new ArrayList<String>();

        for(int i =0;i<settings.length;i++){
            list.add(settings[i]);
        }

        myRef.child("profile").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().toString().equals("First Name")){
                    fname.setText(dataSnapshot.getValue().toString());
                    setOnEdit();
                }
                if(dataSnapshot.getKey().toString().equals("Last Name")){
                    lname.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Birth Date")){
                    dob.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Email")){
                    email.setText(dataSnapshot.getValue().toString());
                }
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
    private void setOnEdit(){
        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                builder.setTitle("Changed");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}
