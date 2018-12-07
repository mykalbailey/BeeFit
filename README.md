# Assignment 3 - Topic: ViewSwitcher 

Viewswitcher is a subclass of Viewanimator which is used to switch between views. It is mainly useful for animating transitions on the screen.  View Switcher has a smooth transition which makes it easy to add different transitions effects.

## History

The viewSwitcher class was added in the very first Api level 1.  It is in the Java.lang.obj package.

## Major Methods

The following creates a new empty viewSwitcher.
```
ViewSwitcher(Context context)
```
The following creates a viewSwitcher for given context and specific attribute set.
```
ViewSwitcher(Context context, AttributeSet attrs)
```
The following method adds a view to the viewSwitcher with layout parameters.
```
addView(View child, int index, ViewGroup.LayoutParamsparams)
```
the following method returns the view that is to be viewed next.
```
getNextView()
```

## How to implement

1.	Create xml file with SwitcherView.
2.	Add up to two views in the SwitcherView.
3.	In order to recall these views in the Java Activity you must set reference Ids for the Switcher and the Views added.
4.	In the Main activity create references to the SwitcherView and views it contains.
5.	Implement a method of switching views (ie. a button).
6.	Use the SwitcherView method showNext() or showPrevious() to switch between the views added.
7.	The default SwitcherView transition is smooth.  Optionally you can set transition animations by calling the method SetInAnimation() or SetOutAnimation().  You can refer to Android Animations Library to view and test different transition animations.


## Code

ViewSwitcherDemo:

activity_main.xml
```
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <ViewSwitcher
            android:id="@+id/viewSwitcher"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1">

            <TextView
                android:id="@+id/textView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:text="TextView"
                android:textAlignment="center"
                android:gravity="center" />

            <ToggleButton
                android:id="@+id/toggleButton"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center"
                android:text="ToggleButton" />
        </ViewSwitcher>

        <Button
            android:id="@+id/button"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Switch View"
            tools:layout_editor_absoluteX="296dp" />

</LinearLayout>
```
MainActiity.java
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final ViewSwitcher viewSwitcher = findViewById(R.id.viewSwitcher);
        final TextView textView = findViewById(R.id.textView);

        //On click listener to switch between views.
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
```

## Authors

* **Mykal Bailey**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* https://developer.android.com/reference/android/widget/ViewSwitcher
