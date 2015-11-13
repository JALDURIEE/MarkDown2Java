package com.pingan.cong.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public enum Type {

	INTEGER("int"), LONG("long"), SHORT("short"), FLOAT("float"), DOUBLE("double"), BYTE("byte"), BOOLEAN(
			"boolean"), CHAR("char"), STRING("String"), LIST("List"), OBJECT("Object"), NULL("null");

	private String name;
	


	Type(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Type getTypeFromElement(JsonElement jsonElement) {
		if (jsonElement.isJsonArray()) {
			return Type.LIST;
		} else if (jsonElement.isJsonObject()) {
			return Type.OBJECT;
		} else if (jsonElement.isJsonPrimitive()) {
			return getPrimitiveType(jsonElement.getAsJsonPrimitive());
		} else {
			return Type.NULL;
		}
	}

	public static Type getPrimitiveType(JsonPrimitive primitive) {
		if (primitive.isNumber()) {
			Number number = primitive.getAsNumber();

			try {
				Integer.parseInt(number.toString());
				return Type.INTEGER;
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				return Type.DOUBLE;
			}
			
		} else if (primitive.isBoolean()) {
			return Type.BOOLEAN;
		} else {
			return Type.STRING;
		}
	}



}
