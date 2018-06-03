package structures;

public class Test {

    public static void main(String[] args) {
	DictTree tree = new DictTree();
	
	System.out.println("READING... ");
	tree.add("cookie", "brown sugar fruit");
	tree.add("apple", "red fruit");
	tree.add("duck", "yellow animal");
	
	tree.save();

	tree.add("mango", "bangin fruit");
	
	tree.save();

    }

}
