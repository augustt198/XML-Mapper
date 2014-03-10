package im.prox.mapper.interpret.presets;

import im.prox.mapper.exception.InterpretationException;
import im.prox.mapper.interpret.Interpreter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInterpreter extends Interpreter<Date> {

	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public DateInterpreter() {
		super(Date.class);
	}

	public Date convert(String date) throws InterpretationException {
		try {
			return format.parse(date);
		} catch(ParseException e) {
			throw new InterpretationException("Error occurred while parsing Date String");
		}
	}

	public void setDateFormat(String s) {
		format = new SimpleDateFormat(s);
	}

}
