package Model;

import com.horstmann.violet.product.diagram.abstracts.Id;

public class SystemOperation {
	private Id Id;
	private String Name;
	private String Responsibility;
	private String Type;
	private String Cross;
	private String Notes;
	private String Exception;
	private String Output;
	private String Preconditions;
	private String Postconditions;
	
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	String op_name;
	public Id getId() {
		return Id;
	}
	public void setId(Id id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getResponsibility() {
		return Responsibility;
	}
	public void setResponsibility(String responsibility) {
		Responsibility = responsibility;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getCross() {
		return Cross;
	}
	public void setCross(String cross) {
		Cross = cross;
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
