package rasbeeco.beefiteats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class profileFragment extends android.support.v4.app.Fragment {

    View  myFragView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragView = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView iv = myFragView.findViewById(R.id.userImage);
        TextView username = myFragView.findViewById(R.id.userName);
        ProgressBar pb = myFragView.findViewById(R.id.progressBar2);
        Button wUpdate = myFragView.findViewById(R.id.weightUpdate);
        Button uGoals = myFragView.findViewById(R.id.goalsButton);
        TextView cWeight = myFragView.findViewById(R.id.CurrentWeight);
        TextView gWeight = myFragView.findViewById(R.id.GoalWeight);
        TextView date = myFragView.findViewById(R.id.ProfileDate);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        wUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        uGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return myFragView;
    }

}
