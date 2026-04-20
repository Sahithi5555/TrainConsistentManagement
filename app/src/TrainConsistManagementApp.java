import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // UC1
        List<String> train = new ArrayList<>();
        System.out.println("Train size: " + train.size());

        // UC2
        train.add("Sleeper");
        train.add("AC");
        train.remove("AC");

        // UC3
        Set<String> ids = new HashSet<>(Arrays.asList("B1","B2","B2"));

        // UC4
        LinkedList<String> ll = new LinkedList<>(Arrays.asList("Engine","Sleeper","Cargo"));
        ll.add(1,"Pantry");
        ll.removeFirst(); ll.removeLast();

        // UC5
        Set<String> set = new LinkedHashSet<>(Arrays.asList("Engine","Sleeper","Sleeper"));

        // UC6
        Map<String,Integer> map = new HashMap<>();
        map.put("Sleeper",72);

        // UC7 + UC14
        List<PassengerBogie> list = new ArrayList<>();
        try {
            list.add(new PassengerBogie("Sleeper",72));
            list.add(new PassengerBogie("Bad",0));
        } catch(Exception e){ System.out.println(e.getMessage()); }

        // UC8
        list.stream().filter(b->b.capacity>50).forEach(b->System.out.println(b.name));

        // UC9
        list.stream().collect(Collectors.groupingBy(b->b.capacity>50?"High":"Low"));

        // UC10
        int total = list.stream().map(b->b.capacity).reduce(0,Integer::sum);

        // UC11
        System.out.println(Pattern.matches("TRN-\\d{4}","TRN-1234"));

        // UC12
        List<GoodsBogie> goods = Arrays.asList(new GoodsBogie("Cylindrical","Petroleum"));
        goods.stream().allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        // UC13
        long t1 = System.nanoTime();
        list.stream().filter(b->b.capacity>50).toList();
        long t2 = System.nanoTime();
        System.out.println("Time: "+(t2-t1));

        // UC15
        try {
            validateCargo(new GoodsBogie("Rectangular","Petroleum"));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        // UC16
        int[] arr = {5,3,8,1};
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr.length-i-1;j++)
                if(arr[j]>arr[j+1]){
                    int t=arr[j]; arr[j]=arr[j+1]; arr[j+1]=t;
                }

        // UC17
        String[] names={"Sleeper","AC","Engine"};
        Arrays.sort(names);

        // ---------------- UC18 ----------------
        System.out.println("\n--- UC18: Linear Search ---");

        String[] bogieIds = {"B1", "B2", "B3", "B4"};
        String searchKey = "B3";

        boolean found = false;

        for (String id : bogieIds) {
            if (id.equals(searchKey)) {
                found = true;
                break; // early stop
            }
        }

        if (found) {
            System.out.println("Bogie Found");
        } else {
            System.out.println("Bogie Not Found");
        }
    }

    static void validateCargo(GoodsBogie g){
        if(g.type.equals("Rectangular") && g.cargo.equals("Petroleum"))
            throw new CargoSafetyException("Unsafe cargo");
    }
}

// Classes
class PassengerBogie {
    String name; int capacity;
    PassengerBogie(String n,int c) throws InvalidCapacityException{
        if(c<=0) throw new InvalidCapacityException("Invalid capacity");
        name=n; capacity=c;
    }
}

class GoodsBogie {
    String type,cargo;
    GoodsBogie(String t,String c){ type=t; cargo=c; }
}

class InvalidCapacityException extends Exception {
    InvalidCapacityException(String m){ super(m); }
}

class CargoSafetyException extends RuntimeException {
    CargoSafetyException(String m){ super(m); }
}