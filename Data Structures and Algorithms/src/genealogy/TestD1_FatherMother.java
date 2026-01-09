package genealogy;

import java.nio.file.Path;

/**
 * Tests για D.1 - Πατέρας / Μητέρα
 */
public class TestD1_FatherMother {
    
    public static void main(String[] args) {
        System.out.println("=== Tests για D.1 - Πατέρας / Μητέρα ===\n");
        
        try {
            // Φόρτωση δεδομένων
            PersonCsvLoader loader = new PersonCsvLoader();
            PersonStore store = loader.load(Path.of("persons.csv"));
            
            FamilyRelations relations = new FamilyRelations(store);
            
            System.out.println("\n=== Εκτέλεση Tests ===\n");
            
            int totalTests = 0;
            int passedTests = 0;
            
            // Test 1: isFather - Θετική περίπτωση
            totalTests++;
            System.out.println("Test 1: isFather - Ο Αυγουστίνος (id=1) είναι πατέρας του Ιωάννη (id=3)");
            boolean result1 = relations.isFather("1", "3");
            if (result1) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();
            
            // Test 2: isFather - Αρνητική περίπτωση
            totalTests++;
            System.out.println("Test 2: isFather - Ο Ιωάννης (id=3) ΔΕΝ είναι πατέρας του Αυγουστίνου (id=1)");
            boolean result2 = relations.isFather("3", "1");
            if (!result2) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();
            
            // Test 3: isMother - Θετική περίπτωση
            totalTests++;
            System.out.println("Test 3: isMother - Η Αναστασία (id=2) είναι μητέρα του Ιωάννη (id=3)");
            boolean result3 = relations.isMother("2", "3");
            if (result3) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
            }
            System.out.println();
            
            // Test 4: isMother - Αρνητική περίπτωση
            totalTests++;
            System.out.println("Test 4: isMother - Η Αναστασία (id=2) ΔΕΝ είναι μητέρα της Ελένης (id=4)");
            boolean result4 = relations.isMother("2", "4");
            if (!result4) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();
            
            // Test 5: Ανύπαρκτο id
            totalTests++;
            System.out.println("Test 5: isFather - Ανύπαρκτο id (999, 3)");
            boolean result5 = relations.isFather("999", "3");
            if (!result5) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για ανύπαρκτο id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();
            
            // Test 6: Null ids
            totalTests++;
            System.out.println("Test 6: isFather - Null id (null, 3)");
            boolean result6 = relations.isFather(null, "3");
            if (!result6) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false για null id");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();
            
            // Test 7: Λάθος φύλο
            totalTests++;
            System.out.println("Test 7: isFather - Γυναίκα ως πατέρας (2, 3) - Η Αναστασία είναι γυναίκα");
            boolean result7 = relations.isFather("2", "3");
            if (!result7) {
                System.out.println("  ✓ PASSED - Επιστράφηκε false (λάθος φύλο)");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν false");
            }
            System.out.println();
            
            // Test 8: Πολλαπλές γενιές
            totalTests++;
            System.out.println("Test 8: isFather - Ο Δημήτρης (id=8) είναι πατέρας της Σοφίας (id=10)");
            boolean result8 = relations.isFather("8", "10");
            if (result8) {
                System.out.println("  ✓ PASSED");
                passedTests++;
            } else {
                System.out.println("  ✗ FAILED - Αναμένονταν true");
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