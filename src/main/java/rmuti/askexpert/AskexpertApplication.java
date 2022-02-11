package rmuti.askexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rmuti.askexpert.model.config.BaseUrlFile;

import java.io.File;

@SpringBootApplication
public class AskexpertApplication {

	public static void main(String[] args) {
		//create Folder
		new File(new BaseUrlFile().getBaseDir()).mkdir();
		new File(new BaseUrlFile().getImageTopicUrl()).mkdir();
		new File(new BaseUrlFile().getImageCommetUrl()).mkdir();
		new File(new BaseUrlFile().getImageProfileUrl()).mkdir();
		SpringApplication.run(AskexpertApplication.class, args);
	}
}
