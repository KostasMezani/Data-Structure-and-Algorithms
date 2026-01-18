package main.java.genealogy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PersonsCsvLoaderTest {

    private PersonStore store;
    private PersonCsvLoader loader;

    /**
     * Τρέχει πριν από κάθε Test - Φορτώνει το αρχείο CSV
     */
    @BeforeEach
    public void setup() throws IOException {
        System.out.println("\n[SETUP] Φόρτωση persons.csv..");
        loader = new PersonCsvLoader();
        store = loader.load(Path.of("persons.csv"));
        System.out.println("[SETUP] Φορτώθηκαν " + store.size() + "εγγραφές\n");
    }

    //** Test 1: Έλεγχος αριθμού εγγράφων */
    @Test
    @DisplayName("Test 1: Έλεγχος αριθμού εγγράφων")
    public void test1_TotalRecordsCount() {

        int expectedCount = 25;
        int actualCount = store.size();

        assertEquals(expectedCount, actualCount,
                String.format("Ο αριθμός των εγγράφων πρέπει να είναι %d και είναι %d",
                        expectedCount, actualCount));

        if (expectedCount == actualCount) {
            System.out.println("Test 1 passed!: Ο αριθμός των εγγράφων είναι ίσος με τις γραμμές του CSV, δηλαδή " + expectedCount);
        } else {
            System.out.println("Test 1 failed: Ο αριθμός των εγγράφων δεν είναι όσες οι γραμμές του CSV");
        }
        ;
    }

    //** Test 2: Έλεγχος αντιστοίχισης id -> name*/

    @Test
    @DisplayName("Test 2: Έλεγχος αντιστοίχισης id -> name")
    public void test2_IdToNameMapping() {
        // id = 1
        Person p1 = store.getById("1");
        assertNotNull(p1, "Το άτομο με id=1 πρέπει να υπάρχει");
        assertEquals("Αυγουστίνος Καποδίστριας", p1.getName(),
                "Το άτομο με id=1 πρέπει να αντιστοιχεί Αυγουστίνος Καποδίστριας");

        // id = 3
        Person p3 = store.getById("3");
        assertNotNull(p3, "Το άτομο με id=3 πρέπει να υπάρχει");
        assertEquals("Ιωάννης Καποδίστριας", p3.getName(),
                "Το άτομο με id=3 πρέπει να αντιστοιχεί Ιωάννης Καποδίστριας");

        // id = 4 
        Person p4 = store.getById("4");
        assertNotNull(p4, "Το άτομο με id=4 πρέπει να υπάρχει");
        assertEquals("Ελένη Καποδίστρια", p4.getName(),
                "Το άτομο με id=4 πρέπει να αντιστοιχεί Ελένη Καποδίστρια");

        // id = 21
        Person p21 = store.getById("21");
        assertNotNull(p21, "Το άτομο με id=21 πρέπει να υπάρχει");
        assertEquals("Μάριος Καποδίστριας", p21.getName(),
                "Το άτομο με id=21 πρέπει να αντιστοιχεί Μάριος Καποδίστριας");

        System.out.println("Test 2 passed! : Όλες οι αντιστοιχίες id -> name είναι σωστές.");
    }

    //** Test 3 : Έλεγχος null τιμών σε κενά πεδία */

    @Test
    @DisplayName("Test 3: Έλεγχος null τιμών σε κενά πεδία")
    public void test3_NullFieldsForEmptyValues() {
        // id = 1
        Person p1 = store.getById("1");
        assertNotNull(p1, "Το άτομο με id=1 πρέπει να υπάρχει");
        assertNull(p1.getFatherId(),
                "Ο πατέρας του id=1 (Αυγουστινός) πρέπει να είναι null");
        assertNull(p1.getMotherId(),
                "Η μητέρα του id=1 (Αυγουστινός) πρέπει να είναι null");
        assertNotNull(p1.getSpouseId(),
                "Ο σύζυγος του id=1 (Αυγουστινός) δεν πρέπει να είναι null");
        System.out.println("id = 1 (Αυγουστινός): Κενά πεδία father_id και mother_id, είναι null.");


        //id = 10
        Person p10 = store.getById("10");
        assertNotNull(p10, "Το άτομο με id=10 πρέπει να υπάρχει");
        assertNotNull(p10.getFatherId(),
                "Ο πατέρας του id=10 (Σοφία) δεν πρέπει να είναι null");
        assertNotNull(p10.getMotherId(),
                "Η μητέρα του id=10 (Σοφία) δεν πρέπει να είναι null");
        assertNull(p10.getSpouseId(),
                "Ο σύζυγος του id=10 (Σοφία) πρέπει να είναι null");
        System.out.println("id = 10 (Σοφία): Κενό πεδίο spouse_id, είναι null. Οι γονείς, ωστόσο, έχουν τιμές.");


        System.out.println("Test 3 passed! : Όλα τα κενά πεδία αποθηκεύονται σωστά σαν null.");
    }

    /* Test 4: Έλεγχος αναζήτησης με όνομα */

    @Test
    @DisplayName("Test 4: Έλεγχος αναζήτησης με όνομα")
    public void test4_SearchByName() {

        Person p = store.getPersonByName("Ιωάννης Καποδίστριας");

        assertNotNull(p,
                "Το άτομο με name = Ιωάννης Καποδίστριας πρέπει να υπάρχει");
        assertEquals("3", p.getId(),
                "Ο Ιωάννης πρέπει να έχει id = 3");
        assertEquals(Gender.MALE, p.getGender(),
                "Ο Ιωάννης πρέπει να είναι Άντρας.");

        assertEquals("1", p.getFatherId(),
                "Ο πατέρας του Ιωάννη πρέπει να είναι ο Αυγουστινός.");
        assertEquals("2", p.getMotherId(),
                "Η μητέρα του Ιωάννη πρέπει να είναι η Αναστασία.");

        System.out.println("Test 4 passed! : Η αναζήτηση με όνομα λειτουργεί σωστά.");
    }
}