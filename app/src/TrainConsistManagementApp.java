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


        // ---------------- UC7 + UC14 ----------------
        List<PassengerBogie> bogies = new ArrayList<>();

        try {
            bogies.add(new PassengerBogie("Sleeper", 72));
            bogies.add(new PassengerBogie("AC Chair", 60));
            bogies.add(new PassengerBogie("First Class", 24));
            bogies.add(new PassengerBogie("Invalid", 0)); // ❌ exception
        } catch (InvalidCapacityException e) {
            System.out.println("ERROR: " + e.getMessage());
        }


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
        List<PassengerBogie> bigList = new ArrayList<>();

        for (int i = 0; i < 30000; i++) {
            try {
                bigList.add(new PassengerBogie("B" + i, i % 100 + 1));
            } catch (InvalidCapacityException e) {}
        }

        long start = System.nanoTime();

        List<PassengerBogie> loop = new ArrayList<>();
        for (PassengerBogie b : bigList) {
            if (b.capacity > 50) {
                loop.add(b);
            }
        }

        long end = System.nanoTime();
        System.out.println("Loop Time: " + (end - start));


        // ---------------- UC15 ----------------
        System.out.println("\n--- UC15: Cargo Safety ---");

        try {
            GoodsBogie unsafe = new GoodsBogie("Rectangular", "Petroleum");
            validateCargo(unsafe);
        } catch (CargoSafetyException e) {
            System.out.println("SAFETY ERROR: " + e.getMessage());
        } finally {
            System.out.println("Validation completed");
        }


        // ---------------- UC16 ----------------
        System.out.println("\n--- UC16: Bubble Sort ---");

        int[] capacities = {72, 60, 24, 100, 45};

        // Bubble Sort
        for (int i = 0; i < capacities.length - 1; i++) {

            for (int j = 0; j < capacities.length - i - 1; j++) {

                if (capacities[j] > capacities[j + 1]) {

                    // swap
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted Capacities:");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        sc.close();
    }


    // ---------------- UC15 ----------------
    public static void validateCargo(GoodsBogie bogie) {

        if (bogie.type.equals("Rectangular") && bogie.cargo.equals("Petroleum")) {
            throw new CargoSafetyException(
                    "Petroleum cannot be transported in Rectangular bogie"
            );
        }
    }
}


// ---------------- UC14 ----------------
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String msg) {
        super(msg);
    }
}

class PassengerBogie {
    String name;
    int capacity;

    PassengerBogie(String name, int capacity) throws InvalidCapacityException {

        if (capacity <= 0) {
            throw new InvalidCapacityException("Invalid capacity for " + name);
        }

        this.name = name;
        this.capacity = capacity;
    }
}


// ---------------- UC12 + UC15 ----------------
class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }
}


// ---------------- UC15 ----------------
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String msg) {
        super(msg);
    }
}