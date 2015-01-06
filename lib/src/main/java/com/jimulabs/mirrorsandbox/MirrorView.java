package com.jimulabs.mirrorsandbox;

import android.view.View;

/**
 * Simple wrapper for {@link android.view.View} which provides a few helper methods to make it easy
 * to create animators for the wrapped view.
 *
 * Created by lintonye on 14-12-16.
 */
public class MirrorView<T extends View> extends MirrorWrap {
    private final T mView;

    public MirrorView(T view) {
        super(view.getContext(), view);
        mView = view;
    }

    public MirrorAnimator scale(float... values) {
        return AnimatorUtils.together(mView.getContext(), scaleX(values), scaleY(values));
    }

    public MirrorAnimator scaleY(float... values) {
        return animator("scaleY", values);
    }

    public MirrorAnimator scaleX(float... values) {
        return animator("scaleX", values);
    }

    public MirrorAnimator bottom(int... values) {
        return animator("bottom", values);
    }

    public MirrorAnimator alpha(float... values) {
        return animator("alpha", values);
    }

    public int getHeight() {
        return mView.getMeasuredHeight();
    }

    public int getTop() {
        return mView.getTop();
    }

    public T getView() {
        return mView;
    }
}
