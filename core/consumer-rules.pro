-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

#-keep, includedescriptorclasses class org.bouncycastle.** { *; }
#-keep, includedescriptorclasses class org.conscrypt.** { *; }
#-keep, includedescriptorclasses class org.openjsse.** { *; }

-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**