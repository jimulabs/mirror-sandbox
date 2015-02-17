package com.jimulabs.mirrorsandbox

import kotlin.properties.Delegates

class PersonNameGen : MockDataGenerator<String> {
    override val samples: Stream<String> by Delegates.lazy {
        var names = arrayListOf("Bill Gates", "Steve Jobs")
        names.stream()
    }
}