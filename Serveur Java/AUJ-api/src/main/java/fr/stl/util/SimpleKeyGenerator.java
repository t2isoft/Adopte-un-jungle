package fr.stl.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

/**
 * Classe de génération de Key
 */
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}
