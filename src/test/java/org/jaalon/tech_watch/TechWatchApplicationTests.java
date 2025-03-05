package org.jaalon.tech_watch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class TechWatchApplicationTests {
	@Autowired
	LinksLibraryController linksLibraryController;

	@Test
	void contextLoads() {
	}

	@Test
	void testLinkCreation() {
		Link linkToCreate = new Link("Micode new video", "ramdom youtube url", "une video super intéressante !");

		String newLinkId = linksLibraryController.saveLink(linkToCreate);

		Assertions.assertNotNull(newLinkId);
		Link linkToCompare = linksLibraryController.getLinkById(newLinkId);
		Assertions.assertNotNull(linkToCompare);

		Assertions.assertEquals(linkToCreate.getTitle(), linkToCompare.getTitle());
		Assertions.assertEquals(linkToCreate.getUrl(), linkToCompare.getUrl());
		Assertions.assertEquals(linkToCreate.getDescription(), linkToCompare.getDescription());

	}

	@Test
	void testLinkUpdate() {
		Link linkToUpdate = new Link("Chinchillas will rule the world tomorrow !", "www.fluffy-and-cute.com", "Wouah c'est trop mignon !");

		String existingLinkId = linksLibraryController.saveLink(linkToUpdate);

		Link updatedLinkSentByUser = new Link("Chinchillas will rule the world tomorrow..?", "www.chins-are-the-masters-of-our-future.org", "Wouah c'est incroyable !");
		updatedLinkSentByUser.setId(existingLinkId);

		Link updatedLink = linksLibraryController.updateLink(updatedLinkSentByUser);

		Assertions.assertNotNull(updatedLink);

		Assertions.assertNotNull(updatedLink.getId());
		Assertions.assertEquals(existingLinkId, updatedLink.getId());
		Assertions.assertEquals(updatedLinkSentByUser.getTitle(), updatedLink.getTitle());
		Assertions.assertEquals(updatedLinkSentByUser.getUrl(), updatedLink.getUrl());
		Assertions.assertEquals(updatedLinkSentByUser.getDescription(), updatedLink.getDescription());

		//on veut vérifier que le lien mis à jour a bien été persisté
		Link jesaispas = linksLibraryController.getLinkById(existingLinkId);

		Assertions.assertNotNull(jesaispas);
		Assertions.assertEquals(updatedLinkSentByUser.getTitle(), jesaispas.getTitle());
		Assertions.assertEquals(updatedLinkSentByUser.getUrl(), jesaispas.getUrl());
		Assertions.assertEquals(updatedLinkSentByUser.getDescription(), jesaispas.getDescription());
	}

	@Test
	void testLinkDelete() {
		Link linkToDelete = new Link("Funny cats video", "www.silly-videos.com", "MDR");
		Link linkToKeep = new Link("Programming best practices", "www.very-serious-blog.net", "Je vais être le futur génie du dev, youpi !");
		String linkToDeleteId = linksLibraryController.saveLink(linkToDelete);
		String linkToKeepId = linksLibraryController.saveLink(linkToKeep);

		linksLibraryController.deleteLink(linkToDeleteId);

		Assertions.assertNotNull(linksLibraryController.getLinkById(linkToKeepId));
		Assertions.assertNull(linksLibraryController.getLinkById(linkToDeleteId));
	}

	@Test
	void testGetAllLinks() {
		Link link1 = new Link("Chinchillas will rule the world tomorrow !", "www.fluffy-and-cute.com", "Wouah c'est trop mignon !");
		Link link2 = new Link("Funny cats video", "www.silly-videos.com", "MDR");
		Link link3 = new Link("Micode new video", "ramdom youtube url", "une video super intéressante !");
		Link link4 = new Link("Programming best practices", "www.very-serious-blog.net", "Je vais être le futur génie du dev, youpi !");
		String link1Id = linksLibraryController.saveLink(link1);
		String link2Id = linksLibraryController.saveLink(link2);
		String link3Id = linksLibraryController.saveLink(link3);
		String link4Id = linksLibraryController.saveLink(link4);

		Map<String, Link> links = linksLibraryController.getAllLinks();

		Assertions.assertNotNull(links);
		Assertions.assertEquals(4, links.size());

		Assertions.assertEquals(link1Id, linksLibraryController.getLinkById(link1Id).getId());
		Assertions.assertEquals(link1.getTitle(), linksLibraryController.getLinkById(link1Id).getTitle());
		Assertions.assertEquals(link1.getUrl(), linksLibraryController.getLinkById(link1Id).getUrl());
		Assertions.assertEquals(link1.getDescription(), linksLibraryController.getLinkById(link1Id).getDescription());

		Assertions.assertEquals(link2Id, linksLibraryController.getLinkById(link2Id).getId());
		Assertions.assertEquals(link2.getTitle(), linksLibraryController.getLinkById(link2Id).getTitle());
		Assertions.assertEquals(link2.getUrl(), linksLibraryController.getLinkById(link2Id).getUrl());
		Assertions.assertEquals(link2.getDescription(), linksLibraryController.getLinkById(link2Id).getDescription());

		Assertions.assertEquals(link3Id, linksLibraryController.getLinkById(link3Id).getId());
		Assertions.assertEquals(link3.getTitle(), linksLibraryController.getLinkById(link3Id).getTitle());
		Assertions.assertEquals(link3.getUrl(), linksLibraryController.getLinkById(link3Id).getUrl());
		Assertions.assertEquals(link3.getDescription(), linksLibraryController.getLinkById(link3Id).getDescription());

		Assertions.assertEquals(link4Id, linksLibraryController.getLinkById(link4Id).getId());
		Assertions.assertEquals(link4.getTitle(), linksLibraryController.getLinkById(link4Id).getTitle());
		Assertions.assertEquals(link4.getUrl(), linksLibraryController.getLinkById(link4Id).getUrl());
		Assertions.assertEquals(link4.getDescription(), linksLibraryController.getLinkById(link4Id).getDescription());
	}

}
