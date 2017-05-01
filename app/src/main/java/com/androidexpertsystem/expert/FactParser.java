package com.androidexpertsystem.expert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FactParser extends XmlParser
{
	private NodeList nodeList;

	private String file;

	public FactParser(String file) {
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
				
				nodeList = doc.getElementsByTagName("Fact");
				
			    } catch (Exception e) {
			    	System.out.println("Error occured during initialization");
			    }
	}
    public FactRepository getFactRepository()
    {
        String description = "";
        LoadXmlDocument(file);
        FactRepository repository = new FactRepository();
        for(int i = 0; i<nodeList.getLength(); i++)
        {
        	Node node = nodeList.item(i);

            Node tempNode = ((Element) node).getElementsByTagName("Description").item(0);
            description = ((Element)tempNode).getAttribute("value");

            String factId = ((Element)node).getAttribute("id");

            Fact fact = new Fact(description);
            fact.setId(factId);
            
            getEvals(node, fact);

            repository.addFact(fact);
        }
        	return repository;
    }
    
    private void getEvals(Node node, Fact fact)
    {
    	Node evalsNode = ((Element) node).getElementsByTagName("Evals").item(0);
    	NodeList evals = ((Element) evalsNode).getElementsByTagName("Eval");
    	String ID;
    	boolean value;
    	for(int i = 0; i < evals.getLength(); i++)
    	{
    		Node eval = evals.item(i);
    		value = Boolean.parseBoolean(eval.getTextContent().trim());
    		ID = ((Element) eval).getAttribute("id");
    		fact.setFactValueByID(ID, value);
    	}
    }
}
