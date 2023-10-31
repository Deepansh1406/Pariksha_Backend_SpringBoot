package com.exam.service.impl;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.repository.QuizRepository;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class QuizServiceImpl  implements QuizService {
    @Autowired
    private QuizRepository quizRepository;



    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {


        this.quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);

    }

    //    Get Active Quiz
    @Override
    public List<Quiz> getActiveQuizes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizesOfCategory(Category c) {
        return this.quizRepository.findByCategoryAndActive(c,true);
    }






}
