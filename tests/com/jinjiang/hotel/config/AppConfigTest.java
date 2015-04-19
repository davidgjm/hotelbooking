package com.jinjiang.hotel.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppConfigTest {

	private AppConfig config=AppConfig.getInstance();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRepoPath() {
		String repodir=config.getString("repo.images.hotels.dir");
		assertTrue(repodir!=null && !repodir.isEmpty());
		System.out.println(repodir);
	}

}
