package populationcensus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import populationcensus.Consts;
import populationcensus.repository.entity.security.User;
import populationcensus.service.interfaces.security.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RequestsListPageController {

    //private final UserService userService;

    @GetMapping("*/requestsList")
    public String load(Model model){
        List<Object> requests = new LinkedList<>(); //userService.findOnlyCustom();
        model.addAttribute("requests", requests);
        return "requestsListPage";
    }

    @PostMapping("/createRequest")
    public String createRequest(HttpServletRequest req){
        return "redirect:" + req.getHeader("referer") + "/createRequest";
    }
}
