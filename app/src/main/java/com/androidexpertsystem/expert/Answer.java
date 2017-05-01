package com.androidexpertsystem.expert;

public class Answer {
	
	private SingleValue value;
	
	public boolean evaluateAnswerbyInput(String input) {
        for (int i = 0; i < value.getTrueValue().length ; i++) {
            if(value.getTrueValue()[i].equals(input))
            {
                return true;
            }
        }
        	return false;
	}

	public void addValue(Value value) {
		this.value = (SingleValue) value;
	}
	public String [] getInputPattern(){
		return value.getInputPattern();
	}
	
}
