package com.example.quiz_Service.Service;


import com.example.quiz_Service.DAO.QuizDao;
import com.example.quiz_Service.Feign.QuizInterface;
import com.example.quiz_Service.Model.QuestionWrapper;
import com.example.quiz_Service.Model.Quiz;
import com.example.quiz_Service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
   QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

     List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
     Quiz quiz = new Quiz();
     quiz.setTitle(title);
     quiz.setQuestionIds(questions);
     quizDao.save(quiz); //saving the quiz

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }



    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
      Quiz quiz = quizDao.findById(id).get();
     List<Integer> questionIds = quiz.getQuestionIds();
     ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

     return questions;

    }


    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
       ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }


}
