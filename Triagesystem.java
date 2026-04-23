package triage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Manages two queues:
 *  - priorityQueue : PriorityQueue for patients with injury level 5 (critical).
 *  - normalQueue   : FIFO queue for patients with injury levels 1–4.
 *
 * Treatment order:
 *  Priority queue is fully drained before normal queue resumes.
 *  Within the priority queue, patients are ordered by TriageComparator.
 */
public class Triagesystem {

    private final PriorityQueue<Patient> priorityQueue;
    private final Queue<Patient> normalQueue;

    public Triagesystem() {
        priorityQueue = new PriorityQueue<>(new Triagecomparator());
        normalQueue   = new LinkedList<>();
    }

    /** Admit a patient — routes to the correct queue automatically. */
    public void admitPatient(Patient patient) {
        if (patient.getInjuryLevel() == 5) {
            priorityQueue.add(patient);
            System.out.println("  → Admitted to PRIORITY queue: " + patient);
        } else {
            normalQueue.add(patient);
            System.out.println("  → Admitted to NORMAL queue  : " + patient);
        }
    }

    /**
     * Treat the next patient.
     * Priority queue is served first; normal queue continues once it is empty.
     *
     * @return the treated patient, or null if both queues are empty.
     */
    public Patient treatNext() {
        if (!priorityQueue.isEmpty()) {
            return priorityQueue.poll();
        }
        if (!normalQueue.isEmpty()) {
            return normalQueue.poll();
        }
        return null;
    }

    /** Treat all patients in priority order. */
    public void treatAll() {
        System.out.println("\n========== TREATMENT SESSION STARTED ==========");
        int position = 1;
        Patient p;
        while ((p = treatNext()) != null) {
            String source = p.getInjuryLevel() == 5 ? "[PRIORITY]" : "[NORMAL]  ";
            System.out.printf("  #%-3d %s Treating → %s%n", position++, source, p);
        }
        System.out.println("========== ALL PATIENTS TREATED ===============\n");
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty() && normalQueue.isEmpty();
    }

    public int priorityQueueSize() { return priorityQueue.size(); }
    public int normalQueueSize()   { return normalQueue.size(); }

    public void printStatus() {
        // --- Priority Queue ---
        System.out.println("  ┌─ PRIORITY QUEUE (" + priorityQueueSize() + " patient(s)) ──────────────────────┐");
        if (priorityQueue.isEmpty()) {
            System.out.println("  │  (empty)");
        } else {
            // Drain into a sorted list, then re-add so the queue is unchanged
            List<Patient> sorted = new ArrayList<>(priorityQueue);
            sorted.sort(new Triagecomparator());
            for (int i = 0; i < sorted.size(); i++) {
                Patient p = sorted.get(i);
                System.out.printf("  │  #%-3d %s%n", i + 1, p);
            }
        }
        System.out.println("  └────────────────────────────────────────────────┘");

        // --- Normal Queue ---
        System.out.println("  ┌─ NORMAL QUEUE   (" + normalQueueSize() + " patient(s)) ──────────────────────┐");
        if (normalQueue.isEmpty()) {
            System.out.println("  │  (empty)");
        } else {
            int pos = 1;
            for (Patient p : normalQueue) {
                System.out.printf("  │  #%-3d %s%n", pos++, p);
            }
        }
        System.out.println("  └────────────────────────────────────────────────┘");
    }
}