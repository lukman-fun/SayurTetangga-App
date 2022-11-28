package com.sayur.tetangga.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cart.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String TB_CART = "tb_cart";

    public abstract DAO dao();
}
