/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import apollogix.FileMultipartMailReader;
import apollogix.model.AppConfig;
import apollogix.model.AppFilter;
import apollogix.model.DataRowExcelWithAnnotation;
import apollogix.model.Result;

public class App {

	public static void main(String[] args) throws ParseException {

		AppConfig email = new AppConfig();
		email.setStrHost("imap.gmail.com");
		email.setIntPort(993);
		email.setStrProtocol("imaps");
		email.setStrPassword("****");
		email.setStrUsername("*****@gmail.com");

		AppFilter appFilter = new AppFilter();
		appFilter.setStrFolder("INBOX");
		appFilter.setStrPrefix("vs");
		appFilter.setStrSender("support@1-stop.biz");
		appFilter.setStrSubject("Combined Vessel Schedule");

		email.setAppFilter(appFilter);

		FileMultipartMailReader<DataRowExcelWithAnnotation> service = new FileMultipartMailReader<>();

		List<Predicate<DataRowExcelWithAnnotation>> allCondition = new ArrayList<>();

		Predicate<DataRowExcelWithAnnotation> filterByEta = (
				DataRowExcelWithAnnotation row) -> ChronoUnit.DAYS.between(row.getLdtEta(), LocalDateTime.now()) == 7;

		allCondition.add(filterByEta);
		List<Result<DataRowExcelWithAnnotation>> results = service.convertRawFileToObject(email, allCondition,
				DataRowExcelWithAnnotation.class);

		for (Result<DataRowExcelWithAnnotation> res : results) {
			System.out.println(res.getStrFileName());
			for (DataRowExcelWithAnnotation row : res.getRowExcel()) {
				System.out.println(row);
			}
		}
	}
}
