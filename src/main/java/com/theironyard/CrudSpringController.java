package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class CrudSpringController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    GameRepo gameRepo;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String webroot(Model model, HttpSession session){

        //redirects to create-game if user is logged in, otherwise redirects to webroot
        if (session.getAttribute("userName") != null){

            String userName = session.getAttribute("userName").toString();
            model.addAttribute("userName", userName);
            return "create-game";

        } else {
            return "home";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password, Model model) throws Exception{

        User user = userRepo.findFirstByName(userName);

        if (user == null){
            user = new User();
            user.setName(userName);

            //hashes password
            user.setPassword(PasswordHasher.createHash(password));
            userRepo.save(user);

            //checks for password validation
        } else if (!PasswordHasher.verifyPassword(password, user.getPassword())) {
            //throw new Exception("Wrong password!");
            //todo: make the mustache say Login Failed!
            return "redirect:/?loginFailed";
        }

        session.setAttribute("userName", userName);
        model.addAttribute("games", gameRepo.findByUser(user));
        return "redirect:/";

    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/create-game", method = RequestMethod.POST)
    public String createGame(HttpSession session, String name, String genre, String platform){

        //kicks to webroot if not logged in
        if (session.getAttribute("userName") == null){
            return "redirect:/";
        }

        //init
        Game game = new Game();

        //gets current user
        User user = userRepo.findFirstByName(session.getAttribute("userName").toString());

        //makes new game
        game.setName(name);
        game.setGenre(genre);
        game.setPlatform(platform);
        game.setUser(user);

        //saves new game to table
        gameRepo.save(game);

        return "redirect:/";

    }

    //todo: make delete-game route

    @RequestMapping(path = "/edit-game", method = RequestMethod.GET)
    public String editGame(HttpSession session, Model model){

        //checks for user in session
        if (session.getAttribute("userName") != null){

            //todo: add game info to model
            //String gameName;
            //model.addAttribute("gameInfo", gameInfo)
            return "edit-game";

        } else {

            return "redirect:/";
        }

    }

    //todo: make edit-game POST route

}
