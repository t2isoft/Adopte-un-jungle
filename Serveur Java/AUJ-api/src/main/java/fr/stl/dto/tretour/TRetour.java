package fr.stl.dto.tretour;

import java.io.Serializable;

/**
 * Classe de Retour des opérations de l'API
 * contenant le code et le message retour correspondant
 */
public class TRetour implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Code Retour */
	private String codeRetour;
	
	/** Message Retour */
	private String messageRetour;

	/**
	 * @return the codeRetour
	 */
	public String getCodeRetour() {
		return codeRetour;
	}

	/**
	 * @param codeRetour the codeRetour to set
	 */
	public void setCodeRetour(String codeRetour) {
		this.codeRetour = codeRetour;
	}

	/**
	 * @return the messageRetour
	 */
	public String getMessageRetour() {
		return messageRetour;
	}

	/**
	 * @param messageRetour the messageRetour to set
	 */
	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

}
