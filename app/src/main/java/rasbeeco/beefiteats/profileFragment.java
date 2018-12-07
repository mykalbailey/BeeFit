package rasbeeco.beefiteats;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class profileFragment extends android.support.v4.app.Fragment {

    View  myFragView;
    private String m_Text = "";
    private static int RESULT_LOAD_IMG = 1;
    FirebaseAuth mAuth;
    TextView username,sWeight,gWeight;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int cWeight;
    ProgressBar pb;
    String goal="Gain";
    int sw=0,cw=0,gw=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragView = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(mAuth.getCurrentUser().getUid());

        ImageView iv = myFragView.findViewById(R.id.userImage);
        username = myFragView.findViewById(R.id.userName);
        pb = myFragView.findViewById(R.id.progressBar2);
        Button wUpdate = myFragView.findViewById(R.id.weightUpdate);
        Button uGoals = myFragView.findViewById(R.id.goalsButton);
        sWeight = myFragView.findViewById(R.id.CurrentWeight);
        gWeight = myFragView.findViewById(R.id.GoalWeight);
        TextView date = myFragView.findViewById(R.id.ProfileDate);



        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });
        wUpdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("New Weight");

                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        myRef.child("profile").child("Current Weight").setValue(m_Text);
                        cWeight = Integer.parseInt(input.getText().toString());
                        //TextView thisWeight = myFragView.findViewById(R.id.CurrentWeight);
                        //thisWeight.setText(m_Text);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                updateProgress();

            }
        });
        uGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Goal: " + goal);

                String[] goals = {"Gain","Maintain","Lose"};

                final Spinner spinner = new Spinner(getContext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, goals);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                builder.setView(spinner);

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newGoal = String.valueOf(spinner.getSelectedItem());
                        myRef.child("profile").child("Goal").setValue(newGoal);
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
        FirebaseDatabase.getInstance().getReference(mAuth.getUid()).child("profile").addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().toString().equals("First Name")){
                    username.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Start Weight")){
                    sWeight.setText(dataSnapshot.getValue().toString());
                    sw = Integer.parseInt(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Goal Weight")){
                    gWeight.setText(dataSnapshot.getValue().toString());
                    gw = Integer.parseInt(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Current Weight")){
                    cWeight = Integer.parseInt(dataSnapshot.getValue().toString());
                    cw = Integer.parseInt(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Goal")){
                    goal = dataSnapshot.getValue().toString();
                }
                updateProgress();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().toString().equals("First Name")){
                    username.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Start Weight")){
                    sWeight.setText(dataSnapshot.getValue().toString());
                    sw = Integer.parseInt(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Goal Weight")){
                    gWeight.setText(dataSnapshot.getValue().toString());
                    gw = Integer.parseInt(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Current Weight")){
                    cWeight = Integer.parseInt(dataSnapshot.getValue().toString());
                    cw = Integer.parseInt(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().toString().equals("Goal")){
                    goal = dataSnapshot.getValue().toString();
                }
                updateProgress();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                updateProgress();
            }

        });

        date.setText(getDate());
        //sWeight.setText("150lbs");
        //gWeight.setText("160lbs");
        return myFragView;
    }
    private String getDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(date);

        return formattedDate;
    }
    private void getProgress() {


        TextView cWeight = myFragView.findViewById(R.id.CurrentWeight);
        TextView gWeight = myFragView.findViewById(R.id.GoalWeight);

        String nw = cWeight.getText().toString();
        String gl = gWeight.getText().toString();

        int now = Integer.parseInt(nw.replaceAll("\\D+", ""));
        int goal = Integer.parseInt(gl.replaceAll("\\D+", ""));

        //str = str.replaceAll("\\D+","");
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateProgress(){

        if(sw!=0&&cw!=0&&gw!=0&&!gWeight.getText().toString().equals("")&&!gWeight.getText().toString().equals("")){
            if(goal.equals("Gain")){
                pb.setMax(gw);
                pb.setMin(sw);
                pb.setProgress(cw,true);
            }
            if(goal.equals("Lose")){
                pb.setMax(sw);
                pb.setMin(gw);
                pb.setProgress(cw,true);
            }
            if(goal.equals("Maintain")){
                pb.setMax(gw);
                pb.setMin(sw);
                pb.setProgress(cw,true);
            }
        }
    }
}
