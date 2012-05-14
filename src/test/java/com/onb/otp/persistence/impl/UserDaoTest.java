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

import com.onb.otp.domain.User;
import com.onb.otp.persistence.base.UserDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class UserDaoTest {
	private static final String DATA_SET_XML = "./src/test/resources/dataSet.xml";
	@Autowired
	private UserDaoBase dao;
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
		User user = new User();
		user.setUsername("user");
		dao.save(user);
	}
	
	@Test
	public void getById() {
        User user = dao.getById(1L);
        assertNotNull(user);
    }
	
	@Test
	public void update() {
		User user = dao.getById(1L);
		user.setUsername("newuser");
		dao.update(user);
		
		User newUser = dao.getById(1L);
		assertEquals("newuser", newUser.getUsername());
	}
}
