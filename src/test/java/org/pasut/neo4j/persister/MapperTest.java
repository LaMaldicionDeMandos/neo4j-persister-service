package org.pasut.neo4j.persister;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MapperTest {
	private Mapper mapper = new JacksonMapper();
	@Before
	public void setUp(){}

	@Test
	public void toMapSimpleObject() {
		SimpleObject object = new SimpleObject(24, "Aída");
		Map<String, Object> map = mapper.toMap(object);
		assertEquals(object.getNumber(), map.get("number"));
		assertEquals(object.getName(), map.get("name"));
	}

}
