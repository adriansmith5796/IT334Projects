package boundedtypes;

public class GardenTest {
    public static void main(String[] args) {
	Plant plant = new Plant("green");
	Flower flower = new Flower("yellow");
	
	Garden<Plant> gardenPlant = new Garden<>(plant);
	System.out.println(gardenPlant.getT().getColor());
	
	Garden<Plant> gardenFlower = new Garden<>(flower);
	System.out.println(gardenFlower.getT());
    }
}
