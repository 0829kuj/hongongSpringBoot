package com.example.firstproject.persistence;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Fail.fail;

public class JDBCTest {
    static {
        try {
            Class.forName("com.mariadb.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {

        try(Connection con =
                    DriverManager.getConnection(
                            "jdbc:mariadb://localhost:3306/hongongspringboot?serverTimezone=Asia/Seoul",
                            "0829kuj",
                            "1234")){
            System.out.println(con);
            System.out.println("연결 확인 성공!");
        } catch (Exception e) {
//            e.printStackTrace();
            fail(e.getMessage());
        }

    }

}
