package triage;

public abstract class Patient {
    private static int idCounter = 1;

    private final int id;
    private final String name;
    private final int injuryLevel; // 1–5

    public Patient(String name, int injuryLevel) {
        if (injuryLevel < 1 || injuryLevel > 5)
            throw new IllegalArgumentException("Injury level must be between 1 and 5.");
        this.id = idCounter++;
        this.name = name;
        this.injuryLevel = injuryLevel;
    }

    public int getId()           { return id; }
    public String getName()      { return name; }
    public int getInjuryLevel()  { return injuryLevel; }

    /** Human-readable patient type shown in output */
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[ID:%d] %-10s | Type: %-8s | Injury Level: %d%s",
                id, name, getType(), injuryLevel,
                injuryLevel == 5 ? "  *** CRITICAL ***" : "");
    }
}