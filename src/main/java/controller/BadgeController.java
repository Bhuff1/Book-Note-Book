package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.*;
import badgebook.BadgeObject;

@Controller	
public class BadgeController {
	
	@GetMapping("/badges")
    public String formGet(Model model) {
        BadgeService badgeService = new BadgeService();
        ArrayList<BadgeObject> badges = badgeService.getBadges(1);
        model.addAttribute("badges", badges);
        return "badges";
    }
	
	@PostMapping("/badges")
    public String formPost(Model model) {
        return "badges";
    }  

}
