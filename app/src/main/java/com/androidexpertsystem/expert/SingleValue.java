package com.androidexpertsystem.expert;

public class SingleValue extends Value {
	
	private String trueValue;
	private String falseValue;

	public SingleValue(String param) {
		trueValue = param;
	}

	@Override
	public String [] getTrueValue(){
		 String [] value = new String [1];
		 value[0] = trueValue;
		 return value;
	}
	@Override
	public String [] getFalseValue(){
		 String [] value = new String [1];
		 value[0] = falseValue;
		 return value;
	}
	public void setFalseValue(String param){
		falseValue = param;
	}

	@Override
	public String[] getInputPattern() {
		String [] pattern = new String [2];
		pattern[0] = trueValue;
		pattern[1] = falseValue;

		return pattern;


	}
}
