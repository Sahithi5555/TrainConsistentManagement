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
    }
}