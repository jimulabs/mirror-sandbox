package com.jimulabs.mirrorsandbox;

import android.view.View;

/**
 * MirrorSandbox classes are considered "design mode" code for quick experiments or populating views
 * with mock data. Think of it as a REPL for Android UI (layouts and animations) when used in
 * combination with the Java code hot-swapping of Mirror.
 *
 * Mirror uses classes that implement this interface and set the "sandbox" attribute of
 * a mirror screen file:
 *
 * &lt;screen sandbox="foo.bar.BarBox"&gt;
 *
 * In most cases, you can subclass {@link com.jimulabs.mirrorsandbox.MirrorSandboxBase} which
 * provides some base implementations.
 *
 * Created by lintonye on 2014-12-23.
 */
public interface MirrorSandbox {
    /**
     * Mirror calls this method from the UI thread after the layout pass for the entire screen finishes.
     * Methods such as View#getMeasuredWidth() will return proper values.
     *
     * This method is considered as a "design mode" used for quick experiments or populating
     * views with mock data. This method is not supposed to be called from production code.
     * @param rootView
     */
    void onLayoutDone(View rootView);

    /**
     * Mirror calls this method from the UI thread in the Activity#onDestroy() call back.
     * This method can be used to release things that are not supposed to be persist across refreshes.
     */
    void onDestroy();
}
