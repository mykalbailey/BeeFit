package rasbeeco.beefiteats;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        populateSettings();
    }

    public void populateSettings(){
        String[] settings = {"Name","Details","Email","Meals","Goals","Notifications","About","Logout"};
        TableLayout tl = (TableLayout) findViewById(R.id.settingsTable);

        for(int i = 0; i < settings.length; i++) {

            TableRow tr = new TableRow(this);
            tr.setBackgroundColor(Color.WHITE);
            TableRow.LayoutParams trl = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tr.setLayoutParams(trl);

            TextView title = new TextView(this);
            title.setText(settings[i]);
            title.setTextColor(Color.BLACK);
            title.setTextSize(25);
            tr.addView(title);
            tl.addView(tr);
        }

    }
}
