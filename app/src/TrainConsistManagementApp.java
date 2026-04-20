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
        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");

        train.remove("AC Chair");

        if (train.contains("Sleeper")) {
            System.out.println("Sleeper exists");
        }


        // ---------------- UC3 ----------------
        Set<String> ids = new HashSet<>();
        ids.add("B1");
        ids.add("B2");
        ids.add("B2");


        // ---------------- UC4 ----------------
        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry");
        consist.removeFirst();
        consist.removeLast();


        // ---------------- UC5 ----------------
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");


        // ---------------- UC6 ----------------
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Sleeper", 72);
        map.put("AC Chair", 60);
        map.put("First Class", 24);


        // ---------------- UC7 ----------------
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));


        // ---------------- UC8 ----------------
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());


        // ---------------- UC9 ----------------
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.capacity >= 60 ? "High" : "Low"));


        // ---------------- UC10 ----------------
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);


        // ---------------- UC11 ----------------
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train ID: ");
        String trainId = sc.nextLine();

        Pattern p = Pattern.compile("TRN-\\d{4}");
        System.out.println(p.matcher(trainId).matches() ? "Valid" : "Invalid");


        // ---------------- UC12 ----------------
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Open", "Coal"));
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));

        boolean safe = goods.stream().allMatch(g ->
                (!g.type.equals("Cylindrical")) ||
                (g.cargo.equals("Petroleum"))
        );

        System.out.println("Safety: " + safe);


        // ---------------- UC13 ----------------
        System.out.println("\n--- UC13: Performance Comparison ---");

        List<Bogie> bigList = new ArrayList<>();

        // create large data for testing
        for (int i = 0; i < 100000; i++) {
            bigList.add(new Bogie("B" + i, i % 100));
        }

        // ----- Loop-Based -----
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bigList) {
            if (b.capacity > 50) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;


        // ----- Stream-Based -----
        long startStream = System.nanoTime();

        List<Bogie> streamResult = bigList.stream()
                .filter(b -> b.capacity > 50)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;


        System.out.println("Loop Time (ns): " + loopTime);
        System.out.println("Stream Time (ns): " + streamTime);

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