package com.javaproject.prova.controller;
import com.javaproject.prova.model.Feedback;
import com.javaproject.prova.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/get")
    public List<Feedback> getFeedback() {
        return feedbackService.findAll();
    }

    @PostMapping("/post/{origem}")
    public String postFeedback(@RequestBody Feedback feedback, @PathVariable int origem) {
        return feedbackService.postFeedback(feedback, origem);
    }

    @PutMapping("/edit/{id}")
    public Feedback editFeedback(@RequestBody Feedback feedback, @PathVariable int id) {
        return feedbackService.editFeedback(feedback, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable int id) {
        return feedbackService.deleteFeedback(id);
    }

    @PutMapping("/prioridade/{id}")
    public Feedback updatePrioridade(@PathVariable int id) {
        return feedbackService.updatePrioridade(id);
    }

    @GetMapping("/get/byorigem/{origem}")
    public List<Feedback> getFeedbackByOrigem(@PathVariable int origem) {
        return feedbackService.getFeedbackByOrigem(origem);
    }
}