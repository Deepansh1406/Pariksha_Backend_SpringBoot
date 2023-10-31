package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public interface QuizService {

    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);


    public Set<Quiz> getQuizes();

    public  Quiz getQuiz(long quizId);

    public  void deleteQuiz(Long  quizId);


    public List<Quiz> getQuizesOfCategory(Category category);

    public  List<Quiz> getActiveQuizes();
    public  List<Quiz> getActiveQuizesOfCategory(Category c);

}
