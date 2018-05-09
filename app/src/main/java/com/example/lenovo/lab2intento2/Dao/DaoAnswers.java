package com.example.lenovo.lab2intento2.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.lenovo.lab2intento2.Modelos.Answers;

import java.util.List;

/**
 * Created by lenovo on 24-04-2018.
 */
@Dao
public interface DaoAnswers {
        @Insert
        void insertOnlySingleAnswer (Answers answer);

        @Insert
        void insertMultipleAnswers (List<Answers> answersList);

        @Query("SELECT * FROM Answers WHERE answerId = :answerId")
        Answers fetchOneFormbyAnswerId (int answerId);

        @Query("SELECT * FROM Answers")
        List<Answers> fetchAllAnswers();

        @Update
        void updateAnswer (Answers answer);

        @Delete
        void deleteAnswer (Answers answer);
}
