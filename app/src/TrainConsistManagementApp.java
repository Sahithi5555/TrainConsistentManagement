import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // UC1
        List<String> train = new ArrayList<>();

        // UC2
        train.add("Sleeper"); train.add("AC"); train.remove("AC");

        // UC3
        Set<String> ids = new HashSet<>(Arrays.asList("B1","B2","B2"));

        // UC4
        LinkedList<String> ll = new LinkedList<>(Arrays.asList("Engine","Sleeper","Cargo"));
        ll.add(1,"Pantry"); ll.removeFirst(); ll.removeLast();

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
        } catch(Exception e){}

        // UC8–UC10
        list.stream().filter(b->b.capacity>50).toList();
        list.stream().collect(Collectors.groupingBy(b->b.capacity>50?"High":"Low"));
        list.stream().map(b->b.capacity).reduce(0,Integer::sum);

        // UC11
        Pattern.matches("TRN-\\d{4}","TRN-1234");

        // UC12
        List<GoodsBogie> goods = Arrays.asList(new GoodsBogie("Cylindrical","Petroleum"));
        goods.stream().allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        // UC13
        long t1 = System.nanoTime();
        list.stream().toList();
        long t2 = System.nanoTime();

        // UC15
        try { validateCargo(new GoodsBogie("Rectangular","Petroleum")); }
        catch(Exception e){}

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

        // UC18 (Linear Search)
        String[] idsArr={"B1","B2","B3","B4"};
        String key="B3";
        for(String id:idsArr) if(id.equals(key)) break;


        // ---------------- UC19 ----------------
        System.out.println("\n--- UC19: Binary Search ---");

        String[] sortedIds = {"B1","B2","B3","B4","B5"};
        String searchKey = "B3";

        int low = 0, high = sortedIds.length - 1;
        boolean found = false;

        while (low <= high) {

            int mid = (low + high) / 2;

            int cmp = sortedIds[mid].compareTo(searchKey);

            if (cmp == 0) {
                found = true;
                break;
            }
            else if (cmp < 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        System.out.println(found ? "Bogie Found" : "Bogie Not Found");
    }

    static void validateCargo(GoodsBogie g){
        if(g.type.equals("Rectangular") && g.cargo.equals("Petroleum"))
            throw new CargoSafetyException("Unsafe cargo");
    }
}

// Classes
class PassengerBogie {
    String name; int capacity;
    PassengerBogie(String n,int c) throws Exception{
        if(c<=0) throw new Exception();
        name=n; capacity=c;
    }
}

class GoodsBogie {
    String type,cargo;
    GoodsBogie(String t,String c){ type=t; cargo=c; }
}

class CargoSafetyException extends RuntimeException {
    CargoSafetyException(String m){ super(m); }
}