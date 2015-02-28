package com.jimulabs.mirrorsandbox

import java.util.Random
import kotlin.properties.Delegates

public class MockData {
    fun imageUrl(size: ImageSize): String {
        var img = ImageGen().tossOne { it.height <= size.size && it.width <= size.size }
        return img.url
    }

    fun personName(): String {
        return PersonNameGen().tossOne()
    }

    fun phrase(): String {
        //TODO make proper phrase
        return PersonNameGen().tossOne()
    }

    fun paragraph(): String {
        //TODO make proper paragraph
        return PersonNameGen().tossOne()
    }
}

public enum class ImageSize(val size: Int) {
    small : ImageSize(360)
    large : ImageSize(1080)
    hd : ImageSize(1980)
}

trait MockDataGenerator<T> {
    val samples: Stream<T>
    fun tossOne(filter: (T) -> Boolean = { true }): T {
        var filtered = samples.filter { filter.invoke(it) }
        var random = Random()
        var idx = random.nextInt(filtered.count());
        return filtered.elementAt(idx)
    }
}

