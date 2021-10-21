package com.ffx.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ffx.data.models.DatabaseConnectionProps;
import com.ffx.data.services.DatabaseService;

@SpringBootApplication
public class FairfaxFireOpsDataUtilityApp  implements CommandLineRunner {
	
	@Autowired
	private DatabaseService databaseService;
	
	private static List<String> optionDescriptions = List.of(
			" -BuildDatabase [host][post][dbname][user][password]    Builds and populates the database",
			" -Help                                                  Provides a list of options");
	
	public static void main(String[] args) {
		SpringApplication.run(FairfaxFireOpsDataUtilityApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("---- Fairfax Country Fire/Rescue Operations Data Utility ----\n");
		
		if (args.length == 6 && args[0].equalsIgnoreCase("-BuildDatabase")) {
			databaseService.buildDatabase(
				DatabaseConnectionProps.builder()
					.host(args[1])
					.port(args[2])
					.databaseName(args[3])
					.user(args[4])
					.password(args[5])
					.build());
		}
		else {
			showApplicationHelp();
		}
	}
	
	private void showApplicationHelp() {
		System.out.println(" This utility is used to create and populate the data stores for the application\n");
		optionDescriptions.stream().forEach(description -> {
			System.out.println(description);
		});
	}
}

