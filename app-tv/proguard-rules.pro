
##---------------Begin: proguard configuration for EventBus  ----------
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
##---------------End: proguard configuration for EventBus  ----------

##---------------Begin: proguard configuration for RxJava+RxAndroid  ----------
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

##---------------End: proguard configuration for RxJava+RxAndroid  ----------


##---------------Begin: proguard configuration for AndPermission  ----------
-dontwarn com.yanzhenjie.permission.**

##---------------End: proguard configuration for AndPermission  ----------


##---------------Begin: proguard configuration for ToastUtils  ----------
-keep class com.hjq.toast.** {*;}
##---------------End: proguard configuration for ToastUtils  ----------





