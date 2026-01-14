package genealogy;

import java.nio.file.Path;

/**
 * Tests για D.2 - Παιδί / Αδέλφια
 */
public class TestD2_ChildSibling {

    public static void main(String[] args) {
        System.out.println("=== Tests για D.2 - Παιδί / Αδέλφια ===\n");

        try {
            // Φόρτωση δεδομένων
            PersonCsvLoader loader = new PersonCsvLoader();
            PersonStore store = loader.load(Path.of("persons.csv"));

            FamilyRelations relations = new FamilyRelations(store);

            System.out.println("\n=== Εκτέλεση Tests ===\n");

            int totalTests = 0;
            int passedTests = 0;

            // ============================================================
            // Tests για isChild()
            // ============================================================

            // Test 1: isChild - Θετική περίπτωση (πατέρας)
            totalTests++;
            System.out.println("Test 1: isChild - Ο Ιωάννης (id=3) είναι παιδί του Αυγουστίνου (id=1)");
            boolean result1 = relations.isChild("3", "1");
            if (result1) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 2: isChild - Θετική περίπτωση (μητέρα)
            totalTests++;
            System.out.println("Test 2: isChild - Ο Ιωάννης (id=3) είναι παιδί της Αναστασίας (id=2)");
            boolean result2 = relations.isChild("3", "2");
            if (result2) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 3: isChild - Αρνητική περίπτωση (αντίστροφη σχέση)
            totalTests++;
            System.out.println("Test 3: isChild - Ο Αυγουστίνος (id=1) ΔΕΝ είναι παιδί του Ιωάννη (id=3)");
            boolean result3 = relations.isChild("1", "3");
            if (!result3) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 4: isChild - Αρνητική περίπτωση (άσχετα άτομα)
            totalTests++;
            System.out.println("Test 4: isChild - Η Ελένη (id=4) ΔΕΝ είναι παιδί του Δημήτρη (id=8)");
            boolean result4 = relations.isChild("4", "8");
            if (!result4) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 5: isChild - Null id
            totalTests++;
            System.out.println("Test 5: isChild - Null id (null, 1)");
            boolean result5 = relations.isChild(null, "1");
            if (!result5) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για null id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // ============================================================
            // Tests για isSibling()
            // ============================================================

            // Test 6: isSibling - Θετική περίπτωση (πλήρη αδέλφια)
            totalTests++;
            System.out.println("Test 6: isSibling - Ο Ιωάννης (id=3) και η Ελένη (id=4) είναι αδέλφια");
            boolean result6 = relations.isSibling("3", "4");
            if (result6) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 7: isSibling - Θετική περίπτωση (αντίστροφη σειρά)
            totalTests++;
            System.out.println("Test 7: isSibling - Η Ελένη (id=4) και ο Ιωάννης (id=3) είναι αδέλφια");
            boolean result7 = relations.isSibling("4", "3");
            if (result7) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 8: isSibling - Αρνητική περίπτωση (ίδιο άτομο)
            totalTests++;
            System.out.println("Test 8: isSibling - Ο Ιωάννης (id=3) ΔΕΝ είναι αδελφός του εαυτού του");
            boolean result8 = relations.isSibling("3", "3");
            if (!result8) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 9: isSibling - Αρνητική περίπτωση (γονέας-παιδί)
            totalTests++;
            System.out.println("Test 9: isSibling - Ο Αυγουστίνος (id=1) και ο Ιωάννης (id=3) ΔΕΝ είναι αδέλφια");
            boolean result9 = relations.isSibling("1", "3");
            if (!result9) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 10: isSibling - Αρνητική περίπτωση (διαφορετικές οικογένειες)
            totalTests++;
            System.out.println("Test 10: isSibling - Ο Ιωάννης (id=3) και η Σοφία (id=10) ΔΕΝ είναι αδέλφια");
            boolean result10 = relations.isSibling("3", "10");
            if (!result10) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 11: isSibling - Null id
            totalTests++;
            System.out.println("Test 11: isSibling - Null id (null, 3)");
            boolean result11 = relations.isSibling(null, "3");
            if (!result11) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για null id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 12: isSibling - Ανύπαρκτο id
            totalTests++;
            System.out.println("Test 12: isSibling - Ανύπαρκτο id (999, 3)");
            boolean result12 = relations.isSibling("999", "3");
            if (!result12) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για ανύπαρκτο id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
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