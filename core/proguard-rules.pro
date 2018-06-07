# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\adt-bundle-windows-x86_64-20140702\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#
##-----------------混淆配置设定------------------------------------------------------------------------
#-optimizationpasses 5                                                      #指定代码压缩级别
#-dontusemixedcaseclassnames                                               #混淆时不会产生形形色色的类名
#-dontskipnonpubliclibraryclasses                                          #指定不忽略非公共类库
#-dontpreverify                                                              #不预校验，如果需要预校验，是-dontoptimize
#-ignorewarnings                                                             #屏蔽警告
#-verbose                                                                     #混淆时记录日志
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    #优化
#
##-----------------导入第三方包,但是在当前版本中使用会报 input jar file is specified twice 错误，所以注释掉
##-libraryjars libs/android.support.v4.jar
##-libraryjars libs/BaiduLBS_Android.jar
##-libraryjars libs/commons-httpclient-3.1.jar
##-libraryjars libs/jackson-annotations-2.1.4.jar
##-libraryjars libs/jackson-core-2.1.4.jar
##-libraryjars libs/jackson-databind-2.1.4.jar
##-libraryjars libs/xUtils-2.6.14.jar
#
##-----------------不需要混淆第三方类库------------------------------------------------------------------
#-dontwarn android.support.v4.**                                             #去掉警告
#-keep class android.support.v4.** { *; }                                    #过滤android.support.v4
#-keep interface android.support.v4.app.** { *; }
#-keep public class * extends android.support.v4.**
#-keep public class * extends android.app.Fragment
#
#-keep class butterknife.** { *; }                                            #butterknife
#-dontwarn butterknife.internal.**
#-keep class **$$ViewBinder { *; }
#
#-keepclasseswithmembernames class * {
#    @butterknife.* <fields>;
#}
#
#-keepclasseswithmembernames class * {
#    @butterknife.* <methods>;
#}
#
#-dontwarn javax.annotation.**                                               #retrofit+rxjava
#-dontwarn javax.inject.**
## OkHttp3
#-dontwarn okhttp3.logging.**
#-keep class okhttp3.internal.**{*;}
#-dontwarn okio.**
## Retrofit
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature-keepattributes Exceptions
## RxJava RxAndroid
#-dontwarn sun.misc.**
#-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
#    long producerIndex;
#    long consumerIndex;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode producerNode;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode consumerNode;
#}
#
## Gson
#-keep class com.google.gson.stream.** { *; }
#-keepattributes EnclosingMethod
#
#-keep public class * implements com.bumptech.glide.module.GlideModule                  #glide
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}
##-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#
#
#-keepclassmembers class * {
#   public <init> (org.json.JSONObject);
#}
#
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#
##-----------------不需要混淆系统组件等-------------------------------------------------------------------
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.preference.Preference
#-keep public class * extends com.trello.rxlifecycle.components.support.RxAppCompatActivity
#-keep public class com.android.vending.licensing.ILicensingService
#
#-keep class com.classtc.test.entity.**{*;}                                   #过滤掉自己编写的实体类
#
#
##----------------保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在------------------------------------
#-keepclasseswithmembernames class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}
#
#-keepclasseswithmembernames class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}