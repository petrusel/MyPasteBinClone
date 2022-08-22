package com.petrusel.paste.service;

import com.petrusel.paste.model.Paste;
import com.petrusel.paste.model.User;
import com.petrusel.paste.repository.PasteRepository;
import com.petrusel.paste.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasteService {

    private final PasteRepository pasteRepository;
    private final UserRepository userRepository;

    public PasteService(PasteRepository pasteRepository, UserRepository userRepository) {
        this.pasteRepository = pasteRepository;
        this.userRepository = userRepository;
    }

    public void addNewPaste(String email, Paste paste) {
        User loggedUser = userRepository.findByEmail(email);
        paste.setUser(loggedUser);
        System.out.println("PasteService addNewPaste()");
        pasteRepository.save(paste);
    }

    public void updatePaste(Long id, Paste paste) {
        Paste existingPaste = pasteRepository.getReferenceById(id);
        existingPaste.setTitle(paste.getTitle());
        existingPaste.setContent(paste.getContent());
        System.out.println("PasteService updatePaste()");
        pasteRepository.save(existingPaste);
    }

    public List<Paste> getAllPastesByUserEmail(String email) {
        User loggedUser = userRepository.findByEmail(email);
        System.out.println("PasteService getAllPastesByUserEmail()");
        return loggedUser.getPastes();
    }

    public Paste getPasteById(Long pasteId) {
        return pasteRepository.getReferenceById(pasteId);
    }

    public void deletePaste(Long textId) {
        Paste paste = pasteRepository.getReferenceById(textId);
        System.out.println("PasteService deletePaste()");
        pasteRepository.delete(paste);
    }
}
