import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital> {

    private String name;
    private int capacity;
    private List<Resident> residentList;

    public Hospital(){
        this.name = null;
        this.capacity = 0;
        this.residentList = new ArrayList<Resident>();
    }

    public Hospital(String name, int capacity, List<Resident> residents){
        this.name = name;
        this.capacity = capacity;
        this.residentList = new ArrayList<Resident>();
        if(residents.size() <= this.capacity){
            this.residentList.addAll(residents);
        }else {
            System.out.println("Not enough capacity");
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setResidentList(List<Resident> residents) {
        if (residents.size() <= this.capacity) {
            this.residentList.clear();
            this.residentList.addAll(residents);
        } else {
            System.out.println("Not enough capacity");
        }
    }

    public void addResident(Resident resident){
        if(this.getNoResidents() + 1 <= this.capacity) {
            this.residentList.add(resident);
        }
    }

    public void addResidents(List<Resident> residents){
        if(this.getNoResidents() + residents.size() <= this.capacity) {
            this.residentList.addAll(residents);
        }
    }

    public String getName(){
        return this.name;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public List<Resident> getResidentList(){
        return this.residentList;
    }

    public int getNoResidents(){
        return this.residentList.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Hospital '" + this.getName() + "' with capacity " + this.getCapacity() + ".");
        sb.append("\nTop preference : \n");
        if(this.residentList.size() > 0) {
            for (Resident resident : this.residentList) {
                sb.append("  --> Resident '" + resident.getFirstName() + " " + resident.getLastName() + "' " + resident.getAge() + " years old \n");
            }
        }else{
            sb.append("none");
        }
        return sb.toString();
    }

    public int compareTo(Hospital hospital){
        if(this.getCapacity() >= hospital.getCapacity()){
            return 1;
        }else{
            return -1;
        }
    }

}
