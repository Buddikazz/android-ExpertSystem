package com.androidexpertsystem.expert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleValue extends Value
{
	String param;
	String[] inputPattern;
	private String trueValue;
	private String falseValue;

	public MultipleValue(String param) {
		this.trueValue = param;
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
