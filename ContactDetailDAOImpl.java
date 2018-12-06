package com.sample.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDetailDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public Map<String, Object> getContactDetailsMap() {
        return jdbcTemplate.queryForMap("select top 1 * from dbo.contact_details_test");
    }

    public List<ContactDetail> getContactDetails() {
        final String query = "select * from dbo.contact_details_test";
        return jdbcTemplate.query(query, new ContactDetailMapper());
    }

    public List<ContactDetail> getContactDetailById(final Long id) {
        final String query = "select * from dbo.contact_details_test where id=?";
        return jdbcTemplate.query(query, new ContactDetailMapper(), id);
    }

    public List<ContactDetail> getContactDetailByIdAndEmail(final Long id, final String email) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("email", email);
        final String query = "select * from dbo.contact_details_test where id=:id and email=:email";
        return namedJdbcTemplate.query(query, map, new ContactDetailMapper());
    }

    public int insertContactDetail(final ContactDetail contactDetail){
        final String query = "insert into dbo.contact_details_test values(?, ?, ?)";
        return jdbcTemplate.update(query, contactDetail.getId(), contactDetail.getEmail(), contactDetail.getPhone());
    }


    public void batchInsert(final List<ContactDetail> contactDetails){
        final String query = "insert into dbo.contact_details_test values(?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter(){
            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException{
                ps.setLong(1, contactDetails.get(i).getId());
                ps.setString(2, contactDetails.get(i).getEmail());
                ps.setString(3, contactDetails.get(i).getPhone());
            }

            @Override
            public int getBatchSize(){
                return contactDetails.size();
            }

        });
    }

}
