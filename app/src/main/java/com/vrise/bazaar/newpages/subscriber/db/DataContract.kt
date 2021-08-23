package com.vrise.bazaar.newpages.subscriber.db

import android.provider.BaseColumns

object DataContract {

    const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DataEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${DataEntry.COLUMN_AMOUNT} TEXT," +
                    "${DataEntry.COLUMN_DETAILS} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DataEntry.TABLE_NAME}"


    object DataEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_DETAILS = "details"
    }

}