package com.jimulabs.mirrorsandbox;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lintonye on 14-12-16.
 */
public class AnimatorUtils {
    private static double sSpeed = 1;

    public synchronized static void setGlobalSpeed(double speed) {
        sSpeed = speed;
    }

    static synchronized long computeDuration(long duration1x) {
        return (long) (duration1x * 1/sSpeed);
    }

    public static MirrorAnimator together(Context context, MirrorAnimator... animators) {
        return together(context, Arrays.asList(animators));
    }

    public static MirrorAnimator sequence(Context context, MirrorAnimator... animators) {
        return sequence(context, Arrays.asList(animators));
    }

    public static MirrorAnimator together(Context context, List<MirrorAnimator> animators) {
        return new MirrorAnimatorSet(context, animators,
                MirrorAnimatorSet.Ordering.Together);
    }

    public static MirrorAnimator sequence(Context context, List<MirrorAnimator> animators) {
        return new MirrorAnimatorSet(context, animators,
                MirrorAnimatorSet.Ordering.Sequentially);
    }

    public static MirrorAnimator animator(Context context, Object target, String property, int... values) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, property, values);
        Keyframe firstFrame = Keyframe.ofInt(0, values[0]);
        Keyframe lastFrame = Keyframe.ofInt(0, values[values.length-1]);
        return new MirrorObjectAnimator(context, animator, firstFrame, lastFrame);
    }

    public static MirrorAnimator animator(Context context, Object target, String property, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, values);
        Keyframe firstFrame = Keyframe.ofFloat(0, values[0]);
        Keyframe lastFrame = Keyframe.ofFloat(0, values[values.length-1]);
        return new MirrorObjectAnimator(context, animator, firstFrame, lastFrame);
    }

    public static void setInterpolator(Context context, Animator animator, int resId) {
        Interpolator interpolator = AnimationUtils.loadInterpolator(context, resId);
        animator.setInterpolator(interpolator);
    }
}
