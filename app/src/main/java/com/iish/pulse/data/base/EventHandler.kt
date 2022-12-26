package com.iish.pulse.data.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}