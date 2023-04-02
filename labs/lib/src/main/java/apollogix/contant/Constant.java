package apollogix.contant;

import java.util.Arrays;

public class Constant {

	private Constant() {

	}

	public enum DataType {
		STRING("String"), INTEGER("Integer"), DOUBLE("Double"), LOCALDATETIME("LocalDateTime");

		private String strType;

		private DataType(String strType) {
			this.strType = strType;
		}

		public String getStrType() {
			return strType;
		}

		public static DataType valueOfEnum(String s) throws IllegalArgumentException {
			return Arrays.stream(DataType.values())
					.filter(v -> v.strType.equals(s))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
		}
	}
}
