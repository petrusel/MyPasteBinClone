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
        return "add_text";
    }

    @PostMapping("/addText")
    public String saveNewText(@ModelAttribute("paste") Paste paste, Principal principal) {
        String email = principal.getName();
        pasteService.addNewText(email, paste);
        return "redirect:/allText";
    }

    @GetMapping("/allText")
    public String showAllText(Model model, Principal principal) {
        String email = principal.getName();
        List<Paste> allUserTexts = pasteService.getAllTextsByUserEmail(email);
        model.addAttribute("allUserTexts", allUserTexts);
        return "all_text";
    }

    @GetMapping("/updateText/{id}")
    public String showUpdateTextForm(@PathVariable Long id, Model model) {
        Paste paste = pasteService.getTextById(id);
        model.addAttribute("paste", paste);
        return "update_text";
    }

    @PostMapping("/updateText/{id}")
    public String saveUpdatedText(@PathVariable Long id, Paste paste) {
        pasteService.updateText(id, paste);
        return "redirect:/allText";
    }

    @GetMapping("/deleteText/{id}")
    public String deleteText(@PathVariable Long id) {
        pasteService.deleteText(id);
        return "redirect:/allText";
    }

    @GetMapping("/showText/{id}")
    public String showSpecificText(@PathVariable Long id, Model model) {
        Paste paste = pasteService.getTextById(id);
        model.addAttribute("paste", paste);
        return "show_text";
    }
}
