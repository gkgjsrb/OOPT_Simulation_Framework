package Model;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class UIDesign {
	
	private ArrayList<Button> buttonList;
	private ArrayList<JTextField> textList;
	private ArrayList<JLabel> labelList;
	
	public UIDesign() {
		buttonList = new ArrayList<>();
		textList = new ArrayList<>();
		labelList = new ArrayList<>();
	}

	public ArrayList<Button> getButtonList() {
		return buttonList;
	}

	public void setButtonList(ArrayList<Button> buttonList) {
		this.buttonList = buttonList;
	}

	public ArrayList<JTextField> getTextList() {
		return textList;
	}

	public void setTextList(ArrayList<JTextField> textList) {
		this.textList = textList;
	}

	public ArrayList<JLabel> getLabelList() {
		return labelList;
	}

	public void setLabelList(ArrayList<JLabel> labelList) {
		this.labelList = labelList;
	}
	
	
}
