#### 一、打包加固说明
1.多渠道打包加固 -> 爱加密：http://www.ijiami.cn
2.腾讯乐固 https://console.cloud.tencent.com/ms/reinforce/tool

gradle签名打包：https://blog.csdn.net/xuwb123xuwb/article/details/78195315
V1：可对签名后的文件，作适当修改，并重新压缩。
V2：不能对签名后的 APK作任何修改，包括 zipalign。因为它是针对字节进行的签名，所以任何改动都会影响最终结果。
Signature Versions不能只选择 V2(Full APK Signature)，应该选择V1(Jar Signature)，或者选择 V1和 V2。

多渠道打包：
- https://blog.csdn.net/johnny901114/article/details/48714849

apk体积优化：
- https://www.cnblogs.com/soaringEveryday/p/5254520.html
- http://www.imooc.com/article/74778

比较打包的签名MD5的值是否一致：
- https://www.jianshu.com/p/ce4089dd653e
- https://www.jianshu.com/p/db709a280d9a
- 获取签名信息：https://blog.csdn.net/qq_28484355/article/details/70158395
- 反编译获取资源文件：https://www.jianshu.com/p/dda9ff90a3c5
- 使用as自带终端命令获取签名信息：keytool -list -v -keystore xxx.jks

gradle config.gradle 全局配置  (参考：https://www.jianshu.com/p/246542472cf3)
```
// 其他模块引入（头部声明）
def ext = rootProject.ext
def version = ext.versions
def dependencie = ext.dependencies

//v7包和v4包
api dependencie.appcompat_v7
api dependencie.support_v4
```
```
ext {
    versions = [
            compileSdkVersion        : 28,
            minSdkVersion            : 15,
            targetSdkVersion         : 26,
            versionCode              : 27,
            versionName              : "1.4.0",

            junit                    : "4.12",
            runner                   : "1.0.1",
            espresso_core            : "3.0.1",

            support                  : "26.1.0",
            constraint_layout        : "1.0.2",

            //Kotlin
            kotlin_version           : '1.2.30',

            //多分包
            multidex                 : '1.0.2',

            //ARouter
            arouter_api              : '1.3.1',
            arouter_compiler         : '1.1.4',
            arouter_annotation       : '1.0.4',

            //Logger
            logger                   : '2.1.1',
            //dagger
            dagger2                  : '2.15',
            dagger2_compiler         : '2.15',

            //Glide
            glide                    : '3.7.0',
            //图片轮播器
            banner                   : '1.4.10',

            //Rx
            rxjava                   : '2.1.3',
            rxandroid2               : '2.0.1',
            rxlifecycle_components   : '2.2.0',
            rxlifecycle              : '2.2.0',
            //Ok
            okhttp3                  : '3.10.0',
            okhttp3_logging          : '3.10.0',
            okio                     : '1.14.0',

            //retrofit2
            retrofit2                : '2.3.0',
            retrofit2_adapter_rxjava2: '2.3.0',
            retrofit2_converter_gson : '2.1.0',

            //Gson  https://github.com/google/gson
            gson                     : '2.8.2',
            //对Recycle中Adapter的进行的抽离
            recycle_adapter          : '2.9.30',
            smart_refresh_layout     : "1.1.0-alpha-5",
            //Eventbus Android端优化的publish/subscribe消息总线, https://github.com/greenrobot/EventBus
            eventbus                 : '3.1.1',

            //AOP AspectJ
            aspectj                  : '1.8.9',
            aspectjx                 : '1.0.10',

            //黄油刀
            butterknife              : '8.4.0',
            butterknife_compiler     : '8.4.0',

            //个推 推送
            getui                    : "2.11.1.0",

            //热修复
            bugly                    : '1.3.4',
    ]

    appID = [
            "app": "com.rd.boruijinfu"//正确的包名
//            "app": "com.rd.boruijinfu_test"//调式的包名

    ]

    dependencies = [
            junit                    : "junit:junit:$versions.junit",
            runner                   : "com.android.support.test:runner:$versions.runner",
            core                     : "com.android.support.test.espresso:espresso-core:$versions.espresso_core",
            //约束布局
            constraint_layout        : "com.android.support.constraint:constraint-layout:$versions.constraint_layout",

            appcompat_v7             : "com.android.support:appcompat-v7:$versions.support",
            support_v4               : "com.android.support:support-v4:$versions.support",
            design                   : "com.android.support:design:$versions.support",
            cardview                 : "com.android.support:cardview-v7:$versions.support",
            customtabs               : "com.android.support:customtabs:$versions.support",
            recyclerview             : "com.android.support:recyclerview-v7:$versions.support",
            percent                  : "com.android.support:percent:$versions.support",

            //Kotlin
            kotlin_stdlib_jdk8       : "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$versions.kotlin_version",
            kotlin_gradle_plugin     : "org.jetbrains.kotlin:kotlin-gradle-plugin:$versions.kotlin_version",

            //dex
            multidex                 : "com.android.support:multidex:$versions.multidex",

            //ARouter https://github.com/alibaba/ARouter
            arouter_api              : "com.alibaba:arouter-api:$versions.arouter_api",
            arouter_compiler         : "com.alibaba:arouter-compiler:$versions.arouter_compiler",

            //Log日志工具 https://github.com/orhanobut/logger
            logger                   : "com.orhanobut:logger:$versions.logger",

            //Dagger2
            dagger2                  : "com.google.dagger:dagger:$versions.dagger2",
            dagger2_compiler         : "com.google.dagger:dagger-compiler:$versions.dagger2_compiler",

            //Glide 图像处理
            glide                    : "com.github.bumptech.glide:glide:$versions.glide",
            //一个强大的图片轮播控件
            banner                   : "com.youth.banner:banner:$versions.banner",

            //Rx https://github.com/ReactiveX/RxAndroid https://github.com/ReactiveX/RxJava
            rxjava                   : "io.reactivex.rxjava2:rxjava:$versions.rxjava",
            rxandroid2               : "io.reactivex.rxjava2:rxandroid:$versions.rxandroid2",
            rxlifecycle_components   : "com.trello.rxlifecycle2:rxlifecycle-components:$versions.rxlifecycle_components",
            rxlifecycle              : "com.trello.rxlifecycle2:rxlifecycle:$versions.rxlifecycle",
            rxpermissions            : "com.tbruyelle.rxpermissions2:rxpermissions:0.9.5@aar",
            //Ok  https://github.com/square/okhttp
            okhttp3                  : "com.squareup.okhttp3:okhttp:$versions.okhttp3",
            okhttp3_logging          : "com.squareup.okhttp3:logging-interceptor:$versions.okhttp3_logging",
            okio                     : "com.squareup.okio:okio:$versions.okio",

            //retrofit https://github.com/square/retrofit
            retrofit2                : "com.squareup.retrofit2:retrofit:$versions.retrofit2",
            retrofit2_adapter_rxjava2: "com.squareup.retrofit2:adapter-rxjava2:$versions.retrofit2_adapter_rxjava2",
            retrofit2_converter_gson : "com.squareup.retrofit2:converter-gson:$versions.retrofit2_converter_gson",

            //Gson https://github.com/google/gson
            gson                     : "com.google.code.gson:gson:$versions.gson",

            //对Recycle中Adapter的进行的抽离 https://github.com/CymChad/BaseRecyclerViewAdapterHelper/releases
            recycle_adapter          : "com.github.CymChad:BaseRecyclerViewAdapterHelper:$versions.recycle_adapter",
            smart_refresh_layout     : "com.scwang.smartrefresh:SmartRefreshLayout:$versions.smart_refresh_layout",
            //Eventbus Android端优化的publish/subscribe消息总线, https://github.com/greenrobot/EventBus
            eventbus                 : "org.greenrobot:eventbus:$versions.eventbus",
            aspectjtools             : "org.aspectj:aspectjtools:$versions.aspectj",
            aspectjrt                : "org.aspectj:aspectjrt:$versions.aspectj",
            aspectjx                 : "com.hujiang.aspectjx:gradle-android-plugin-aspectjx:$versions.aspectjx",

            //黄油刀
            butterknife              : "com.jakewharton:butterknife:$versions.butterknife",
            butterknife_compiler     : "com.jakewharton:butterknife-compiler:$versions.butterknife_compiler",

            //个推  推送
            getui                    : "com.getui:sdk:2.12.3.0",

            //热修复
            bugly                    : "com.tencent.bugly:crashreport_upgrade:$versions.bugly",
            bugly_nativecrashreport  : "com.tencent.bugly:nativecrashreport:latest.release"
    ]
}
```

php提供的下载apk接口无法获取apk总大小的问题
```
//下载apk
function downloads($filename)
{
    $header = get_headers($filename, 1);
    $size = $header['Content-Length'];
    $showname = "download.apk";
    // 下面两个必须配置，否则无法获取下载 Content-Length
    header('Connection: keep-alive');
    header('Content-Encoding: none');
    // header("Content-type: text/plain");
    header("Content-Type: application/octet-stream");
    header("Accept-Ranges: bytes");
    header("Content-Disposition: attachment; filename=".$showname);
    // header("Cache-Control: must-revalidate, post-check=0, pre-check=0" );
    // header("Pragma: public" );
    header("Accept-Length:".$size);
    header('Content-Length: ' . $size);
    echo readfile($filename);
    exit;
}
```

#### 二、第三方框架整理

##### 热更新
- 1.Tinker热更新：https://www.jianshu.com/p/2674f7bd905f
- 2.Bugly热更新：https://www.jianshu.com/p/9f393a4d3a2a

##### MVP的变形
- View绑定多个Presenter: https://github.com/lizixian18/EasyMvp
- MVP脚手架工具：
    1、java： https://github.com/githubwing/MVPHelper
    2、kotlin: https://github.com/y1xian/KotlinMvp

##### 代码模板
- 后台模板工程：https://github.com/xwjie/PLMCodeTemplate

##### 面向AOP切面编程
- 使用框架：https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
- 申请用于权限：https://github.com/crazyqiang/Aopermission
- 通知某个方法的时间、调优：https://blog.csdn.net/wangwangli6/article/details/72820867
- AOP切面编程项目包含登录、动态权限申请拦截：https://github.com/north2016/T-MVP
- 比较简单的说明、参数说明：https://www.cnblogs.com/weizhxa/p/8567942.html

##### 框架
- 0.application入口文件优化：https://juejin.im/post/5b59a4e7e51d455f5f4cfa38
- 1.底部导航tabbar：https://github.com/tyzlmjj/PagerBottomTabStrip
- 2.ViewPager: https://github.com/LuckyJayce/ViewPagerIndicator
- 3.web浏览器：https://github.com/Justson/AgentWeb
- 4.快速设置圆角、边框控件不需要写一堆selector背景：https://github.com/niniloveyou/StateButton
- 5.加载框：https://github.com/maning0303/MNProgressHUD
- 6.页面显示奔溃日志：https://github.com/maning0303/MNCrashMonitor
- 7.Toast 弹窗提示：https://github.com/bboylin/UniversalToast
- 8.加载提示框架：https://github.com/ybq/Android-SpinKit
- 9.解决关闭通知toast弹不出来： https://github.com/getActivity/ToastUtils
- 10.仿微信朋友圈列表：https://www.jianshu.com/p/37a34a58b0c7
- 11.带动画的底部导航：https://github.com/Ashok-Varma/BottomNavigation

##### 拍照、图片选择
- 1.TakePhoto：https://github.com/crazycodeboy/TakePhoto/

##### 常用的小组件
- 0.汉字转拼音：https://github.com/promeG/TinyPinyin
- 1.城市、联系人选择：https://github.com/YoKeyword/IndexableRecyclerView
- 2.城市选择（有定位、开通城市、城市列表）：https://blog.csdn.net/meixi_android/article/details/80076065
- 3.城市选择：https://github.com/zaaach/CityPicker
- 4.RadioButton 自定义： https://github.com/zaaach/TabRadioButton
- 5.qq加号菜单：https://github.com/zaaach/TopRightMenu
- 6.StackLabel 类似flowlayout布局: https://github.com/kongzue/StackLabel
- 7.联系人、城市列表索引条绘制：https://blog.csdn.net/a_zhon/article/details/53214849

##### 组件化
- 1.toolbar: https://www.jianshu.com/p/671c370cb9ed
- 2.路由：https://github.com/alibaba/ARouter (具体用法可参考 https://www.jianshu.com/p/6021f3f61fa6)
- 3.日志收集：https://www.jianshu.com/p/59bc2784379f
- 4. 不得不知的自带组件： LiveData(时间总线，用法与Rxjava类似，也可使用Rxjava替代)、ViewModel(ViewModel 是一个框架组件。它为 UI 组件 (fragment或activity) 提供数据，并且可以调用其它组件加载数据或者转发用户指令。)、Room(持久化数据)  参考链接：https://www.jianshu.com/p/48df7dd49454
- 5.组件化搭建: https://www.jianshu.com/p/8dddeef229f9

##### 使用引导
- 1.NewbieGuide：https://github.com/huburt-Hu/NewbieGuide
-2.动态添加引导页：https://github.com/q296494327/GuidePage

##### 状态栏
- 1.ImmersionBar：https://github.com/gyf-dev/ImmersionBar
- 2.通用导航栏：https://github.com/getActivity/TitleBar
- 2.StatusBarUtil: https://github.com/laobie/StatusBarUtil


##### popupWindow&Dialog
- 1.EasyPopup: https://github.com/zyyoona7/EasyPopup
- 2.BasePopup: https://github.com/razerdp/BasePopup
- 3.XPopup: https://github.com/li-xiaojun/XPopup
- 4.dialogplus: https://github.com/orhanobut/dialogplus
- 5.小巧Dialog: https://github.com/kongzue/Dialog

##### 下载器
- 1.Aria：https://github.com/AriaLyy/Aria
- 2.FileDownloader：https://github.com/lingochamp/FileDownloader
- 3.apk下载：https://blog.csdn.net/qq_34261214/article/details/81487110

##### 二维码扫描
- 1.*工具库（支付、扫码、弹窗）： https://github.com/vondear/RxTool
- 2.barcodescanner 封装有zbar(c语言)、zxing: https://github.com/dm77/barcodescanner
- 3.BGAQRCode生成二维码、扫描二维码、识别图片二维码：https://github.com/bingoogolapple/BGAQRCode-Android


##### 动态权限
- 1.easypermissions动态权限: https://github.com/googlesamples/easypermissions
- 2.权限：https://github.com/permissions-dispatcher/PermissionsDispatcher
- 3.权限库：https://github.com/tbruyelle/RxPermissions.git
- 4.AndPermission: https://github.com/yanzhenjie/AndPermission


##### 分页tab查看
- 1.MagicIndicator: https://github.com/hackware1993/MagicIndicator
- 2.FlycoTabLayout：https://github.com/H07000223/FlycoTabLayout
- 3.PageIndicatorView指示器：https://github.com/romandanylyk/PageIndicatorView
- 4.RecyclerViewPager：https://github.com/lsjwzh/RecyclerViewPager


##### 多状态提示
- 1.stateView: https://github.com/vlonjatg/progress-activity.git
- 2.MultipleStatusView：https://github.com/qyxxjd/MultipleStatusView


##### 刷新框架：
- 1.smartrefresh：https://github.com/scwang90/SmartRefreshLayout
- 2.BGARefreshLayout-Android：https://github.com/bingoogolapple/BGARefreshLayout-Android


##### 工具库：
- 1.AndroidUtilCode：https://github.com/Blankj/AndroidUtilCode
- 2.HTML解析：https://github.com/jhy/jsoup
- 3.收款码：https://github.com/Exrick/xpay


##### 数据库
- 1.mmkv: https://github.com/Tencent/MMKV
- 2.realm: https://github.com/realm/realm-java
- 3.greendao: https://github.com/greenrobot/greenDAO
- 4.数据库调试工具：https://github.com/amitshekhariitbhu/Android-Debug-Database
- 5.数据存储：https://github.com/orhanobut/hawk
- 6.ORM数据库框架：https://github.com/LitePalFramework/LitePal


##### 侧滑菜单：
- 1.MaterialDrawer：https://github.com/mikepenz/MaterialDrawer
- 2.SwipeBackLayout（类QQ）：https://github.com/ikew0ng/SwipeBackLayout
- 3./BGASwipeBackLayout-Android侧滑返回：https://github.com/bingoogolapple/BGASwipeBackLayout-Android


##### 弹窗：
- 1.Toasty：https://github.com/GrenderG/Toasty
- 2.可定制的Dialog： https://github.com/afollestad/material-dialogs
- 3.一行代码下载Apk: https://github.com/teprinciple/UpdateAppDemo
- 4.EasyPopup弹窗：https://github.com/zyyoona7/EasyPopup


##### 图片：
- 1.gif图片：https://github.com/koral--/android-gif-drawable
- 2.圆形图片：https://github.com/hdodenhof/CircleImageView
- 3.图片剪裁：https://github.com/Yalantis/uCrop
- 3.1.SmartCropper图片剪裁：https://github.com/pqpo/SmartCropper
- 4.glide图片下载：https://github.com/bumptech/glide
- 5.picasso图片下载: https://github.com/square/picasso
- 6.图片压缩Luban： https://github.com/Curzibn/Luban
- 7.图片压缩Compressor：https://github.com/zetbaitsu/Compressor
- 8.大图查看，防止OOM: https://github.com/davemorrissey/subsampling-scale-image-view
- 9.多图上传显示：https://www.jianshu.com/p/281fd392870b


##### 布局：
- 1.flex-box: https://github.com/google/flexbox-layout
- 2.flowLayout: https://github.com/hongyangAndroid/FlowLayout


##### pickerView
- 1.仿iOSPickerView: https://github.com/Bigkoo/Android-PickerView
- 2.安卓PickerView: https://github.com/gzu-liyujiang/AndroidPicker
- 3.文件选择器：https://github.com/DroidNinja/Android-FilePicker


##### 轮播图：
- 1.ConvenientBanner：https://github.com/Bigkoo/Android-ConvenientBanner
- 2.BGABanner：https://github.com/bingoogolapple/BGABanner-Android
- 3.MZBannerView：https://github.com/pinguo-zhouwei/MZBannerView
- 4.RecyclerBanner：https://github.com/amy993/RecyclerBanner
- 5.XBanner：https://github.com/xiaohaibin/XBanner
- 6.banner 已经有段时间不维护了: https://github.com/youth5201314/banner
- 7.引导页：https://github.com/Cleveroad/SlidingTutorial-Android
- 8.轮播图：https://github.com/pinguo-zhouwei/MZBannerView


##### 跑马灯
- 1.MarqueeView: https://github.com/sunfusheng/MarqueeView
- 2.MarqueeViewLibrary: https://github.com/gongwen/MarqueeViewLibrary
- 2.XMarqueeView: https://github.com/xiaohaibin/XMarqueeView


##### webView
- 1.高度封装的webView: https://github.com/Justson/AgentWeb


##### 角标
- 1.BadgeView 包含拖拽：https://github.com/bingoogolapple/BGABadgeView-Android
- 2.桌面图片角标：https://github.com/leolin310148/ShortcutBadger


##### 文件下载框架：
- 1.FileDownloader：https://github.com/lingochamp/FileDownloader


##### 图片或视频选择
- 1.Matisse由知乎推出的图片选择器：https://github.com/zhihu/Matisse
- 2.仿微信图片选择器：https://github.com/donglua/PhotoPicker
- 3.图片选择器：https://github.com/Lichenwei-Dev/ImagePicker
- 4.图片选取器：https://github.com/donkingliang/ImageSelector
- 5.图片选取器：https://github.com/bingoogolapple/BGAPhotoPicker-Android
- 6.多文件选择：https://github.com/fishwjy/MultiType-FilePicker
- 7.图片选择：https://github.com/jeasonlzy/ImagePicker


##### 视频播放器：
- 1.节操播放器：https://github.com/lipangit/JiaoZiVideoPlayer
- 2.多功能视频播放器：https://github.com/CarGuo/GSYVideoPlayer


##### 蓝牙
- 1.FastBle：https://github.com/Jasonchenlijian/FastBle
- 2.蓝牙: https://github.com/Polidea/RxAndroidBle


##### 日志打印：
- 1.logger：https://github.com/orhanobut/logger


##### fragment:
- 1.Fragmentation: https://github.com/YoKeyword/Fragmentation


##### 注解：
- 1.butterknife：https://github.com/JakeWharton/butterknife
- 2.注入dagger2: https://github.com/google/dagger


##### Rx
- 1.控件的事件绑定RxBinding：https://github.com/JakeWharton/RxBinding
- 3.工具库：https://github.com/vondear/RxTool
- 4.RxJava:  https://github.com/ReactiveX/RxJava
- 5.RxAndroid:  https://github.com/ReactiveX/RxAndroid
- 6.Rx生命周期：https://github.com/trello/RxLifecycle


##### 事件总线
- 1.事件总线RxBus: https://github.com/AndroidKnife/RxBus
- 1.事件总线EventBus: https://github.com/greenrobot/EventBus
- 3.LiveData: https://www.jianshu.com/p/9677b7a584bb

##### 图表
- 1.MPAndroidChart：https://github.com/PhilJay/MPAndroidChart
- 2.表格：https://github.com/huangyanbin/smartTable


##### JSON
- 1.fastjson: https://github.com/alibaba/fastjson
- 2.gson: https://github.com/google/gson


##### 动画
- 1.recyclerview-animators：https://github.com/wasabeef/recyclerview-animators
- 2.ViewAnimator动画：https://github.com/florent37/ViewAnimator


#### 三、优化
- 0.使用插件优化代码：https://www.jianshu.com/p/2b8d34b2267c
- 1.内测泄漏检测：https://github.com/square/leakcanary
- 2.网络调试工具需要翻墙：https://github.com/facebook/stetho
- 3.dex65535文件报错multidex：implementation 'com.android.support:multidex:1.0.1'
- 4.安卓插件生成模型：gsonformat 支持gson、fastjson等
- 5.图片高质量压缩：https://www.jianshu.com/p/f305fb008ab6
- 6.apk优化：https://www.jianshu.com/p/244910d4e942
- 7.java文件头的注释修改：https://www.jianshu.com/p/6dde8bcd5ed7
- 8..9图片制作：https://www.jianshu.com/p/803b62730218 & https://www.jianshu.com/p/3fd048644e3f
- 9.apk下载安装：https://www.jianshu.com/p/e3b33f57bc8d
- 10.加快gradle编译速度：https://www.jianshu.com/p/200d55b4d40a
- 11.打造输入自己的AS插件：https://www.jianshu.com/p/bf54eddfba6e
- 12.欢迎页优化SplashFragment+StubView：https://www.jianshu.com/p/add0d4a03914 & https://blog.csdn.net/chenliguan/article/details/53997436 & 源码：https://github.com/kpioneer123/SplashOptimize
- 13.图片文件目录drawable 和 mipmap 说明：https://www.jianshu.com/p/68ae4aa43648


#### 四、具有参考性的项目
- 0.AOP切面编程项目：https://github.com/north2016/T-MVP
- 1.今日头条RxJava + Retrofit + MVP：https://github.com/chaychan/TouTiao
- 2.足球项目RxJava+Retrofit+MVP：https://github.com/wangjian1154/FootballApp
- 3.*新闻类很好的mvp架构MVP + RxJava + Retrofit+ARouter+Dagger2：https://github.com/Peakmain/gankzhihu
- 4.DialogFragment弹窗封装：https://github.com/developerHaoz/DialogFragmentDemos
- 5.万能的Dialog：https://github.com/AnthonyCoder/PowerfulDialog
- 6.通用的PopupWindow: https://github.com/crazyqiang/AndroidStudy
- 7.建造者模式衍生的全局Dialog: https://github.com/YuanTiger/Design-Pattern/blob/master/app/src/main/java/com/my/designdemo/design/builder/dialog/DialogProduct.java
- 8.tags: https://github.com/ithedan/CustomRadioGroupTag
- 9.aidl 不同进程之间进行数据通信
- 10.全面屏、刘海屏适配：https://blog.csdn.net/DJY1992/article/details/80689632  &   https://github.com/LuckyYangChen/AdaptNotch
- 11、引导页制作：https://www.jianshu.com/p/adb21180862a
- 12、LiveData 使用: https://github.com/SelfZhangTQ/T-MVVM


#### 五、适配
##### 屏幕适配
- AndroidAutoSize: https://github.com/JessYanCoding/AndroidAutoSize
- 高效UI适配：https://www.jianshu.com/p/b031c40cdd76
- 好用的UI适配方案：https://www.jianshu.com/p/1302ad5a4b04
- 参考：https://www.jianshu.com/p/1302ad5a4b04

##### 约束布局（减少嵌套，提高性能）
- 实战：https://www.jianshu.com/p/f110b4fcfe93
- 实战: https://blog.csdn.net/vicwudi/article/details/81254371
- 实战：https://blog.csdn.net/lingjianglin/article/details/81835110
- activity手写布局：https://www.jianshu.com/p/7888cde8292f
- addview: http://xgfe.github.io/2017/09/17/ivanchou/layout-with-constraintlayout-by-programming/
- 补充：https://blog.csdn.net/xu404741377/article/details/79480802?utm_source=blogxgwz2
- *约束布局一：https://www.jianshu.com/p/0e0d440f9c2f
- *约束布局二：https://www.jianshu.com/p/35896a8abb35

##### 全面屏和刘海屏适配
1.全面屏适配
`AndroidManifest.xml`中的`application`配置 `android:resizeableActivity="true"`
```
<meta-data android:name="android.notch_support" android:value="true"/>
<meta-data
android:name="android.max_aspect"
android:value="2.4" />
```
2.刘海屏适配
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    if (getWindow() != null) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().setAttributes(lp);
    }
}
```
布局调整 参考：https://github.com/LuckyYangChen/AdaptNotch

##### android 9.0 （Build.VERSION_CODES.P）适配
参考：https://www.jianshu.com/p/9e9e902ea039

1.网络适配
`res`下添加`xml`目录，并添加`network_security_config.xml`文件， 最后在`AndroidManifest.xml`中的`application`配置 `android:networkSecurityConfig="@xml/network_security_config"`
```
<network-security-config>
<base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

##### android 8.0适配
- 参考：https://blog.csdn.net/qq_17766199/article/details/80965631
- 应用图片适配：https://www.jianshu.com/p/e593515cbf87 & https://www.jianshu.com/p/25abce3f0492
- 关于透明、强制横、竖屏某一引起的闪退：https://www.jianshu.com/p/d0d907754603 & https://www.jianshu.com/p/1ed07ddbbbe5
- 通知栏适配、拓展了应用角标、通知栏自定义：https://www.jianshu.com/p/39f33accd687 & https://www.jianshu.com/p/b83fc1697232 & https://www.jianshu.com/p/839672499aaa

##### android 7.0适配
- 参考：https://blog.csdn.net/qq_17766199/article/details/77404712

##### android 6.0适配
- 1.动态权限申请适配

##### 软键盘适配
- 1.几种键盘遮挡输入框的适配方案：https://www.jianshu.com/p/ac7063e23b93

##### jdk 8 的新特性
- Optional  有效的避免空指针错误
- 避免空指针异常：https://www.jianshu.com/p/461eb574b2fc

##### 模拟器
- 上传文件到模拟器中：https://blog.csdn.net/whdAlive/article/details/80686807
- 查看模拟器的文件目录：https://blog.csdn.net/arldrz/article/details/77651665


#### 六、进阶
- 1.Glide: 添加下载进度监听： https://www.jianshu.com/p/61a0761e6e81

