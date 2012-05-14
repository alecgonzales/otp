package com.onb.otp.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.persistence.base.OneTimePasswordDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class OneTimePasswordDaoTest {
	private static final String DATA_SET_XML = "./src/test/resources/dataSet.xml";
	@Autowired
	private OneTimePasswordDaoBase dao;
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
	@Test
	public void save() {
		OneTimePassword password = new OneTimePassword("password");
		dao.save(password);
	}
	
	@Test
	public void getById() {
        OneTimePassword password = dao.getById(1L);
        assertNotNull(password);
    }
	
	@Test
	public void update() {
		OneTimePassword password = dao.getById(1L);
		password.setCode("new password");
		dao.update(password);
		
		OneTimePassword newPassword = dao.getById(1L);
		assertEquals("new password", newPassword.getCode());
	}
}