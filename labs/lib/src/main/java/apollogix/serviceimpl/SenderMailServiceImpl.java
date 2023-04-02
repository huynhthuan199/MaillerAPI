package apollogix.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.apache.commons.lang3.StringUtils;

import apollogix.model.AppConfig;
import apollogix.model.AppFilter;
import apollogix.service.SenderMailService;
import apollogix.validation.CustomDateTerm;
import apollogix.validation.ValidationCommon;
import lombok.extern.slf4j.Slf4j;

/**
 * SenderMailServiceImpl <br/>
 * <p>
 * Responsible for checking email and downloading files
 * </p>
 * @author ThuanNH
 */
@Slf4j
public class SenderMailServiceImpl implements SenderMailService {

	private AppConfig appConfig;

	private Folder emailFolder;

	private Properties properties = null;

	public SenderMailServiceImpl(AppConfig appConfig) {

		this.appConfig = appConfig;
		properties = new Properties();
	}

	/**
	 * 
	 */
	@Override
	public List<String> readMailGetMultiPartFile() throws ParseException {

		List<String> result = new ArrayList<>();

		properties.put("mail.pop3.host", appConfig.getStrHost());
		properties.put("mail.pop3.port", appConfig.getIntPort());
		properties.put("mail.pop3.socketFactory", appConfig.getIntPort());
		properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session emailSession = Session.getDefaultInstance(properties);

		try {
			Store store = emailSession.getStore(appConfig.getStrProtocol());
			store.connect(appConfig.getStrHost(), appConfig.getStrUsername(), appConfig.getStrPassword());
			emailFolder = store.getFolder(appConfig.getAppFilter().getStrFolder());

			emailFolder.open(Folder.READ_ONLY);
			Message[] messages = null;

			if (StringUtils.isNotBlank(appConfig.getAppFilter().getStrSubject())
					&& StringUtils.isNotBlank(appConfig.getAppFilter().getStrSender())) {
				messages = emailFolder.search(this.getSearchTerm(appConfig.getAppFilter()));
			} else {
				messages = emailFolder.getMessages();
			}
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				Object content = message.getContent();

				//Only get file multipart in content
				if (!Objects.isNull(content) && !(content instanceof String)) {

					String strAbsolutePath = this.getFileMultipart(message.getContent());
					if (StringUtils.isNotBlank(strAbsolutePath)) {
						result.add(strAbsolutePath);
					}
				}
			}
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			try {
				emailFolder.close();
			} catch (MessagingException e) {
				log.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * Download file in folder temporary to OS
	 * @param message            - content for mail
	 * @return String            - absolute path for temporary file
	 * @throws IOException
	 * @throws MessagingException
	 */
	private String getFileMultipart(Object message) throws IOException, MessagingException {
		File f = null;
		FileOutputStream fos = null;
		Multipart multipart = (Multipart) message;
		try {
			for (int j = 0; j < multipart.getCount(); j++) {
				BodyPart bodyPart = multipart.getBodyPart(j);
				String strFileName = bodyPart.getFileName();

				//dealing with attachments only and prefix file
				if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) &&
						StringUtils.isNotBlank(bodyPart.getFileName())
						&& ValidationCommon.predicateCheckPrefix.test(strFileName,
								appConfig.getAppFilter().getStrPrefix())) {
					InputStream is = bodyPart.getInputStream();

					f = new File(System.getProperty("java.io.tmpdir") + bodyPart.getFileName());
					byte[] buf = new byte[4096];
					fos = new FileOutputStream(f);
					int bytesRead;
					while ((bytesRead = is.read(buf)) != -1) {
						fos.write(buf, 0, bytesRead);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			if (!Objects.isNull(fos)) {
				fos.close();
			}
		}

		return Objects.isNull(f) ? StringUtils.EMPTY : f.getAbsolutePath();
	}

	/**
	 * Search mail by condition
	 * @param filter            - Setting filter for library
	 * @return SearchTerm
	 * @throws AddressException
	 */
	private SearchTerm getSearchTerm(AppFilter filter) throws AddressException {

		return new AndTerm(new SubjectTerm(filter.getStrSubject()),
				new AndTerm(new FromTerm(new InternetAddress(filter.getStrSender())),
						new CustomDateTerm(ComparisonTerm.EQ, new Date())));
	}
}
