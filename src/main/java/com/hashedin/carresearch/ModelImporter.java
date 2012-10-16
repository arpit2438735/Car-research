package com.hashedin.carresearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ModelImporter {

	private final JdbcTemplate jdbcTemplate;
     
	private static final String INSERT_MODEL_QUERY = "insert into model (make, modelname, year, startPrice, endPrice) "
													+ "values (?, ?, ?, ?, ?)";

	public static void main(String args[]) throws IOException {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		DataSource dataSource = factory.getBean(DataSource.class);
		Scanner scan=new Scanner(System.in);
		
		ModelImporter importer = new ModelImporter(dataSource);
		
		importer.insertModels();
		System.out.println("Select any max price and min price");
		Double input = scan.nextDouble();
		Double secondinput = scan.nextDouble();
		importer.printModels(input,secondinput);
	}
	
	public ModelImporter(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private void printModels(Double input, Double secondinput) {
		List<Model> allModels = jdbcTemplate.query("select make, modelname, year, startPrice, " +
				"endPrice from model where startPrice<="+input+"and endPrice<="+secondinput, new RowMapper<Model>() {

			public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
				Model m = new Model();
				m.setMake(rs.getString(1));
				m.setModelName(rs.getString(2));
				m.setYear(rs.getString(3));
				m.setMaxPrice(rs.getDouble(4));
				m.setMinPrice(rs.getDouble(5));
				return m;
			}
		});

		for(Model m : allModels) {
			System.out.println(m);
		}
	}
	private void insertModels() throws IOException{
		InputStream rawXMlStream = getRawInputStream();
		ModelParser parser = new DomParser();
		final List<Model> models = parser.parse(rawXMlStream);
		String sql="INSERT INTO model(make,modelname,year,startprice,endprice) VALUES(?,?,?,?,?)";
		
		jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
	
			
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Model model=models.get(arg1);
			  arg0.setString(1,model.getMake());
			  arg0.setString(2,model.getModelName());
			  arg0.setString(3,model.getYear());
			  arg0.setDouble(4,model.getMaxPrice());
			  arg0.setDouble(5,model.getMinPrice());
			 //arg0.setString(2,model.getModelName());
			 
			}
			
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return models.size();
				
			}
		});
		System.out.println("Data Inserted");
		
	}
	private static InputStream getRawInputStream() throws IOException {
		URL url = new URL("http://services.forddirect.fordvehicles.com/products/ModelSlices?make=Ford");
		return url.openStream();
	}
	
}
