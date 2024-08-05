package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@Order(1)
@Profile("h2")
public class ProfileService implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Running in H2 profile.");
        log.info("args.length = {}", args.length);
        log.info("args = {}", Arrays.deepToString(args));
    }
}