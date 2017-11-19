package fr.stl;

import org.junit.BeforeClass;

/**
 * Classe abstraite des TUs
 */
public class AbstractTest {

    /** Initialisation des tests. */
    @BeforeClass
    public static void initEnvironnement() {
        Environment.initEnvironment("test");
    }
    
}
