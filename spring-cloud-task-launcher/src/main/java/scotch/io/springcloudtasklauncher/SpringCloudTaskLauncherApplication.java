package scotch.io.springcloudtasklauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;

@SpringBootApplication
@EnableTaskLauncher
public class SpringCloudTaskLauncherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTaskLauncherApplication.class, args);
	}
}
