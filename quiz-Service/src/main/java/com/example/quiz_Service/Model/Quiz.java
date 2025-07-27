package com.example.quiz_Service.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ElementCollection //used when we have numbers or a specific type which is single or only one
    private List<Integer> questionIds;

}
