package com.example.lenovo.lab2intento2.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lenovo.lab2intento2.Modelos.Questions;

import java.util.List;

/**
 * Created by lenovo on 24-04-2018.
 */

@Dao
public interface DaoQuestions {

    @Insert
    void insertOnlySingleQuestion (Questions question);

    @Insert
    void insertMultipleQuestions (List<Questions> questionsList);

    @Query("SELECT * FROM Questions WHERE questionId = :questionId")
    Questions fetchOneFormbyQuestionId (int questionId);

    @Query("SELECT * FROM Questions")
    List<Questions> fetchAllQuestions();

    @Update
    void updateQuestion (Questions question);

    @Delete
    void deleteQuestion (Questions question);
}