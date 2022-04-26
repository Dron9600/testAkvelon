package kz.api.test;

import kz.api.test.repository.PostgresRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = { DataSourceAutoConfiguration.class })
public class TestApplication {
	static String tableName= "proj"; // name of the new dataBase
	String url = "jdbc:postgresql://localhost:5432/akvelonTest"; // url for database
	String user = "postgres";   // database login
	String password = "postgres"; // database password



	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		PostgresRepository postgresRepository = new PostgresRepository();
		postgresRepository.createTable(tableName);

	}


	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
