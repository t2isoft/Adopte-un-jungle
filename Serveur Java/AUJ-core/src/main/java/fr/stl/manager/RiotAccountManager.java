package fr.stl.manager;

import fr.stl.dao.DAOFactory;
import fr.stl.entity.RiotAccountEntity;
import fr.stl.exceptions.TechniqueException;

/**
 * Classe de management de la transaction
 */
public class RiotAccountManager {

    /**
     * Persiste
     * @param riotAccountEntity un RiotAccountEntity
     * @return un RiotAccountEntity
     * @throws TechniqueException erreur technique
     */
    public RiotAccountEntity persiste(RiotAccountEntity riotAccountEntity) throws TechniqueException {
        return DAOFactory.getInstance().getRiotAccountDAO().persiste(riotAccountEntity);
    }
    
}
