package com.example.sebastiangebert.worktime.infrastructure.test

import com.example.sebastiangebert.worktime.infrastructure.Database

class FlexTimeStorageTest {
    companion object MockDataBase : Database {
        override fun select(): Iterable<String> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun insert(values: Iterable<String>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}