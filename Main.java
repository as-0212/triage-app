package triage;

import java.util.Scanner;

/**
 * Earthquake Disaster Triage System
 * ----------------------------------
 * Interactive console application.
 *
 * Menu:
 *   1. Admit patient
 *   2. Treat next patient
 *   3. Treat all patients
 *   4. Show queue status
 *   5. Run demo scenario
 *   0. Exit
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Triagesystem triage = new Triagesystem();

    public static void main(String[] args) {
        printBanner();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> admitPatientInteractive();
                case "2" -> treatNextInteractive();
                case "3" -> triage.treatAll();
                case "4" -> { System.out.println("\n--- Queue Status ---");
                    triage.printStatus(); System.out.println(); }
                case "5" -> runDemo();
                case "0" -> { running = false; System.out.println("\nSystem shut down. Stay safe.\n"); }
                default  -> System.out.println("  Invalid option. Please try again.\n");
            }
        }
    }

    // -------------------------------------------------------------------------
    // Interactive helpers
    // -------------------------------------------------------------------------

    private static void admitPatientInteractive() {
        System.out.println("\n--- Admit Patient ---");

        System.out.print("  Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Unknown";

        System.out.print("  Type [1=Adult, 2=Elderly, 3=Child]: ");
        int typeChoice = readInt(1, 3);

        System.out.print("  Injury level [1-5]: ");
        int injury = readInt(1, 5);

        Patient patient = switch (typeChoice) {
            case 2  -> new Elderly(name, injury);
            case 3  -> new Child(name, injury);
            default -> new Adult(name, injury);
        };

        triage.admitPatient(patient);
        System.out.println();
    }

    private static void treatNextInteractive() {
        System.out.println("\n--- Treat Next Patient ---");
        Patient p = triage.treatNext();
        if (p == null) {
            System.out.println("  Both queues are empty. No patients to treat.\n");
        } else {
            String src = p.getInjuryLevel() == 5 ? "[PRIORITY]" : "[NORMAL]  ";
            System.out.println("  Treating " + src + " → " + p);
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Demo scenario
    // -------------------------------------------------------------------------

    private static void runDemo() {
        System.out.println("\n========== DEMO SCENARIO ==========");
        System.out.println("Admitting 10 patients from the earthquake site...\n");

        Patient[] patients = {
                new Adult  ("Maria",   3),
                new Child  ("Tommy",   2),
                new Elderly("George",  4),
                new Adult  ("John",    5),   // → Priority
                new Child  ("Lily",    5),   // → Priority
                new Elderly("Rosa",    1),
                new Adult  ("Carlos",  5),   // → Priority
                new Child  ("Emma",    3),
                new Elderly("Hans",    5),   // → Priority
                new Adult  ("Sophie",  2),
        };

        for (Patient p : patients) triage.admitPatient(p);

        System.out.println("\nQueue status after admissions:");
        triage.printStatus();

        triage.treatAll();
    }

    // -------------------------------------------------------------------------
    // UI helpers
    // -------------------------------------------------------------------------

    private static void printBanner() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   EARTHQUAKE DISASTER TRIAGE SYSTEM v1.0    ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("┌─ MAIN MENU ──────────────────────────────────┐");
        System.out.println("│  1. Admit patient                            │");
        System.out.println("│  2. Treat next patient                       │");
        System.out.println("│  3. Treat ALL remaining patients             │");
        System.out.println("│  4. Show queue status                        │");
        System.out.println("│  5. Run demo scenario                        │");
        System.out.println("│  0. Exit                                     │");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("  Choice: ");
    }

    /** Read an integer within [min, max], re-prompting on bad input. */
    private static int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.printf("  Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("  Invalid input. Enter a number (%d–%d): ", min, max);
            }
        }
    }
}