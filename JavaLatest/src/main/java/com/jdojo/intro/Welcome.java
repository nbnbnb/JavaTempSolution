package com.jdojo.intro;

import java.util.logging.Logger;

public class Welcome {

    static Logger logger = Logger.getLogger("Welcome");

    public static void main(String[] args) {
        System.out.println("Welcome to the Module System.");
        // Print the module name of the Welcome class
        Class<Welcome> cls = Welcome.class;
        Module mod = cls.getModule();
        String moduleName = mod.getName();
        System.out.format("Module Name: %s%n", moduleName);

        logger.info("Welcome");
    }
}
