[![Build Status](https://travis-ci.org/jimulabs/mirror-sandbox.png)](https://travis-ci.org/jimulabs/mirror-sandbox)

#Mirror Sandbox
Mirror Sandbox is a companion library for the Java code hot-swapping feature in [jimu Mirror](http://jimumirror.com). Mirror Sandbox classes are "design mode" code that allow you to build and preview animations and interactions **piecewise** -- think of it as a [REPL](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop) for Android UI development.

Typical use cases for Mirror Sandbox include:

1. Live-coding animations and interactions. Once satisfied, **directly use the animation code in production**.
2. Experimenting and learning UI related Android APIs by executing code **piecewise** - just as what you can do with Ruby REPL.
3. Populating views with mock data where Mirror's sample data XML doesn't have support yet: `myCustomView.setData(someDataModel)`

See [this blog post](http://jimulabs.com/2015/01/building-android-animations-mirror-sandbox-piecewise/) for more information.

#Download
via Gradle:
```
debugCompile 'com.jimulabs.mirrorsandbox:mirror-sandbox:0.2.+'
```
#Usage
## Overview
Mirror Sandbox consists of two parts: 

1. `MirrorSandbox` interface that Mirror calls when previewing a screen
2. A set of simple utilities for generating mock data. See [MockData](https://github.com/jimulabs/mirror-sandbox/blob/master/lib/src/main/kotlin/com/jimulabs/mirrorsandbox/Mockdata.kt) class.

Note, the wrappers of Android's property animators have been moved to its own repo, [motion-kit](https://github.com/jimulabs/motion-kit).

##Steps
**Step 1:** Extend `MirrorSandboxBase` (or implement the interface `MirrorSandbox`).


```Java
public class MySandbox extends MirrorSandboxBase {
	public MySandbox(View view) { super(view); }
    @Override
    public void onLayoutDone(View rootView) {
        List<Album> albums = new ArrayList<>();
        MockData md = new MockData();
        for (int i = 0; i < 100; i++) {
            Album album = new Album(md.imageUrl(ImageSize.small), md.personName(),
                    md.phrase(), md.paragraph());
            albums.add(album);
        }
        albumList.setAdapter(new AlbumsAdapter(albums));
    }
}
```

**Step 2:** In a mirror screen file, set the `sandbox` attribute of the `screen` element to the fully qualified name of your sandbox class:

```xml
<screen sandbox="com.mypackage.MySandbox">
  ...
</screen>
```
See [examples](https://github.com/jimulabs/mirror-sandbox/tree/master/examples) for details.

#License
```
Copyright 2015 jimu Labs Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

> Written with [StackEdit](https://stackedit.io/). <== I don't mind leaving this here as it's an awesome editor!