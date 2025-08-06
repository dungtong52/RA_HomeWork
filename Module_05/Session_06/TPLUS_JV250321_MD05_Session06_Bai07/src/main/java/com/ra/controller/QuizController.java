package com.ra.controller;

import com.ra.model.Question;
import com.ra.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {
    private final QuizService quizService;
    private static Question currentQuestion;
    private static int guessCount;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(value = {"/", "/quiz"})
    public String showQuiz(Model model) {
        currentQuestion = quizService.getRandomQuestion();
        guessCount = 3;

        model.addAttribute("currentQuestion", currentQuestion);
        model.addAttribute("guessCount", guessCount);
        model.addAttribute("message", "Hãy đoán xem đây là gì?");
        return "quiz";
    }

    @PostMapping("/guess")
    public String handleGuess(@RequestParam("userAnswer") String userAnswer, Model model) {
        if (quizService.checkAnswer(currentQuestion, userAnswer)) {
            model.addAttribute("message", "Chúc mừng! Bạn đã đoán đúng!");
            model.addAttribute("currentQuestion", currentQuestion);
            model.addAttribute("guessCount", guessCount);
            return "quiz";
        } else {
            guessCount--;
            if (guessCount <= 0) {
                model.addAttribute("message", "Hết lượt đoán. Đáp án là: " + currentQuestion.getAnswer());
            } else {
                model.addAttribute("message", "Sai rồi! Bạn còn " + guessCount + " lượt đoán.");
            }
            model.addAttribute("currentQuestion", currentQuestion);
            model.addAttribute("guessCount", guessCount);
            return "quiz";
        }
    }
}
