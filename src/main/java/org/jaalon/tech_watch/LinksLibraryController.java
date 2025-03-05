package org.jaalon.tech_watch;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/links")

public class LinksLibraryController {

//    private LinksLibraryService linksLibraryService;
    private Map<String, Link> links = new HashMap<>();

    public LinksLibraryController() {

    }

    public Map<String, Link> getAllLinks () {
        return links;
    }

    public Link getLinkById (String id) {
        return links.get(id);
    }

    public String saveLink(Link newLink) {
        String id = UUID.randomUUID().toString();
        Link link = new Link(newLink.getTitle(), newLink.getUrl(), newLink.getDescription());
        link.setId(id);
        links.put(id, link);

        return id;
    }

    public Link updateLink (Link linkWithUpdatedData) {
        //récupère id du lien en param
        String id = linkWithUpdatedData.getId();

        //récupère lien de la persistance
        Link linkToUpdate = links.get(id);

        //change les données du lien
        linkToUpdate.setTitle(linkWithUpdatedData.getTitle());
        linkToUpdate.setUrl(linkWithUpdatedData.getUrl());
        linkToUpdate.setDescription(linkWithUpdatedData.getDescription());

        //renvoie le lien modifié
        return linkToUpdate;
    }

    public void deleteLink (String id) {
        links.remove(id);

    }


}
 