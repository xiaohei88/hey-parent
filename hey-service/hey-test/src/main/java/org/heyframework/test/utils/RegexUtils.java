package org.heyframework.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static void main(String[] args) {

		StringBuilder str = new StringBuilder();
		str.append(
				"<td width=\"20%\"><pm:editfield id=\"VNMT_ID\" SUBSQL=\"select vnmt_id,company_name from cm_vnmt\" attributesText=\"onchange='EditField_Save(this)'\"/></td>");
		str.append("<pm:datagrid id=\"AM_LIBR\" view=\"SELECT LIBR_ID,A.CODE,LIBR_NAME,COMPANY_NAME,a.VNMT_ID,LIBR_DATE FROM AM_LIBR a,CM_VNMT b "
				+ "WHERE a.vnmt_id=b.vnmt_id and PROJ_ID='${sessionScope._ProjectId}'\" datactr=\"\">");
		List<String> list = match(str.toString(), "subsql");
		System.out.println(list);
		List<String> list1 = match(str.toString(), "view");
		System.out.println(list1);
	}

	public static List<String> match(String source, String attr) {
		List<String> result = new ArrayList<String>();
		String reg = "(?i)" + attr + "=\"([^\"]+)\"";
		Matcher m = Pattern.compile(reg).matcher(source);
		while (m.find()) {
			// String r = m.group(); [SUBSQL="select vnmt_id,company_name from
			// cm_vnmt"]
			String r = m.group(1);// [select vnmt_id,company_name from cm_vnmt]
			result.add(r);
		}
		return result;
	}
}
