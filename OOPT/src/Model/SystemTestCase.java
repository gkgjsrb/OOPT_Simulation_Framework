package Model;

public class SystemTestCase extends TestArtifact{
	
	private String usecase;
	private String systemFunction;

	public SystemTestCase() {
		
	}

	public String getUsecase() {
		return usecase;
	}

	public void setUsecase(String usecase) {
		this.usecase = usecase;
	}

	public String getSystemFunction() {
		return systemFunction;
	}

	public void setSystemFunction(String systemFunction) {
		this.systemFunction = systemFunction;
	}
	
	
}
