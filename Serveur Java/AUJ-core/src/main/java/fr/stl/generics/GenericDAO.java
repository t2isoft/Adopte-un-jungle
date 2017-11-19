package fr.stl.generics;

import java.io.Serializable;
import java.util.List;

import fr.stl.exceptions.TechniqueException;

/**
 * Interface généric des Objets de type DAO
 * @param <T> la Classe (l'entity)
 * @param <ID> l'ID de l'entity
 */
public interface GenericDAO<T, ID extends Serializable> {
    
    /**
     * FindById
     * @param id l'id en base
     * @return un objet de type T
     * @throws TechniqueException erreur technique
     */
    T findById(ID id) throws TechniqueException;
 
    /**
     * FindAll
     * @return une liste d'objet T
     * @throws TechniqueException erreur technique
     */
    List<T> findAll() throws TechniqueException;
 
    /**
     * Persist
     * @param entity l'entity à persister
     * @return un entity
     * @throws TechniqueException erreur technique
     */
    T persiste(T entity) throws TechniqueException;
 
}
