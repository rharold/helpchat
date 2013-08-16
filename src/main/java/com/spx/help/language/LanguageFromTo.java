package com.spx.help.language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LanguageFromTo {

	private static List<LanguageFromTo> languagesFromTo = new ArrayList<LanguageFromTo>();
	private static List<String> supportedLanguages = new ArrayList<String>();
	private static Map<String, String> supportedLanguageDescriptions = new HashMap<String, String>();
	
	static {
		supportedLanguages.add("de");
		supportedLanguages.add("en");
		supportedLanguages.add("fr");
		supportedLanguages.add("ru");
		supportedLanguages.add("ja");
		supportedLanguages.add("jv");
		supportedLanguages.add("ko");
		supportedLanguages.add("zh");
		
		supportedLanguageDescriptions.put("de", "German");
		supportedLanguageDescriptions.put("en", "English");
		supportedLanguageDescriptions.put("fr", "French");
		supportedLanguageDescriptions.put("ru", "Russian");
		supportedLanguageDescriptions.put("ja", "Japanese");
		supportedLanguageDescriptions.put("jv", "Javanese");
		supportedLanguageDescriptions.put("ko", "Korean");
		supportedLanguageDescriptions.put("zh", "Chinese");

		for(String countryCodeFrom : supportedLanguages) {
			for(String countryCodeTo : supportedLanguages) {
				if(! countryCodeFrom.equals(countryCodeTo)) {
					
					LanguageFromTo languageFromTo = new LanguageFromTo(countryCodeFrom, countryCodeTo, 
							supportedLanguageDescriptions.get(countryCodeFrom), supportedLanguageDescriptions.get(countryCodeTo));
					
					languagesFromTo.add(languageFromTo);
				}
			}
		}
					
	}
	
	private String from;
	private String to;
	
	private String languageDescriptionFrom;
	private String languageDescriptionTo;
	
	private LanguageFromTo(String from, String to, String languageDescriptionFrom, String languageDescriptionTo) {
		this.from = from;
		this.to = to;
		
		this.languageDescriptionFrom = languageDescriptionFrom;
		this.languageDescriptionTo = languageDescriptionTo;
	}

	public static List<LanguageFromTo> getLanguagesFromTo() {
		return languagesFromTo;
	}
	
	public String getDisplayString() {
	    return languageDescriptionFrom + " to " + languageDescriptionTo;	
	}
	
	public String getValueString() {
	    return from + "|" + to;	
	}
	
}
