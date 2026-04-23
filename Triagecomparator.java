package triage;

import java.util.Comparator;

/**
 * Ordering rules (highest priority first):
 *  1. Higher injury level wins.
 *  2. Tie-break: Child > Elderly > Adult.
 *  3. Tie-break: lower patient ID (arrived first) wins.
 */
public class Triagecomparator implements Comparator<Patient> {

    private static int typeRank(Patient p) {
        if (p instanceof Child)   return 3;
        if (p instanceof Elderly) return 2;
        return 1; // Adult
    }

    @Override
    public int compare(Patient a, Patient b) {
        // Higher injury level = higher priority
        int cmp = Integer.compare(b.getInjuryLevel(), a.getInjuryLevel());
        if (cmp != 0) return cmp;

        // Higher type rank = higher priority
        cmp = Integer.compare(typeRank(b), typeRank(a));
        if (cmp != 0) return cmp;

        // Earlier arrival (lower ID) = higher priority
        return Integer.compare(a.getId(), b.getId());
    }
}
