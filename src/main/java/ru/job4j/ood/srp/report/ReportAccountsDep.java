package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccountsDep implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Currency currSource;
    private final Currency currTarget;

    public ReportAccountsDep(Store store, DateTimeParser<Calendar> dateTimeParser, Currency currSource, Currency currTarget) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currSource = currSource;
        this.currTarget = currTarget;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(currencyConverter.convert(currSource, employee.getSalary(), currTarget))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
