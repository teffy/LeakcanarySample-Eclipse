# LeakcanarySample-Eclipse
#Leakcanary的Eclipse版本

本项目是根据Leakcanary项目改造而来的，本人项目中用到Leakcanary，如果只是作为普通app来开发，只需要根据leakcanary项目说明中的一样，添加
```
dependencies {
   debugCompile 'com.squareup.leakcanary:leakcanary-android:1.3.1'
   releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.3.1'
 }
```
即可；
但是本人做rom开发，项目要编进rom中，所以需要通过源码来编译，需要把code放进项目中，所以就根据Leakcanary[https://github.com/square/leakcanary]改造成了一个Eclipse项目
