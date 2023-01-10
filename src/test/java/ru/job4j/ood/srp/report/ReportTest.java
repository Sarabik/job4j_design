package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountsDepReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Currency currSource = Currency.RUB;
        Currency currTarget = Currency.EUR;
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new AccountsDepReport(store, parser, currSource, currTarget, currencyConverter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(currencyConverter.convert(currSource, worker.getSalary(), currTarget))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRDepReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 80);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRDepReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenProgrammerDepReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 80);
        store.add(worker1);
        store.add(worker2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ProgrammerDepReport(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(parser.parse(worker1.getHired())).append(";")
                .append(parser.parse(worker1.getFired())).append(";")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(parser.parse(worker2.getHired())).append(";")
                .append(parser.parse(worker2.getFired())).append(";")
                .append(worker2.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new JsonReport(store, parser);
        StringBuilder expect = new StringBuilder()
                .append(String.format("[{\"fired\":\"%s\",", parser.parse(worker1.getHired())))
                .append(String.format("\"name\":\"%s\",", worker1.getName()))
                .append(String.format("\"hired\":\"%s\",", parser.parse(worker1.getFired())))
                .append(String.format("\"salary\":%.0f}]", worker1.getSalary()));
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXmlReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = null;
        try {
            engine = new XmlReport(store);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(s)
                .append("<model>").append(s)
                .append(String.format("<list name=\"%s\">", worker.getName())).append(s)
                .append(String.format("<hired>%s</hired>", parser.parse(worker.getHired()))).append(s)
                .append(String.format("<fired>%s</fired>", parser.parse(worker.getFired()))).append(s)
                .append(String.format("<salary>%s</salary>", worker.getSalary())).append(s)
                .append("</list>").append(s)
                .append("</model>").append(s);
        assertThat(engine.generate(em -> true)).isEqualToIgnoringWhitespace(expect.toString());
    }
}