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

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM ORDERTABLE");

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

        return "OK";
    }
}
