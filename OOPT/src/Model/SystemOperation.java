package Model;

import com.horstmann.violet.product.diagram.abstracts.Id;

public class SystemOperation extends TextualRequirement{
	private Id Id;
	private String Responsibility;
	private String Notes;
	private String Exception;
	private String Output;
	private String Preconditions;
	private String Postconditions;
	private String op_name;
	
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	
	public Id getId() {
		return Id;
	}
	public void setId(Id id) {
		Id = id;
	}
	public String getResponsibility() {
		return Responsibility;
	}
	public void setResponsibility(String responsibility) {
		Responsibility = responsibility;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	public String getException() {
		return Exception;
	}
	public void setException(String exception) {
		Exception = exception;
	}
	public String getOutput() {
		return Output;
	}
	public void setOutput(String output) {
		Output = output;
	}
	public String getPreconditions() {
		return Preconditions;
	}
	public void setPreconditions(String preconditions) {
		Preconditions = preconditions;
	}
	public String getPostconditions() {
		return Postconditions;
	}
	public void setPostconditions(String postconditions) {
		Postconditions = postconditions;
	}
	
}
