package im.prox.mapper;

import im.prox.mapper.exception.MissingRequiredFieldException;
import im.prox.mapper.mapping.Mappable;
import im.prox.mapper.mapping.MappedObject;
import im.prox.mapper.mapping.MappingException;
import im.prox.mapper.mapping.MappingFactory;
import org.dom4j.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class XMLMapper {

	/**
	 * Main entry point for XML to Object mapping
	 */
	public Mappable parse(Mappable type, Element base) throws MappingException {
		MappedObject mapped = new MappedObject(type);
		try {
			return MappingFactory.map(mapped, base);
		} catch(MissingRequiredFieldException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Mappable parse(Class<? extends Mappable> type, Element base) throws MappingException {
		Constructor<?> constructor = type.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		Object object;
		try {
			object = constructor.newInstance();
		} catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new MappingException("Could not create instance of type to be mapped");
		}
		return parse((Mappable) object, base);
	}

}