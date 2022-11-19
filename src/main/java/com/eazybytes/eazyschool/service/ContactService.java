package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.controller.ContactController;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static Logger log = LoggerFactory.getLogger(ContactService.class.getName());

    public ContactService() {
        System.out.println("Contact Service bean initialized");
    }

    public boolean saveMessageDetail(Contact contact) {
        boolean isSaved = true;
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0)
            isSaved = true;
        return isSaved;
    }

    public List<Contact> findOpenMessages() {
//        return contactRepository.findMessagesByStatus(EazySchoolConstants.OPEN);
        return null;
    }

    public boolean updateMessageStatus(int id, String updatedBy) {
        boolean isUpdated = false;
//        int result = contactRepository.updateMessageStatus(id, EazySchoolConstants.CLOSE, updatedBy);
//        if (result > 0)
//            isUpdated = true;

        return isUpdated;
    }


}
