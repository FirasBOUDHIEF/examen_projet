package com.example.examen_projet;

public class Patient {

    private String name;
    private String age;


    public Patient() {
        // Default constructor required for Firebase
    }

    public Patient(String name, String age, String gender) {
        this.name = name;
        this.age = age;
    }

    // Add getters and setters if needed
}
