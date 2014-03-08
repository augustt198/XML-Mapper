package im.prox.mapper.utils;


import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementUtils {

	/*
	For navigating through an element with a path string like this:
	tag1.tag2.tag3
	 */
	public static Element resolvePath(Element root, String path) {
		String[] parts = path.split("\\.");
		Element element = root;
		for(String tag : parts) {
			element = element.element(tag);
		}
		return element;
	}

	/*
	Returns all sub-elements of the given element
	 */
	public static List<Element> getElements(Element element) {
		List<Element> elements = new ArrayList<>();
		for(Object o : element.elements()) {
			if(o instanceof Element) {
				elements.add((Element) o);
			}
		}
		return elements;
	}

	/*
	Returns all sub-elements of the given element with the given tag name
	 */
	public static List<Element> getElements(Element element, String name) {
		List<Element> elements = new ArrayList<>();
		for(Object o : element.elements(name)) {
			if(o instanceof Element) {
				element.add((Element) o);
			}
		}
		return elements;
	}


}
