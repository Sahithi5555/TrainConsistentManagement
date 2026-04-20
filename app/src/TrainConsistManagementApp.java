import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // ---------------- UC1 ----------------
        System.out.println("=== Train Consist Management App ===");

        List<String> train = new ArrayList<>();
        System.out.println("Initial bogie count: " + train.size());


        // ---------------- UC2 ----------------
        System.out.println("\n--- UC2: Dynamic Bogie Management ---");

        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");

        System.out.println("Bogies after addition: " + train);

        train.remove("AC Chair");
        System.out.println("After removing AC Chair: " + train);

        if (train.contains("Sleeper")) {
            System.out.println("Sleeper bogie exists in train");
        }


        // ---------------- UC3 ----------------
        System.out.println("\n--- UC3: Unique Bogie IDs ---");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("B1");
        bogieIds.add("B2");
        bogieIds.add("B3");
        bogieIds.add("B2"); // duplicate

        System.out.println("Unique IDs: " + bogieIds);


        // ---------------- UC4 ----------------
        System.out.println("\n--- UC4: LinkedList Operations ---");

        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry Car");

        consist.removeFirst();
        consist.removeLast();

        System.out.println("Final Consist: " + consist);


        // ---------------- UC5 ----------------
        System.out.println("\n--- UC5: LinkedHashSet Order ---");

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate

        System.out.println("Formation: " + formation);


        // ---------------- UC6 ----------------
        System.out.println("\n--- UC6: Capacity Mapping ---");

        HashMap<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 24);

        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        // ---------------- UC7 ----------------
        System.out.println("\n--- UC7: Sorting Bogies ---");

        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 24));

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        for (Bogie b : bogieList) {
            System.out.println(b.name + " -> " + b.capacity);
        }


        // ---------------- UC8 ----------------
        System.out.println("\n--- UC8: Filtering (capacity > 60) ---");

        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        for (Bogie b : filtered) {
            System.out.println(b.name + " -> " + b.capacity);
        }


        // ---------------- UC9 ----------------
        System.out.println("\n--- UC9: Grouping ---");

        Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.capacity >= 60 ? "High" : "Low"));

        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Bogie b : entry.getValue()) {
                System.out.println("  " + b.name);
            }
        }


        // ---------------- UC10 ----------------
        System.out.println("\n--- UC10: Total Capacity ---");

        int total = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + total);


        // ---------------- UC11 ----------------
        System.out.println("\n--- UC11: Regex Validation ---");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train ID (TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code (PET-AB): ");
        String cargoCode = sc.nextLine();

        // regex patterns
        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        if (trainMatcher.matches()) {
            System.out.println("Valid Train ID");
        } else {
            System.out.println("Invalid Train ID");
        }

        if (cargoMatcher.matches()) {
            System.out.println("Valid Cargo Code");
        } else {
            System.out.println("Invalid Cargo Code");
        }

        sc.close();
    }
}


// Bogie class
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}