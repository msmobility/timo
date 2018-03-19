package de.tum.bgu.msm.io.input.readers;

import cern.colt.matrix.tdouble.DoubleMatrix2D;
import de.tum.bgu.msm.data.DataSet;
import de.tum.bgu.msm.data.travelDistances.MatrixTravelDistances;
import de.tum.bgu.msm.data.travelTimes.SkimTravelTimes;
import de.tum.bgu.msm.io.input.AbstractOmxReader;
import de.tum.bgu.msm.resources.Properties;
import de.tum.bgu.msm.resources.Resources;
import org.apache.log4j.Logger;

public class SkimsReader extends AbstractOmxReader {

    private static final Logger logger = Logger.getLogger(SkimsReader.class);

    public SkimsReader(DataSet dataSet) {
        super(dataSet);
    }

    @Override
    public void read() {
        logger.info("  Reading skims");
        readHighwaySkims();
        readTransitSkims();
        readBusSkimForModeChoice();
        readTrainSkimForModeChoice();
        readTramMetroSkimForModeChoice();
        readTravelDistanceForModeChoice();
    }

    private void readHighwaySkims() {
        DoubleMatrix2D timeSkimAutos = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.AUTO_PEAK_SKIM), "timeByTime");
        dataSet.addTravelTimeForMode("car", new SkimTravelTimes(timeSkimAutos));
    }

    private void readTransitSkims() {
        DoubleMatrix2D timeSkimTransit = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.TRANSIT_PEAK_SKIM), "CheapJrnyTime");
        dataSet.addTravelTimeForMode("pt", new SkimTravelTimes(timeSkimTransit));
    }

    private void readBusSkimForModeChoice(){
        DoubleMatrix2D timeSkimBus = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.BUS_TRAVEL_TIME_SKIM),"mat1");
        dataSet.addTravelTimeForMode("bus", new SkimTravelTimes(timeSkimBus));
    }

    private void readTramMetroSkimForModeChoice(){
        DoubleMatrix2D timeSkimTramMetro = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.TRAM_METRO_TRAVEL_TIME_SKIM),"mat1");
        dataSet.addTravelTimeForMode("tramMetro", new SkimTravelTimes(timeSkimTramMetro));
    }

    private void readTrainSkimForModeChoice(){
        DoubleMatrix2D timeSkimTrain = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.TRAIN_TRAVEL_TIME_SKIM),"mat1");
        dataSet.addTravelTimeForMode("train", new SkimTravelTimes(timeSkimTrain));
    }

    private void readTravelDistanceForModeChoice(){
        DoubleMatrix2D distanceSkimAuto = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.AUTO_TRAVEL_DISTANCE_SKIM),"distanceByTime");
        dataSet.setTravelDistancesAuto(new MatrixTravelDistances(distanceSkimAuto));
        DoubleMatrix2D distanceSkimNMT = super.readAndConvertToDoubleMatrix(Resources.INSTANCE.getString(Properties.NMT_TRAVEL_DISTANCE_SKIM),"distanceByDistance");
        dataSet.setTravelDistancesNMT(new MatrixTravelDistances(distanceSkimNMT));
    }
}
