package com.jimulabs.googlemusicmock.box;

import android.content.Context;
import android.view.View;

import com.jimulabs.mirrorsandbox.MirrorSandbox;
import com.jimulabs.mirrorsandbox.MirrorSandboxBase;
import com.jimulabs.motionkit.MirrorAnimator;
import com.jimulabs.motionkit.MirrorView;
import com.jimulabs.motionkit.MirrorWrap;
import com.jimulabs.motionkit.MotionKit;

import java.util.List;

/**
 *
 * Extend this class to get a sandbox which is useful for designing and developing
 * Android's property animators. This class includes some helper methods for finding a view
 * and choreographing animators.
 *
 * Created by lintonye on 14-12-16.
 */
public abstract class MirrorAnimatorSandbox extends MirrorSandboxBase {
    private static final String LOG_TAG = "MirrorAnimatorSandbox";

    public MirrorAnimatorSandbox(View root) {
        super(root);
        setGlobalSpeed(1);
    }

    @Override
    public void destroySandbox() {
        // does nothing by default
    }

    public static class CannotFindViewException extends RuntimeException {
        public final String ref;

        public CannotFindViewException(String ref) {
            super(ref);
            this.ref = ref;
        }
    }

    protected Context getContext() {
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
        return MotionKit.together(animators);
    }

    protected MirrorAnimator together(List<MirrorAnimator> animators) {
        return MotionKit.together(animators);
    }

    protected MirrorAnimator sq(MirrorAnimator... animators) {
        return sequence(animators);
    }

    protected MirrorAnimator sq(List<MirrorAnimator> animators) {
        return MotionKit.sequence(animators);
    }

    protected MirrorWrap wrapToAnimate(Object obj) {
        return new MirrorWrap(getContext(), obj);
    }

    protected MirrorAnimator sequence(MirrorAnimator... animators) {
        return MotionKit.sequence(animators);
    }

    protected MirrorAnimator sequence(List<MirrorAnimator> animators) {
        return MotionKit.sequence(animators);
    }

    public void setGlobalSpeed(double speed) {
        MotionKit.setGlobalSpeed(speed);
    }
}
