import java.util.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // ---------------- UC1 ----------------
        System.out.println("=== Train Consist Management App ===");

        List<String> train = new ArrayList<>();

        System.out.println("Initial bogie count: " + train.size());


        // ---------------- UC2 ----------------
        System.out.println("\n--- UC2: Dynamic Bogie Management ---");

        // Add bogies
        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");

        // Display after adding
        System.out.println("Bogies after addition: " + train);

        // Remove a bogie
        train.remove("AC Chair");
        System.out.println("After removing AC Chair: " + train);

        // Check existence
        if (train.contains("Sleeper")) {
            System.out.println("Sleeper bogie exists in train");
        } else {
            System.out.println("Sleeper bogie not found");
        }

        // Final state
        System.out.println("Final train consist: " + train);

        // ---------------- UC3 ----------------
        System.out.println("\n--- UC3: Unique Bogie IDs using HashSet ---");

        Set<String> bogieIds = new HashSet<>();

        // Adding bogie IDs
        bogieIds.add("B1");
        bogieIds.add("B2");
        bogieIds.add("B3");
        bogieIds.add("B2"); // duplicate
        bogieIds.add("B1"); // duplicate

        // Display unique IDs
        System.out.println("Unique Bogie IDs: " + bogieIds);

        // ---------------- UC4 ----------------
        System.out.println("\n--- UC4: LinkedList Train Operations ---");

        LinkedList<String> consist = new LinkedList<>();

        // Add bogies
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        // Insert Pantry Car at position 2
        consist.add(2, "Pantry Car");

        System.out.println("After insertion: " + consist);

        // Remove first and last bogie
        consist.removeFirst();
        consist.removeLast();

        System.out.println("Final train consist: " + consist);

        // ---------------- UC5 ----------------
        System.out.println("\n--- UC5: LinkedHashSet Ordered Unique Bogies ---");

        LinkedHashSet<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        formation.add("Sleeper"); // duplicate (ignored)

        System.out.println("Train Formation (No duplicates, ordered): " + formation);
    }
}
