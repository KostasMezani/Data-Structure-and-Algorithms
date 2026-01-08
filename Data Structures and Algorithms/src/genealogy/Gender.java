package genealogy;

public enum Gender {
    MALE, FEMALE, UNKNOWN;

    public static Gender fromCsv(String s) {
        if(s == null) return UNKNOWN;
        return switch (s.trim().toLowerCase()){
            case "male" -> MALE;
            case "female" -> FEMALE;
            case "unknown" -> UNKNOWN;
            default -> UNKNOWN;
        };
    }
}
