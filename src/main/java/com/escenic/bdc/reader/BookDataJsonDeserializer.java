package main.java.com.escenic.bdc.reader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import main.java.com.escenic.bdc.json.beans.BookJson;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * A custom deserializer that will deserialize json data and validate them
 * @author Shajedul Islam
 *
 */
public class BookDataJsonDeserializer implements JsonDeserializer<BookJson> {

	List<String> requiredFields = new ArrayList<String>();

	void registerRequiredField(String fieldName) {
		requiredFields.add(fieldName);
	}

	@Override
	public BookJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		JsonObject jsonObject = (JsonObject) json;
		for (String fieldName : requiredFields) {
			if (jsonObject.get(fieldName) == null) {
				throw new JsonParseException("Error: Required Field Not Found: " + fieldName + "!");
			}
		}
		return new Gson().fromJson(json, BookJson.class);
	}

}
