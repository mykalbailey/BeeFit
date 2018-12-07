package c.rasbeeco.viewswitcherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final ViewSwitcher viewSwitcher = findViewById(R.id.viewSwitcher);
        final TextView textView = findViewById(R.id.textView);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewSwitcher.getCurrentView() == textView){
                    viewSwitcher.showNext();
                }else{
                    viewSwitcher.showPrevious();
                }
            }
        });
    }
}
