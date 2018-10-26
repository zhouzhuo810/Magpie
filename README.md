[![](https://jitpack.io/v/zhouzhuo810/Magpie.svg)](https://jitpack.io/#zhouzhuo810/Magpie)
[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![MinSdk](https://img.shields.io/badge/MinSDK-16-orange.svg)](https://android-arsenal.com/api?level=16)

# Magpie
 A powerful Android Develop Framework.


### How to use


```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

> For Phone And Pad.

```
    implementation 'com.github.zhouzhuo810.Magpie:magpie:1.1.5'
```

If you use this. That means you added dependencies below:
```
    //v7
    api 'com.android.support:appcompat-v7:27.1.1'
    //RecyclerView
    api 'com.android.support:recyclerview-v7:27.1.1'
    //BaseRecyclerViewAdapterHelper
    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.40'
    //EventBus
    api 'org.greenrobot:eventbus:3.1.1'
    //Glide
    api 'com.github.bumptech.glide:glide:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
    //RxJava2
    api "io.reactivex.rxjava2:rxjava:2.2.0"
    //RxAndroid2
    api 'io.reactivex.rxjava2:rxandroid:2.1.0'
    //Retrofit2
    api 'com.squareup.retrofit2:retrofit:2.4.0'
    //Retrofit2+RxJava2
    api 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
    //Retrofit2+Gson
    api 'com.squareup.retrofit2:converter-gson:2.4.0'
    //OkHttp打印请求数据
    api 'com.squareup.okhttp3:logging-interceptor:3.11.0'
    //OkHttp进度监听progressmanager
    api 'me.jessyan:progressmanager:1.5.0'
```

> For Android TV.

```
    implementation 'com.github.zhouzhuo810.Magpie:magpie-tv:1.1.5'
```

If you use this. That means you added dependencies below:
```
    //v7
    api 'com.android.support:appcompat-v7:27.1.1'
    //RecyclerView
    api 'com.android.support:recyclerview-v7:27.1.1'
    //EventBus
    api 'org.greenrobot:eventbus:3.1.1'
    //RxJava2
    api "io.reactivex.rxjava2:rxjava:2.2.0"
    //RxAndroid2
    api 'io.reactivex.rxjava2:rxandroid:2.1.0'
    //zz-horizontal-progressbar
    api 'me.zhouzhuo.zzhorizontalprogressbar:zz-horizontal-progressbar:1.0.9'
```

#### Screen Adapter

- Add UI Design size in your AndroidManifest.xml.

```xml
        <!--设计图的宽,单位是像素,推荐用markman测量,量出来如果是750px那么请尽量去找ui设计师要一份android的设计图.-->
        <meta-data
            android:name="design_width"
            android:value="1080" />
        <!--设计图对应的标准dpi,根据下面的那张图找到对应的dpi,比如1080就对应480dpi,如果拿到的是其他宽度的设计图,那么选择一个相近的dpi就好了-->
        <!--
            宽         	    240 	320 	480 	720     1080 	1440
            DPI等级	        LDPI	MDPI	HDPI	XHDPI	XXHDPI	XXXHDPI
            DPI数值	        120	    160	    240	    320	    480	    640
        -->
        <meta-data
            android:name="design_dpi"
            android:value="480" />

        <!--全局字体的大小倍数,有时候老板会觉得你的所有的字小了或者大了,你总不能一个一个去改吧-->
        <meta-data
            android:name="font_size"
            android:value="1.0" />

        <!--你的布局里面用的是px这就写px,你的布局里面用的是dp这就写dp,要统一,不要一会儿px一会儿dp,字体也用px或者dp,不要用sp,微信qq用的肯定不是sp.-->
        <meta-data
            android:name="unit"
            android:value="px" />
```

- init BaseUtil in your custom Application#onCreate().

```java
public class MyApplication extends Application {
  
    @Override
    public void onCreate() {
        super.onCreate();
  
        BaseUtil.init(this);
    }
}
```

- ok, Just use `px` unit in your layout.

- If you do not use BaseActivity, Then you should invoke the method
 `   ScreenAdapterUtil.getInstance().loadView(getWindow().getDecorView());` in `Activity#onCreate()` method after `setContentView()`.

- If you do not use BaseFragment, Then you should invoke the method
 `   ScreenAdapterUtil.getInstance().loadView(view);` in `Fragment#onCreateView()` method after `inflate()`.

- If you do not use LvBaseAdapter or RvBaseAdapter, Then you should invoke the method
 `ScreenAdapterUtil.getInstance().loadView(view);` when you create ViewHolder.

- If you want to use in custom View, you should invoke the method
`ScreenAdapterUtil.getInstance().getScaledValue(int);` when you what to scale the dynamic value.

- If you don't want to use `ScreenAdapterUtil.getInstance()`, just replace it with `SimpleUtil`.

#### BaseActivity

it supports:
- Multi-Style Dialog.
- Multi-Language support.
- Replace Fragment easily.


#### BaseFragment

it supports:
- Multi-Style Dialog.
- Load data lazily.

#### Utils

- BaseUtil
> This is the key util.

- ScreenAdapterUtil
> It's use for screen Adapter.

- ApiUtil
> It's for RxJava2+Retrofit2+OkHttp3 Api Creation.

- LanguageUtil
> It's for Language Setting.

- KeyboardUtil
> It's for Software Keyboard operating.

- ApkUtil
> It's for Apk Installation.

- BarUtils
> It's for StatusBar Setting.

- SpUtil
> It's use for SharedPreferences.

- StrUtil
> It's use for String operating.

- FileUtil
> It's use for File operating.

- DateUtil
> It's use for Date operating.

- RxHelper
> It's use for RxJava.

- CrashUtil
> It's for UncaughtExceptionHandler.

- CopyUtil
> It's for ClipboardManager.

- ImageUtil
> It's for bitmap operating.

- ToastUtil
> It's for simple Toast.

- NoticeUtil
> It's for Notification.

#### Widgets

- Indicator
> It's for viewpager indicator.

- TabBar
> It's for bottom navigation.

- TitleBar
> It's same as ToolBar.

- MarkView
> It's for bandage.

- SimpleSpinner
> It's for using Spinner simply.

( These utils is added in v1.0.1.)

- ColorUtil
> It's for color operating.

- EditUtil
> It's for EditText operating.

- SimpleSpinner
> It's for Spinner operating.

- FontUtil
> It's for setting custom typeface in TextView.

- AssetsUtil
> It's for converting file's content to String in Assets.

- SimpleUtil
> It's for converting file's content to String in Assets.

- ShellUtil
> It's for running shell command.

- NetworkUtil
> It's for network status monitoring.

- CRC16
> It's for CRC16 encryption.

- ByteUtil
> It's for Byte operating.

- RandomUtil
> It's for generate a random number.

### Update Logs

> 1.1.6 (Developing)
- Add `RandomUtil`.

> 1.1.5 (Published)

- Add `cancelDisposable` method in `BaseActivity`.
- Add `timer` method in `RxHelper`.
- Add `interval` method in `RxHelper`.
- Add several methods in `ShineTextView`.
- Fix several bugs in TV framework.

> 1.1.4 (Published)

- Remove `Retrofit2` in TV framework.
- Add `Rxjava2` to TV framework.

> 1.1.3 (Published)

- Fix several Bugs.
- Add several methods in `DateUtil`.
- Add `CRC16`.
- Add `ByteUtil`.

> 1.1.2 (Published)

- Fix several Bugs.

> 1.1.1 (Published)

- Add `NetworkUtils`.
- Add `ShellUtils`.
- Add `getFontFromAssets` and `getFileContentFromAssets` methods in `SimpleUtil`.


> 1.1.0 (Published)

- Add `SimpleUtil`.
- Add `startAct` and `startActForResult` methods in BaseFragment and BaseActivity.
- Add several methods in `FileUtil`.

> 1.0.9 (Published)

- Add `isLoading` and `getLoadingDialog` methods in BaseActivity.

> 1.0.8 (Published)

- Fix `Indicator` several bugs.
- Fix `BaseFragment` several bugs.

> 1.0.7 (Published)

- Fix `LoadingDialog` several bugs.

> 1.0.6 (Published)

- Upgrade the version of `ZzHorizontalProgressBar` to `1.0.9` for TV framework.
- Fix `BaseActivity#lazyLoadData` bugs.

> 1.0.5 (Published)

- Fix `BaseActivity#addOrShowFragment` several bugs.
- Add `BaseActivity#addOrShowFragmentCustomTag` methods.

> 1.0.4 (Published)

- Add `ScrollListRecyclerView` and `ScrollGridRecyclerView` widgets for TV framework.
- Add `MapRecyclerView` and `ShineTextView` widgets for TV framework.
- Add `FontUtil` and `AssetsUtil` in Phone and TV.
- Fixed several bugs.

> 1.0.3 (Published)

- Add`addOrShowFragment`and `hideFragment` methods in BaseActivity.

> 1.0.2 (Published)

- TitleBar add `ttb_iconSize` attribute.
- Add `shouldNotInvokeInitMethods` method
  to skip invoking `initView、initData、initEvent` in BaseActivity and BaseFragment.

> 1.0.1 (Published)

- Add ColorUtil.
- Add EditUtil.
- Add SimpleSpinner.

> 1.0.0 (Published)

- 2018/9/2 initial commit.

### Thanks

[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)

[AndroidScreenAdaptation](https://github.com/yatoooon/AndroidScreenAdaptation)


### License

```
Copyright © zhouzhuo810

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```





