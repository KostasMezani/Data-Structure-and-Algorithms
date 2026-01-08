package genealogy

// -- Μέρος D από εδώ και πέρα --  

public class FamilyRelations {
    
    private PersonStore store;

    public FamilytRelations(PersonStore store) {
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

    Person pA = store.getPersonById(idA); 
    Person pB = store.getPersonById(idB); 

    if (personA == null){
        System.out.println("[WARN] isFather: Το άτομο με id=" + idA + " δεν υπάρχει.");
        return false;
    }

    if (personB == null){
        System.out.println("[WARN] isFather: Το άτομο με id=" + idB + " δεν υπάρχει.");
        return false;
    }

    // Έλεγχος αν ο Α είναι πατέρας του Β
    String fatherIdOfB = personB.getFatherId();

    if(fatherOfB == null) {
        return false;
    }

    return fatherIdOfB.equals(A);
}

// Ελέγχει αν το άτομο με idA είναι μητέρα του ατόμου με idB

public boolean isMother(String idA, String idB) {
    if (idA == null || idB == null){ 
        System.out.println("[WARN] isMother: Ένα από τα id's είναι null.");
        return false;
    }   
    
    Person pA = store.getPersonById(idA); 
    Person pB = store.getPersonById(idB);

    if (personA == null){
        System.out.println("[WARN] isMother: Το άτομο με id=" + idA + " δεν υπάρχει.");
        return false;
    }

    if (personB == null){
        System.out.println("[WARN] isMother: Το άτομο με id=" + idB + " δεν υπάρχει.");
        return false;
    }

    // Ελέγχει αν ο Α είναι μητέρα του Β
    String motherIdOfB = personB.getMotherId();

    if(motherOfB == null) {
        return false;
    }

    return motherIdOfB.equals(idA);

}