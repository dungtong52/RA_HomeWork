package com.ra.service;

import com.ra.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {
    private final List<Question> questions;

    public QuizService() {
        questions = new ArrayList<>();

        questions.add(new Question(1, "https://example.com/image1.jpg", "apple"));
        questions.add(new Question(2, "https://example.com/image2.jpg", "banana"));
        questions.add(new Question(3, "https://example.com/image3.jpg", "cat"));
        questions.add(new Question(4, "https://example.com/image4.jpg", "dog"));
        questions.add(new Question(5, "https://example.com/image5.jpg", "car"));
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }

    public boolean checkAnswer(Question question, String userAnswer) {
        if (question == null || userAnswer == null) {
            return false;
        }
        return question.getAnswer().equalsIgnoreCase(userAnswer.trim());
    }
}
