package ma.aui.openerp.services.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"ma.aui.openerp.commons", "ma.aui.openerp.services.employee"})
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan({"org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.tokenstore",
        "org.axonframework.modelling.saga.repository.jpa",
        "ma.aui.openerp.services.employee.model"})
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

}
