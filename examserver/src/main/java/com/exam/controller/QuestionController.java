package com.exam.controller;


import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


//    Add question

    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));

    }

//    Update Question

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));

    }

//    Get all Question of any quiz

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionOfQuiz=  this.questionService.getQuestionsOfQuiz(quiz);
//        return  ResponseEntity.ok(questionOfQuiz);


        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }

        list.forEach((q)->{
            q.setAnswers("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);


    }
//    Get all Question of any quiz

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Question> questionOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionOfQuiz);


//        return ResponseEntity.ok(list);


    }

//    Get aa Single question

    @GetMapping("/{quesid}")
    public Question get(@PathVariable("quesid") Long quesid) {
        return this.questionService.getQuestions(quesid);

    }

//    Delete single question

    @DeleteMapping("/{quesid}")
    public void delete(@PathVariable("quesid") Long quesid) {
        this.questionService.deleteQuestion(quesid);

    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double marksGot = 0;
        int correctAnswer = 0;
        int attempted = 0;

        for (Question q : questions) {
            // single question
            Question question = this.questionService.get(q.getQid());

            if (question != null && question.getAnswers() != null && q.getGivenAnswer() != null) {
                if (question.getAnswers().equals(q.getGivenAnswer())) {
                    // Correct answer
                    correctAnswer++;
                    double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                    marksGot += marksSingle;
                }
                attempted++;
            }
        }

        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswer", correctAnswer, "attempted", attempted);

        return ResponseEntity.ok(map);
    }
}