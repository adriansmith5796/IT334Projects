package pairProgramming;

import java.util.ArrayList;
import java.util.Random;

public class HerdTest {
	
	public static final Random RAND = new Random();
	public static final String[] COLORS = 
		{ "black", "roan", "gray", "white", "chestnut", "buckskin", "pinto", "bay", "dun" };
	
	public static void main(String[] args) {
		
		// create a leader
		Horse leader = createHorse();
		Herd<Horse> herdOfHorses = new Herd<>(leader);
		
		// create the herd
		int swapTime = RAND.nextInt(3) + 3;
		for (int i = 0; i < 20; i++) {
			Horse horse = createHorse();
			herdOfHorses.add(horse);
			if (i % swapTime == 0) {
				System.out.println("Current leader: " + herdOfHorses.getLeader());
				System.out.println("Changing with " + horse);
				System.out.println("New leader: " + herdOfHorses.getLeader() + "\n");
			}
		}
		
		ArrayList<Elephant> elephants = new ArrayList<Elephant>();
		for (int i = 0; i < elephants.size(); i++) {
			elephants.add(i, createElephant());
		}
		
		Elephant bull = new Elephant(25, 12500);
		Herd<Elephant> herdOfElephants = new Herd<>(bull, elephants);
		System.out.println(herdOfElephants.getLeader());
		System.out.println("Elephant herd size: " + herdOfElephants.getHerdSize());
				
	}
	
	public static Horse createHorse() {
		String color = COLORS[RAND.nextInt(COLORS.length)];
		int age = RAND.nextInt(20);
		return new Horse(color, age);
	}
	
	public static Elephant createElephant() {
		int age = RAND.nextInt(70);
		int weight = (RAND.nextInt(10) + 5) * 100;
		return new Elephant(age, weight);
	}

}

class Horse {
	
	String color;
	int age;
	
	public Horse(String color, int age) {
		this.color = color;
		this.age = age;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getAge() {
		return age;
	}
	
	public String toString() {
		return "Horse -- age: " + age + ", coat color: " + color;
	}

}

class Elephant {
	
	int age;
	int weight;
	
	public Elephant(int age, int weight) {
		this.age = age;
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getAge() {
		return age;
	}
	
	public String toString() {
		return "Elephant -- age: " + age + ", weight: " + weight;
	}


}

