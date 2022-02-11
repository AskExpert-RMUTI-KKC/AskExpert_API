package rmuti.askexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class AskexpertApplication {

	public static void main(String[] args) {
		//create Folder
		new File("uploads").mkdir();
		new File("uploads/profileImg").mkdir();
		new File("uploads/topicImg").mkdir();
		new File("uploads/commentImg").mkdir();
		SpringApplication.run(AskexpertApplication.class, args);
	}

}
