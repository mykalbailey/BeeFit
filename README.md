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

1.	Create xml file with view switcher view and set id and parameters.
2.	In the activity you plan on using to implement this, create reference to the view switcher with the findViewById() method.
3.	Set a factory by using the setFactoryId() method.

## Code

Explain how to run the automated tests for this system

## Authors

* **Mykal Bailey**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* https://developer.android.com/reference/android/widget/ViewSwitcher
