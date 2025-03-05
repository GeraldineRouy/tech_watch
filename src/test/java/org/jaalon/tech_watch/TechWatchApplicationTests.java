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

}
