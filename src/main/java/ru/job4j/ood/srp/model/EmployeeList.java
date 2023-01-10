package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)

public class EmployeeList {

    List<Employee> list;

    public EmployeeList() {
    }

    public EmployeeList(List<Employee> list) {
        this.list = list;
    }
}
