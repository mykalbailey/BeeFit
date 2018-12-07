package rasbeeco.beefiteats;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by rasb on 2018-12-05.
 */

public class RecentFoods {
    String fname;
    int sSize;
    int noServ;
    int fats;
    int carbs;
    int prots;

    RecentFoods(Object oj){
        if(oj.getClass() == DataSnapshot.class) {

            DataSnapshot dataSnapshot = (DataSnapshot) oj;

            fname = dataSnapshot.child("fname").getValue().toString();
            sSize = Integer.parseInt(dataSnapshot.child("ssize").getValue().toString());
            noServ = Integer.parseInt(dataSnapshot.child("nserv").getValue().toString());
            fats = Integer.parseInt(dataSnapshot.child("fats").getValue().toString());
            carbs = Integer.parseInt(dataSnapshot.child("carbs").getValue().toString());
            prots = Integer.parseInt(dataSnapshot.child("prots").getValue().toString());
        }
    }
    public String toString(){
        String str = " "+ fname + ", " + fats;
        return str;
    }
}
