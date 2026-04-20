import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // UC1–UC2
        List<String> train = new ArrayList<>();
        train.add("Sleeper");

        // UC3–UC6
        Set<String> ids = new HashSet<>(Arrays.asList("B1","B2","B2"));
        Map<String,Integer> map = new HashMap<>();
        map.put("Sleeper",72);

        // UC7 + UC14
        List<PassengerBogie> list = new ArrayList<>();
        try {
            list.add(new PassengerBogie("Sleeper",72));
        } catch(Exception e){}

        // UC11
        Pattern.matches("TRN-\\d{4}","TRN-1234");

        // UC15
        try { validateCargo(new GoodsBogie("Rectangular","Petroleum")); }
        catch(Exception e){}

        // UC16
        int[] arr = {5,3,1};
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr.length-i-1;j++)
                if(arr[j]>arr[j+1]){
                    int t=arr[j]; arr[j]=arr[j+1]; arr[j+1]=t;
                }

        // UC17
        String[] names={"Sleeper","AC","Engine"};
        Arrays.sort(names);

        // UC18
        String[] idsArr={"B1","B2","B3"};
        String key="B3";
        for(String id:idsArr) if(id.equals(key)) break;

        // UC19
        String[] sorted={"B1","B2","B3"};
        int low=0, high=sorted.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            int cmp=sorted[mid].compareTo("B3");
            if(cmp==0) break;
            else if(cmp<0) low=mid+1;
            else high=mid-1;
        }


        // ---------------- UC20 ----------------
        System.out.println("\n--- UC20: Defensive Search ---");

        List<String> bogies = new ArrayList<>(); // empty list

        try {

            // ❗ Defensive check
            if (bogies.isEmpty()) {
                throw new IllegalStateException("No bogies available for search");
            }

            // search logic (won’t run if empty)
            String search = "B1";
            for (String b : bogies) {
                if (b.equals(search)) {
                    System.out.println("Found");
                }
            }

        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("Program continues safely...");
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