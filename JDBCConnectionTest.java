package com.sample.jdbc;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class JDBCConnectionTest {

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/applicationContext-jdbc.xml");
/*
        final DataSource dataSource = (DataSource)applicationContext.getBean("dataSource");
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
*/
/*        final JdbcTemplate jdbcTemplate = (JdbcTemplate)applicationContext.getBean("jdbcTemplate");*/

        //--------Connection testing with Query for Map
        final ContactDetailDAOImpl contactDetailDAOImpl = (ContactDetailDAOImpl) applicationContext.getBean("contactDetailDAOImpl");
        //System.out.println(contactDetailDAOImpl.getContactDetailsMap());

        //Fetch List of Contact Details.
        //System.out.println(contactDetailDAOImpl.getContactDetails());

        //Fetch By Id
        //System.out.println(contactDetailDAOImpl.getContactDetailById(1L));

        //Fetch by NamedParameter
        //System.out.println("------>>>"+contactDetailDAOImpl.getContactDetailByIdAndEmail(1L, "test@gmail.com"));

        //Insert record
/*
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setId(1L);
        contactDetail.setEmail("inset@gmail.com");
        contactDetail.setPhone("12345678");
        System.out.println(contactDetailDAOImpl.insertContactDetail(contactDetail));
*/

        final ContactDetail contactDetail1 = new ContactDetail();
        contactDetail1.setId(4L);
        contactDetail1.setEmail("batch4@gmail.com");
        contactDetail1.setPhone("12345678");

        final ContactDetail contactDetail2 = new ContactDetail();
        contactDetail2.setId(5L);
        contactDetail2.setEmail("batch5@gmail.com");
        contactDetail2.setPhone("12345678");

        final ContactDetail contactDetail3 = new ContactDetail();
        contactDetail3.setId(6L);
        contactDetail3.setEmail("batch6@gmail.com");
        contactDetail3.setPhone("12345678");

        contactDetailDAOImpl.batchInsert(Arrays.asList(contactDetail1, contactDetail2, contactDetail3));

    }

}
