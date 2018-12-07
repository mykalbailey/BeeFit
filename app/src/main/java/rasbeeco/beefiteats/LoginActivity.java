package rasbeeco.beefiteats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public static LoginActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        instance=this;

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        Button loginButton = findViewById(R.id.buttonlogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        TextView signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });

        if(currentUser != null){
            sLogin();
        }

    }
    public void Login(){

        EditText un = findViewById(R.id.lUser);
        EditText pw = findViewById(R.id.lPass);

        String lgin = un.getText().toString().trim();
        String pswd = pw.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(lgin,pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sLogin();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                }
            }

        });



        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public void sLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void SignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    @Override
    public void finish() {
        super.finish();
        instance = null;
    }
}
