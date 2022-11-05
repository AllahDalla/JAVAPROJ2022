package P1;

import java.util.ArrayList;

class Test {
    public static void main(String[] args) {
        Station portmore = new Station("Portmore", RSStatus.OPEN);
        Station newKingston = new Station("New Kingston", RSStatus.OPEN);
        Station downtown = new Station("Down Town", RSStatus.OPEN);

        ArrayList<Station> stationsR1 = new ArrayList<Station>();
        ArrayList<Station> stationsR2 = new ArrayList<Station>();

        stationsR1.add(portmore);
        stationsR1.add(newKingston);

        stationsR2.add(newKingston);
        stationsR2.add(downtown);

        Segment hagleyPark = new Segment("Hagley Park", RSStatus.OPEN, "Portmore", "New Kingston");
        Segment crossRoads = new Segment("Crossroads", RSStatus.OPEN, "New Kingston", "Down Town");

        Route highway = new Route("HighWay 2000", false, RSStatus.OPEN, stationsR1);
        Route halfwaytreeRd = new Route("Half-Way Tree Road", false, RSStatus.OPEN, stationsR2);

        Train t1 = new Train(0, 1, 1, portmore, highway);
        Train t2 = new Train(1, 2, 2, newKingston, halfwaytreeRd);

        TrainSystem ts = new TrainSystem(SystemStatus.OPERATIONAL);

        ts.addStation("Portmore");
        ts.addStation("New Kingston");
        ts.addStation("Down Town");

        // System.out.println(ts.getStationInfo("Portmore"));
        // System.out.println(ts.getStationInfo("New Kingston"));
        // System.out.println(ts.getStationInfo("Down Town"));

        ts.addRoute("HighWay 2000", false, stationsR1);
        ts.addRoute("Half-Way Tree Road", false, stationsR2);

        // System.out.println(ts.getRouteInfo("HighWay 2000"));

        ts.addSegment("Hagley Park", "Portmore", "New kingston");
        ts.addSegment("Crossroads", "New kingston", "Down Town");

        // System.out.println(ts.getSegmentInfo("Hagley Park"));
        // System.out.println(ts.getSegmentInfo("Crossroads"));

        ts.addTrain(t1);
        ts.addTrain(t2);

        // System.out.println(ts.getTrainInfo(0));
        // System.out.println(ts.getTrainInfo(1));

        // System.out.println(ts.containsRoute("HighWay 2000"));
        // System.out.println(ts.getRouteInfo("HighWay 2000"));

        // System.out.println(ts.verify());

    }
}