package rasbeeco.beefiteats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DiaryDetails extends AppCompatActivity {

    DiaryObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_diary_details);

        TextView fname = findViewById(R.id.ddfname);
        TextView date = findViewById(R.id.ddDate);
        TextView time = findViewById(R.id.ddTime);
        TextView mtype = findViewById(R.id.ddMtype);
        TextView nserv = findViewById(R.id.ddnserv);
        TextView ssize = findViewById(R.id.ddSsize);
        TextView prots = findViewById(R.id.ddProts);
        TextView carbs = findViewById(R.id.ddCarbs);
        TextView fats = findViewById(R.id.ddFats);

        DiaryObject obj = (DiaryObject) getIntent()
                .getSerializableExtra("data");

        System.out.println(obj);

        fname.setText(obj.fname);
        date.setText("Date: " + obj.date);
        time.setText("Time: " + obj.time);
        mtype.setText(obj.mType);
        nserv.setText("No of servings: "+Integer.toString(obj.noServ));
        ssize.setText("Serving Size: " + Integer.toString(obj.sSize));
        prots.setText("Protein: "+Integer.toString(obj.prots)+"g");
        carbs.setText("Carbs: "+Integer.toString(obj.carbs)+"g");
        fats.setText("Fats: "+Integer.toString(obj.fats)+"g");

        /*
        fname.setText(obj.fname);
        date.setText(obj.date);
        time.setText(obj.time);
        mtype.setText(obj.mType);
        //nserv.setText(obj.noServ);
        //ssize.setText(obj.sSize);
        prots.setText(obj.prots);
        carbs.setText(obj.carbs);
        fats.setText(obj.fats);
        */
    }
}
