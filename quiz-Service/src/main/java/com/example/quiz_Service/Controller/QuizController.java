package com.example.quiz_Service.Controller;

import com.example.quiz_Service.Model.QuestionWrapper;

import com.example.quiz_Service.Model.QuizDTO;
import com.example.quiz_Service.Model.Response;
import com.example.quiz_Service.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){

        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumQ(),quizDTO.getTitle());

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
          return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
          return quizService.calculateResult(id,responses);
    }

}
