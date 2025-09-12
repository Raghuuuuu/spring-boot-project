package project.MainMethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "project.*"
	})
@EnableJpaRepositories(basePackages = "project.repo")
@EntityScan(basePackages="project.entity")
public class MainMethod {

	public static void main(String[] args) {
		SpringApplication.run(MainMethod.class, args);
	}

}
