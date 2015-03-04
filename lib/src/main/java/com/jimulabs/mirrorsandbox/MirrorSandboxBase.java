package com.jimulabs.mirrorsandbox;

import android.view.View;

/**
 * Created by lintonye on 15-02-13.
 */
public abstract class MirrorSandboxBase implements MirrorSandbox {
    protected final View mRootView;

    public MirrorSandboxBase(View rootView) {
        mRootView = rootView;
    }

    @Override
    public void $onCreate(View rootView) {
        // do nothing by default
    }

    @Override
    public void $onDestroy() {
        // do nothing by default
    }
}
