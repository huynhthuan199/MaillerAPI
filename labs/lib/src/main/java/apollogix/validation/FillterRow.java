package apollogix.validation;

import java.time.LocalDateTime;

import apollogix.model.DataRowExcel;

/**
 * FillterRow <br/>
 * <p>
 * Use extends filter by maintainer
 * </p>
 * @author ThuanNH
 */
public final class FillterRow {

	private FillterRow() {

	}

	public static final int LE = 1;
	public static final int LT = 2;
	public static final int EQ = 3;
	public static final int NE = 4;
	public static final int GT = 5;
	public static final int GE = 6;

	public static final boolean checkETAbyCond(DataRowExcel row, Integer cond) {

		LocalDateTime now = LocalDateTime.now();
		switch (cond) {
		case LE:
			return compareToNow(now, row.getLdtEta()) <= 0;
		case LT:
			return compareToNow(now, row.getLdtEta()) < 0;
		case EQ:
			return compareToNow(now, row.getLdtEta()) == 0;
		case NE:
			return compareToNow(now, row.getLdtEta()) != 0;
		case GT:
			return compareToNow(now, row.getLdtEta()) > 0;
		case GE:
			return compareToNow(now, row.getLdtEta()) >= 0;
		default:
			throw new IllegalArgumentException("Unexpected value: " + cond);
		}
	}

	private static int compareToNow(LocalDateTime now, LocalDateTime otherDate) {
		int cmp = (now.getYear() - otherDate.getYear());
		if (cmp == 0) {
			cmp = (now.getMonthValue() - otherDate.getMonthValue());
			if (cmp == 0) {
				cmp = (now.getDayOfYear() - otherDate.getDayOfYear());
			}
		}
		return cmp;
	}
}
