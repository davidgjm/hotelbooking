package com.jinjiang.hotel.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.GenericTest;
import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.service.impl.CarRentalServiceImpl;

public class CarRentalServiceTest {
	private CarRentalService service=CarRentalServiceImpl.getInstance();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveOrUpdateCarRental() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCars() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCarUsages() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCarRentalMatrixByUsage() {
		Map<String, List<CarRental>> matrix=service.getCarRentalMatrixByUsage();
		assertNotNull(matrix);
		Set<String> usages=matrix.keySet();
		for (String u : usages) {
			System.out.println(matrix.get(u));
		}
	}

}
