package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Order(1)
@Profile("h2")
public class ProfileService implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(ProfileService.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Running in H2 profile.");
        logger.info("args.length = {}", args.length);
        logger.info("args = {}", Arrays.deepToString(args));
    }
}
