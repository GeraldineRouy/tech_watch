package org.jaalon.tech_watch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
