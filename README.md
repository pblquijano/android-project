# android-project
Android Login Project

##Set Up Android Environment

###Prerequisites
-A Mac OS X or Windows development machine.
-Internet connection.

###Install Java Development Kit
1- To check if it is already installed, at the OS command prompt, type: java -version
-If this command reports Java version 1.8.x or later, you’re done—proceed to the next installation. If the reported version is earlier than 1.8.x, continue with this step.
-If you get a “command not found” error message, cocontinue with this step.
2- Go to oracle.com/java.
3- Download and run the installer for your operating system.

###Install Android Studio
1- You download installers for Android Studio tools from developer.android.com/studio.

2- To check if it’s already installed, look for the program file: Android Studio.app on Mac OS; studio.exe or studio64.exe (or a shortcut to Android Studio) on Windows.
-If you can’t find the program file, continue with this step.
-If you found the program file, run it, and then click About Android Studio in the menu. If the dialog box lists version 3.0 or later, skip to "Install Required Android SDK Versions". If it’s earlier than that version, continue with this step.
3- Go to developer.android.com/studio.
4- Download and run the installer for your operating system.
5- Step through the Android Studio Setup Wizard, then click Finish.

###Install Required Android SDK Versions
1- Open Android Studio.
2- At the bottom of the Welcome screen, click Configure, and in the dropdown menu, click SDK Manager.The Android SDK Manager appears.
3- In SDK Platforms tab select Api Level 23.
4- In SDK Tools tab select Android SDK Build-Tools, Android SDÑ Platform-Tools, Android SDK Tools And Support Repository.
5- Click in Ok to download all. 
6- When the downloads are finished, dismiss the SDK Manager.

##Deploy
###Open project
1- In the right Panel click "Open a existing Android Studio Project." 
2- Search and select the project folder to open.
3- Wait for the project to be loaded.
4- You can see the files and resources of the project.

###Configurate Emulator
1-In Android Studio, click the AVD Manager tool.
2- On the Your Virtual Devices page, click Create Virtual Device…
3- Select a device definition, and click Next.
4- Under Select a System Image, click Othe Images.
5- Scroll down to an image that supports API level 23 armeabi, and click Download next to the image’s name.
6-After the download completes, select the image and click Next.
7- If you like, you can change the AVD Name field to any value that helps you identify the AVD configuration. Leave the other values set to their defaults.
8- Click Finish.

###Run Project
1- Click Run Icon or type 'F5'.
2- Select the virtual device to run the project.
3- Wait until the app is fully loaded in the virtual device.
