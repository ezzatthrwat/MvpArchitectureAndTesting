package com.example.e_commericemvp.Activities.registration.pojo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface User_DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertNewUser(UserModel user);

    @Query("SELECT * FROM Users WHERE Username = :username AND Password = :password")
    UserModel getUserDataFromDatabase(String username , String password );
}
