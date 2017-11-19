package fr.stl.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.PostManager;
import fr.stl.manager.UserManager;
import fr.stl.services.PostService;

/**
 * Implémentation de l'interface PostService
 */
public class PostServiceImpl implements PostService {

    /** UserManager */
    private UserManager userManager = new UserManager();

    /** PostManager */
    private PostManager postManager = new PostManager();

    /** ContactManager */
    // private ContactManager contactManager = new ContactManager();

    @Override
    public void nouveauPost(String message, long idUser) throws TechniqueException {
        // Récupération de l'utilisateur
        UserEntity userEntity = userManager.findById(idUser);
        if (userEntity == null) {
            throw new TechniqueException("Erreur technique de récupération de l'utilisateur.");
        }

        // Enregistrement du post
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(message);
        postEntity.setPostDate(new Date());
        postEntity.setPoster(userEntity);

        postManager.persiste(postEntity);
    }

    @Override
    public List<PostEntity> tousLesPosts(long idUser) throws TechniqueException {
        // Récupération de l'utilisateur
        // UserEntity userEntity = userManager.findById(idUser);
        //
        // if (userEntity == null)
        // throw new TechniqueException("Erreur technique de récupération de l'utilisateur.");
        //
        // Récupération des contacts
        // List<ContactEntity> contactEntities = contactManager.findContactByUser(userEntity);
        //
        List<PostEntity> postEntities = new ArrayList<>();
        postEntities = postManager.findAll();
        Collections.sort(postEntities, new Comparator<PostEntity>() {

            public int compare(PostEntity o1, PostEntity o2) {
                return o1.getPostDate().compareTo(o2.getPostDate());
            }
        });
        Collections.reverse(postEntities);
        //
        // Ajout des pots de l'utilisateur appelant
        // postEntities.addAll(userEntity.getPosts());
        //
        // Ajout des posts des contacts l'utilisateur
        // RAPPEL : un contactEntity à pour premier champ moi
        // en deuxième champ le contact avec lequel il est en contact
        // for (ContactEntity contactEntity : contactEntities) {
        // List<PostEntity> contactEntityPosts = new ArrayList<>(contactEntity.getMyContact().getPosts());
        // if (!contactEntityPosts.isEmpty()) {
        // postEntities.addAll(contactEntityPosts);
        // }
        // }

        // Trie par date
        return postEntities;
        // return postEntities.stream().sorted((e1, e2) -> e2.getPostDate().compareTo(e1.getPostDate()))
        // .collect(Collectors.toList());
    }

    @Override
    public List<PostEntity> mesPosts(long idUser) throws TechniqueException {
        // Récupération de l'utilisateur
        UserEntity userEntity = userManager.findById(idUser);

        if (userEntity == null)
            throw new TechniqueException("Erreur technique de récupération de l'utilisateur.");
        
        List<PostEntity> listPost = new ArrayList<>(userEntity.getPosts());
        
        Collections.sort(listPost, new Comparator<PostEntity>() {

            public int compare(PostEntity o1, PostEntity o2) {
                return o1.getPostDate().compareTo(o2.getPostDate());
            }
        });
        Collections.reverse(listPost);
        // Trie par date
        return listPost;
        // return new ArrayList<>(userEntity.getPosts()).stream()
        // .sorted((e1, e2) -> e2.getPostDate().compareTo(e1.getPostDate())).collect(Collectors.toList());
    }

    @Override
    public void updatePostUser(long idUser, long idPost, String messageUpdated)
                    throws TechniqueException, FonctionnelleException {
        // Récupération de la publication
        PostEntity postEntity = postManager.findPostById(idPost);
        if (postEntity == null) {
            throw new FonctionnelleException("Erreur, la publication n'existe pas.");
        }

        // Vérification que la publication appartient à l'utilisateur
        if (postEntity.getPoster().getId().compareTo(idUser) != 0) {
            throw new TechniqueException("Erreur, la publication n'appartient pas à l'utilisateur connecté.");
        }

        // Mise à jour de la publication
        postEntity.setContent(messageUpdated);
        postEntity.setPostDate(new Date());
        postManager.persiste(postEntity);
    }

}
