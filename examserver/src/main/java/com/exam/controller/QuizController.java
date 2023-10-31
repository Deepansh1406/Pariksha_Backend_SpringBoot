package com.exam.controller;


import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

//    Add quiz service

    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));

    }

//    Update Quiz

    @PutMapping("/")
    public  ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));

    }

//    Get Quiz
    @GetMapping("/")
    public  ResponseEntity<?>quizes(){
        return  ResponseEntity.ok(this.quizService.getQuizes());

    }

//    Get a Single Quiz

    @GetMapping("/{qid}")
    public  Quiz quiz(@PathVariable("qid")Long qid){
        return  this.quizService.getQuiz(qid);

    }

//    Delete Quiz

    @DeleteMapping("{qid}")
    public  void delete(@PathVariable("qid")Long qid){
         this.quizService.deleteQuiz(qid);
    }



    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizesOfCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getQuizesOfCategory(category);

    }

//    get Active Quizes

    @GetMapping("/active")
    public  List<Quiz> getActiveQuizes(){
        return this.quizService.getActiveQuizes();
    }


    @GetMapping("/category/active/{cid}")
    public  List<Quiz> getActiveQuizes(@PathVariable("cid")Long cid){

        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizesOfCategory(category);
    }


}


