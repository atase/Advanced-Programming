import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Resident implements Comparable<Resident>{

    private String firstName;
    private String lastName;
    private int age;
    private List<Hospital> hospitalList;

    public Resident(){
        this.firstName = null;
        this.lastName = null;
        this.age = 0;
        this.hospitalList = new ArrayList<Hospital>();
    }

    public Resident(String firstName, String lastName, List<Hospital> hospitals){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
        this.hospitalList = new ArrayList<Hospital>();
        this.hospitalList.addAll(hospitals);
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setHospitalList(List<Hospital> hospitals){
        this.hospitalList.clear();
        this.hospitalList.addAll(hospitals);
    }

    public void addHospital(Hospital hospital){
        this.hospitalList.add(hospital);
    }

    public void addHospitals(List<Hospital> hospitals){
        this.hospitalList.addAll(hospitals);
    }

    public void addHospitals(Set<Hospital> hospitals){
        this.hospitalList.addAll(hospitals);
    }

    public int getAge(){
        return this.age;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public List<Hospital> getHospitalList(){
        return this.hospitalList;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Resident '" + this.getLastName() + " " + this.getFirstName() + "' " + this.getAge() + " years old, prefer : \n");
        if(this.hospitalList.size() > 0) {
            for (Hospital hospital : this.hospitalList) {
                sb.append("  --> Hospital '" + hospital.getName() + "'\n");
            }
        }else{
            sb.append("none");
        }
        return sb.toString();
    }

    public static Comparator<Resident> residentNameComparator = new Comparator<Resident>(){
        public int compare(Resident resident1, Resident resident2){
            String name1 = resident1.getFirstName().toUpperCase() + " " + resident1.getLastName().toUpperCase();
            String name2 = resident2.getFirstName().toUpperCase() + " " + resident2.getLastName().toUpperCase();
            return name1.compareTo(name2);
        }
    };

    public int compareTo(Resident resident){
        if(this.getAge() >= resident.getAge()){
            return 1;
        }else{
            return -1;
        }
    }


    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(!(obj instanceof Resident)){
            return false;
        }

        Resident resident =(Resident) obj;

        return this.toString().equals(resident.toString());
    }

}
