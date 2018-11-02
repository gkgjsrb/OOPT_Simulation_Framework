package Model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.sqlite.SQLiteConfig;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.persistence.IFilePersistenceService;
import com.horstmann.violet.framework.file.persistence.IFileReader;
import com.horstmann.violet.framework.file.persistence.IFileWriter;
import com.horstmann.violet.framework.file.persistence.JFileReader;
import com.horstmann.violet.framework.file.persistence.JFileWriter;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.product.diagram.abstracts.Id;

public class Datainfo {
	
	@InjectedBean
    private IFilePersistenceService filePersistenceService;
	
	private Connection connection;
	private Connection connection2;
	private PreparedStatement statement;
	//private ResultSet result;
	private File graphfile;
	
	public Datainfo() {
		
        BeanInjector.getInjector().inject(this);

	}
	
	
	static {
		try{
			Class.forName("org.sqlite.JDBC");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean open() {
		try{
			this.connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
			SQLiteConfig config = new SQLiteConfig();
			config.setReadOnly(true);
			this.connection2 = DriverManager.getConnection("jdbc:sqlite:db.sqlite", config.toProperties());
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void setText(int index, String text) {
		try {
			String sql = "insert or replace into TextTable values(?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, text);
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setConcept(int index, String text) {
		try {
			String sql = "insert or replace into concept values(?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, text);
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncConcept(int size) {
		try {
			String sql = "delete from concept where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, size);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setRisk(int index, Risk risk) {
		try {
			String sql = "insert or replace into Risk values(?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, risk.getName());
			statement.setInt(3, risk.getProbability());
			statement.setInt(4, risk.getSignificance());
			statement.setInt(5, risk.getWeight());
			statement.setString(6, risk.getPlan());
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void syncRisk(int size) {
		try {
			String sql = "delete from Risk where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, size);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setReq(int index, Requirement req) {
		try {
			String sql = "insert or replace into Requirement values(?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, req.getRef(index));
			statement.setString(3, req.getName(index));
			statement.setString(4, req.getCategory(index));
			statement.setString(5, req.getuCategory(index));
			statement.setInt(6, req.getuNumber(index));
			statement.setString(7, req.getuName(index));
			statement.setString(8, req.getRank(index));
			statement.setString(9, req.getTestcase(index));
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncReq(int size) {
		try {
			String sql = "delete from Requirement where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, size);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setTerm(int index, Glossary gl) {
		try {
			String sql = "insert or replace into Glossary values(?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			if(gl.getType().equals("D")) {
				statement.setInt(1, index);
				statement.setString(2, gl.getTerm());
				statement.setString(3, gl.getDescription());
				statement.setString(4, gl.getRemarks());
				statement.setString(5, gl.getCategory());
				statement.setString(6, gl.getType());
			}
			else {
				statement.setInt(1, index+100);
				statement.setString(2, gl.getTerm());
				statement.setString(3, gl.getDescription());
				statement.setString(4, gl.getRemarks());
				statement.setString(5, gl.getCategory());
				statement.setString(6, gl.getType());
			}
			
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncTerm(String type, int size) {
		try {
			String sql = "delete from Glossary where type = ? and id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, type);
			if(type.equals("D")) {
				statement.setInt(2, size);
			}
			else {
				statement.setInt(2, size+100);
			}
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setBasedUsecase(int index, String type, String text) {
		try {
			String sql;
			if(type.equals("A")) {
				sql = "insert or replace into Ausecase values(?,?)";
			}
			else {
				sql = "insert or replace into Eusecase values(?,?)";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, text);
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncBUsecase(String type, int size) {
		try {
			String sql;
			if(type.equals("A")) {
				sql = "delete from Ausecase where id = ?";
			}
			else {
				sql = "delete from Eusecase where id = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, size);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setNonReq(int index, NonFuncReq req) {
		try {
			String sql = "insert or replace into NonFuncReq values(?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, req.getCategory());
			statement.setString(3, req.getTestcase());
			statement.setString(4, req.getType());
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncNonReq(String type) {
		try {
			String sql = "delete from NonFuncReq where type = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, type);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void setSystemTestCase(int index, SystemTestCase req) {
		try {
			String sql = "insert or replace into FuncReq values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, req.getNumber());
			statement.setString(3, req.getName());
			statement.setString(4, req.getDescription());
			statement.setString(5, req.getUsecase());
			statement.setString(6, req.getSystemFunction());
			statement.setString(7, req.getResult());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncSystemTestCase() {
		try {
			String sql = "delete from FuncReq";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void setTestCase(String type, UnitTestCase req) {
		try {
			String sql = "insert or replace into TestCase values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, type);
			statement.setString(2, req.getNumber());
			statement.setString(3, req.getName());
			statement.setString(4, req.getDescription());
			statement.setString(5, req.getInput());
			statement.setString(6, req.getOutput());
			statement.setString(7, req.getResult());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setTestCase(String type, IntegrationTestCase req) {
		try {
			String sql = "insert or replace into TestCase values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, type);
			statement.setString(2, req.getNumber());
			statement.setString(3, null);
			statement.setString(4, req.getDescription());
			statement.setString(5, req.getInput());
			statement.setString(6, req.getOutput());
			statement.setString(7, req.getResult());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setTestCase(String type, TestCase req) {
		try {
			String sql = "insert or replace into TestCase values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, type);
			statement.setString(2, req.getNumber());
			statement.setString(3, req.getName());
			statement.setString(4, req.getDescription());
			statement.setString(5, null);
			statement.setString(6, null);
			statement.setString(7, req.getResult());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncTestCase(String type) {
		try {
			String sql = "delete from TestCase where type = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, type);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void setGraph(String type, UMLDiagram g) {
		try {
			String sql = "insert or replace into Graph values(?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, type);
			statement.setString(2, g.getName());
			statement.setString(3, g.getId());
			statement.executeUpdate();
			
			String filename = type + "." + g.getName() + "." + "html";
			graphfile = new File(filename);
			IFileWriter j = new JFileWriter(graphfile);
			OutputStream out = j.getOutputStream();
			
			
			filePersistenceService.write(g.getGraph().getGraph(), out);
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	public void syncGraph(String type, String id) {
		try {
			String sql;
			if(id.equals("")) {
				sql = "delete from Graph where type = ?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, type);
				statement.executeUpdate();
			}
			
			else {
				sql = "delete from Graph where type = ? and id = ?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, type);
				statement.setString(2, id);
				statement.executeUpdate();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setUsecase(int index, UseCase uc) {
		try {			
			String sql = "insert or replace into UseCase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, uc.getId().toString());
			statement.setString(3, uc.getName());
			statement.setString(4, uc.getActor());
			statement.setString(5, uc.getDes());
			statement.setString(6, uc.getPurpose());
			statement.setString(7, uc.getOverview());
			statement.setString(8, uc.getType());
			statement.setString(9, uc.getCross());
			statement.setString(10, uc.getPreRequistes());
			statement.setString(11, uc.getTypical());
			statement.setString(12, uc.getAlternative());
			statement.setString(13, uc.getExceptional());
			statement.setString(14, uc.getUI());
			
			statement.executeUpdate();
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncUsecase() {
		try {
			String sql = "delete from UseCase";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setRealUsecase(int index, UseCase uc) {
		try {			
			String sql = "insert or replace into RealUseCase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, uc.getId().toString());
			statement.setString(3, uc.getName());
			statement.setString(4, uc.getActor());
			statement.setString(5, uc.getDes());
			statement.setString(6, uc.getPurpose());
			statement.setString(7, uc.getOverview());
			statement.setString(8, uc.getType());
			statement.setString(9, uc.getCross());
			statement.setString(10, uc.getPreRequistes());
			statement.setString(11, uc.getTypical());
			statement.setString(12, uc.getAlternative());
			statement.setString(13, uc.getExceptional());
			statement.setString(14, uc.getUI());
			
			statement.executeUpdate();
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncRealUsecase() {
		try {
			String sql = "delete from RealUseCase";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setSchedule(int row, int column) {
		try {
			String sql = "insert or replace into schedule values(?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, row);
			statement.setInt(2, column);
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncSchedule() {
		try {
			String sql = "delete from schedule";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setOp(int index,SystemOperation op) {
		try {			
			String sql = "insert or replace into SystemOperation values(?,?,?,?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, op.getId().toString());
			statement.setString(3, op.getName());
			statement.setString(4, op.getResponsibility());
			statement.setString(5, op.getType());
			statement.setString(6, op.getCross());
			statement.setString(7, op.getNotes());
			statement.setString(8, op.getException());
			statement.setString(9, op.getOutput());
			statement.setString(10, op.getPreconditions());
			statement.setString(11, op.getPostconditions());
			statement.setString(12, op.getOp_name());
			statement.executeUpdate();
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncOp() {
		try {
			String sql = "delete from SystemOperation";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setMethod(MethodDescription md) {
		try {			
			String sql = "insert or replace into Method values(?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, md.getType());
			statement.setString(2, md.getName());
			statement.setString(3, md.getPurpose());
			statement.setString(4, md.getOverview());
			statement.setString(5, md.getCross());
			statement.setString(6, md.getInput());
			statement.setString(7, md.getOutput());
			statement.setString(8, md.getAbstract());
			statement.setString(9, md.getException());
			statement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncMethod() {
		try {
			String sql = "delete from Method";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setUI(UIDesign ui) {
		try {			
			String sql = "insert or replace into UI values(?,?,?,?)";
			statement = connection.prepareStatement(sql);
			for(Button tmp : ui.getButtonList()) {
				statement.setString(1, "B");
				statement.setDouble(2, tmp.getLocation().getX());
				statement.setDouble(3,tmp.getLocation().getY());
				statement.setString(4, tmp.getText());
				statement.executeUpdate();
			}
			for(JTextField tmp : ui.getTextList()) {
				statement.setString(1, "T");
				statement.setDouble(2, tmp.getLocation().getX());
				statement.setDouble(3,tmp.getLocation().getY());
				statement.setString(4, tmp.getText());
				statement.executeUpdate();
			}
			for(JLabel tmp : ui.getLabelList()) {
				statement.setString(1, "L");
				statement.setDouble(2, tmp.getLocation().getX());
				statement.setDouble(3,tmp.getLocation().getY());
				statement.setString(4, tmp.getText());
				statement.executeUpdate();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void syncUI() {
		try {
			String sql = "delete from UI";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<StageText> getSearchText() {
		ArrayList<StageText> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM TextTable";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				StageText text = new StageText();
				text.setText(result.getString("text"));
				data.add(text);
				//System.out.println(text.getText());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<Risk> getSearchRisk() {
		ArrayList<Risk> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Risk";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Risk risk = new Risk();
				risk.setName(result.getString("name"));
				risk.setProbability(result.getInt("probability"));
				risk.setSignificance(result.getInt("significance"));
				risk.setWeight();
				risk.setPlan(result.getString("plan"));
				data.add(risk);
				//System.out.println(text.getText());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public Requirement getSearchReq() {
		Requirement req = new Requirement();
		req.clear();
		try {
			String sql = "SELECT * FROM Requirement";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				req.add_row();
				req.setRef(result.getString("ref"), result.getInt("id"));
				req.setName(result.getString("name"), result.getInt("id"));
				req.setCategory(result.getString("category"), result.getInt("id"));
				req.setuCategory(result.getString("ucategory"), result.getInt("id"));
				req.setuNumber(result.getInt("uNumber"), result.getInt("id"));
				req.setuName(result.getString("uName"), result.getInt("id"));
				req.setRank(result.getString("rank"), result.getInt("id"));
				req.setTestcase(result.getString("testcase"), result.getInt("id"));
				
				//System.out.println(text.getText());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return req;
	}
	public ArrayList<NonFuncReq> getSearchNonReq(String type) {
		ArrayList<NonFuncReq> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM NonFuncReq where type = ?";
			statement = connection2.prepareStatement(sql);
			statement.setString(1, type);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				NonFuncReq req = new NonFuncReq();
				req.setCategory(result.getString("category"));
				req.setTestcase(result.getString("testcase"));
				
				data.add(req);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<SystemTestCase> getSearchSystemTC() {
		ArrayList<SystemTestCase> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM FuncReq";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				SystemTestCase req = new SystemTestCase();
				req.setNumber(result.getString("number"));
				req.setName(result.getString("name"));
				req.setDescription(result.getString("description"));
				req.setUsecase(result.getString("usecase"));
				req.setSystemFunction(result.getString("function"));
				data.add(req);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<Glossary> getSearchGl(String type) {
		ArrayList<Glossary> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Glossary where type = ?";
			statement = connection2.prepareStatement(sql);
			statement.setString(1, type);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Glossary g = new Glossary();
				g.setTerm(result.getString("term"));
				g.setDescription(result.getString("description"));
				g.setRemarks(result.getString("remarks"));
				g.setCategory(result.getString("category"));
				g.setType(result.getString("type"));
				data.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<String> getSearchConcept() {
		ArrayList<String> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM concept";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String s = result.getString("text");
				data.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<String> getSearchBUsecase(String type) {
		ArrayList<String> data = new ArrayList<>();
		
		try {
			String sql;
			if(type.equals("A")) {
				sql = "SELECT * FROM Ausecase";
			}
			else {
				sql = "SELECT * FROM Eusecase";
			}
			
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String s = result.getString("name");
				data.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	} 
	public ArrayList<UseCase> getSearchUseCase() {
		ArrayList<UseCase> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM UseCase";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				UseCase uc = new UseCase();
				Id i = new Id();
			
				i.setValue(result.getString("id"));
				uc.setId(i);
				uc.setName(result.getString("name"));
				uc.setActor(result.getString("actor"));
				uc.setDes(result.getString("description"));
				uc.setPurpose(result.getString("purpose"));
				uc.setOverview(result.getString("overview"));
				uc.setType(result.getString("type"));
				uc.setCross(result.getString("cross"));
				uc.setPreRequistes(result.getString("prerequistes"));
				uc.setTypical(result.getString("typical"));
				uc.setAlternative(result.getString("alternative"));
				uc.setExceptional(result.getString("exceptional"));
							
				data.add(uc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<UseCase> getSearchRealUseCase() {
		ArrayList<UseCase> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM RealUseCase";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				UseCase uc = new UseCase();
				Id i = new Id();
			
				i.setValue(result.getString("id"));
				uc.setId(i);
				uc.setName(result.getString("name"));
				uc.setActor(result.getString("actor"));
				uc.setDes(result.getString("description"));
				uc.setPurpose(result.getString("purpose"));
				uc.setOverview(result.getString("overview"));
				uc.setType(result.getString("type"));
				uc.setCross(result.getString("cross"));
				uc.setPreRequistes(result.getString("prerequistes"));
				uc.setTypical(result.getString("typical"));
				uc.setAlternative(result.getString("alternative"));
				uc.setExceptional(result.getString("exceptional"));
							
				data.add(uc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<Schedule> getSearchSche() {
		
		ArrayList<Schedule> data = new ArrayList<>();
		try {
			String sql = "SELECT * FROM schedule";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Schedule s = new Schedule();
				s.setRow(result.getInt("row"));
				s.setColumn(result.getInt("column"));
				data.add(s);
				//System.out.println(text.getText());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList<UMLDiagram> getSearchGraph(String type) {
		ArrayList<UMLDiagram> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Graph where type = ?";
			statement = connection2.prepareStatement(sql);
			statement.setString(1, type);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				UMLDiagram g = new UMLDiagram();
				g.setName(result.getString("name"));
				g.setId(result.getString("id"));
				String filename = type + "." + g.getName() + "." + "html";
				graphfile = new File(filename);
				IFileReader r = new JFileReader(graphfile);
				
				IGraphFile igf = new GraphFile(r.getFileDefinition());
				
				g.setGraph(igf);
				data.add(g);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public ArrayList<SystemOperation> getSearchOperation() {
		ArrayList<SystemOperation> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM SystemOperation";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				SystemOperation op = new SystemOperation();
				Id i = new Id();
			
				i.setValue(result.getString("id"));
				op.setId(i);
				op.setName(result.getString("name"));
				op.setResponsibility(result.getString("respons"));
				op.setType(result.getString("type"));
				op.setCross(result.getString("cross"));
				op.setNotes(result.getString("notes"));
				op.setException(result.getString("exception"));
				op.setOutput(result.getString("output"));
				op.setPreconditions(result.getString("pre"));
				op.setPostconditions(result.getString("post"));
				op.setOp_name(result.getString("op"));
				
				data.add(op);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public ArrayList getSearchTC(String type) {
		
		if(type.equals("U")) {
			ArrayList<UnitTestCase> data = new ArrayList<>();
			try {
				String sql = "SELECT * FROM TestCase where type = ?";
				statement = connection2.prepareStatement(sql);
				statement.setString(1, type);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					UnitTestCase req = new UnitTestCase();
					req.setNumber(result.getString("number"));
					req.setName(result.getString("name"));
					req.setDescription(result.getString("description"));
					req.setInput(result.getString("input"));
					req.setOutput(result.getString("output"));
					req.setResult(result.getString("result"));
					data.add(req);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return data;
		}
		else if(type.equals("I")) {
			//integration
			ArrayList<IntegrationTestCase> data = new ArrayList<>();
			try {
				String sql = "SELECT * FROM TestCase where type = ?";
				statement = connection2.prepareStatement(sql);
				statement.setString(1, type);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					IntegrationTestCase req = new IntegrationTestCase();
					req.setNumber(result.getString("number"));
					req.setDescription(result.getString("description"));
					req.setInput(result.getString("input"));
					req.setOutput(result.getString("output"));
					req.setResult(result.getString("result"));
					data.add(req);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return data;
		}
		else {
			//etc testcase
			ArrayList<TestCase> data = new ArrayList<>();
			try {
				String sql = "SELECT * FROM TestCase where type = ?";
				statement = connection2.prepareStatement(sql);
				statement.setString(1, type);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					TestCase req = new TestCase();
					req.setNumber(result.getString("number"));
					req.setName(result.getString("name"));
					req.setDescription(result.getString("description"));
					req.setResult(result.getString("result"));
					data.add(req);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return data;
		}
	}
	public ArrayList<MethodDescription> getSearchMethod() {
		ArrayList<MethodDescription> data = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Method";
			statement = connection2.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				MethodDescription md = new MethodDescription();
				
				md.setType(result.getString("type"));
				md.setName(result.getString("name"));
				md.setPurpose(result.getString("purpose"));
				md.setOverview(result.getString("overview"));
				md.setCross(result.getString("cross"));
				md.setInput(result.getString("input"));
				md.setOutput(result.getString("output"));
				md.setAbstract(result.getString("abstract"));
				md.setException(result.getString("exception"));
				
				data.add(md);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public UIDesign getSearchUI() {
		UIDesign data = new UIDesign();
		ArrayList<Button> buttonList = new ArrayList<>();
		ArrayList<JTextField> textList = new ArrayList<>();
		ArrayList<JLabel> labelList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM UI";
			statement = connection2.prepareStatement(sql);
			//statement.setString(1, "B");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				if(result.getString("type").equals("B")) {
					Button b = new Button(result.getString("name"),1);
					Point p = new Point();
					p.setLocation(result.getDouble("x"), result.getDouble("y"));
					b.setLocation(p);
					buttonList.add(b);
				}
				else if(result.getString("type").equals("T")) {
					JTextField t = new JTextField();
					Point p = new Point();
					p.setLocation(result.getDouble("x"), result.getDouble("y"));
					t.setLocation(p);
					textList.add(t);
				}
				else if(result.getString("type").equals("L")) {
					JLabel l = new JLabel();
					Point p = new Point();
					p.setLocation(result.getDouble("x"), result.getDouble("y"));
					l.setLocation(p);
					labelList.add(l);
				}
			}
			data.setButtonList(buttonList);
			data.setTextList(textList);
			data.setLabelList(labelList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
