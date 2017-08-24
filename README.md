# IndicatorDialog
a dialog with arrow indicator in the location where you want [minSdkVersion 15] <br />
[download apk](https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/app.apk)

[![](https://jitpack.io/v/jiang111/IndicatorDialog.svg)](https://jitpack.io/#jiang111/IndicatorDialog)


### Demo:

<p>
<img src="https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/left_1.png" width="120" height="230" alt=""  />
<img src="https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/l2.png"  width="120" height="230" alt="" />
<img src="https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/l3.png" width="120" height="230" alt="" />
<img src="https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/d1.png"  width="120" height="230" alt="" />
<img src="https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/d2.png"  width="120" height="230" alt="" />
<img src="https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/d3.png"  width="120" height="230" alt="" />
</p>


### Depend:
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency [![](https://jitpack.io/v/jiang111/IndicatorDialog.svg)](https://jitpack.io/#jiang111/IndicatorDialog)

```
dependencies {
    compile 'com.github.jiang111:IndicatorDialog:v1.3.7'
}
```

### Usage:
```
//we use RecyclerView.Adapter to fill the content of dialog

// create  IndicatorBuilder to init parameters

IndicatorDialog dialog = new IndicatorBuilder(this)  //must be activity
                .width(400)                           // the dialog width in px
                .height(100)                          // the dialog max height in px or -1 (means auto fit)
                 .animator(R.style.dialog_exit) //add animator (optional)
                .ArrowDirection(IndicatorBuilder.BOTTOM)      // the position of dialog's arrow indicator(TOP BOTTOM LEFT or RIGHT) 
                .bgColor(Color.parseColor("#49484b"))  // the bg color of the dialog
                .dimEnabled(true)                      // set bg blurry enable , default is true
                .gravity(GRAVITY_LEFT)                // dialog' sgravity (GRAVITY_LEFT or GRAVITY_RIGHT or GRAVITY_CENTER)
                //.arrowDrawable(BaseDrawable)        //custom arrow style if you needed
                .radius(8)                            // the radius in dialog
                .ArrowRectage(0.2f)                   // the arrow's offset Relative to the dialog's width  even though you set the ArrowDirection to IndicatorBuilder.LEFT or RIGHT
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))  //the LayoutManager of RecyclerView
                .adapter(adapter).create();            //the adapter of RecyclerView
dialog.setCanceledOnTouchOutside(true);                // outside cancelable
dialog.show(v);                                        // determine the location of dialog
// dialog.show(v,x,y);                                // x,y offset of the dialog 
dialog.dismiss();                                     //dismiss the dialog
dialog.getDialog();                                   // get the real dialog object
//you can custom arrow style by new class extend BaseDrawable and use the arrowDrawable(baseDrawable) method to config 
```
[see the demo](https://github.com/jiang111/IndicatorDialog/blob/master/app/src/main/java/com/jiang/android/indicatordialogdemo/MainActivity.java) or [download apk](https://raw.githubusercontent.com/jiang111/IndicatorDialog/master/art/app.apk)


### Other
 If you found this library helpful or you learned something today and want to thank me, [buying me a cup of ☕️  with paypal](https://www.paypal.me/jyuesong) <br /><br />
![](https://raw.githubusercontent.com/jiang111/RxJavaApp/master/qrcode/wechat_alipay.png)


### License

    Copyright 2016 NewTab

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

