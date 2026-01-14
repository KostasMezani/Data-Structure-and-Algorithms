package genealogy;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Test Suite για όλα τα Unit Tests του Genealogy System
 *
 * Εκτελεί όλα τα tests μαζί με μία εντολή: mvn test
 *
 * Καλύπτει:
 * - CSV Parsing (Μέρος C.4) - 4 tests
 * - Father/Mother relationships (Μέρος D.1) - 8 tests
 * - Child/Sibling relationships (Μέρος D.2) - 12 tests
 * - Grandparent/Grandchild relationships (Μέρος D.3) - 13 tests
 * - First Cousin relationships (Μέρος D.4) - 11 tests
 *
 * Συνολικά: 48 tests
 */
@Suite
@SuiteDisplayName("Genealogy System - Complete Test Suite")
@SelectClasses({
        PersonsCsvLoaderTest.class,         // 4 tests - CSV Parsing
        TestD1_FatherMother.class,          // 8 tests - D.1
        TestD2_ChildSibling.class,          // 12 tests - D.2
        TestD3_GrandparentGrandchild.class, // 13 tests - D.3
        TestD4_FirstCousin.class            // 11 tests - D.4
})
public class GenealogyTestSuite {
    // Αυτή η κλάση μένει άδεια
    // Χρησιμοποιείται μόνο για τα annotations παραπάνω
}
