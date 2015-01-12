package com.jimulabs.mirrorsandbox;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.jimulabs.util.Optional;

import java.lang.reflect.Method;
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
        return (long) (duration1x * 1 / sSpeed);
    }

    public static MirrorAnimator together(Context context, List<MirrorAnimator> animators) {
        return new MirrorAnimatorSet(context, animators,
                MirrorAnimatorSet.Ordering.Together);
    }

    public static MirrorAnimator together(Context context, MirrorAnimator... animators) {
        return together(context, Arrays.asList(animators));
    }

    public static MirrorAnimator sequence(Context context, List<MirrorAnimator> animators) {
        return new MirrorAnimatorSet(context, animators,
                MirrorAnimatorSet.Ordering.Sequentially);
    }

    public static MirrorAnimator sequence(Context context, MirrorAnimator... animators) {
        return sequence(context, Arrays.asList(animators));
    }

    public static MirrorAnimator animator(Context context, Object target, String property, int... values) {
        if (hasIntSetter(target, property)) {
            ObjectAnimator animator = ObjectAnimator.ofInt(target, property, values);
            Keyframe firstFrame = Keyframe.ofInt(0, values[0]);
            Keyframe lastFrame = Keyframe.ofInt(0, values[values.length - 1]);
            return new MirrorObjectAnimator(context, animator, firstFrame, lastFrame);
        } else {
            float[] fvalues = new float[values.length];
            for (int i = 0; i < fvalues.length; i++) {
                fvalues[i] = values[i];
            }
            return animator(context, target, property, fvalues);
        }
    }

    private static boolean hasIntSetter(Object target, String property) {
        return getSetter(target, property, int.class).isPresent();
    }

    public static MirrorAnimator animator(Context context, Object target, String property, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, values);
        Keyframe firstFrame = Keyframe.ofFloat(0, values[0]);
        Keyframe lastFrame = Keyframe.ofFloat(0, values[values.length - 1]);
        return new MirrorObjectAnimator(context, animator, firstFrame, lastFrame);
    }

    public static void setInterpolator(Context context, Animator animator, int resId) {
        Interpolator interpolator = AnimationUtils.loadInterpolator(context, resId);
        animator.setInterpolator(interpolator);
    }

    static Optional<Method> getSetter(Object target, String propertyName, Class paramType) {
        String setterName = "set" + capitalizeHead(propertyName);
        try {
            return Optional.of(target.getClass().getMethod(setterName, paramType));
        } catch (NoSuchMethodException e) {
            return Optional.absent();
        }
    }

    private static String capitalizeHead(String propertyName) {
        return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }
}
