package ru.job4j.ood.dip.mistakes;

import java.util.ArrayList;

public class Example1 {
    public class StudentList {
        ArrayList<Student> studList;

        public StudentList() {
            this.studList = new ArrayList<>();
        }

        /* методы */
    }

    public class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }
    }
}
