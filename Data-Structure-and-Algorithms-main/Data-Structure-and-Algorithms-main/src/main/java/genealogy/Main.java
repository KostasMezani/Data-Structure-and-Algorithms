package genealogy;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1) Load dataset
        PersonCsvLoader loader = new PersonCsvLoader();
        Path csvPath = resolvePersonCsvPath();
        PersonStore store = loader.load(csvPath);

        // 2) Αν δεν δόθηκαν args -> κρατάμε το behavior του Μέρους C (C.3)
        if (args.length == 0){
            for (Person p : store.allPersons()){
                System.out.println(p.getName()+ "-" + p.getGender());
            }
            System.out.println("Loaded persons: " + store.size());
            System.out.println("CSV path used: " + csvPath.toAbsolutePath());
            return;
        }

        // 3) CLI mode (ΔΕΝ ΤΟ ΠΕΙΡΑΖΟΥΜΕ)
        String command = args[0].trim().toLowerCase();

        if (command.equals("relation")) {
            // relation "Όνομα1" "Όνομα2"
            if (args.length < 3) {
                System.out.println("Χρήση: relation \"Όνομα1\" \"Όνομα2\"");
                return;
            }

            String nameA = args[1];
            String nameB = args[2];

            FamilyRelations fr = new FamilyRelations(store);
            System.out.println(fr.relations(nameA, nameB));
            return;
        }

        // 4) Interactive mode με Scanner
        if (command.equals("interactive")) {
            runInteractive(store);
            return;
        }

        // 5) Άγνωστη Εντολη
        System.out.println("Άγνωστη εντολή: " + args[0]);
        System.out.println("Διαθέσιμες εντολές:");
        System.out.println(" relation \"Όνομα1\" \"Όνομα2\"");
        System.out.println(" interactive");
    }

    private static void runInteractive(PersonStore store){
        FamilyRelations fr = new FamilyRelations(store);

        System.out.println("=== Genealogy Interactive Mode ===");
        System.out.println("Commands:");
        System.out.println(" relation (Θα σου ζητήσει 2 ονόματα)");
        System.out.println(" list     (Δείχνει όλα τα ονόματα)");
        System.out.println(" help");
        System.out.println(" exit");
        System.out.println("----------------------------");

        try(Scanner sc = new Scanner(System.in)){
            while (true){
                System.out.println("> ");
                String cmd = sc.nextLine().trim().toLowerCase();

                if (cmd.isEmpty()) continue;

                switch (cmd){
                    case "exit":
                        System.out.println("Bye!");
                        return;

                    case "help":
                        System.out.println("Commands: relations, list, help, exit");
                        System.out.println("Tip: μέσα στα prompts μπορείς να γράψεις list για διαθέσιμα ονόματα");
                        break;

                    case "list":
                        store.allPersons().forEach(p -> System.out.println("- " + p.getName()));
                        break;

                    case "relation": {
                        String nameA = askValidName(sc, store, "Δώσε το 1ο όνομα: ");
                        String nameB = askValidName(sc, store, "Δώσε το 2ο όνομα: ");
                        System.out.println(fr.relations(nameA, nameB));
                        break;
                    }
                    default:
                        System.out.println("Άγνωστο command. Γράψε 'help'.");
                }
            }
        }
    }

    private static String askValidName(Scanner sc, PersonStore store, String prompt){
        while (true){
            System.out.println(prompt);
            String name = sc.nextLine().trim();

            if (name.equalsIgnoreCase("list")){
                store.allPersons().forEach(p -> System.out.println("- " + p.getName()));
                continue;
            }

            if (store.getIdByName(name) != null){
                return name;
            }

            System.out.println("Δεν βρέθηκε αυτό το όνομα στο dataset. Γράψε 'list' για διαθέσιμα ονόματα");

        }
    }

    /**
     * Προσπαθούμε να βρούμε το persons.csv χωρίς να πειράξουμε τα υπόλοιπα αρχεία.
     * 1) ./persons.csv (ιδιο working dir)
     * 2) ./Data Structures and Algorithms/persons.csv
     * Αν δεν βρεθεί -> πετάει error με cwd.
     */

    pricate static Path resolvePersonCsvPath(){
        Path p1 = Path.of("persons.csv");
        if (Files.exists(p1)) return p1;

        Path p2 = Path.of("Data Structures and Algorithms","persons.csv");
        if (Files.exists(p2)) return p2;

        String cwd = Path.of("").toAbdolutePath().toString();
        throw new IllegalStateExeption(
                "Δεν βρέθηκε persons.csv.\n" +
                "Tried:\n" +
                " - " + p1.toAbsolutePath() + "\n" +
                " - " + p2.toAbsolutePath() + "\n" +
                "Working dir: " + cwd + "\n" +
                "Βάλε το persons.csv στο working dir ή στον φάκελο 'Data Structures and Algorithms/'.";
        );
    }
}