package com.javaproject.prova.model;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int avaliacao;
    private String comentario;
    private LocalDate date;
    private Origem origem;
    private Prioritario prioritario;

    public Feedback() {
        this.prioritario = Prioritario.NAO;
    }

    public Prioritario getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(Prioritario prioritario) {
        this.prioritario = prioritario;
    }

    @PrePersist
    public void onCreate() {
        this.date = LocalDate.now();
    }


    public int getId() {
        return id;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public LocalDate getDate() {
        return date;
    }

}