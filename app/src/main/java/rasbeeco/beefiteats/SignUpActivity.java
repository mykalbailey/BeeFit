package rasbeeco.beefiteats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private View SignUpView;

    private FirebaseAuth mAuth;
    private SignUpArrayAdapter sAdapter;
    String[] creds = {"First Name","Last Name","Email","Password","Confirm","Current Weight","Goal","Goal Weight","Birth Date"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        ListView lv = findViewById(R.id.signUpListView);

        ArrayList<String> list = new ArrayList<String>();
        list.add("teser");
        sAdapter = new SignUpArrayAdapter(this,R.layout.signup_textedit,list);

        //sAdapter.notifyDataSetChanged();

        lv.setAdapter(sAdapter);
    }


    public void Signup(){
        /*EditText usr = findViewById(R.id.sUser);
        EditText pss1 = findViewById(R.id.sPass1);
        EditText pss2 = findViewById(R.id.sPass2);

        String email = usr.getText().toString();
        String pass1 = pss1.getText().toString();
        String pass2 = pss2.getText().toString();

        if(pass1.equals(pass2)){
            mAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    success();
                }
            });
        }
        */
    }

    public void success(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
