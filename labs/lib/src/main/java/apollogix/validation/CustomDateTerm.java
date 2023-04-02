package apollogix.validation;

import java.util.Calendar;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.DateTerm;

/**
 * CustomDateTerm <br/>
 * <p>
 * Class custom follow business
 * </p>
 * @author ThuanNH
 */
public class CustomDateTerm extends DateTerm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomDateTerm(int comparison, Date date) {
		super(comparison, date);
	}

	@Override
	public boolean match(Message msg) {
		try {

			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal1.setTime(this.date);
			cal2.setTime(msg.getSentDate());

			return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
					cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

		} catch (MessagingException e) {
			return false;
		}
	}

}
