[README.md](https://github.com/user-attachments/files/24696667/README.md)
# CN5005 Data Structures and Algorithms - Coursework 2025-2026

Υλοποίηση BST/AVL δέντρων και σύστημα ανάλυσης γενεαλογικών σχέσεων.

---

## 👥 Ομάδα

- **Philippos Lefteriotis** (ΑΜ: 2873432)  -- ( A & B Part & F.2(Screencast) & F.3(Παρουσίαση))
- **Kostas Mezani** (ΑΜ: 2873433)  -- ( C & D & E & F.1 )
- **Vasilis Pasiotis** (ΑΜ: 2873435) -- ( C & D & E & F.1 )

---

## 📋 Περιεχόμενα

Το project περιλαμβάνει:

- **BST & AVL Trees** - Δυαδικά δέντρα αναζήτησης με duplicates
- **CSV Parser** - Φόρτωση δεδομένων από `persons.csv`
- **Family Relations** - Ανίχνευση συγγενικών σχέσεων
- **Unit Tests** - Αυτόματα tests με JUnit

---

## 🚀 Εκτέλεση

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

## 🧪 Testing

### Unit Tests με Maven

```bash
mvn test
```

Τρέχει όλα τα JUnit tests για το CSV parsing και validation.

---

## 📁 Δομή Project

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

## 📊 Dataset

Το `persons.csv` περιέχει 25 άτομα.

**Format:**
```csv
id,name,gender,father_id,mother_id,spouse_id
1,Αυγουστίνος Καποδίστριας,Male,,,2
2,Αναστασία Μαυροκορδάτου,Female,,,1
```

---

## ✨ Υλοποιημένες Λειτουργίες

### Μέρος Α - BST (25 μονάδες)
- ✅ Node class με count για duplicates
- ✅ Insert με recursive implementation
- ✅ Delete με 3 περιπτώσεις (0, 1, 2 παιδιά)
- ✅ Inorder, Preorder, Postorder traversals

### Μέρος Β - AVL (15 μονάδες)
- ✅ Automatic balancing με rotations (LL, RR, LR, RL)
- ✅ Height tracking και balance factor
- ✅ changeKey implementation

### Μέρος C - CSV (20 μονάδες)
- ✅ Person class με όλα τα πεδία
- ✅ CSV parser με error handling
- ✅ Dual indexing (id→Person, name→id)
- ✅ Unit tests για validation

### Μέρος D - Relations (25 μονάδες)
- ✅ isFather, isMother
- ✅ isChild, isSibling
- ✅ isGrandparent, isGrandchild
- ✅ isFirstCousin
- ✅ relation(nameA, nameB) function

### Μέρος Ε - Complex Relations (5 μονάδες)
- ✅ [Σχέση 1 - συμπληρώστε]
- ✅ [Σχέση 2 - συμπληρώστε]

### Μέρος F - Tests & Documentation (10 μονάδες)
- ✅ Unit Tests με JUnit
- ✅ README documentation
- ✅ Viva preparation

---

## 📝 Σημειώσεις

All code is in **Java 17**
- Uses **Maven** for build/dependency management
- CSV is **UTF-8 encoded**
- Tests run with **JUnit 5**
- AM keys for demo: {10, 4, 18, 43, 32, 10, 25, 4, 50, 18, 32, 7}

