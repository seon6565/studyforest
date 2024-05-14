package org.fullstack4.studyforest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class StudyforestApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyforestApplication.class, args);
    }

}
