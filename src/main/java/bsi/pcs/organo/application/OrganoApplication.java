package bsi.pcs.organo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("bsi.pcs.organo.repository")
@EntityScan("bsi.pcs.organo.entity")
@ComponentScan(basePackages = "bsi.pcs.organo")
public class OrganoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganoApplication.class, args);
    }
}
