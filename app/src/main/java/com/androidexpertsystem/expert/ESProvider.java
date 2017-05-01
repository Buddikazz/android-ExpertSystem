package com.androidexpertsystem.expert;

import com.androidexpertsystem.utils.UpdateUi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ESProvider {
    FactParser factParser;
    RuleParser ruleParser;
    FactRepository factRepo;
    RuleRepository ruleRepo;
    private UpdateUi ui;
    private HashMap<String, Boolean> resultMap = new HashMap<>();
    private boolean toAdd;


    public ESProvider(UpdateUi ui, FactParser factParser, RuleParser ruleParser) {
        this.ui = ui;
        this.factParser = factParser;
        this.ruleParser = ruleParser;
        factRepo = this.factParser.getFactRepository();
        ruleRepo = this.ruleParser.getRuleRepository();
    }

    public void collectAnswers(String ID, Question question, String answer) {
        if (Arrays.asList(question.getInputPattern()).contains(answer)) {
            boolean evalValue = question.getEvaluatedAnswer(answer);
            resultMap.put(ID, evalValue);
        }
    }

    public String getQuestion(String key) {
        return ruleRepo.getRuleMap().get(key).getQuestion();
    }


    public boolean getAnswerByQuestion(String questionID) {
        return false;

    }

    public void printLogo() {
        String[] logo = new String[]{"Expert system ™ \n\n"};
        for (String line : logo) {
            System.out.println(line);
        }
    }


    public ArrayList<Fact> preEvaluate() {
        ArrayList<Fact> facts = new ArrayList<>();
        for (Fact fact : factRepo.getFactsList()) {
            for (String key : resultMap.keySet()) {
                toAdd = fact.getFact().get(key).booleanValue() == resultMap.get(key).booleanValue();
                if (!toAdd) {
                    break;
                }
            }
            if (toAdd && !facts.contains(fact)) {
                facts.add(fact);
            }
        }
        return facts;

    }

    public String evaluate() {
        for (Fact fact : factRepo.getFactsList()) {
            if (fact.getFact().equals(resultMap)) {
                System.out.println("");
                return fact.getDescription();
            }
        }
        return null;

    }

    public FactRepository getFactRepo() {
        return factRepo;
    }

    public RuleRepository getRuleRepo() {
        return ruleRepo;
    }

    public HashMap<String, Boolean> getResultMap() {
        return resultMap;
    }
}
