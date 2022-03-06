package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class ExamenBlancApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ExamenBlancApplication.class, args);
	}

}
