package utils;

public class Validate {

	// validate ky tu la 1 so
	public static boolean validateNumber(String id) {
		if (id == null)
			return false;
		return id.matches("^\\d+");
	}

	// min 6
	public static boolean validatePassword(String password) {
		if (password == null)
			return false;
		return password.length() >= 6;
	}
	
	/*
	 * Dinh dang: A@B.C hoac A@B.C.D A va B gom chu cai (hoa+thuong),chu so, dau
	 * gach ngang("-","_") A co them dau "." C va D (co the co E,F,.... phia sau
	 * D)chi gom ky tu thuong ,co tong ky tu tu 2->6
	 */
	public static boolean validateEmail(String email) {
		if (email == null)
			return false;
		return email
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,6})$");
	}
}
