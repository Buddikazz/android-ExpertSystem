package com.androidexpertsystem.expert;

import java.util.ArrayList;

public class FactRepository{
	
	ArrayList<Fact> factsList = new ArrayList<>();
	
	public void addFact(Fact fact){
		factsList.add(fact);
	}
	
	public Iterator getIterator()
	{
		return new FactIterator();
		
	}
	public class FactIterator implements Iterator {
		
		int index;
		
		@Override
		public boolean hasNext() {
			if (index < factsList.size()){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
	         if(this.hasNext()){
	             return factsList.get(index++);
	          }
	          return null;
		}
		
	}
	public ArrayList<Fact> getFactsList()
	{
		return factsList;
	}
}
