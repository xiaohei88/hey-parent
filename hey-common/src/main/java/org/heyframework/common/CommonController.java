package org.heyframework.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("comm")
public class CommonController {

	/**
	 * 页面跳转(页面中央处理器)
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("/redirect")
	public String redirect(@RequestParam("url") String url) {
		return url;
	}
}
