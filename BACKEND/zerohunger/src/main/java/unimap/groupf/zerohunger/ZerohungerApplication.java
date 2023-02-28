package unimap.groupf.zerohunger;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "unimap.groupf.zerohunger")

public class ZerohungerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerohungerApplication.class, args);
	}

}
