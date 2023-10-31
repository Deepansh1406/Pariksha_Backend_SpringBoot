package com.exam.service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service

public interface QuestionService  {

    public  Question addQuestion(Question question);
    public  Question updateQuestion(Question question);


    public Set<Question> getQuestions();

    public  Question getQuestions(Long questionId);

    public  void deleteQuestion(Long quesId);


    public  Set<Question> getQuestionsOfQuiz(Quiz quiz);

    public Question get(Long questionId);





}
