package genealogy;

import java.nio.file.Path;

/**
 * Tests για D.3 - Παππούς/Γιαγιά και Εγγόνια
 */
public class TestD3_GrandparentGrandchild {

    public static void main(String[] args) {
        System.out.println("=== Tests για D.3 - Παππούς/Γιαγιά και Εγγόνια ===\n");

        try {
            // Φόρτωση δεδομένων
            PersonCsvLoader loader = new PersonCsvLoader();
            PersonStore store = loader.load(Path.of("persons.csv"));

            FamilyRelations relations = new FamilyRelations(store);

            System.out.println("\n=== Εκτέλεση Tests ===\n");

            int totalTests = 0;
            int passedTests = 0;

            // ============================================================
            // Tests για isGrandparent()
            // ============================================================

            // Test 1: isGrandparent - Θετική περίπτωση (μέσω πατέρα)
            totalTests++;
            System.out.println("Test 1: isGrandparent - Ο Αυγουστίνος (id=1) είναι παππούς του Νικόλαου (id=15)");
            System.out.println("  (Αυγουστίνος → Ιωάννης → Νικόλαος)");
            boolean result1 = relations.isGrandparent("1", "15");
            if (result1) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 2: isGrandparent - Θετική περίπτωση (μέσω μητέρας)
            totalTests++;
            System.out.println("Test 2: isGrandparent - Η Αναστασία (id=2) είναι γιαγιά του Νικόλαου (id=15)");
            System.out.println("  (Αναστασία → Ιωάννης → Νικόλαος)");
            boolean result2 = relations.isGrandparent("2", "15");
            if (result2) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 3: isGrandparent - Θετική περίπτωση (άλλη οικογένεια)
            totalTests++;
            System.out.println("Test 3: isGrandparent - Ο Αυγουστίνος (id=1) είναι παππούς της Αικατερίνης (id=16)");
            System.out.println("  (Αυγουστίνος → Ιωάννης → Αικατερίνη)");
            boolean result3 = relations.isGrandparent("1", "16");
            if (result3) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 4: isGrandparent - Αρνητική περίπτωση (άμεσος γονέας)
            totalTests++;
            System.out.println("Test 4: isGrandparent - Ο Αυγουστίνος (id=1) ΔΕΝ είναι παππούς του Ιωάννη (id=3)");
            System.out.println("  (Είναι πατέρας, όχι παππούς)");
            boolean result4 = relations.isGrandparent("1", "3");
            if (!result4) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 5: isGrandparent - Αρνητική περίπτωση (άσχετα άτομα)
            totalTests++;
            System.out.println("Test 5: isGrandparent - Ο Αυγουστίνος (id=1) ΔΕΝ είναι παππούς της Σοφίας (id=10)");
            System.out.println("  (Διαφορετικές οικογένειες)");
            boolean result5 = relations.isGrandparent("1", "10");
            if (!result5) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 6: isGrandparent - Αρνητική περίπτωση (αντίστροφη σχέση)
            totalTests++;
            System.out.println("Test 6: isGrandparent - Ο Νικόλαος (id=15) ΔΕΝ είναι παππούς του Αυγουστίνου (id=1)");
            boolean result6 = relations.isGrandparent("15", "1");
            if (!result6) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 7: isGrandparent - Null id
            totalTests++;
            System.out.println("Test 7: isGrandparent - Null id (null, 15)");
            boolean result7 = relations.isGrandparent(null, "15");
            if (!result7) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για null id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // ============================================================
            // Tests για isGrandchild()
            // ============================================================

            // Test 8: isGrandchild - Θετική περίπτωση
            totalTests++;
            System.out.println("Test 8: isGrandchild - Ο Νικόλαος (id=15) είναι εγγόνι του Αυγουστίνου (id=1)");
            boolean result8 = relations.isGrandchild("15", "1");
            if (result8) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 9: isGrandchild - Θετική περίπτωση (μέσω γιαγιάς)
            totalTests++;
            System.out.println("Test 9: isGrandchild - Η Αικατερίνη (id=16) είναι εγγόνι της Αναστασίας (id=2)");
            boolean result9 = relations.isGrandchild("16", "2");
            if (result9) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();

            // Test 10: isGrandchild - Αρνητική περίπτωση (άμεσο παιδί)
            totalTests++;
            System.out.println("Test 10: isGrandchild - Ο Ιωάννης (id=3) ΔΕΝ είναι εγγόνι του Αυγουστίνου (id=1)");
            System.out.println("  (Είναι παιδί, όχι εγγόνι)");
            boolean result10 = relations.isGrandchild("3", "1");
            if (!result10) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 11: isGrandchild - Αρνητική περίπτωση (άσχετα άτομα)
            totalTests++;
            System.out.println("Test 11: isGrandchild - Η Σοφία (id=10) ΔΕΝ είναι εγγόνι του Αυγουστίνου (id=1)");
            boolean result11 = relations.isGrandchild("10", "1");
            if (!result11) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 12: isGrandchild - Null id
            totalTests++;
            System.out.println("Test 12: isGrandchild - Null id (15, null)");
            boolean result12 = relations.isGrandchild("15", null);
            if (!result12) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για null id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();

            // Test 13: Συμμετρία - isGrandparent ↔ isGrandchild
            totalTests++;
            System.out.println("Test 13: Συμμετρία - isGrandparent(1,15) == isGrandchild(15,1)");
            boolean gp = relations.isGrandparent("1", "15");
            boolean gc = relations.isGrandchild("15", "1");
            if (gp == gc && gp == true) {
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