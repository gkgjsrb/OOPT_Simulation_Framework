package Model;

public abstract class TextualRequirement extends DevelopmentArtifact{
	private String Type;
	private String Cross;
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
	
}
