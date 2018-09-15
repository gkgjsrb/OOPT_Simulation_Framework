package Model;

import com.horstmann.violet.product.diagram.abstracts.Id;

public class SystemOperation {
	Id Id;
	String Name;
	String Responsibility;
	String Type;
	String Cross;
	String Notes;
	String Exception;
	String Output;
	String Preconditions;
	String Postconditions;
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
