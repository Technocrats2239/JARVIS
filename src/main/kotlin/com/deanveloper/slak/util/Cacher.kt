package com.deanveloper.slak.util

import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * Represents the Companion Object for a cachable class.
 *
 * @author Dean B
 */
open class Cacher<V> {
    private val lock = ReentrantReadWriteLock()
    private val cached: MutableMap<String, V> = mutableMapOf()

    operator fun get(index: String): V {
        return lock.read {
            cached[index.toLowerCase()] ?: throw RuntimeException("Index not found!")
        }
    }

    internal fun put(index: String, value: V) = lock.write { cached.put(index.toLowerCase(), value) }

    internal fun remove(index: String) = lock.write { cached.remove(index.toLowerCase()) }

    internal val keys: Set<String>
        get() = lock.read { cached.keys }

    internal val values: Collection<V>
        get() = lock.read { cached.values }
}
