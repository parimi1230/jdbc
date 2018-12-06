package com.sample.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ContactDetailMapper implements RowMapper<ContactDetail>{

    @Override
    public ContactDetail mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        final ContactDetail contactDetail = new ContactDetail();
        contactDetail.setId(rs.getLong("id"));
        contactDetail.setEmail(rs.getString("email"));
        contactDetail.setPhone(rs.getString("phone"));
        return contactDetail;
    }

}
