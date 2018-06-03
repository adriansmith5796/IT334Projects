import hashmap.MyHashMap;

public class TestHashMap {

    public static void main(String[] args) {
        Flight[] flights = new Flight[8];
        flights[0] = new Flight(123, "DTW", "SEA");
        flights[1] = new Flight(456, "SEA", "DTW");
        flights[2] = new Flight(789, "FLG", "SEA");
        flights[3] = new Flight(101, "SEA", "FLG");
        flights[4] = new Flight(112, "LAX", "SEA");
        flights[5] = new Flight(131, "SEA", "LAX");
        flights[6] = new Flight(415, "SFO", "SEA");
        flights[7] = new Flight(161, "SEA", "SFO");

        MyHashMap<Integer, Flight> table = new MyHashMap<>();

        for(int i = 0; i < 4; i++){
            System.out.println("Adding " + flights[i]);
            table.add(flights[i].flightNo, flights[i]);
        }
        System.out.println();

        for(Flight flight: flights){
            System.out.println(table.find(flight.flightNo));
        }

        System.out.println();
        System.out.println("Size: " + table.size());
    }

    // TODO: add static
    private static class Flight {
        private int flightNo;
        private String destination;
        private String origination;

        public Flight(int flightNo, String dest, String orig) {
            this.flightNo = flightNo;
            this.destination = dest;
            this.origination = orig;
        }

        public String toString() {
            return "Flight #" + flightNo + " to " + destination + " from " + origination;
        }
    }
}