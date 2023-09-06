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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg devices: Device)

    @Update
    fun updateAll(vararg devices: Device)

    @Query("SELECT * FROM devices WHERE pkDevice LIKE :pkDevice LIMIT 1")
    fun getDeviceByPkDevice(pkDevice: String): Device

    @Query("SELECT * FROM devices WHERE name LIKE :name LIMIT 1")
    fun getDeviceByName(name: String): Device

    @Delete
    fun delete(device: Device)
}