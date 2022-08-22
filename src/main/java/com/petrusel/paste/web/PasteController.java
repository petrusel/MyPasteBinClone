package com.petrusel.paste.web;

import com.petrusel.paste.model.Paste;
import com.petrusel.paste.service.PasteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PasteController {

    public final PasteService pasteService;

    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @GetMapping("/addText")
    public String showAddTextForm(Model model) {
        Paste paste = new Paste();
        model.addAttribute("paste", paste);
        System.out.println("PasteController formular paste");
        return "add_text";
    }

    @PostMapping("/addText")
    public String saveNewText(@ModelAttribute("paste") Paste paste, Principal principal) {
        String email = principal.getName();
        System.out.println("PasteController save paste");
        pasteService.addNewPaste(email, paste);
        return "redirect:/allText";
    }

    @GetMapping("/allText")
    public String showAllPastes(Model model, Principal principal) {
        String email = principal.getName();
        System.out.println("PasteController afisam toate pastes");
        List<Paste> allUserPastes = pasteService.getAllPastesByUserEmail(email);
        model.addAttribute("allUserTexts", allUserPastes);
        return "all_text";
    }

    @GetMapping("/updateText/{id}")
    public String showUpdatePasteForm(@PathVariable Long id, Model model) {
        Paste paste = pasteService.getPasteById(id);
        model.addAttribute("paste", paste);
        System.out.println("PasteController paste formular update");
        return "update_text";
    }

    @PostMapping("/updateText/{id}")
    public String saveUpdatedText(@PathVariable Long id, Paste paste) {
        System.out.println("PasteController paste save update");
        pasteService.updatePaste(id, paste);
        return "redirect:/allText";
    }

    @GetMapping("/deleteText/{id}")
    public String deletePaste(@PathVariable Long id) {
        System.out.println("PasteController sterge paste");
        pasteService.deletePaste(id);
        return "redirect:/allText";
    }

    @GetMapping("/showText/{id}")
    public String showPasteDetails(@PathVariable Long id, Model model) {
        Paste paste = pasteService.getPasteById(id);
        model.addAttribute("paste", paste);
        System.out.println("PasteController afisam detalii paste");
        return "show_text";
    }
}
