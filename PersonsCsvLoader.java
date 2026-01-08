package genealogy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PersonCsvLoaderTest {

    private PersonStore store;
    private PersonCsvLoader loader;

    /** Τρέχει πριν από κάθε Test - Φορτώνει το αρχείο CSV */
    @BeforeEach
    public void setup() throws IOException {
        System.out.println("\n[SETUP] Φόρτωση persons.csv..");
        loader = new PersonCsvLoader();
        store = loader.load(Path.of("persons.csv"));
        System.out.println("[SETUP] Φορτώθηκαν " + store.size() + "εγγραφές\n");
    }

    
}