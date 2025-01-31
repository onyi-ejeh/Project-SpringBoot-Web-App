package ng.victoriaejeh.projectspringbootwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot Web Application.
 * <p>
 * This class bootstraps the application by invoking {@link SpringApplication#run(Class, String...)}
 * with the {@code ProjectSpringBootWebAppApplication} class and command-line arguments.
 * </p>
 *
 * @author Onyi Ejeh
 * @version 1.0
 * @since 2025
 */
@SpringBootApplication
public class ProjectSpringBootWebAppApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjectSpringBootWebAppApplication.class, args);
    }
}
