# 系统应用开发需要的命令

## 文件转移

pc:

adb push C:/Sun/AndroidWorkSpace/ProtectService/app/build/outputs/apk/app-debug.apk /sdcard/ddkj

shell:

mv /sdcard/ddkj/app-debug.apk /system/app

## adb
adb shell su

mount -o remount /system

## 权限的修改
rw permission:
chmod 755 app-debug.apk
chown root:root app-debug.apk

## 参考文章