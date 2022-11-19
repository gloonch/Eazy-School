package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class.getName());

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg" ,method = RequestMethod.POST)
//    public ModelAndView saveMessage(Contact contact) {
//        contactService.setCounter(contactService.getCounter() + 1);
//        contactService.saveMessageDetail(contact);
//        log.info("Number of times the Contact form is submitted : " + contactService.getCounter());
//        return new ModelAndView("redirect:/contact");
//    }

    @RequestMapping(value = "/saveMsg" ,method = RequestMethod.POST)
    public String saveMessage(@ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()){
            log.error("Contact from validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetail(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model) {
        List<Contact> contactList = contactService.findOpenMessages();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMessages", contactList);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMessage", method = RequestMethod.GET)
    public String closeMessage(@RequestParam int id, Authentication authentication) {
        contactService.updateMessageStatus(id, authentication.getName());
        return "redirect:;/displayMessages";
    }
}
