package com.ust.mobilejdbc.util;

import com.ust.mobilejdbc.dto.IMobileDAO;
import com.ust.mobilejdbc.dto.MobileDAO;

public class MobileManager {
public static IMobileDAO getContactInfo() {
	return new MobileDAO();
}
}
