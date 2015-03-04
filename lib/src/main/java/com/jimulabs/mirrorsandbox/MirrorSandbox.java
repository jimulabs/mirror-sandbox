package com.jimulabs.mirrorsandbox;

import android.view.View;

/**
 * MirrorSandbox classes are considered "design mode" code for quick experiments or populating views
 * with mock data. Think of it as a REPL for Android UI (layouts and animations) when used in
 * combination with the Java code hot-swapping of Mirror.
 * <p/>
 * Mirror uses classes that implement this interface and set the "sandbox" attribute of
 * a mirror screen file:
 * <p/>
 * &lt;screen sandbox="foo.bar.BarBox"&gt;
 * <p/>
 * In most cases, you can subclass {@link com.jimulabs.mirrorsandbox.MirrorSandboxBase} which
 * provides some base implementations.
 * <p/>
 * Created by lintonye on 2014-12-23.
 */
public interface MirrorSandbox {
    /**
     * Mirror calls this method from the UI thread right after the sandbox object is created, BEFORE
     * the layout pass is done. This method can be used for sandbox-only initialization code.
     * <p/>
     * Methods with the prefix '$' are considered as "design mode" methods used for quick experiments
     * or populating views with mock data. Do not call from production code. However, it is encouraged
     * to call other methods in the sandbox class from production code to reuse the code written in
     * sandbox mode.
     *
     * @param rootView the root view that can be used to find child views in the layout
     */
    void $onCreate(View rootView);

    /**
     * Mirror calls this method from the UI thread after the layout pass for the entire screen finishes.
     * Methods such as View#getMeasuredWidth() will return proper values.
     * <p/>
     * Methods with the prefix '$' are considered as "design mode" methods used for quick experiments
     * or populating views with mock data. Do not call from production code. However, it is encouraged
     * to call other methods in the sandbox class from production code to reuse the code written in
     * sandbox mode.
     *
     * @param rootView the root view that can be used to find child views in the layout
     */
    void $onLayoutDone(View rootView);

    /**
     * Mirror calls this method from the UI thread in the Activity#onDestroy() call back.
     * This method can be used to release things that are not supposed to be persist across refreshes.
     * <p/>
     * Methods with the prefix '$' are considered as "design mode" methods used for quick experiments
     * or populating views with mock data. Do not call from production code. However, it is encouraged
     * to call other methods in the sandbox class from production code to reuse the code written in
     * sandbox mode.
     */
    void $onDestroy();
}
