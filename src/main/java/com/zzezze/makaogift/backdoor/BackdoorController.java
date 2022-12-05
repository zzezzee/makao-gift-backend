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

        jdbcTemplate.update("INSERT INTO product" +
                "(id, name, maker, price, description, image) " +
                "VALUES(1,'나 100% 2.3L(1개)','서울우유','5470','나 100% 2.3L(1개)','https://img.danawa.com/prod_img/500000/050/344/img/3344050_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (2,'흰우유 200ml (멸균)(48개)','서울우유','26050','흰우유 200ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/482/976/img/1976482_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (3,'흰우유 1L (멸균)(10개)','서울우유','18570','흰우유 1L (멸균)(10개)','https://img.danawa.com/prod_img/500000/149/008/img/1008149_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (4,'나 100% 우유 1L(10개)','서울우유','24660','나 100% 우유 1L(10개)','https://img.danawa.com/prod_img/500000/561/943/img/11943561_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (5,'소화가 잘되는 우유 오리지널 190ml (멸균)(48개)','매일유업','29470','소화가 잘되는 우유 오리지널 190ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/069/294/img/4294069_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (6,'매일우유 오리지널 3.4% 200ml (멸균)(48개)','매일유업','22310','매일우유 오리지널 3.4% 200ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/473/085/img/2085473_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (7,'상하목장 유기농 우유 200ml (멸균)(48개)','매일유업','36310','상하목장 유기농 우유 200ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/472/167/img/3167472_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (8,'매일우유 오리지널 3.4% 1L (멸균)(12개)','매일유업','24360','매일우유 오리지널 3.4% 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/769/123/img/2123769_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (9,'맛있는우유 GT 200ml (멸균)(48개)','남양유업','24750','맛있는우유 GT 200ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/960/167/img/3167960_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (10,'맛있는우유 GT 고소한 저지방 180ml (멸균)(48개)','남양유업','25510','맛있는우유 GT 고소한 저지방 180ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/136/653/img/5653136_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (11,'맛있는우유 GT 900ml(2개)','남양유업','4780','맛있는우유 GT 900ml(2개)','https://img.danawa.com/prod_img/500000/870/545/img/10545870_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (12,'맛있는우유 GT 고소한 락토프리 900ml(2개)','남양유업','4480','맛있는우유 GT 고소한 락토프리 900ml(2개)','https://img.danawa.com/prod_img/500000/031/962/img/14962031_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (13,'파스퇴르 무항생제 바른목장 우유 250ml (멸균)(18개)','롯데푸드','19030','파스퇴르 무항생제 바른목장 우유 250ml (멸균)(18개)','https://img.danawa.com/prod_img/500000/763/930/img/6930763_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (14,'파스퇴르 무항생제 바른목장 우유 190ml (멸균)(48개)','롯데푸드','29890','파스퇴르 무항생제 바른목장 우유 190ml (멸균)(48개)','https://img.danawa.com/prod_img/500000/073/838/img/3838073_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (15,'파스퇴르 전용목장 1급A 파스퇴르 우유 200ml (멸균)(36개)','롯데푸드','25710','파스퇴르 전용목장 1급A 파스퇴르 우유 200ml (멸균)(36개)','https://img.danawa.com/prod_img/500000/849/611/img/12611849_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (16,'파스퇴르 무항생제 바른목장 우유 1L (멸균)(10개)','롯데푸드','29890','파스퇴르 무항생제 바른목장 우유 1L (멸균)(10개)','https://img.danawa.com/prod_img/500000/993/008/img/10008993_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (17,'우유 1L (멸균)(12개)','믈레코비타','15490','우유 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/492/439/img/13439492_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (18,'파르카디아 우유 3.5% 1L (멸균)(12개)','LOWICZ(로비츠)','18780','파르카디아 우유 3.5% 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/839/621/img/10621839_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (19,'갓밀크 우유 1L (멸균)(12개)','믈레코비타','20610','갓밀크 우유 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/249/938/img/15938249_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (20,'로위키 3.2% 1L (멸균)(12개)','LOWICZ(로비츠)','17130','로위키 3.2% 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/451/679/img/15679451_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (21,'로위키 1.5% 1L (멸균)(12개)','LOWICZ(로비츠)','17370','로위키 1.5% 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/323/678/img/15678323_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (22,'Milch 데이리스타 밀쉬 우유 1L (멸균)(12개)','F+S','19060','Milch 데이리스타 밀쉬 우유 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/101/944/img/11944101_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (23,'우유 1L (멸균)(10개)','아르보리아','18600','우유 1L (멸균)(10개)','https://img.danawa.com/prod_img/500000/807/269/img/5269807_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (24,'Group 올덴버거 우유 3.5% 1L (멸균)(12개)','DMK','19330','Group 올덴버거 우유 3.5% 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/304/942/img/13942304_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (25,'폴스 퓨어 밀크 1L (멸균)(12개)','Pauls','34150','폴스 퓨어 밀크 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/955/684/img/6684955_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (26,'무쵸 우유 3.2% 1L (멸균)(12개)','MLEKPOL','19240','무쵸 우유 3.2% 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/674/660/img/15660674_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (27,'밀키스마 우유 1L (멸균)(12개)','LOWICZ(로비츠)','20110','밀키스마 우유 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/387/900/img/7900387_1.jpg??shrink=360:360&_v=20221201101009'),\n"
        + "  (28,'라라비타 우유 1L (멸균)(12개)','LOWICZ(로비츠)','24480','라라비타 우유 1L (멸균)(12개)','https://img.danawa.com/prod_img/500000/465/649/img/6649465_1.jpg??shrink=360:360&_v=20221201101009')\n");

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
