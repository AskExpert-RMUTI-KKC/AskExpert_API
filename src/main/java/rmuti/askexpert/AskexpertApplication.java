package rmuti.askexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rmuti.askexpert.model.config.BaseUrlFile;

import java.io.File;

@SpringBootApplication
public class AskexpertApplication {

	public static void main(String[] args) {
		//create Folder
		BaseUrlFile pathUrl = new BaseUrlFile();
		new File(pathUrl.getBaseDir()).mkdir();
		new File(pathUrl.getImageTopicUrl()).mkdir();
		new File(pathUrl.getImageCommetUrl()).mkdir();
		new File(pathUrl.getImageProfileUrl()).mkdir();
		SpringApplication.run(AskexpertApplication.class, args);
	}
}
