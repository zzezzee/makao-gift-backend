package com.zzezze.makaogift.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM orders");

        jdbcTemplate.update(
                "INSERT INTO product(" +
                        "id, name, price, maker, description, image)" +
                        " VALUES(1, ?, ?, ?, ?, ?)",
                "파스퇴르 전용목장 1급A 파스퇴르 우유 200ml (멸균)(36개)",
                25710,
                "롯데푸드",
                "우유 설명",
                "https://img.danawa.com/prod_img/500000/849/611/img/12611849_1.jpg??shrink=360:360&_v=20221201101009"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, encoded_password, name, amount" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?)",
                "test", passwordEncoder.encode("test"), "홍길동", 500000
        );
        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, encoded_password, name, amount" +
                        ")" +
                        " VALUES(2, ?, ?, ?, ?)",
                "test2", passwordEncoder.encode("test2"), "홍길동", 500000
        );

        return "OK";
    }
}
