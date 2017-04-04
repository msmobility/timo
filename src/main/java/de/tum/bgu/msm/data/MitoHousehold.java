package de.tum.bgu.msm.data;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds households objects for the Microsimulation Transport Orchestrator (MITO)
 * @author Rolf Moeckel
 * Created on Sep 21, 2016 in Munich, Germany
 *
 */

public class MitoHousehold implements Serializable {
    static Logger logger = Logger.getLogger(MitoHousehold.class);

    private int hhId;
    private int hhSize;
    private int females;
    private int children;
    private int youngAdults;
    private int retirees;
    private int numberOfWorkers;
    private int students;
    private int licenseHolders;
    private int income;
    private int autos;
    private int homeZone;
    private ArrayList<MitoTrip> trips;
    private static final Map<Integer,MitoHousehold> householdMap = new HashMap<>();


    public MitoHousehold(int id, int hhSize, int females, int children, int youngAdults, int retirees,
                         int numberOfWorkers, int students, int licenseHolders, int income, int autos, int homeZone) {
        // create new MitoHousehold
        this.hhId = id;
        this.hhSize = hhSize;
        this.females = females;
        this.children = children;
        this.youngAdults = youngAdults;
        this.retirees = retirees;
        this.numberOfWorkers = numberOfWorkers;
        this.students = students;
        this.licenseHolders = licenseHolders;
        this.income = income;
        this.autos = autos;
        this.homeZone = homeZone;
        this.trips = new ArrayList<>();
        householdMap.put(id, this);
    }


    public static MitoHousehold getHouseholdFromId (int id) {
        return householdMap.get(id);
    }

    public static MitoHousehold[] getHouseholdArray() {
        return householdMap.values().toArray(new MitoHousehold[householdMap.size()]);
    }


    public int getHhId() {
        return hhId;
    }

    public int getHhSize() {
        return hhSize;
    }

    public void setFemales(int females) {
        this.females = females;
    }

    public int getFemales() {
        return females;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getYoungAdults() {
        return youngAdults;
    }

    public void setYoungAdults(int youngAdults) {
        this.youngAdults = youngAdults;
    }

    public int getRetirees() {
        return retirees;
    }

    public void setRetirees(int retirees) {
        this.retirees = retirees;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(int workers) {
        numberOfWorkers = workers;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getLicenseHolders() {
        return licenseHolders;
    }

    public void setLicenseHolders(int licenseHolders) {
        this.licenseHolders = licenseHolders;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int inc) {
        income = inc;
    }

    public int getAutos() {
        return autos;
    }

    public int getHomeZone() {
        return homeZone;
    }

    public void addTrip(MitoTrip trip) {
        trips.add(trip);
    }

    public MitoTrip[] getTrips () {
        MitoTrip[] tripArray = new MitoTrip[trips.size()];
        for (int i = 0; i < trips.size(); i++) tripArray[i] = trips.get(i);
        return tripArray;
    }
}