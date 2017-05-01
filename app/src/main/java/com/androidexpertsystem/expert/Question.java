package com.androidexpertsystem.expert;

public class Question {
	
	private Answer answerEval;
	private String question;

	public Question(String question) {
		this.question = question;

	}
	
	public String getQuestion() {
		return question;
	}

    public String[] getInputPattern()
    {
        return answerEval.getInputPattern();
    }
	
	public void setAnswerEvaluator(Answer answer) {
		 this.answerEval = answer;
	}

	public boolean getEvaluatedAnswer(String input) {
        Boolean bool = answerEval.evaluateAnswerbyInput(input);
        return bool;
	}

	public Answer getAnswerEval() {
		return answerEval;
	}
}
