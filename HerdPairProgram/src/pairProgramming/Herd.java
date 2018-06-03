/*
 * Adrian Smith
 * Cynthia Pham
 * 
 * IT 333 - Herd Parameterized Type Pair Program
 * 
 * Generic class Herd that uses an internal ArrayList
 * structure to hold members of a herd
 */

package pairProgramming;

import java.util.ArrayList;

public class Herd<T> {
	
	private ArrayList<T> herd;
	
	public Herd(T leader) {
		if(leader == null)
		{
			try {
				throw new NullLeaderException("The herd must have a leader!");
			} catch (NullLeaderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		herd = new ArrayList<T>();
		herd.add(leader);
	}
	
	public Herd(T leader, ArrayList<T> herd) {
		this.herd = new ArrayList<T>();
		this.herd.add(leader);
		this.herd.addAll(herd);
	}
	
	public boolean changeLeader(T leader, boolean remains) 
	{
		if (remains == true) 
		{
			herd.add(0, leader);
		}else{
			herd.remove(0);
			herd.add(0, leader);
		}
		
		if(leader != herd.get(0)){
			return true;
		}
		
		return false;
	}
	
	public T getLeader(){
		return herd.get(0);
	}

	public int getHerdSize(){
		return herd.size();
	}
	
	public boolean isInHerd(T member){
		if(herd.contains(member)){
			return true;
		}
		
		return false;
	}
	
	public boolean isLeader(T member){
		if(herd.get(0) == member){
			return true;
		}
		
		return false;
	}
	
	public boolean add(T member){
		if(herd.contains(member)){
			return false;
		}
		
		herd.add(member);
		return true;
	}
	
	public boolean remove(T member){
		if(herd.get(0) == member) {
			throw new NullLeaderException("Cannot remove the leader!");
		}
		
		return herd.remove(member);
	}
}
