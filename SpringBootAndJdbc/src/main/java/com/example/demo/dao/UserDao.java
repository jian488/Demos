package com.example.demo.dao;


import com.example.demo.pojo.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public UserInfo createUser(UserInfo u) {
        String sql = "insert into user(name,address) values(?,?)";

        KeyHolder holder=new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {


            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, u.getName());
                ps.setString(2, u.getAddress());
                return ps;
            }
        }, holder);

        int insertId=holder.getKey().intValue();
        u.setId(insertId);
        return u;

    }

    public void createUserList() {
        String sql="insert into user (name,address) values (?,?)";
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        batchArgs.add(new Object[]{"caoyc","北京"});
        batchArgs.add(new Object[]{"zhh","重庆"});
        batchArgs.add(new Object[]{"cjx","天津"});

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public void deleteUser(int id) {
        String sql="delete from user where id=?";

        jdbcTemplate.update(sql, new Object[] {id},new int[] {java.sql.Types.INTEGER});
    }

    public void updateUser(UserInfo u) {
        String sql="update user set name=? where id=?";

        jdbcTemplate.update(sql, new Object[] {u.getName(),u.getId()});
    }

    public List<UserInfo> queryUser(int id) {
        String sql="select * from user where id=?";
        //	RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbcTemplate.query(sql,new Object[] {id},new UserRowMapper());
    }

    class UserRowMapper implements RowMapper<UserInfo> {


        public UserInfo mapRow(ResultSet res, int arg1) throws SQLException {
            UserInfo u=new UserInfo();
            u.setId(res.getInt("id"));
            u.setName(res.getString("name"));
            u.setAddress(res.getString("address"));
            return u;
        }

    }


}
