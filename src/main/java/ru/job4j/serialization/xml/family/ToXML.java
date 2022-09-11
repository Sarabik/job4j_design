package ru.job4j.serialization.xml.family;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class ToXML {
    public static void main(String[] args) {
        Family family = new Family("Ivanovi",
                new String[]{"Ivan", "Olga", "Lisa"},
                new Address("Moscow", "Markova", 52),
                true);
        toXMLFile(family, "./data/file.xml");
    }

    public static void toXMLFile(Family family, String fileName) {
        String xlm = toXMLString(family);
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(fileName, StandardCharsets.UTF_8, false))) {
            pw.print(xlm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toXMLString(Family family) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>");
        joiner.add(String.format("<family surname=\"%s\" membersCount=\"%d\" hasPet=\"%s\">",
                family.getSurname(), family.getMembersCount(), family.isHasPet()));
        joiner.add("\t<names>");
        for (String name : family.getNames()) {
            joiner.add(String.format("\t\t<names>\"%s\"</names>", name));
        }
        joiner.add("\t</names>");
        joiner.add(String.format("\t<address city=\"%s\" street=\"%s\" house=\"%d\"/>",
                family.getAddress().getCity(),
                family.getAddress().getStreet(),
                family.getAddress().getHouse()));
        joiner.add("</family>");
        return joiner.toString();
    }
}
