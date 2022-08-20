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

    public void addNewText(String email, Paste paste) {
        User loggedUser = userRepository.findByEmail(email);
        paste.setUser(loggedUser);
        pasteRepository.save(paste);
    }

    public void updateText(Long id, Paste paste) {
        Paste existingPaste = pasteRepository.getReferenceById(id);
        existingPaste.setTitle(paste.getTitle());
        existingPaste.setContent(paste.getContent());
        pasteRepository.save(existingPaste);
    }

    public List<Paste> getAllTextsByUserEmail(String email) {
        User loggedUser = userRepository.findByEmail(email);
        return loggedUser.getPastes();
    }

    public Paste getTextById(Long textId) {
        return pasteRepository.getReferenceById(textId);
    }

    public void deleteText(Long textId) {
        Paste paste = pasteRepository.getReferenceById(textId);
        pasteRepository.delete(paste);
    }
}
