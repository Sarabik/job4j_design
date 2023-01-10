package ru.job4j.ood.srp.report;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class JsonReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public JsonReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (Employee e : store.findBy(filter)) {
            jsonObject = new JSONObject();
            jsonObject.put("name", e.getName());
            jsonObject.put("hired", dateTimeParser.parse(e.getHired()));
            jsonObject.put("fired", dateTimeParser.parse(e.getFired()));
            jsonObject.put("salary", e.getSalary());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

}
