package cz.pauki.service;

import org.flywaydb.core.Flyway;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;

@SpringBootTest(classes = {AbstractServiceTest.FlywayConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
abstract class AbstractServiceTest {

    @Configuration
    public static class FlywayConfiguration{

        @Autowired
        private DataSource dataSource;

        @Bean
        Flyway flyway() throws NamingException {
            String path = CodetableServiceImplTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            return Flyway.configure()
                    .dataSource(dataSource)
                    .baselineOnMigrate(true)
                    .locations("filesystem:" + path + "db" + File.separator + "migration")
                    //.initSql("CREATE USER IF NOT EXISTS user PASSWORD 'abc'")
                    .cleanDisabled(false)
                    .load();
        }
    }
}
