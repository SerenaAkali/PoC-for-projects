package promo.demo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class PromotionCode {
	private String codeId;
	private String codeNumber;
	private LocalDateTime dueDate;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(int bit) {
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < bit; i++) {
			int num = rand.nextInt(62);
			sb.append(str.charAt(num));
		}
		this.codeNumber = sb.toString();
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
}
