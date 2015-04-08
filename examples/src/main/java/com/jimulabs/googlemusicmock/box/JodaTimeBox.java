package com.jimulabs.googlemusicmock.box;

import android.content.Context;
import android.view.View;

import com.jimulabs.mirrorsandbox.MirrorSandboxBase;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by lintonye on 15-04-05.
 */
public class JodaTimeBox extends MirrorSandboxBase {
    public JodaTimeBox(View rootView) {
        super(rootView);
    }

    /**
     * Mirror calls this method from the UI thread BEFORE the sandbox object is instantiated.
     * It is useful for initialization before any views are created, for example the code you put
     * in Application#onCreate().
     *
     * @param context
     */
    @SuppressWarnings("unused")
    public static void $init(Context context) {
        JodaTimeAndroid.init(context);
    }

    @Override
    public void $onLayoutDone(View rootView) {

    }
}
