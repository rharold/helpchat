package com.spx.help;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import com.google.api.translate.TranslateV1;
import com.spx.help.connection.ChatHelper;
import com.spx.help.connection.ChatStatus;
import com.spx.help.connection.CometDMessageListener;
import com.spx.help.connection.ConnectionManager;
import com.spx.help.connection.UiMessage;
import com.spx.help.connection.UserChats;
import com.spx.help.connection.UserMessages;
import com.spx.help.language.LanguageFromTo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final String USER = "user";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ChatHelper chatHelper;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		String user = (String) session.getAttribute(USER);
		if(user == null) {
			return "login";
		}
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sdd", method = RequestMethod.GET)
	public String sdd(Locale locale, Model model, HttpSession session) {
		String user = (String) session.getAttribute(USER);
		
		return "sdd";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/vsoh", method = RequestMethod.GET)
	public String vsoh(Locale locale, Model model, HttpSession session) {
		String user = (String) session.getAttribute(USER);
		
		return "vsoh";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpSession session, HttpServletRequest request) throws Exception {
		String user = (String) session.getAttribute(USER);
		if(user != null) {
			ConnectionManager.getInstance().logout(user);			
			UserChats.removeUser(user);
			session.removeAttribute(USER);
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model, HttpSession session, HttpServletRequest request, 
			@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
		
		String user = (String) session.getAttribute(USER);
		XMPPConnection connection = null;
		
		if(user == null) {
			CometDMessageListener cometDMessageListener = 
					(CometDMessageListener) session.getServletContext().getAttribute(CometDMessageListener.class.getName());
			
			
			try {
				connection = ConnectionManager.getInstance().login(email, password, cometDMessageListener);
				session.setAttribute(USER, connection.getUser());
				chatHelper.setStatus(ChatStatus.ONLINE, connection);
			} catch (XMPPException e) {
				e.printStackTrace();
				return "login";
			}
		} else {
			connection = ConnectionManager.getInstance().getLoggedInConnection(user);	
		}
		
		
		model.addAttribute("selectedStatus", ChatStatus.ONLINE.getStatus());
		model.addAttribute("statuss", ChatStatus.getStatuss());
		
		List<RosterEntry> availableFriends = chatHelper.getAvailableFriends(connection);
		List<RosterEntry> unavailableFriends = chatHelper.getUnavailableFriends(connection);
		
		model.addAttribute("availableFriends", availableFriends);
		model.addAttribute("unavailableFriends", unavailableFriends);
		model.addAttribute("name", connection.getAccountManager().getAccountAttribute("name"));
		model.addAttribute("user", connection.getUser());
		model.addAttribute("languagesFromTo", LanguageFromTo.getLanguagesFromTo());
		
	    return "home";
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	public void changeStatus(Locale locale, Model model, HttpSession session, 
			@RequestParam("status") String status) throws Exception {
		
		String user = (String) session.getAttribute(USER);
		XMPPConnection connection = ConnectionManager.getInstance().getLoggedInConnection(user);
		chatHelper.setStatus(ChatStatus.fromString(status), connection);
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public void message(Locale locale, Model model, HttpSession session, 
			@RequestParam("user") String userToMessage, @RequestParam("message") String message) throws Exception {
		
		String user = (String) session.getAttribute(USER);
		XMPPConnection connection = ConnectionManager.getInstance().getLoggedInConnection(user);
		
		Chat chat = UserChats.getChat(user, userToMessage);
		if(chat == null) {
			CometDMessageListener cometDMessageListener = 
					(CometDMessageListener) session.getServletContext().getAttribute(CometDMessageListener.class.getName());
			chat = chatHelper.initialiseChat(connection, userToMessage, cometDMessageListener);			
		} 
		
		chatHelper.sendMessage(chat, message);
		
	}
	
	@RequestMapping(value = "/getTranslation", method = RequestMethod.GET)
	public @ResponseBody String getTranslation(
			Locale locale, Model model, HttpSession session, 
			@RequestParam("message") String message, 
			@RequestParam("fromTo") String fromTo) throws Exception {
		
		String[] languages = StringUtils.split(fromTo, "|");
		
		GoogleAPI.setHttpReferrer("http://localhost");
	    GoogleAPI.setKey("AIzaSyCdATzv2LE_VkElOhcikcY9_VB9XRr7aeg");

	    String translatedText = TranslateV1.DEFAULT.execute(message, Language.fromString(languages[0]), 
	    		Language.fromString(languages[1]));
	    
	    return translatedText;
	}
	
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public @ResponseBody UiMessage receive(Locale locale, Model model, HttpSession session) throws Exception {
		String user = (String) session.getAttribute(USER);

		List<UiMessage> messages = new ArrayList<UiMessage>();
		
		
		List<Chat> chats = UserChats.getChats(user);
		if(chats != null) {
			for (Chat chat : chats) {
				String threadId = chat.getThreadID();
				String message = UserMessages.getMessage(threadId);
				if(message != null && !"".equals(message)) {
					UiMessage uiMessage = new UiMessage();
					uiMessage.setMessage(message);
					uiMessage.setParticipent(chat.getParticipant());
					messages.add(uiMessage);
					return uiMessage;
				}
			}
		}
		
		return null;
	}
}
