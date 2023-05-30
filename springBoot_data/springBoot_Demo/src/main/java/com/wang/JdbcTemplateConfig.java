package com.wang;

import com.wang.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource);
//    }
//
//    private JdbcTemplate jdbcTemplate;
//
//    public void insertUser(User user) {
//        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
//        jdbcTemplate.update(sql, user.getName(), user.getAge());
    //}
}