package org.pasut.neo4j.persister;

import java.util.Objects;

public class SimpleObject {
	private final int number;
	private final String name;
	
	public SimpleObject(final int number, final String name) {
		this.number = number;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SimpleObject)) return false;
		SimpleObject s = (SimpleObject)o;
		return number == s.number && name.equals(s.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this);
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
}
