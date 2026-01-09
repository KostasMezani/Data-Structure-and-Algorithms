package genealogy;

import java.nio.file.Path;

/**
 * Tests για D.4 - Πρώτα Ξαδέρφια (First Cousins)
 */
public class TestD4_FirstCousin {

    public static void main(String[] args) {
        System.out.println("=== Tests για D.4 - Πρώτα Ξαδέρφια ===\n");

        try {
            // Φόρτωση δεδομένων
            PersonCsvLoader loader = new PersonCsvLoader();
            PersonStore store = loader.load(Path.of("persons.csv"));

            FamilyRelations relations = new FamilyRelations(store);

            System.out.println("\n=== Εκτέλεση Tests ===\n");

            int totalTests = 0;
            int passedTests = 0;

            // ============================================================
            // Tests για isFirstCousin()
            // ============================================================

            // Test 1: isFirstCousin - Θετική περίπτωση (πατέρες αδέλφια)
            totalTests++;
            System.out.println("Test 1: isFirstCousin - Ο Νικόλαος (id=15) και η Μαρία (id=17) είναι πρώτα ξαδέρφια");
            System.out.println("  (Οι πατέρες τους - Ιωάννης (3) και Ελένη (4) - είναι αδέλφια)");
            boolean result1 = relations.isFirstCousin("15", "17");
            if (result1) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 2: isFirstCousin - Θετική περίπτωση (αντίστροφη σειρά)
            totalTests++;
            System.out.println("Test 2: isFirstCousin - Η Μαρία (id=17) και ο Νικόλαος (id=15) είναι πρώτα ξαδέρφια");
            boolean result2 = relations.isFirstCousin("17", "15");
            if (result2) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 3: isFirstCousin - Θετική περίπτωση (περισσότερα ξαδέρφια)
            totalTests++;
            System.out.println("Test 3: isFirstCousin - Ο Νικόλαος (id=15) και η Αικατερίνη (id=16) είναι πρώτα ξαδέρφια με τη Μαρία (id=17)");
            boolean result3a = relations.isFirstCousin("15", "17");
            boolean result3b = relations.isFirstCousin("16", "17");
            if (result3a && result3b) {
                System.out.println("  ✓ PASSED - Και τα δύο ζευγάρια είναι ξαδέρφια");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true για και τα δύο");
            }
            System.out.println();

            // Test 4: isFirstCousin - Αρνητική περίπτωση (αδέλφια)
            totalTests++;
            System.out.println("Test 4: isFirstCousin - Ο Νικόλαος (id=15) και η Αικατερίνη (id=16) ΔΕΝ είναι ξαδέρφια");
            System.out.println("  (Είναι αδέλφια, όχι ξαδέρφια)");
            boolean result4 = relations.isFirstCousin("15", "16");
            if (!result4) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 5: isFirstCousin - Αρνητική περίπτωση (γονέας-παιδί)
            totalTests++;
            System.out.println("Test 5: isFirstCousin - Ο Ιωάννης (id=3) και ο Νικόλαος (id=15) ΔΕΝ είναι ξαδέρφια");
            System.out.println("  (Είναι πατέρας-γιος)");
            boolean result5 = relations.isFirstCousin("3", "15");
            if (!result5) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 6: isFirstCousin - Αρνητική περίπτωση (παππούς-εγγόνι)
            totalTests++;
            System.out.println("Test 6: isFirstCousin - Ο Αυγουστίνος (id=1) και ο Νικόλαος (id=15) ΔΕΝ είναι ξαδέρφια");
            System.out.println("  (Είναι παππούς-εγγόνι)");
            boolean result6 = relations.isFirstCousin("1", "15");
            if (!result6) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 7: isFirstCousin - Αρνητική περίπτωση (ίδιο άτομο)
            totalTests++;
            System.out.println("Test 7: isFirstCousin - Ο Νικόλαος (id=15) ΔΕΝ είναι ξάδερφος του εαυτού του");
            boolean result7 = relations.isFirstCousin("15", "15");
            if (!result7) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 8: isFirstCousin - Αρνητική περίπτωση (άσχετα άτομα)
            totalTests++;
            System.out.println("Test 8: isFirstCousin - Ο Νικόλαος (id=15) και η Σοφία (id=10) ΔΕΝ είναι ξαδέρφια");
            System.out.println("  (Διαφορετικές οικογένειες)");
            boolean result8 = relations.isFirstCousin("15", "10");
            if (!result8) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 9: isFirstCousin - Null id
            totalTests++;
            System.out.println("Test 9: isFirstCousin - Null id (null, 15)");
            boolean result9 = relations.isFirstCousin(null, "15");
            if (!result9) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για null id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 10: isFirstCousin - Ανύπαρκτο id
            totalTests++;
            System.out.println("Test 10: isFirstCousin - Ανύπαρκτο id (999, 15)");
            boolean result10 = relations.isFirstCousin("999", "15");
            if (!result10) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για ανύπαρκτο id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 11: Συμμετρία - isFirstCousin(A,B) == isFirstCousin(B,A)
            totalTests++;
            System.out.println("Test 11: Συμμετρία - isFirstCousin(15,17) == isFirstCousin(17,15)");
            boolean c1 = relations.isFirstCousin("15", "17");
            boolean c2 = relations.isFirstCousin("17", "15");
            if (c1 == c2 && c1 == true) {
                System.out.println("  ✓ PASSED - Και τα δύο επιστρέφουν true");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Δεν είναι συμμετρικά");
            }
            System.out.println();

            // Αποτελέσματα
            System.out.println("=== Αποτελέσματα ===");
            System.out.println("Συνολικά tests: " + totalTests);
            System.out.println("Επιτυχίες: " + passedTests + " ✓");
            System.out.println("Αποτυχίες: " + (totalTests - passedTests) + " ✗");

            if (passedTests == totalTests) {
                System.out.println("\n🎉 ΟΛΑ ΤΑ TESTS ΠΕΡΑΣΑΝ! 🎉");
            } else {
                System.out.println("\n⚠️  Κάποια tests απέτυχαν.");
            }

        } catch (Exception e) {
            System.err.println("ΣΦΑΛΜΑ: " + e.getMessage());
            e.printStackTrace();
        }
    }
}