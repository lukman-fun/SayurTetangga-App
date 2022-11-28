package com.sayur.tetangga.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM " + AppDatabase.TB_CART)
    List<Cart> getCartAll();

    @Query("SELECT * FROM " + AppDatabase.TB_CART + " WHERE id_produk=:id_produk")
    Cart getCartById(String id_produk);

    @Insert
    long addCart(Cart cart);

    @Update
    int upCart(Cart cart);

    @Delete
    void delCart(Cart cart);

    @Query("DELETE FROM " + AppDatabase.TB_CART)
    void delAllCart();
}
