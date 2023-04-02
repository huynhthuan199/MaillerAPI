package apollogix.service;

import java.text.ParseException;
import java.util.List;

public interface SenderMailService {
	
	List<String> readMailGetMultiPartFile() throws ParseException;
}
