package main.java.genealogy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonCsvLoader {
    private static final String EXPECTED_HEADER = "id,name,gender,father_id,mother_id,spouse_id";

    public PersonStore load(Path csvPath) throws IOException{
        PersonStore store = new PersonStore();

        try(BufferedReader br = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)){
            String header = br.readLine();
            header = stripBom(header);

            if (header == null) {
                throw new IllegalArgumentException("CSV is empty: " + csvPath);
            }

            if(!header.trim().equalsIgnoreCase(EXPECTED_HEADER)){
                throw new IllegalArgumentException(
                        "Invalid header. Expected: " + EXPECTED_HEADER + " but got: " + header
                );
            }

            String line;
            int lineNo = 1;
            while ((line = br.readLine()) != null) {
                lineNo++;

                if(line.trim().isEmpty()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length != 6) {
                    System.out.println("[WARN] Line " + lineNo + ": Expected 6 columns, got " + parts.length + " -> " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                Gender gender = Gender.fromCsv(parts[2]);

                String fatherId = emptyToNull(parts[3]);
                String motherId = emptyToNull(parts[4]);
                String spouseId = emptyToNull(parts[5]);

                if(id.isEmpty() || name.isEmpty()){
                    System.err.println("[WARN] Line " + lineNo + ": Missing id or name-> " + line);
                    continue;
                }

                Person p = new Person(id, name, gender, fatherId, motherId, spouseId);
                store.put(p);
            }
        }
        return store;
    }

    private String stripBom(String s) {
        if(s == null) return null;
        return s.startsWith("\uFEFF") ? s.substring(1) : s;
    }

    private String emptyToNull(String s){
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}
