package com.jimulabs.mirrorsandbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 *
 * Extend this class to get a sandbox which is useful for designing and developing
 * Android's property animators. This class includes some helper methods for finding a view
 * and choreographing animators.
 *
 * When you are done experimenting, just use the animators in production:
 *
 *   new MyFantasticAnimatorBox(view).someAnimation().start();
 *
 * Created by lintonye on 14-12-16.
 */
public abstract class MirrorAnimatorSandbox implements MirrorSandbox {
    private static final String LOG_TAG = "MirrorAnimatorSandbox";

    private View mRootView;

    public MirrorAnimatorSandbox() {
        setGlobalSpeed(1);
    }

    @Override
    public void destroySandbox() {
        // does nothing by default
    }

    @Override
    public void setRootView(View rootView) {
        mRootView = rootView;
    }

    @Override
    public void onViewCreated(View view, Context context, AttributeSet attrs) {
        // do nothing by default
    }

    public static class CannotFindViewException extends RuntimeException {
        public final String ref;

        public CannotFindViewException(String ref) {
            super(ref);
            this.ref = ref;
        }
    }

    private Context getContext() {
        return mRootView.getContext();
    }

    protected MirrorView $(int viewId) {
        View v = mRootView.findViewById(viewId);
        if (v!=null) {
            return new MirrorView(v);
        } else {
            throw new CannotFindViewException(String.format("0x%08X", viewId));
        }
    }

    protected MirrorAnimator tg(MirrorAnimator... animators) {
        return together(animators);
    }

    protected MirrorAnimator together(MirrorAnimator... animators) {
        return AnimatorUtils.together(getContext(), animators);
    }

    protected MirrorAnimator together(List<MirrorAnimator> animators) {
        return AnimatorUtils.together(getContext(), animators);
    }

    protected MirrorAnimator sq(MirrorAnimator... animators) {
        return sequence(animators);
    }

    protected MirrorAnimator sq(List<MirrorAnimator> animators) {
        return AnimatorUtils.sequence(getContext(), animators);
    }

    protected MirrorWrap wrapToAnimate(Object obj) {
        return new MirrorWrap(getContext(), obj);
    }

    protected MirrorAnimator sequence(MirrorAnimator... animators) {
        return AnimatorUtils.sequence(getContext(), animators);
    }

    protected MirrorAnimator sequence(List<MirrorAnimator> animators) {
        return AnimatorUtils.sequence(getContext(), animators);
    }

    public void setGlobalSpeed(double speed) {
        AnimatorUtils.setGlobalSpeed(speed);
    }
}
