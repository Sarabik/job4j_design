package ru.job4j.ood.dip.mistakes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Example2 {
    public class StudentList {
        List<Student> studList = new ArrayList<>();

        public ArrayList<Student> getStudentsStartsWith(String c) {
            ArrayList<Student> list = new ArrayList<>();
            studList.forEach(s -> {
                if (s.getName().startsWith(c)) {
                    list.add(s);
                }
            });
            return list;
        }
    }

    public class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
