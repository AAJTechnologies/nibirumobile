Nibiru Mobile is a framework for developing portable mobile applications. It was originally created at Oxen and is currently developed and improved by AAJ Technologies.

You can use it in order to create portable HTML-based (GWT) application and/or native Android/iOS applications reusing most of the code.

It provides:
 * A common API for components typically used on mobile development.
 * Implementations of these APIs for GWT, Android and iOS.

It was built with the same philosophy used in [Nibiru Framework](https://github.com/AAJTechnologies/nibiru): creating a common API and structure for typical projects. But it is a completely different framework.

The components are based on JSR330 dependency injection, but implementations are based on many technologies, such as:

 * [HTML](http://en.wikipedia.org/wiki/HTML) platform: [GWT](http://www.gwtproject.org), [GIN](http://code.google.com/p/google-gin/), [Apache Cordova](http://incubator.apache.org/cordova/), [PhoneGap](http://phonegap.com/), [persistence.js](http://persistencejs.org/), [MGWT](http://www.m-gwt.com/).
 * [Android](https://www.android.com/) platform: [Guice](http://code.google.com/p/google-guice/), [RoboGuice](http://code.google.com/p/roboguice/), [OrmLite](http://ormlite.com/).
 * [iOS](http://www.apple.com/ios) platform: [RoboVM](http://robovm.com/).
 * [Windows Phone](https://www.windowsphone.com) platform: [GWT](http://www.gwtproject.org), [WinJS](https://dev.windows.com/en-us/develop/winjs).
 * [Firefox OS](https://www.mozilla.org/en-US/firefox/os) platform: [GWT](http://www.gwtproject.org).


just to name the main ones.

Please check the reference documentation and demo:
 * [Reference (WIP)](https://github.com/AAJTechnologies/nibirumobile/wiki/reference)
 * [Online demo](http://nibiru.oxen.com.ar) (Chrome and Safari only, since it is based on [MGWT](http://www.m-gwt.com/))
 * [Android demo](https://github.com/AAJTechnologies/nibirumobile/raw/master/sample/dist/NibiruMobileDemo.apk) (Android 2.2 required)
 * [Demo app screenshots](https://github.com/AAJTechnologies/nibirumobile/wiki/SampleAppScreenshots)

Look at the [Getting Started](https://github.com/AAJTechnologies/nibirumobile/wiki/GettingStarted) section for a quick start. Also check the [brainstorming](https://github.com/AAJTechnologies/nibirumobile/wiki/Brainstorming) section for ideas about the future of the framework.

Believe it or not, there are [people using](https://github.com/AAJTechnologies/nibirumobile/wiki/WhoIsUsingThis) this framework!

The API is still unstable. Wer'e working on many improvements, as you can see on the [releases page](https://github.com/AAJTechnologies/nibirumobile/wiki/Releases).
