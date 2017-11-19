package fr.stl;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

/**
 * Gestionnaire d'environnement.
 */
public final class Environment {

    /** Logger */
    private static final Logger LOG = Logger.getLogger(Environment.class.getName());

    /** L'environnement courant. */
    private static String currentEnvironment;
    
    /** Servlet */
    private static ServletContext servlet;

    /** Les properties de l'environnement courant. */
    private static final Properties PROPS = new Properties();

    /**
     * Constructeur privé.
     */
    private Environment() {
        super();
    }

    /**
     * Initialisation de l'environnement
     * @param environnement l'environnement
     */
    public static void initEnvironment(final String environnement) {
        Environment.currentEnvironment = environnement;
        LOG.log(Level.INFO, "Environnement : " + environnement);
    }

    /**
     * Charge une propriété.
     * @param name clé
     * @param value valeur
     */
    public static void addProperty(final String name, final String value) {
        PROPS.put(name, value);
        LOG.log(Level.INFO, name + " = " + value);
    }

    /**
     * Récupération de l'environnement courant.
     * @return l'environnement courant
     */
    public static String getCurrentEnvironment() {
        if (currentEnvironment == null) {
            throw new IllegalStateException(
                            "Environnement non initialisé : appeler la méthode Environment.initEnvironment");
        }
        return currentEnvironment;
    }

    /**
     * @return les propriétés de l'environnement courant
     */
    public static Properties getProperties() {
        return PROPS;
    }

    /**
     * Récupère une propriété de l'environnement courant
     * @param key la clé de la propriété
     * @return la valeur
     */
    public static String getProperty(final String key) {
        return PROPS.getProperty(key);
    }

    /**
     * Récupère une propriété de l'environnement courant
     * @param key la clé de la propriété
     * @param defaultValue la valeur par défaut
     * @return la valeur
     */
    public static String getProperty(final String key, final String defaultValue) {
        return PROPS.getProperty(key, defaultValue);
    }
    
    /**
     * Initialisation de la servlet
     * @param servlet la servlet
     */
    public static void initServlet(final ServletContext servlet) {
    	if (Environment.servlet == null && servlet != null) {
    		Environment.servlet = servlet;
    	}
    }
    
    /**
     * Récupère un Paramètre de la servlet
     * @param key la clé du paramètre
     * @return la valeur
     */
    public static String getParameterFromServlet(final String key) {
    	if (StringUtils.isNotBlank(key)) {
    		if (servlet == null) {
    			throw new IllegalStateException(
                        "Servlet non initialisée : appeler la méthode Environment.initServlet");
    		}
    		return servlet.getInitParameter(key);
    	}
    	return null;
    }

}
