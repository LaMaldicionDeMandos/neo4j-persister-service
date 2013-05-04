package org.pasut.neo4j.persister;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import sun.reflect.ReflectionFactory;

final class JacksonMapper implements Mapper {
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> Map<String, Object> toMap(T value) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.convertValue(value, HashMap.class);
		return map;
	}
	
	@Override
	public <T> T toObject(Map<String, Object> map, Class<T> clazz) {
		Set<String> keys = map.keySet();
		final Constructor<T> constructor = getEmptyConstructor(clazz);
		try {
			T object = constructor.newInstance(new Object[0]);
			for(String key : keys) {
				Field field = clazz.getDeclaredField(key);
				setField(object, field, map.get(key));
			}
			return object;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void setField(Object object, Field field, Object value) {
		try {
			boolean accesible = field.isAccessible();
			field.setAccessible(true);
			field.set(object, value);
			field.setAccessible(accesible);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> Constructor<T> getEmptyConstructor(Class<T> clazz) {
		final ReflectionFactory reflection = ReflectionFactory.getReflectionFactory();
		try {
			final Constructor<T> constructor = reflection.newConstructorForSerialization(clazz,
					Object.class.getDeclaredConstructor(new Class[0]));
			return constructor;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	}

}
