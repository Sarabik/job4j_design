package ru.job4j.serialization.json.family;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Family family = new Family("Ivanovi",
                new String[]{"Ivan", "Olga", "Lisa"},
                new Address("Moscow", "Markova", 52),
                true);
        /*serialization*/
        final Gson familyGson = new GsonBuilder().create();
        String familyGsonString = familyGson.toJson(family);
        System.out.println(familyGsonString);

        /*deserialization*/
        Family family1 = familyGson.fromJson(familyGsonString, Family.class);
        System.out.println(family.equals(family1));
    }
}
