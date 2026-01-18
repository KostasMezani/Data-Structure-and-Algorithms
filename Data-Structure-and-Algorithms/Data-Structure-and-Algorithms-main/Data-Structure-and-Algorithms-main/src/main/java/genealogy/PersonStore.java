package main.java.genealogy;

import java.util.*;
public class PersonStore {

    private final Map<String, Person> idToPerson = new HashMap<>();
    private final Map<String, String> nameToId = new HashMap<>();

    public void put(Person p){
        idToPerson.put(p.getId(), p);
        nameToId.put(p.getName(), p.getId());
    }

    public Person getById(String id){
        return idToPerson.get(id);
    }

    public String getIdByName(String name){
        return nameToId.get(name);
    }

    public Collection<Person> allPersons(){
        return idToPerson.values();
    }

    public int size(){
        return idToPerson.size();
    }

    public Person getPersonByName(String name) {
        String id = nameToId.get(name);
        if (id == null) return null;
        return idToPerson.get(id);
    }
}
