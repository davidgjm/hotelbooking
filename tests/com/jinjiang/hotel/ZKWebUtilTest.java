package com.jinjiang.hotel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZKWebUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteImage() {
		String path="/upload/images/7c619cd6-0763-43d3-abe4-6eda552f2047.jpg";
		boolean result=ZKWebUtil.deleteImage(path);
		assertTrue(result);
	}

	@Test
	public void testUploadImage() {
		fail("Not yet implemented"); // TODO
	}

}
