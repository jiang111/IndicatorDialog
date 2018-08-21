# IndicatorDialog
a dialog with arrow indicator in the location where you want [minSdkVersion 9] <br />
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
    compile 'com.github.jiang111:IndicatorDialog:2.1.0'
}
```

### Usage:
```
最终内部是通过RecyclerView来实现.
IndicatorDialog dialog = new IndicatorBuilder(this)  //must be activity
                .width(dp2px(400))                      
                .height(-1)                        
                .animator(R.style.dialog_exit) 
                .ArrowDirection(IndicatorBuilder.BOTTOM)     
		.bgColor(Color.parseColor("#49484b"))  
                .dimEnabled(true)                     
                .gravity(GRAVITY_LEFT)                
                .radius(8)                            
                .ArrowRectage(0.2f)                  
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))  //the LayoutManager of RecyclerView
                .adapter(adapter)
		.create();           
dialog.setCanceledOnTouchOutside(true);               
dialog.show(v);                                                                     
dialog.dismiss();                                    
dialog.getDialog();                                 


//方法解释

width()     //宽,单位px,必填
height()    //高,单位px,推荐传-1 可以自动适配
ArrowDirection()   //箭头方向 TOP BOTTOM LEFT RIGHT
bgColor()          //背景颜色
dimEnabled()      //背景模糊,默认true
gravity()         //对话框的权重 GRAVITY_LEFT  GRAVITY_RIGHT  GRAVITY_CENTER
radius()          //圆角
ArrowRectage()    //箭头距离左边或者右边的偏移量 取值 0-1, 左右依据gravity来判断是左边偏移还是右边

//高级方法
animator()  //dialog显示消失的动画
arrowDrawable()   //自定义箭头样式,可以自定义成任何形状

dialog.show(v,x,y);    //x为向左右偏移,y为上下偏移   

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

