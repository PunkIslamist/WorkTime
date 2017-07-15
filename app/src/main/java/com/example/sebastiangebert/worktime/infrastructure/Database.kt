package com.example.sebastiangebert.worktime.infrastructure

interface Database {
    fun select(): Iterable<String>
    fun insert(values: Iterable<String>)
}