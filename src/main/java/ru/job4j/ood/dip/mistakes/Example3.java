package ru.job4j.ood.dip.mistakes;

import java.util.ArrayList;
import java.util.List;

public class Example3 {
    public class StudentList {
        List<Student> studList;

        public StudentList(ArrayList<Student> list) {
            this.studList = list;
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
