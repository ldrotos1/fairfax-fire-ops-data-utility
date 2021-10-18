package com.ffx.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ffx.data.services.DatabaseService;

@SpringBootApplication
public class FairfaxFireOpsDataUtilityApp  implements CommandLineRunner {
	
	@Autowired
	private DatabaseService databaseService;
	
	private static List<String> validOptions = List.of(
			"-CreateDatabase",
			"-DatabaseConnection",
			"-Help"
			);
	
	private static List<String> optionDescriptions = List.of(
			"  -CreateDatabase       Creates and populates the database",
			"  -DatabaseConnection   View the database connection info",
			"  -Help                 Provides a list of options");
	
	
	public static void main(String[] args) {
		SpringApplication.run(FairfaxFireOpsDataUtilityApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("---- Fairfax Country Fire/Rescue Operations Data Utility ----\n");
		if (args.length == 1 && validOptions.contains(args[0])) {
			
			if (args[0].equalsIgnoreCase("-CreateDatabase")) {
				databaseService.createDatabase();
			}
			else if (args[0].equalsIgnoreCase("-DatabaseConnection")) {
				 databaseService.viewDatabaseConnection();
			}
			else {
				showApplicationHelp();
			}
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

