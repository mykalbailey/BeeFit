package rasbeeco.beefiteats;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

/**
 * Created by rasb on 2018-11-27.
 */

public class DiaryObject implements Serializable{
    String fname;
    int sSize;
    int noServ;
    int fats;
    int carbs;
    int prots;
    String mType;
    String notes;
    String date;
    String organize;
    String time;

  DiaryObject(Object oj){
      if(oj.getClass() == DataSnapshot.class) {

          DataSnapshot dataSnapshot = (DataSnapshot) oj;
          fname = dataSnapshot.child("fname").getValue().toString();
          mType = dataSnapshot.child("mtype").getValue().toString();
          sSize = Integer.parseInt(dataSnapshot.child("ssize").getValue().toString());
          noServ = Integer.parseInt(dataSnapshot.child("nserv").getValue().toString());
          fats = Integer.parseInt(dataSnapshot.child("fats").getValue().toString());
          carbs = Integer.parseInt(dataSnapshot.child("carbs").getValue().toString());
          prots = Integer.parseInt(dataSnapshot.child("prots").getValue().toString());
          //notes=dataSnapshot.child("notes").getValue().toString();
          date = dataSnapshot.child("date").getValue().toString();
          time = dataSnapshot.child("time").getValue().toString();
      }else if(oj.getClass() == String.class){
          fname = oj.toString();
          //organize= "String";
          //mType = "string";
          //if(fname.equals("Bre"))
      }

      /*int[] arr = {sSize,noServ,fats,carbs,prots};
      for(int i=0;i<arr.length;i++){
          System.out.println(arr[i]);
      }*/
  }
  public void fromObject(Object oj){
      if(oj.getClass() == DataSnapshot.class){

      };
  }

  public String toString(){
      String str = fname + ", " + mType + ", " + sSize+ ", " + noServ+ ", " + fats+ ", " + prots+ ", " + carbs+ ", " + date+ ", " + time;
      return str;
  }

}
