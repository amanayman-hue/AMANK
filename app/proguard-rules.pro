# AMANK - حماية روحي 2.0
-keep class com.amank.family.** { *; }
-keep class com.google.android.gms.location.** { *; }
-keep class androidx.security.crypto.** { *; }
-dontwarn **
-optimizations !code/simplification/arithmetic
-repackageclasses 'amank.rouhi.secure'
-allowaccessmodification
-keepclasseswithmembers class * { native <methods>; }
