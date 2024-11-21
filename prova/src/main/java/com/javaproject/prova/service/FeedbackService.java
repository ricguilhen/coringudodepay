package com.javaproject.prova.service;
import com.javaproject.prova.model.Feedback;
import com.javaproject.prova.model.Origem;
import com.javaproject.prova.model.Prioritario;
import com.javaproject.prova.repository.FeedbackRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    public String postFeedback(Feedback feedback, int origem) {
        switch (origem) {
            case 1 -> feedback.setOrigem(Origem.APP);
            case 2 -> feedback.setOrigem(Origem.SITE);
            case 3 -> feedback.setOrigem(Origem.EMAIL);
        }
        if (feedback.getAvaliacao() > 0 && feedback.getAvaliacao() < 6) {
            feedbackRepository.save(feedback);
            return "OK";
        } else {
            return "Avaliação é obrigatória e deve ser de 1 a 5";
        }
    }

    public Feedback editFeedback(Feedback feedback, int id) {
        Feedback newFeedback = feedbackRepository.findById(id).orElse(null);
        if (newFeedback != null) {
            newFeedback.setAvaliacao(feedback.getAvaliacao());
            if (feedback.getComentario() != null) {
                newFeedback.setComentario(feedback.getComentario());
            }
            return feedbackRepository.save(newFeedback);
        } else {
            return null;
        }
    }

    public String deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
        return "OK, Feedback deletado com sucesso!";
    }

    public Feedback updatePrioridade(int id) {
        Feedback newFeedback = feedbackRepository.findById(id).orElse(null);
        if (newFeedback != null) {
            if (newFeedback.getPrioritario() == Prioritario.NAO) {
                newFeedback.setPrioritario(Prioritario.SIM);
            } else {
                newFeedback.setPrioritario(Prioritario.NAO);
            }
            return feedbackRepository.save(newFeedback);
        } else {
            return null;
        }
    }

    public List<Feedback> getFeedbackByOrigem (int origem) {
        List <Feedback> feedbacks = feedbackRepository.findAll();
        List <Feedback> filteredFeedbacks = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            switch (origem) {
                case 1 -> {
                    if (feedback.getOrigem() == Origem.APP) {
                        filteredFeedbacks.add(feedback);
                    }
                }
                case 2 -> {
                    if (feedback.getOrigem() == Origem.SITE) {
                        filteredFeedbacks.add(feedback);
                    }
                }
                case 3 -> {
                    if (feedback.getOrigem() == Origem.EMAIL) {
                        filteredFeedbacks.add(feedback);
                    }
                }
            }
        }
        if (filteredFeedbacks.isEmpty()) {
            return null;
        } else {
            return filteredFeedbacks;
        }
    }
}
