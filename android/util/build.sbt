import android.Keys._

android.Plugin.androidBuild

name := "util"

platformTarget in Android := "android-19"

// call install and run without having to prefix with android:
run <<= run in Android
 
install <<= install in Android