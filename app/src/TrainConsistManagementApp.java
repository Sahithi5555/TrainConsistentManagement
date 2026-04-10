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

        // ---------------- UC6 ----------------
        System.out.println("\n--- UC6: Bogie Capacity Mapping using HashMap ---");

        HashMap<String, Integer> capacityMap = new HashMap<>();

        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 24);

        // iterate using entrySet
        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println("Bogie: " + entry.getKey() + " -> Capacity: " + entry.getValue());
        }

        // ---------------- UC7 ----------------
        System.out.println("\n--- UC7: Sorting Bogies using Comparator ---");

        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 24));

        // sort using comparator (ascending capacity)
        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Sorted Bogies by Capacity:");
        for (Bogie b : bogieList) {
            System.out.println(b.name + " -> " + b.capacity);
        }
    }
}

 // ---------------- UC8 ----------------
        System.out.println("\n--- UC8: Stream Filtering (Capacity > 60) ---");

        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered Bogies:");
        for (Bogie b : filtered) {
            System.out.println(b.name + " -> " + b.capacity);
        }
    }
}

// Bogie Class (UC7)
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;

    }
}
