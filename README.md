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

> For Phone And Pad (Support) .

```
    implementation 'com.github.zhouzhuo810.Magpie:magpie:1.4.4'
```

If you use this. That means you added dependencies below:
```
    //v7
    api 'com.android.support:appcompat-v7:28.0.0'
    //RecyclerView
    api 'com.android.support:recyclerview-v7:28.0.0'
    //BaseRecyclerViewAdapterHelper
    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.46'
    //EventBus
    api 'org.greenrobot:eventbus:3.1.1'
    //Glide
    api 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
    //RxJava2
    api "io.reactivex.rxjava2:rxjava:2.2.10"
    //RxAndroid2
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'
    //Retrofit2
    api 'com.squareup.retrofit2:retrofit:2.5.0'
    //Retrofit2+RxJava2
    api 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'
    //Retrofit2+Gson
    api 'com.squareup.retrofit2:converter-gson:2.5.0'
    //OkHttp打印请求数据
    api 'com.squareup.okhttp3:logging-interceptor:3.12.0'
    //OkHttp进度监听progressmanager
    api 'me.jessyan:progressmanager:1.5.0'
    //AndPermission
    api 'com.yanzhenjie.permission:support:2.0.1'
    //ToastUtils
    api 'com.hjq:toast:8.0'

```


> For Phone And Pad (AndroidX) .


> For Android TV(Support).

```
    implementation 'com.github.zhouzhuo810.Magpie:magpie-tv:1.4.4'
```

If you use this. That means you added dependencies below:
```
    //v7
    api 'com.android.support:appcompat-v7:28.0.0'
    //RecyclerView
    api 'com.android.support:recyclerview-v7:28.0.0'
    //EventBus
    api 'org.greenrobot:eventbus:3.1.1'
    //RxJava2
    api "io.reactivex.rxjava2:rxjava:2.2.10"
    //RxAndroid2
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'
    //zz-horizontal-progressbar
    api 'me.zhouzhuo.zzhorizontalprogressbar:zz-horizontal-progressbar:1.1.0'
    //AndPermission
    api 'com.yanzhenjie.permission:support:2.0.1'
    //ToastUtils
    api 'com.hjq:toast:8.0'
```

#### Screen Adapter

- Add UI Design size in your AndroidManifest.xml.

```xml
        <!--设计图的宽,单位是像素,推荐用markman测量-->
        <meta-data
            android:name="design_width"
            android:value="1080" />
        <!--设计图的高,单位是像素,推荐用markman测量，（可选配置项，配置了design_height则按宽高缩放，否则只按design_width缩放）-->
        <meta-data
            android:name="design_height"
            android:value="1920"/>
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

- init Magpie Context in your custom Application.

```java

//方式1 (推荐)
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化8.0通知渠道
        NoticeUtil.initNoticeChannel("您的渠道id", "您的渠道名称", "您的渠道描述", 0, true);
    }
}

Or

//方式2
public class MyApplication extends Application {
  
    @Override
    public void onCreate() {
        super.onCreate();
  
        BaseUtil.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        if (shouldSupportMultiLanguage()) {
            int language = SpUtil.getInt(base, Cons.SP_KEY_OF_CHOOSED_LANGUAGE);
            switch (language) {
                case LanguageUtil.SIMPLE_CHINESE:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(base, Cons.SIMPLIFIED_CHINESE));
                    break;
                case LanguageUtil.TRADITIONAL_CHINESE:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(base, Cons.TRADITIONAL_CHINESE));
                    break;
                case LanguageUtil.ENGLISH:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(base, Cons.ENGLISH));
                    break;
                case LanguageUtil.VI:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(base, Cons.VI));
                    break;
            }
        } else {
            super.attachBaseContext(base);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (shouldSupportMultiLanguage()) {
            LanguageUtil.updateApplicationLanguage();
        }
        SimpleUtil.resetScale(this);
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

#### BaseApplication

it supports:
- Multi-Language.

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

- PackageUtil
> It's for checking if app is installed.

- ShareUtil
> It's for sharing Text or File to other app.

### Update Logs

> 1.4.4 (Published)
- RxHelper参数int改成long；

> 1.4.3 (Published)
- 添加俄文和葡萄牙文支持；

> 1.4.2 (Published)
- 修复RvBaseAdapter数组越界问题；

> 1.4.1 (Published)
- 修复TV滚动控件滚动到底部减速问题；
- TitleBar添加`setOnLeftClickListener`方法。

> 1.4.0 (Published)
- 修复Application Context多语言获取strings不正确问题；

> 1.3.9 (Published)
- Change the max line of ListDialog to 2.


> 1.3.8 (Published)
- Fix showing `TwoBtnEditDialog` bugs in BaseActivity .


> 1.3.5 (Published)
- Fix `Toast` not show when Notification Permission closed.

> 1.3.4 (Published)
- Add vertical `GapLine` support for `Indicator`.

> 1.3.3 (Published)
> 1.3.2 (Published)

- Fix `Indicator` several bugs.
- Fix `MarkView` several bugs.

> 1.3.1 (Published)

- Add `icon_bg_selector.xml`.
- Add `getFileToByteArrayFromAssets()` method to `AssetsUtil`.
- Upgrade support version to 28.0.0.

> 1.3.0 (Published)

- Add `AutoIconColor` attr in `TabBar`.
- 
> 1.2.9 (Published)

- Add `AutoIconColor` attr in `TabBar`.

> 1.2.8 (Published)

- Fix `BaseActivity` several bugs.
- Add `setEnable` in `LvBaseAdapter` And `RvBaseAdapter`.
- Add `FileChooser` util.
- Add `OSUtils` util.
- Add `PhotoUtil` util.
- Add `StatusBarUtil` util.

> 1.2.7 (Published)

- Add `Indicator#updateText()` method in `Indicator`.

> 1.2.6 (Published)

- Fix `CopyUrlActivity`'s bugs in TV framework.

> 1.2.5 (Published)

- Add `NoticeUtil.initNoticeChannel()` method in `NoticeUtil`;
- Add `NoticeUtil.showNormalNoticeWithCopyAction()` method in `NoticeUtil`;
- Add `NoticeUtil.showNormalNoticeWithShareAction()` method in `NoticeUtil`;
- Add `PackageUtil` for checking if app is installed;
- Add `ShareUtil` for sharing to other app;
- Add `createApiWithCopyNotice` method in `ApiUtil`;
- Add `createApiWithShareNotice` method in `ApiUtil`;

> 1.2.4 (Published)

- Add `UriUtil`;
- Add `copyStream` method to `IOUtil`;
- Add `showNormalNoticeWithCopyAction` method in `NoticeUtil`;
- Add `CopyUrlActivity` for copy Notification.

> 1.2.3 (Published)

- Fix several bugs;

> 1.2.2 (Published)

- Add `BaseUtil#init(this)` to `BaseApplication`;

> 1.2.1 (Published)

- Add `BaseApplication`;
- Fix Application Context multiple language.

> 1.2.0 (Published)

- Add `vi` language support.

> 1.1.9 (Published)

- Add `design_height` meta-data option.
- Fix `TabBar` several bugs.

> 1.1.8 (Published)

- Fix `findViewById` method in `BaseFragment` of TV framework.

> 1.1.7 (Published)

- Add `cancelDisposable` method in `BaseFragment`.

> 1.1.6 (Published)
- Add `RandomUtil`.
- Fix several bugs of `ShineTextView`.

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

### Proguard

For magpie, see [app/proguard-rules.pro](app/proguard-rules.pro)

For magpie-tv, see [app-tv/proguard-rules.pro](app-tv/proguard-rules.pro)

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





