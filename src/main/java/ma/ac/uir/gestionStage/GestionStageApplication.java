package ma.ac.uir.gestionStage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ma.ac.uir.gestionStage.*"})
public class GestionStageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStageApplication.class, args);}
		@Bean
		public ModelMapper modelMapper() { return new ModelMapper(); }

}
