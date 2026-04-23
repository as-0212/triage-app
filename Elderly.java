package triage;

public class Elderly extends Patient {
    public Elderly(String name, int injuryLevel) {
        super(name, injuryLevel);
    }

    @Override
    public String getType() { return "Elderly"; }
}
