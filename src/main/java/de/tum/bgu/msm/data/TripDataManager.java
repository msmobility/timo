package de.tum.bgu.msm.data;

import org.apache.log4j.Logger;

/**
 * Manages trip objects for the Microsimulation Transport Orchestrator (MITO)
 * @author Rolf Moeckel
 * Created on Mar 26, 2017 in Munich, Germany
 *
 */

public class TripDataManager {
    static Logger logger = Logger.getLogger(TripDataManager.class);
    private static int highestTripIdInUse;


    public TripDataManager() {
        highestTripIdInUse = 0;
    }


    public void setHighestTripIdInUse () {
        // identify highest household ID and highest person ID in use
        highestTripIdInUse = 0;
        for (MitoTrip trip: MitoTrip.getTripArray()) highestTripIdInUse = Math.max(highestTripIdInUse, trip.getTripId());
    }


    public static int getNextTripId () {
        // increase highestTripIdInUse by 1 and return value
        highestTripIdInUse++;
        return highestTripIdInUse;
    }


    public int getTotalNumberOfTripsGeneratedByPurpose (int purpose) {
        // sum up trips generated by purpose

        int prodSum = 0;
        for (MitoTrip trip: MitoTrip.getTripArray()) {
            try {
                if (trip.getTripPurpose() == purpose) prodSum++;
            } catch (Exception e) {
                System.out.println("Purpose "+purpose);
                System.out.println("Trips   "+MitoTrip.getTripCount());
                System.out.println(trip.getTripId());
                System.out.println(trip.getTripOrigin());
                System.out.println(trip.getTripPurpose());
                System.out.println(e);
            }
        }
        return prodSum;
    }


    public int getTotalNumberOfTrips() {
        // sum up total number of trips generated
        return MitoTrip.getTripCount();
    }

}
