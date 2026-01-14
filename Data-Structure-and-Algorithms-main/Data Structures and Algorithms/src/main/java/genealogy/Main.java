package genealogy;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1) Load dataset (κοινό για ολα τα modes)
        PersonCsvLoader loader = new PersonCsvLoader();
        PersonStore store = loader.load(Path.of("persons.csv"));

        // 2) Αν δεν δόθηκαν args -> κρατάμε το behavior του Μέρους C (C.3)
        if (args.length == 0) {
            for (Person p : store.allPersons()){
                System.out.println(p.getName() + " - " + p.getGender());
            }
            System.out.println("Loaded persons: " + store.size());
            return;
        }

        // 3) CLI mode
        String command = args[0].trim().toLowerCase();

        if (command.equals("relation")) {
            // relation "Όνομα1" "Όνομα2"
            if (args.length < 3){
                System.out.println("Χρήση: relation \"Όνομα1\" \"Όνομα2\" ");
                return;
            }

            String nameA = args[1];
            String nameB = args[2];

            FamilyRelations fr = new FamilyRelations(store);

            // Προϋπόθεση: έχεις ήδη προσθέσει τη relation(nameA, nameB) στο FamilyRelations
            String result = fr.relations(nameA, nameB);
            System.out.println(result);
            return;
        }

        // 4) Άγνωστη εντολή
        System.out.println("Άγνωστη εντολή: " + args[0]);
        System.out.println("Διαθέσιμες εντολές: ");
        System.out.println(" relation \"Όνομα1\" \"Όνομα2\"");
    }
}