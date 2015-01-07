
#Mirror Sandbox
Mirror Sandbox is a companion library for the [Java code hot-swapping feature in jimu Mirror](TODO link). Mirror Sandbox classes are "design mode" code that allow you to build and preview animations and interactions **piecewise** -- think of it as a [REPL](TODO link) for Android UI development.

Typical use cases for Mirror Sandbox include:

1. Live-coding animations and interactions. Once satisfied, **directly use the animation code in production**.
2. Experimenting and learning UI related Android APIs by executing code **piecewise** - just as what you can do with Ruby REPL.
3. Populating views with mock data where Mirror's sample data XML doesn't have support yet: `myCustomView.setData(someDataModel)`

See [this blog post](TODO link) for more information.

#Download
via Gradle:
```
compile 'com.jimulabs.mirrorsandbox:mirror-sandbox:0.1.+'
```
#Usage
## Overview
Mirror Sandbox consists of two parts: 

1. `MirrorSandbox` interface that Mirror calls when previewing a screen
2. A set of simple wrappers of Android's property animators that make it easy to create and choreograph animations, such as [`MirrorAnimatorSandbox`](TODO link), [`MirrorAnimator`](TODO link) etc. See the source code for detailed documentation.

##Steps
**Step 1:** Extend `MirrorAnimatorSandbox` (or implement the interface `MirrorSandbox`).


```Java
public class MySandbox extends MirrorAnimatorSandbox {
	public MySandbox(View view) { super(view); }
	@Override
	public void enterSandbox() {
		// here you can experiment animations
		MirrorAnimator anim1 = $(R.id.view1).scale(0, 3, 1)
			.interpolator(android.R.interpolator.bounce)
			.duration(1000);
		MirrorAnimator anim2 = $(R.id.view2).alpha(0, 1);
		// choreograph animators
		sequence(anim1, anim2).start();
		// or populate your views with mock data
		ChartView chart = (ChartView)$(R.id.chart).getView();
		chart.setData(createSomeMockData());
	}
}
```

**Step 2:** In a mirror screen file, set the `sandbox` attribute of the `screen` element to the fully qualified name of your sandbox class:

```xml
<screen sandbox="com.mypackage.MySandbox">
  ...
</screen>
```
See [examples](TODO link) for details.

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