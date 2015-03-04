package com.jimulabs.mirrorsandbox.mockdata;

import com.android.internal.util.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lintonye on 15-03-04.
 */
public class MockData {

    public String imageUrl(final ImageSize maxSize) {
        ImageGen.MockImage img = new ImageGen().tossOne(new Predicate<ImageGen.MockImage>() {
            @Override
            public boolean apply(ImageGen.MockImage mockImage) {
                return mockImage.width <= maxSize.width;
            }
        });
        return img.url;
    }

    public String personName() {
        return new PersonNameGen().tossOne();
    }

    public String phrase() {
        //TODO make proper phrase
        return new PersonNameGen().tossOne();
    }

    public String paragraph() {
        //TODO make proper paragraph
        return new PersonNameGen().tossOne();
    }

    public static abstract class MockDataGenerator<T> {
        private Random mRandom = new Random();

        T tossOne() {
            return tossOne(null);
        }

        T tossOne(Predicate<T> predicate) {
            List<T> filtered;
            if (predicate == null) {
                filtered = getSamples();
            } else {
                filtered = new ArrayList<>();
                for (T i : getSamples()) {
                    if (predicate.apply(i)) {
                        filtered.add(i);
                    }
                }
            }
            if (filtered.size() <= 0) {
                throw new IllegalArgumentException("No samples found that match the predicate");
            }
            int idx = mRandom.nextInt(filtered.size());
            return filtered.get(idx);
        }

        protected abstract List<T> getSamples();
    }
}
