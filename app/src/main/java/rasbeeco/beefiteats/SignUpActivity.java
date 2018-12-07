package rasbeeco.beefiteats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    public static SignUpActivity instance = null;

    Map<String, String> creds2 = new HashMap<String, String>();
    //String[] creds2 = {"Current Weight","Goal","GWeight"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        instance=this;

        //SetUp(creds1);

        Button next = findViewById(R.id.SignUpNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next();
            }
        });

    }
    private void Next(){
        if(getCreds()){
            Intent intent = new Intent(this, SignUpActivity2.class);
            for(Map.Entry<String, String> cred : creds2.entrySet()){
                intent.putExtra(cred.getKey(),cred.getValue());
            }
            startActivity(intent);
        }
    }

    private boolean getCreds(){
        EditText etFname = findViewById(R.id.etFname);
        EditText etLname = findViewById(R.id.etLname);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPass = findViewById(R.id.etPass);
        EditText etConf = findViewById(R.id.etConf);
        EditText etDOB = findViewById(R.id.etDOB);

        String[] creds1= {"First Name","Last Name","Email","Password","Confirm","Birth Date"};

        List<EditText> input = new ArrayList<EditText>();
        input.add(etFname);
        input.add(etLname);
        input.add(etEmail);
        input.add(etPass);
        input.add(etConf);
        input.add(etDOB);

        boolean comp = true;
        creds2.clear();

        for(int i = 0;i<input.size();i++){
            if(input.get(i).getText().toString().equals("")){
                Toast.makeText(this, (String)creds1[i] + " field is empty",
                        Toast.LENGTH_LONG).show();
                comp=false;
                continue;
            }
            else{
                creds2.put(creds1[i],input.get(i).getText().toString());
            }
        }
        if(!etPass.getText().toString().equals(etConf.getText().toString())){
            Toast.makeText(this, " passwords are not matching",
                    Toast.LENGTH_LONG).show();
            comp=false;
        }
        return comp;
    }
    @Override
    public void finish() {
        super.finish();
        instance = null;
    }
}
