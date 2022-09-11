package ru.job4j.serialization.json.family;

import org.json.JSONObject;

public class ToJSONObject {
    public static void main(String[] args) {
        Family family = new Family("Ivanovi",
                new String[]{"Ivan", "Olga", "Lisa"},
                new Address("Moscow", "Markova", 52),
                true);

        /*serialization*/
        JSONObject jsonObj = new JSONObject(family);
        String json = jsonObj.toString();
        System.out.println(json);
    }
}
