package cho.me.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cho.me.app.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cho.me.app.mapper.MemberMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MemberMapper memberMapper;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/test")
	public @ResponseBody String test() {
		return "test";
	}
	
	@RequestMapping(value = "/test2")
	public @ResponseBody String test1() {
		return "Hi User";
	}
	
	@RequestMapping(value = "/getUser")
    public @ResponseBody String getUser(@RequestParam String userId) {
		System.out.println("userId : "+userId);
		return memberMapper.getMember().getEmail();
    }


	@RequestMapping(value = "/getUser2", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody String getUser(HttpServletRequest request) {

		Map<String, String[]> parameterMap = request.getParameterMap();

		Map<String, Object> paramMap = new HashMap<String,Object>();

		for (String key : parameterMap.keySet()) {
			System.out.println(key);
			paramMap.put(key, parameterMap.get(key)[0]);
		}
		System.out.println(paramMap);

		return memberMapper.getMember().getEmail();
	}

		@RequestMapping(value = "/putUser")
	public  String putUser(@RequestBody MemberDto memberDto) {
		//memberMapper.putMember();
		return "OK";
	}
/*
	@RequestMapping(value = "/putUser2")
	public @ResponseBody String putUser2() {
		memberMapper.putMember();
		return "OK";
	}


	@RequestMapping(value = "/posttest")
	public String createUser(@RequestBody MemberDto memberDto) {

		return "Ok";
	}
	*/



	
}
