package com.serhiivoloshyn.smarthousetest.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serhiivoloshyn.smarthousetest.data.Device

@Database(
    version = 1,
    entities = [Device::class]
)
abstract class DevicesDatabase: RoomDatabase() {

    abstract fun devicesDao(): DevicesDao
}