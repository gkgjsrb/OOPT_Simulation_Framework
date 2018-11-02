package Model;

import com.horstmann.violet.product.diagram.abstracts.Id;

public class MethodDescription extends TextualRequirement{
	
	private String Purpose;
	private String Overview;
	private String Input;
	private String Output;
	private String Abstract;
	public MethodDescription() {
		
	}
	
	public String getPurpose() {
		return Purpose;
	}
	public void setPurpose(String purpose) {
		Purpose = purpose;
	}
	public String getOverview() {
		return Overview;
	}
	public void setOverview(String overview) {
		Overview = overview;
	}
	public String getInput() {
		return Input;
	}
	public void setInput(String input) {
		Input = input;
	}
	public String getOutput() {
		return Output;
	}
	public void setOutput(String output) {
		Output = output;
	}
	public String getAbstract() {
		return Abstract;
	}
	public void setAbstract(String abstract1) {
		Abstract = abstract1;
	}
	public String getException() {
		return Exception;
	}
	public void setException(String exception) {
		Exception = exception;
	}
	private String Exception;
}
