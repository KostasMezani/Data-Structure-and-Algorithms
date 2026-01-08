package genealogy;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonCsvLoader loader = new PersonCsvLoader();
        PersonStore store = loader.load(Path.of("persons.csv"));

        for (Person p : store.allPersons()){
            System.out.println(p.getName() + " - " + p.getGender());
        }

        System.out.println("Loaded persons: " + store.size());
    }
}