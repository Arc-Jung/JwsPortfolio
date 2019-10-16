package project.controller;

import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.Plan;
import project.domain.User;
import project.service.PlanService;
import project.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;

	// 마이페이지 -> 나의 플레너 보기를 위한 서비스 호출
	@Autowired
	private PlanService planService;
	
	@Autowired
	private JavaMailSenderImpl mailSender;


	@RequestMapping(value = "signup.do", method = RequestMethod.POST)
	public String signup(@RequestParam(value = "inputid") String userID,
			@RequestParam(value = "inputpassword") String userPW,
			@RequestParam(value = "inputpasswordconfirm") String userPWConfirm,
			@RequestParam(value = "inputname") String userName, @RequestParam(value = "inputtel") String userTel,
			@RequestParam(value = "inputnname") String userNName, @RequestParam(value = "inputmail") String userMail,
			User user, HttpSession session) throws Exception {

		user.setUserID(userID);
		user.setUserName(userName);
		user.setUserTel(userTel);
		user.setUserNName(userNName);
		user.setUserMail(userMail);

		if (userPW.equals(userPWConfirm)) {

			try {
				String encryptionPW = encryption(userPW);

				user.setUserPW(encryptionPW);
				service.insertUser(user);
			} catch (Exception e) {

				session.setAttribute("success", false);
				System.out.println("회원가입 오류 메시지 출력");
				return "project/alert/signupAlert";
			}

			System.out.println(userID + ", " + userPW + ", " + userName + ", " + userTel + ", " + userNName + ", "
					+ userMail + ", ");

			session.setAttribute("success", true);
		} else {
			session.setAttribute("success", false);
			System.out.println("컨트롤러측: 비밀번호 불일치!!");
			return "project/alert/signupAlert";
		}

		return "project/alert/signupAlert";
	}

	@RequestMapping(value = "signup.do", method = RequestMethod.GET)
	public String signup() {
		return "project/user/signup";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(@RequestParam(value = "loginid") String userID, @RequestParam(value = "loginpw") String userPW,
			HttpSession session) throws Exception {

		User user = service.loginUser(userID, encryption(userPW));

		if (user.getLogined() == true) {
			System.out.println("컨트롤러측: 로그인 성공!");

			session.setAttribute("loginID", userID);
			session.setAttribute("loginPW", encryption(userPW));
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userNName", user.getUserNName());
			session.setAttribute("userMail", user.getUserMail());
			session.setAttribute("userTel", user.getUserTel());
			session.setAttribute("userOrder", user.getUserOrder());
			session.setAttribute("isAdmin", user.getAdmin());

			session.setAttribute("success", true);

			System.out.println("회원정보 세션주입완료!");
			System.out.println("userID: " + userID + "\n" + "userPW: " + encryption(userPW) + "\n" + "userName: "
					+ user.getUserName() + "\n" + "userNName: " + user.getUserNName() + "\n" + "userMail: "
					+ user.getUserMail() + "\n" + "userTel: " + user.getUserTel() + "\n" + "userLogined: "
					+ user.getLogined() + "\n" + "userOrder: " + user.getUserOrder());

			return "project/alert/loginAlert";

		} else {
			System.out.println("컨트롤러측: 로그인 실패!");
			session.setAttribute("success", false);

			return "project/alert/loginAlert";
		}

	}

	@RequestMapping(value = "logout.do")
	public String logout(HttpSession session) {
		// 세션 초기화
		session.invalidate();

		return "redirect:/project/index.do";
	}

	@RequestMapping(value = "userinfo.do")
	public String userInfo(Model model, HttpSession session) throws Exception {
		// 나의 여행 계획 정보 받아오기
		System.out.println("컨트롤러측: 마이페이지 요청받음");

		String userID = (String) session.getAttribute("loginID");
		List<Plan> plans = planService.showAllPlanner(userID);
		model.addAttribute("plans", plans);

		return "project/user/userInfo";
	}

	@RequestMapping(value = "update.do")
	public String updateInfo(@RequestParam(value = "inputpassword") String userPW,
			@RequestParam(value = "inputname") String userName, @RequestParam(value = "inputtel") String userTel,
			@RequestParam(value = "inputnname") String userNName, @RequestParam(value = "inputmail") String userMail,
			User user, Model model, HttpSession session) throws Exception {

		if (userPW.equals("")) {
			session.setAttribute("updateSuccess", false);
			return "project/alert/updateAlert";
		} else {
			String userID = (String) session.getAttribute("loginID");

			userPW = encryption(userPW);

			user.setUserID(userID);
			user.setUserPW(userPW);
			user.setUserName(userName);
			user.setUserNName(userNName);
			user.setUserTel(userTel);
			user.setUserMail(userMail);

			service.updateUser(user);

			session.setAttribute("loginID", userID);
			session.setAttribute("loginPW", userPW);
			session.setAttribute("userName", userName);
			session.setAttribute("userNName", userNName);
			session.setAttribute("userMail", userMail);
			session.setAttribute("userTel", userTel);

			System.out.println("회원정보 세션주입완료!\n새로운 계정정보");

			System.out
					.println("userID: " + userID + "\n" + "userPW: " + userPW + "\n" + "userName: " + user.getUserName()
							+ "\n" + "userNName: " + user.getUserNName() + "\n" + "userMail: " + user.getUserMail()
							+ "\n" + "userTel: " + user.getUserTel() + "\n" + "userLogined: " + user.getLogined());

			session.setAttribute("updateSuccess", true);
			return "project/alert/updateAlert";
		}

	}

	@RequestMapping(value = "lost.do", method = RequestMethod.GET)
	public String lostAccount() {
		return "project/user/findUser";
	}

	@RequestMapping(value = "lost.do", method = RequestMethod.POST)
	public String lostAccount(@RequestParam(value = "inputid") String userID,
			@RequestParam(value = "inputname") String userName, @RequestParam(value = "inputmail") String userMail,
			HttpSession session) {
		
		boolean findPW = false;
		
		try {
			findPW = service.findUserPW(userID, userName, userMail);
			
			if(findPW==true)
			{
				
				try {
					String newPwd;
					newPwd = getNewPwd();
					String subject = "비밀번호를 알려드립니다";

					String msg = "";
					msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
					msg += "<h3 style='color: blue;'><strong>" + userID;
					msg += "님</strong>의 임시비밀번호 입니다. 로그인 후 비밀번호를 변경하세요.</h3>";
					msg += "<p> 임시 비밀번호: <strong>" + newPwd + "</strong></p></div";
					sendMail(userMail, subject, msg);

					service.changePW(userID, encryption(newPwd));
					
					session.setAttribute("success", 1);
					return "project/alert/sendMailAlert";

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("컨트롤러측: 계정찾기 기능 오류!!");
									
					e.printStackTrace();
					session.setAttribute("success", 3);
					return "project/alert/sendMailAlert";
				}
			}
			else
			{
				session.setAttribute("success", 2);
				System.out.println("계정정보 틀림");
			}
						
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println("컨트롤러측: 패스워드 찾기 DB오류 발생!");
			session.setAttribute("success", 3);
			
			e.printStackTrace();
			return "project/alert/sendMailAlert";
		}
		
		return "project/alert/sendMailAlert";

	}

	@RequestMapping(value = "deleteConfirm.do", method = RequestMethod.POST)
	public String delete(HttpSession session, Model model, String inputpassword) {

		inputpassword = encryption(inputpassword);

		String sessionPW = (String) session.getAttribute("loginPW");

		if (sessionPW.equals(inputpassword)) {
			try {
				service.deleteUser((String) session.getAttribute("loginID"), sessionPW);
				model.addAttribute("success", 1);
				session.invalidate();

				System.out.println("회원탈퇴 수행 완료!");
				return "project/alert/deleteUserAlert";
			} catch (Exception e) {
				model.addAttribute("success", 2);
				return "project/alert/deleteUserAlert";
			}
		} else {
			model.addAttribute("success", 3);
			return "project/alert/deleteUserAlert";
		}
	}

	public String encryption(String userPW) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(userPW.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			System.out.println("입력한암호: " + hexString.toString());

			return hexString.toString();
		} catch (Exception e) {
			return "error";
		}
	}
	
	

	public void sendMail(String email, String subject, String msg) throws Exception {

		String fromEmail = "chjcmy@gmail.com";
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator(){
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom(fromEmail);
				helper.setTo(email);
				helper.setSubject(subject);
				helper.setText(msg, true);
			}
		};


		try {
			
			mailSender.send(preparator);
			
			System.out.println(email+"로 메일 전송완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getNewPwd() throws Exception {

		char[] charSet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '~', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
				'#', '$', '%', '^', '&', '*', '(', ')', '_' };
		StringBuffer newKey = new StringBuffer();

        Random rand = new Random();

        for( int i = 0 ; i < 10 ; i++ ){
            newKey.append( charSet[ rand.nextInt( charSet.length ) ] );
        }

		return newKey.toString();

	}
}