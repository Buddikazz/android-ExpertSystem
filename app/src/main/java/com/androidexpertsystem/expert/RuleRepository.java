package com.androidexpertsystem.expert;

import java.util.HashMap;
import java.util.Map;

public class RuleRepository{
	
	Map<String, Question> ruleMap = new HashMap<String, Question>();
	
	public Question addQuestion(String id, Question question)
	{
		ruleMap.put(id, question);
		return null;
		
	}
	
	public Iterator getIterator()
	{
		return new QuestionIterator();
	}
	
	public class QuestionIterator implements Iterator {

		int index;
		
		@Override
		public boolean hasNext() {
			if (index < ruleMap.size()){
				return true;
			}
			return false;

		}

		@Override
		public Question next() {
	         if(this.hasNext()){
	             Question question = ruleMap.values().toArray(new Question[ruleMap.values().size()])[index++];
	             return question;
	          }return null;
	     
		}
		
		
	}

	public Map<String, Question> getRuleMap()
	{
		return ruleMap;
	}


}
