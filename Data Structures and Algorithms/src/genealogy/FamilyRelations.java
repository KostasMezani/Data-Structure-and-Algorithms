package genealogy;

// -- Μέρος D από εδώ και πέρα --  

public class FamilyRelations {
    
    private PersonStore store;

    public FamilyRelations(PersonStore store) {
        this.store = store;
    }


// -- D.1 Πατέρας - Μητέρα --

// Έλεγχος για το αν το άτομο με idA είναι πατέρας του ατόμου με idB.

public boolean isFather(String idA, String idB) {

    // Έλεγχος Null
    if (idA == null || idB == null){ 
        System.out.println("[WARN] isFather: Ένα από τα id's είναι null.");
        return false;
    }

    Person personA = store.getById(idA); 
    Person personB = store.getById(idB); 

    if (personA == null){
        System.out.println("[WARN] isFather: Το άτομο με id=" + idA + " δεν υπάρχει.");
        return false;
    }

    if (personB == null){
        System.out.println("[WARN] isFather: Το άτομο με id=" + idB + " δεν υπάρχει.");
        return false;
    }
    if (personA.getGender() != Gender.MALE) {
        System.out.println("[WARN] isFather: Το άτομο " + personA.getName() + 
                        " (id=" + idA + ") δεν είναι άνδρας");
        return false;
    }

    // Έλεγχος αν ο Α είναι πατέρας του Β
    String fatherIdOfB = personB.getFatherId();

    if(fatherIdOfB == null) {
        return false;
    }

    return fatherIdOfB.equals(idA);
}

// Ελέγχει αν το άτομο με idA είναι μητέρα του ατόμου με idB

public boolean isMother(String idA, String idB) {
    if (idA == null || idB == null){ 
        System.out.println("[WARN] isMother: Ένα από τα id's είναι null.");
        return false;
    }   
    
    Person personA = store.getById(idA); 
    Person personB = store.getById(idB);

    if (personA == null){
        System.out.println("[WARN] isMother: Το άτομο με id=" + idA + " δεν υπάρχει.");
        return false;
    }

    if (personB == null){
        System.out.println("[WARN] isMother: Το άτομο με id=" + idB + " δεν υπάρχει.");
        return false;
    }

    if (personA.getGender() != Gender.FEMALE) {
        System.out.println("[WARN] isMother: Το άτομο " + personA.getName() + 
                        " (id=" + idA + ") δεν είναι γυναίκα");
        return false;
    }

    // Ελέγχει αν ο Α είναι μητέρα του Β
    String motherIdOfB = personB.getMotherId();

    if(motherIdOfB == null) {
        return false;
    }

    return motherIdOfB.equals(idA);

}

// -- D.2 : Παιδί - Αδέλφια --


    // Ελέγχει αν το άτομο με idA είναι παιδί του ατόμου με idB
    public boolean isChild(String idA, String idB){
        //Έλεγχος null
        if(idA == null || idB == null){
            System.out.println("[WARN] isChild: Ένα από τα id's είναι null.");
            return false;
        }

        // Έλεγχος αν τα άτομα υπάρχουν και δεν είναι Null
        Person personA = store.getById(idA);
        Person personB = store.getById(idB);

        if (personA == null){
            System.out.println("[WARN] isChild: Το άτομο με id=" + idA + " δεν υπάρχει.");
            return false;
        }

        if (personB == null){
            System.out.println("[WARN] isChild: Το άτομο με id=" + idB + " δεν υπάρχει.");
            return false;
        }

        //Το Α είναι παιδί του Β αν ο Β είναι πατέρας ή μητέρα του Α
        return isMother(idB, idA) || isFather(idB, idA);
    }

    //Ελέγχει αν τα άτομα με idA και idB είναι αδέλφια
    public boolean isSibling(String idA, String idB){
        //Έλεγχος null
        if(idA == null || idB == null){
            System.out.println("[WARN] isSibling: Ένα από τα id's είναι null.");
            return false;
        }

        //Έλεγχος για ίδιο άτομο
        if(idA.equals(idB)){
            System.out.println("[WARN] isSibling: Το ίδιο άτομο (id=" + idA + ") δεν μπορεί να είναι αδελφός του εαυτού του.");
            return false;
        }

        //Έλεγχος αν τα άτομα υπάρχουν
        Person personA = store.getById(idA);
        Person personB = store.getById(idB);

        if (personA == null){
            System.out.println("[WARN] isSibling: Το άτομο με id=" + idA + " δεν υπάρχει.");
            return false;
        }

        if (personB == null){
            System.out.println("[WARN] isSibling: Το άτομο με id=" + idB + " δεν υπάρχει.");
            return false;
        }

        //Γονείς του Α
        String motherA = personA.getMotherId();
        String fatherA = personA.getFatherId();

        //Γονείς του Β
        String motherB = personB.getMotherId();
        String fatherB = personB.getFatherId();

        //Αν κανένα από τα δύο δεν έχει γονείς , τότε δεν μπορούν να είναι αδέλφια
        if(motherA == null && fatherA == null ||motherB == null && fatherB == null){
            return false;
        }

        //Έλεγχος κοιν΄ων γονιών
        boolean sameFather = fatherA != null && fatherA.equals(fatherB);
        boolean sameMother = motherA != null && motherA.equals(motherB);

        //Είναι αδέλφια αν έχουν κοινό πατέρα και μητέρα
        return sameFather || sameMother;
    }

    // -- D.3 : Παππούς/Γιαγιά - Εγγονός --


    // Ελέγχει αν το άτομο με idA είναι παππούς/γιαγιά του ατόμου με idB
    public boolean isGrandparent(String idA, String idB){
        //Έλεγχος για Null
        if(idA == null || idB == null){
            System.out.println("[WARN] isGrandparent: Ένα από τα id's είναι null.");
            return false;
        }

        //Έλεγχος αν τα άτομα υπάρχουν
        Person personA = store.getById(idA);
        Person personB = store.getById(idB);

        if(personA == null){
            System.out.println("[WARN] isGrandparent: Το άτομο με id=" + idA + " δεν υπάρχει.");
            return false;
        }

        if(personB == null){
            System.out.println("[WARN] isGrandparent: Το άτομο με id=" + idB + " δεν υπάρχει.");
            return false;
        }

        // Γονείς του Β
        String motherB = personB.getMotherId();
        String fatherB = personB.getFatherId();

        // Έλεγχος αν το Β δεν έχει γονείς
        if(motherB == null && fatherB == null){
            return false;
        }

        // Έλεγχος αν το άτομο Α είναι γονέας του πατέρα του Β
        boolean isGrandparentOfFather = false;
        if (fatherB != null){
            isGrandparentOfFather = isChild(fatherB, idA);
        }

        // Έλεγχος αν το άτομο Α είναι γονέας της μητέρας του Β
        boolean isGrandparentOfMother = false;
        if (motherB != null){
            isGrandparentOfMother = isChild(motherB, idA);
        }

        return isGrandparentOfFather || isGrandparentOfMother;
    }

    // Ελέγχει αν το άτομο με idA είναι εγγόνι του ατόμου με idB
    public boolean isGrandchild(String idA, String idB){

        // Έλεγχος για null
        if(idA == null || idB == null){
            System.out.println("[WARN] isGrandchild: Ένα από τα id's είναι null.");
            return false;
        }

        // Έλεγχος αν τα άτομα υπάρχουν
        Person personA = store.getById(idA);
        Person personB = store.getById(idB);

        if(personA == null){
            System.out.println("[WARN] isGrandchild: Το άτομο με id=" + idA + " δεν υπάρχει.");
            return false;
        }

        if(personB == null){
            System.out.println("[WARN] isGrandchild: Το άτομο με id=" + idB + " δεν υπάρχει.");
            return false;
        }

        return isGrandparent(idB, idA);
    }

    // -- D.4 : Πρώτα Ξαδέρφια --

    // Ελέγχει αν τα άτομα με idA και idB είναι πρώτα ξα΄δ΄έρφια
    public boolean isFirstCousin(String idA, String idB){

        // Έλεγχος για null
        if(idA == null || idB == null){
            System.out.println("[WARN] isFirstCousin: Ένα από τα id's είναι null.");
            return false;
        }

        // Έλεγχος για ίδιο άτομο
        if (idA.equals(idB)){
            System.out.println("[WARN] isFirstCousin: Το ίδιο άτομο (id=" + idA + ") δεν μπορεί να είναι ξάδερφος του εαυτού του.");
            return false;
        }

        // Έλεγχος αν τα άτομα υπάρχουν
        Person personA = store.getById(idA);
        Person personB = store.getById(idB);

        if(personA == null){
            System.out.println("[WARN] isFirstCousin: Το άτομο με id=" + idA + " δεν υπάρχει.");
            return false;
        }

        if(personB == null){
            System.out.println("[WARN] isFirstCousin: Το άτομο με id=" + idB + " δεν υπάρχει.");
            return false;
        }

        //Γονείς Α
        String motherA = personA.getMotherId();
        String fatherA = personA.getFatherId();

        //Γονείς Β
        String motherB = personB.getMotherId();
        String fatherB = personB.getFatherId();

        //Αν κάποιο άτομο δεν έχει γονείς, δεν μπορεί να είναι ξαδέρφια τα άτομα μεταξύ τους
        if((motherA == null && fatherA == null) || (motherB == null && fatherB == null)) {
            return false;
        }

        // Πιθανοί συνδυασμοί

        // -- 1 --
        boolean fatherA_siblingOf_fatherB =
                (fatherA != null && fatherB != null && isSibling(fatherA, fatherB));

        // -- 2 --
        boolean fatherA_siblingOf_motherB =
                (fatherA != null && motherB != null && isSibling(fatherA, motherB));

        // -- 3 --
        boolean motherA_siblingOf_fatherB =
                (motherA != null && fatherB != null && isSibling(motherA, fatherB));

        // -- 4 --
        boolean motherA_siblingOf_motherB =
                (motherA != null && motherB != null && isSibling(motherA, motherB));

        // Αν ισχύει έστω κάποιος από τους συνδυασμούς τότε είναι ξαδέρφια
        return (fatherA_siblingOf_fatherB ||
                fatherA_siblingOf_motherB ||
                motherA_siblingOf_fatherB ||
                motherA_siblingOf_motherB);

    }

    // -- D.5 : relation(nameA, nameB) --
    // Επιστρέφει την ΠΡΏΤΗ σχέση που ταιριάζει (με τη σειρά που ζητείται απο την εκφώνηση)
    // ή " Δεν σχετίζονται " αν δεν βρεθεί σχέση.
    public String relations(String nameA, String nameB){

        //Basic validation
        if (nameA == null || nameB == null) {
            return "Σφάλμα: Δόθηκαν null ονόματα";
        }

        nameA = nameA.trim();
        nameB = nameB.trim();

        if (nameA.isEmpty() || nameB.isEmpty()) {
            return "Σφάλμα: Δόθηκε κενό όνομα";
        }

        //1) name -> id lookup (απο το index που φτιάξαμε στο Μέρος C)
        String idA = store.getIdByName(nameA);
        String idB = store.getIdByName(nameB);

        if (idA == null){
            return "Το όνομα '" + nameA + "' dδεν βρέθηκε σε dataset";
        }

        if (idB == null) {
            return "Το όνομα '" + nameB + "' dδεν βρέθηκε σε dataset";
        }

        // Προαιρετικό αλλά χρήσιμο: ίδιο ατομο
        if (idA.equals(idB)){
            return "Το '" + nameA + "' και το '" + nameB + "' είναι το ίδιο άτομο";
        }

        // 2) Έλεγχοι ΜΕ ΑΚΡΙΒΩΣ τη σειρά που ζητάει η εκφώνηση
        if (isFather(idA, idB)){
            return nameA + " είναι πατέρας του/της " + nameB + ".";
        }

        if (isMother(idA, idB)){
            return nameA + " είναι μητέρα του/της " + nameB + ".";
        }

        if (isChild(idA, idB)){
            return nameA + " είναι παιδί του/της " + nameB + ".";
        }

        if (isSibling(idA, idB)){
            return nameA + " είναι αδελφός του/της " + nameB + ".";
        }

        if (isGrandparent(idA, idB)){
            return nameA + " είναι παππούς/γιαγιά του/της " + nameB + ".";
        }

        if (isGrandchild(idA, idB)){
            return nameA + " είναι εγγόνι του/της " + nameB + ".";
        }

        if (isFirstCousin(idA, idB)){
            return nameA + " είναι πρώτος/πρώτη ξάδελφος/ξαδέλφη του/της " + nameB + ".";
        }

        // 3) Καμία σχέση
        return "Δεν σχετίζονται";
    }
}