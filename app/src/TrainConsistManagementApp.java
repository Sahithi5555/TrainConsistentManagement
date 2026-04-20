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
            System.out.println("Sleeper exists");
        }


        // ---------------- UC3 ----------------
        System.out.println("\n--- UC3: Unique Bogie IDs ---");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("B1");
        bogieIds.add("B2");
        bogieIds.add("B2"); // duplicate

        System.out.println("Unique IDs: " + bogieIds);


        // ---------------- UC4 ----------------
        System.out.println("\n--- UC4: LinkedList ---");

        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry");

        consist.removeFirst();
        consist.removeLast();

        System.out.println("Final Consist: " + consist);


        // ---------------- UC5 ----------------
        System.out.println("\n--- UC5: LinkedHashSet ---");

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate

        System.out.println("Formation: " + formation);


        // ---------------- UC6 ----------------
        System.out.println("\n--- UC6: HashMap Capacity ---");

        HashMap<String, Integer> map = new HashMap<>();
        map.put("Sleeper", 72);
        map.put("AC Chair", 60);
        map.put("First Class", 24);

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }


        // ---------------- UC7 ----------------
        System.out.println("\n--- UC7: Sorting ---");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }


        // ---------------- UC8 ----------------
        System.out.println("\n--- UC8: Filtering ---");

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        filtered.forEach(b -> System.out.println(b.name));


        // ---------------- UC9 ----------------
        System.out.println("\n--- UC9: Grouping ---");

        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.capacity >= 60 ? "High" : "Low"));

        System.out.println(grouped);


        // ---------------- UC10 ----------------
        System.out.println("\n--- UC10: Total Capacity ---");

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + total);


        // ---------------- UC11 ----------------
        System.out.println("\n--- UC11: Regex Validation ---");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train ID: ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code: ");
        String cargo = sc.nextLine();

        Pattern p1 = Pattern.compile("TRN-\\d{4}");
        Pattern p2 = Pattern.compile("PET-[A-Z]{2}");

        System.out.println(p1.matcher(trainId).matches() ? "Valid Train ID" : "Invalid Train ID");
        System.out.println(p2.matcher(cargo).matches() ? "Valid Cargo Code" : "Invalid Cargo Code");


        // ---------------- UC12 ----------------
        System.out.println("\n--- UC12: Safety Validation (Lambda) ---");

        List<GoodsBogie> goods = new ArrayList<>();

        goods.add(new GoodsBogie("Open", "Coal"));
        goods.add(new GoodsBogie("Covered", "Grain"));
        goods.add(new GoodsBogie("Cylindrical", "Petroleum")); // valid

        // RULE:
        // Cylindrical → ONLY Petroleum allowed

        boolean isSafe = goods.stream().allMatch(g ->
                (!g.type.equals("Cylindrical")) ||
                (g.type.equals("Cylindrical") && g.cargo.equals("Petroleum"))
        );

        if (isSafe) {
            System.out.println("Train is SAFE");
        } else {
            System.out.println("Train is NOT SAFE");
        }

        sc.close();
    }
}


// ---------------- Classes ----------------

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }
}