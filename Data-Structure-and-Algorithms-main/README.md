# CN5005 Data Structures and Algorithms - Coursework 2025-2026

Υλοποίηση BST/AVL δέντρων και σύστημα ανάλυσης γενεαλογικών σχέσεων.

---

##  Ομάδα

- **Philippos Lefteriotis** (ΑΜ: 2873432)  -- ( A & B Part & F.2(Screencast) & F.3(Παρουσίαση))
- **Kostas Mezani** (ΑΜ: 2873433)  -- ( C & D & E & F.1 )
- **Vasilis Pasiotis** (ΑΜ: 2873435) -- ( C & D & E & F.1 )

---

##  Περιεχόμενα

Το project περιλαμβάνει:

- **BST & AVL Trees** - Δυαδικά δέντρα αναζήτησης με duplicates
- **CSV Parser** - Φόρτωση δεδομένων από `persons.csv`
- **Family Relations** - Ανίχνευση συγγενικών σχέσεων
- **Unit Tests** - Αυτόματα tests με JUnit

---

##  Εκτέλεση

### Προαπαιτούμενα

- Java 17+
- Maven 3.6+

### Build του project

```bash
mvn clean compile
```

### Εκτέλεση DSA Trees Demo (Μέρη Α & Β)

```bash
# Από Maven
mvn exec:java -Dexec.mainClass="dsatrees.Main"

# Ή από compiled classes
java -cp target/classes dsatrees.Main
```

### Εκτέλεση Genealogy System (Μέρη C, D & Ε)

```bash
# Από Maven
mvn exec:java -Dexec.mainClass="genealogy.Main"

# Ή από compiled classes
java -cp target/classes genealogy.Main
```

**Σημείωση:** Για να δοκιμάσετε διαφορετικά ονόματα, επεξεργαστείτε το `genealogy.Main.java` και αλλάξτε τις τιμές στη μέθοδο `main()`.

---

##  Testing

### Unit Tests με Maven

```bash
mvn test
```

Τρέχει όλα τα JUnit tests για το CSV parsing και validation.

---

##  Δομή Project

```
Data-Structure-and-Algorithms-main/
├── src/
│   ├── main/java/
│   │   ├── dsatrees/                    # Μέρη Α & Β
│   │   │   ├── Node.java
│   │   │   ├── BinarySearchTree.java
│   │   │   ├── AVLTree.java
│   │   │   └── Main.java
│   │   │
│   │   └── genealogy/                   # Μέρη C, D & Ε
│   │       ├── Person.java
│   │       ├── Gender.java
│   │       ├── PersonCsvLoader.java
│   │       ├── PersonStore.java
│   │       ├── FamilyRelations.java
│   │       └── Main.java
│   │
│   └── test/java/genealogy/
│       └── PersonsCsvLoaderTest.java
│
├── persons.csv
├── pom.xml
└── README.md
```

---

##  Dataset

Το `persons.csv` περιέχει 25 άτομα.

**Format:**
```csv
id,name,gender,father_id,mother_id,spouse_id
1,Αυγουστίνος Καποδίστριας,Male,,,2
2,Αναστασία Μαυροκορδάτου,Female,,,1
```

---

##  Σημειώσεις

- Όλος ο κώδικας είναι σε **Java 17**
- Χρησιμοποιεί **Maven** για build/dependency management
- Το CSV είναι **UTF-8 encoded**
- Τα tests τρέχουν με **JUnit 5**
- AM keys για demo: {10, 4, 18, 43, 32, 10, 25, 4, 50, 18, 32, 7}


