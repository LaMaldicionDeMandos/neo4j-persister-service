package org.pasut.neo4j.persister;

import java.util.Map;

interface Mapper {
	<T> Map<String, Object> toMap(T value);
	<T> T toObject(Map<String, Object> map, Class<T> clazz);
}
