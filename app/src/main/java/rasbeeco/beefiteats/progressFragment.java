package rasbeeco.beefiteats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class progressFragment extends android.support.v4.app.Fragment {

    private View myFragView;
    private progressArrayAdapter mAdapter;

    String[] titles = {" ","Current","Goal"};
    private String[] fats = {"Fats","25%","25%"};
    private String[] carbs = {"Carbs","25%","30%"};
    private String[] prots = {"Protein","50%","45%"};
    //String[] items ={"Eggs","Sandwich","Steak Dinner","Chocolate Chip Cookie","Chips"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myFragView = inflater.inflate(R.layout.fragment_progress, container, false);
        ListView lv = (ListView) myFragView.findViewById(R.id.progressListView);

        int headerCount = 0;

        mAdapter = new progressArrayAdapter(getActivity());
        mAdapter.addSectionHeaderItem(titles);
        mAdapter.addItem(fats);
        mAdapter.addItem(carbs);
        mAdapter.addItem(prots);
        lv.setAdapter(mAdapter);

        return myFragView;
    }
}
