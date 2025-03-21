package com.example.demo.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "testcases")
public class TestCase {
    
    @Id
    private String id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Date createdAt;
    private Date updatedAt;
}
