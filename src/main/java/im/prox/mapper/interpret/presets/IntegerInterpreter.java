package im.prox.mapper.interpret.presets;

import im.prox.mapper.interpret.InterpretationException;
import im.prox.mapper.interpret.Interpreter;
import org.dom4j.Element;

public class IntegerInterpreter extends Interpreter<Integer> {

	public IntegerInterpreter(Class type) {
		super(type);
	}

	public Integer convert(Element e) throws InterpretationException {
		String text = e.getText();
		if(text == null) throw new InterpretationException("Material tag contains no text");
		try {
			return Integer.parseInt(text);
		} catch(NumberFormatException ex) {
			throw new InterpretationException("Unable to parse integer :" + text);
		}
	}

}
