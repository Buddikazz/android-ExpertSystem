package com.androidexpertsystem.expert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class RuleParser extends XmlParser {
	
	private NodeList nodeList;

	private String file;

	public RuleParser(String file) {
		this.file = file;
	}

	@Override
	public void LoadXmlDocument(String fullpath) {
		 try {

			 	FileInputStream file = new FileInputStream(new File(fullpath));
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);

				doc.getDocumentElement().normalize();
				
				nodeList = doc.getElementsByTagName("Rule");
				
			    } catch (Exception e) {
			    	System.out.println("Error occured during initialization");
			    }
	}
    public RuleRepository getRuleRepository()
    {

        LoadXmlDocument(file);
        RuleRepository repository = new RuleRepository();
        for(int i = 0; i<nodeList.getLength(); i++)
        {
        	Node node = nodeList.item(i);


            String id = ((Element)node).getAttribute("id");
            String questionMsg = ((Element) node).getElementsByTagName("Question").item(0).getTextContent();
            Question question = new Question(questionMsg);
            Value value = getValue(node);
            Answer answer = new Answer();
            answer.addValue(value);
            question.setAnswerEvaluator(answer);
            repository.addQuestion(id, question);
        }
        return repository;
    }
    public Value getValue(Node node)
    {
		Element value = (Element) node;
		Node answernode = value.getElementsByTagName("Answer").item(0);
		Element answer = (Element) answernode;
		NodeList selectionNode = answer.getElementsByTagName("Selection");

		Node trueValue = selectionNode.item(0);
		Node falseValue = selectionNode.item(1);
		
		String trueV = ((Element)((Element) trueValue).getElementsByTagName("SingleValue").item(0)).getAttribute("value");
		String falseV = ((Element)((Element) falseValue).getElementsByTagName("SingleValue").item(0)).getAttribute("value");
		
		Value returnValue = new SingleValue(trueV);
		((SingleValue)returnValue).setFalseValue(falseV);
		
    	return returnValue;
    }
    
}
