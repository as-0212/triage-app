package triage;

public class Adult extends Patient {
    public Adult(String name, int injuryLevel) {
        super(name, injuryLevel);
    }

    @Override
    public String getType() { return "Adult"; }
}
