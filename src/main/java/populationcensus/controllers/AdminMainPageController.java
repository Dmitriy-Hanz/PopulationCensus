package populationcensus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import populationcensus.Consts;
import populationcensus.dto.mapper.UserMapper;
import populationcensus.dto.security.UserDto;
import populationcensus.repository.entity.security.User;
import populationcensus.service.impl.security.UserDetailsImpl;
import populationcensus.service.interfaces.security.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@SessionAttributes("currentUser")
public class AdminMainPageController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(Consts.Url.$_ADMIN_MAIN)
    public String load(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = ((UserDetailsImpl)authentication.getPrincipal()).getUser();
        User currentUser = userService.findById(authenticatedUser.getId());
        if (currentUser.getUsername().equals("admin")){
            model.addAttribute("currentUser", userMapper.toSystemUserDto(currentUser));
            return "adminMainPage";
        }
        model.addAttribute("currentUser", userMapper.toUserDto(currentUser));
        return "adminMainPage";
    }

    @PostMapping(Consts.Url.$_NAV_ADMIN_MAIN)
    public String navAdminMainPage(){
        return "redirect:" + Consts.Url.ADMIN_MAIN;
    }
    @PostMapping(Consts.Url.$_NAV_USERS_LIST)
    public String navUsersListPage(){
        return "redirect:" + Consts.Url.$_ADMIN_MAIN_$_USERS_LIST;
    }
    @PostMapping(Consts.Url.$_NAV_PERSONS_LIST)
    public String navPersonsListPage(){
        return "redirect:" + Consts.Url.$_ADMIN_MAIN_$_PERSONS_LIST;
    }
    @PostMapping(Consts.Url.$_NAV_CENSUS_RESULTS)
    public String navCensusResultsPage(){
        return "redirect:" + Consts.Url.$_ADMIN_MAIN_$_CENSUS_RESULTS;
    }



    @PostMapping(Consts.Url.$_DELETE_CURRENT_USER)
    public String deleteCurrentUser(Model model){
        User user = userMapper.toUser((UserDto) model.getAttribute("currentUser"));
        userService.deleteUser(user);
        return "redirect:/customLogout";
    }

    @PostMapping(Consts.Url.$_EDIT_CURRENT_USER)
    public String editCurrentUser(){
        return "redirect:" + Consts.Url.$_ADMIN_MAIN_$_EDIT_USER;
    }

    @GetMapping(Consts.Url.$_CUSTOM_LOGOUT)
    public String logoutGet(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:" + Consts.Url.$_MAIN;
    }
}
