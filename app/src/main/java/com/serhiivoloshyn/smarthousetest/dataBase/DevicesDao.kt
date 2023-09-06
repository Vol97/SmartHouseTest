package com.serhiivoloshyn.smarthousetest.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.serhiivoloshyn.smarthousetest.data.Device

@Dao
interface DevicesDao {
    @Query("SELECT * FROM devices")
    fun getAll(): List<Device>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg devices: Device)

    @Update
    fun updateAll(vararg devices: Device)

    @Delete
    fun delete(user: Device)
}