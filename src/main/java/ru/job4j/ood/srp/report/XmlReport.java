package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeList;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XmlReport implements Report {

    private final Store store;

    private final JAXBContext context;

    private final Marshaller marshaller;

    public XmlReport(Store store) throws Exception {
        this.store = store;
        this.context = JAXBContext.newInstance(EmployeeList.class);
        this.marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        EmployeeList list = new EmployeeList(store.findBy(filter));
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(list, writer);
            xml = writer.getBuffer().toString();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
