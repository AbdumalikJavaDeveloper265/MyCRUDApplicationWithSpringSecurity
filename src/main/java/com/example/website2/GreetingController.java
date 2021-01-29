package com.example.website2;

import com.example.website2.domain.UserData;
import com.example.website2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class GreetingController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/")
    public String greeting(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @GetMapping()
//    public String main(Map<String, Object> model) {
//        model.put("main",  "Hello Mr Abdumalik Ibraximov!");
//        return "main";
//    }

    @GetMapping("/main")
    public String getAllUsers(Model model) {
        Iterable<UserData> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "main";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(value = "id") Integer id, Model model) {
        Optional<UserData> user = userRepo.findById(id);
        ArrayList<UserData> person = new ArrayList<>();
        user.ifPresent(person::add);
        model.addAttribute("person", person);
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("person", new UserData());
        return "new";
    }


    @PostMapping(path="/new") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String firstName, @RequestParam String lastName,
                       @RequestParam String date, @RequestParam String region, @RequestParam int year) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        UserData n = new UserData();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setDate(date);
        n.setRegion(region);
        n.setYear(year);
        userRepo.save(n);
        return "redirect:/main";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        UserData userData = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        model.addAttribute("person" , userData);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, UserData userData,  Model model) {
        userRepo.save(userData);
        model.addAttribute("users", userRepo.findAll());
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        UserData userData = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        userRepo.delete(userData);
        model.addAttribute("users", userRepo.findAll());
        return "main";

    }
//    @PatchMapping("id")
//    public String update(@ModelAttribute("userDate") UserData userData,
//                         @PathVariable("id") Integer id) {
//        userRepo.
//    }

}