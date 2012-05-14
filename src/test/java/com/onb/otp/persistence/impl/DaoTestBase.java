package com.onb.otp.persistence.impl;

import java.io.FileInputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

public abstract class DaoTestBase {
	private static final String DATA_SET_XML = "./src/test/resources/dataSet.xml";
	@Autowired
	DataSource dataSource;
	
	@SuppressWarnings("deprecation")
	@Before public void setUp() throws Exception {
		Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
        IDataSet dataSet = new FlatXmlDataSet(new FileInputStream(DATA_SET_XML));
        
        try {
            DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
	}
}
