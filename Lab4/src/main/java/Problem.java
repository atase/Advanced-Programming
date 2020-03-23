import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {

    public static void main(String[] args){

        /**
         * Set of hospitals
         * List of residents
         */
        Set<Hospital> hospitalSet = new TreeSet<Hospital>();
        List<Resident> residentList = new ArrayList<Resident>();

        /**
         * Map(Resident, List<Hospital>) - each resident have a list of hospitals (type : TreeMap)
         * Map(Hospital, list<Resident>) - each hospital have a list of residents (type : HashMap)
         */
        Map<Resident, List<Hospital>> residentPreference = new TreeMap<Resident, List<Hospital>>();
        Map<Hospital, List<Resident>> hospitalPreference = new HashMap<Hospital, List<Resident>>();

        /**
         * Object of type Faker - use to create fake name, fake first name, fake last name
         */

        Faker faker = new Faker();

        /**
         * Create 3 objects of type Resident
         * Set first name, last name (faker) for each resident
         * Set age for each resident
         * Add each resident to list
         */

        Resident resident0 = new Resident();
        Resident resident1 = new Resident();
        Resident resident2 = new Resident();
        Resident resident3 = new Resident();

        resident0.setFirstName(faker.name().firstName());
        resident0.setLastName(faker.name().lastName());

        resident1.setFirstName(faker.name().firstName());
        resident1.setLastName(faker.name().lastName());

        resident2.setFirstName(faker.name().firstName());
        resident2.setLastName(faker.name().lastName());

        resident3.setFirstName(faker.name().firstName());
        resident3.setLastName(faker.name().lastName());

        resident0.setAge(20);
        resident1.setAge(22);
        resident2.setAge(29);
        resident3.setAge(90);


        residentList.add(resident0);
        residentList.add(resident1);
        residentList.add(resident2);
        residentList.add(resident3);

        /**
         * Output unsorted list of residents
         * Sort list using Comparator : compare(resident1.fullName.toUpper, resident2.fullName.toUpper)
         * Output sorted list of residents
         */

        System.out.println("\n********************************************************************\n");
        System.out.println("Unsorted : \n");
        for(Resident resident : residentList){
            System.out.println(resident.toString());
        }

        Collections.sort(residentList, Resident.residentNameComparator);

        System.out.println("\n********************************************************************\n");
        System.out.println("Sorted : ");
        for(Resident resident : residentList){
            System.out.println(resident.toString());
        }
        System.out.println("\n********************************************************************\n");


        /**
         * Create 3 objects of type hospital
         * Set name (Faker) for each hospital
         * Set capacity
         * Add each hospital to set
         */
        Hospital hospital0 = new Hospital();
        Hospital hospital1 = new Hospital();
        Hospital hospital2 = new Hospital();

        hospital0.setName(faker.name().fullName());
        hospital1.setName(faker.name().fullName());
        hospital2.setName(faker.name().fullName());

        hospital0.setCapacity(4);
        hospital1.setCapacity(3);
        hospital2.setCapacity(3);


        hospitalSet.add(hospital0);
        hospitalSet.add(hospital1);
        hospitalSet.add(hospital2);

        /**
         * Add resident for each hospital
         * @param list of residents/ object resident
         */

        hospital0.addResidents(residentList);

        hospital1.addResident(resident0);
        hospital1.addResident(resident2);
        hospital1.addResident(resident1);

        hospital2.addResident(resident0);
        hospital2.addResident(resident1);
        hospital2.addResident(resident3);

        /**
         * Add pair of form(hospital, List<Resident>) in map
         * Each hospital have a list of residents
         * Output pairs
         */

        hospitalPreference.put(hospital0, hospital0.getResidentList());
        hospitalPreference.put(hospital1, hospital1.getResidentList());
        hospitalPreference.put(hospital2, hospital2.getResidentList());
        System.out.println("\n********************************************************************\n");
        for(Map.Entry<Hospital, List<Resident>> entry : hospitalPreference.entrySet()){
            System.out.println("*** Hospital " + entry.getKey().getName() + "\n");
            List<Resident> hospitalResidents = entry.getValue();
            for(Resident resident : hospitalResidents){
                System.out.println("  ==> Resident " + resident.getFirstName() + " " + resident.getLastName() + " " + resident.getAge() + " years old\n");
            }
        }
        System.out.println("\n********************************************************************\n");

        /**
         * Add hospitals for each resident.
         * @param set of hospitals / object hospital
         */
        resident0.addHospitals(hospitalSet);

        resident1.addHospitals(hospitalSet);

        resident2.addHospital(hospital0);
        resident2.addHospital(hospital1);

        resident3.addHospital(hospital0);
        resident3.addHospital(hospital2);

        /**
         * Add pair of form (resident, list<Hospital>) in map
         * Each resident have a list of hospitals
         * Output pairs
         */
        residentPreference.put(resident0, resident0.getHospitalList());
        residentPreference.put(resident1, resident1.getHospitalList());
        residentPreference.put(resident2, resident2.getHospitalList());
        residentPreference.put(resident3, resident3.getHospitalList());

        System.out.println("\n********************************************************************\n");
        for(Map.Entry<Resident, List<Hospital>> entry : residentPreference.entrySet()){

            System.out.println("*** Resident " + entry.getKey().getFirstName() + " " + entry.getKey().getLastName() + " " + entry.getKey().getAge() + " years old\n");
            List<Hospital> residentHospitals = entry.getValue();
            for(Hospital hospital : residentHospitals){
                System.out.println("  ==> Hospital " + hospital.getName() + "\n");
            }
        }
        System.out.println("\n********************************************************************\n");

        /**
         * Output all object with overloaded toString() method.
         */
        System.out.println(hospital0.toString());
        System.out.println(hospital1.toString());
        System.out.println(hospital1.toString());

        System.out.println(resident0.toString());
        System.out.println(resident1.toString());
        System.out.println(resident2.toString());
        System.out.println(resident3.toString());

        /**
         * Use Stream API to search residents and hospitals
         *
         */

        System.out.println("\n********************************************************************\n");
        System.out.println("[ Resident prefer hospital0 or hospital 2]");
        List<Resident> selectedResidents = residentPreference.entrySet().stream()
                .filter(list -> list.getValue().contains(hospital0) && list.getValue().contains(hospital2))
                .map(map -> map.getKey())
                .collect(Collectors.toList());


        for(Resident resident : selectedResidents){
            System.out.println(resident.toString());
        }

        System.out.println("\n********************************************************************\n");
        System.out.println("[ Hospital -  1st place resident0 ]");
        List<Hospital> selectedHospitals = hospitalPreference.entrySet().stream()
                .filter(list -> list.getValue().get(0).equals(resident0))
                .map(map -> map.getKey())
                .collect(Collectors.toList());

        for(Hospital hospital : selectedHospitals){
            System.out.println(hospital.toString());
        }



    }
}
