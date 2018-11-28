package rasbeeco.beefiteats;

//import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
//import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fragmentView, new diaryFragment());
        ft.commit();

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, World!");


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_diary:
                    ft.replace(R.id.fragmentView, new diaryFragment());
                    ft.commit();
                    //mTextMessage.setText(R.string.title_diary);
                    return true;
                case R.id.navigation_progress:
                    ft.replace(R.id.fragmentView, new progressFragment());
                    ft.commit();

                    //mTextMessage.setText(R.string.title_progress);
                    return true;
                case R.id.navigation_profile:
                    ft.replace(R.id.fragmentView, new profileFragment());
                    ft.commit();
                    //mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //menu.clear();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openSettings();
        }
        if (id == R.id.action_quit) {
            //android.os.Process.killProcess(android.os.Process.myPid());
            finishAndRemoveTask();
        }
        if (id == R.id.action_logout){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            finishAndRemoveTask();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void openSettings(){

        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    protected void addFood(){
        Intent intent = new Intent(this, AddFood.class);
        startActivity(intent);
    }

}
