package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.CheckboxQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Question;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class QAModelFactory {
	private static final String OPTION = "options";
	private static final String ITEM = "item";
	private static final String VALUE = "value";

	public static QuestionModel createQuestionModel(Question question) {
		QuestionType type = question.getType();
		if (type == QuestionType.CHECKBOXES)
			return createCheckBoxQuestion(question);
		else
			return createFillingQuestion(question);
	}

	private static Document createDocument(String xml){
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			is.setEncoding("UTF-8");
			return builder.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static CheckboxQuestion createCheckBoxQuestion(Question question){
			CheckboxQuestion checkboxQuestion = new CheckboxQuestion(question.getId(), question.getQuestion(), QuestionType.CHECKBOXES);
			Document document = createDocument(question.getOptions());
			NodeList nodeList = document.getElementsByTagName(OPTION);
			for (int i = 0 ; i < nodeList.getLength() ; i ++)
			{
				Element element = (Element) nodeList.item(i);
				String optionName = element.getTextContent();
				checkboxQuestion.addOption(new CheckboxQuestion.Option(optionName, false));
			}
			return checkboxQuestion;

	}

	private static FillingQuestion createFillingQuestion(Question question){
		FillingQuestion fillingQuestion = new FillingQuestion(question.getId(), question.getQuestion(), QuestionType.FILLING);
		Document document = createDocument(question.getOptions());
		Element itemElement = ((Element) document.getElementsByTagName(ITEM).item(0));
		fillingQuestion.setHint(itemElement.getTextContent());
		return fillingQuestion;
	}

	public static Answer createAnswerFeedback(String deviceUID, CheckboxQuestion question) {
		Document document = newDocument();
		Element optionElm = document.createElement(OPTION);
		for(CheckboxQuestion.Option option : question)
		{
			Element itemElm = document.createElement(ITEM);
			itemElm.setAttribute(VALUE, String.valueOf(option.getValue()));
			itemElm.setTextContent(option.getOptionName());
			optionElm.appendChild(itemElm);
		}
		document.getDocumentElement().appendChild(optionElm);
		return new Answer(question.getQuestionId(), domToString(document), deviceUID);
	}

	public static Answer createAnswerFeedback(String deviceUID, FillingQuestion question) {
		Document document = newDocument();
		Element optionElm = document.createElement(OPTION);
		Element itemElm = document.createElement(ITEM);
		itemElm.setTextContent(question.getHint());
		itemElm.setAttribute(VALUE, question.getAnswer());
		optionElm.appendChild(itemElm);
		document.getDocumentElement().appendChild(optionElm);
		return new Answer(question.getQuestionId(), domToString(document), deviceUID);
	}

	private static Document newDocument(){
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return builder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static String domToString(Document document){
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			return writer.toString();
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
