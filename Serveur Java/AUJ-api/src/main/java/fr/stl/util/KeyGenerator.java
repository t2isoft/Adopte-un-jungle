package fr.stl.util;

import java.security.Key;

/**
 * Interface de génération de clé
 */
public interface KeyGenerator {

    /**
     * Retourne une clé
     * @return une Key
     */
	Key generateKey();
	
}
