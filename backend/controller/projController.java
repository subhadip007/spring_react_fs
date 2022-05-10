package com.example.springproj1.controller;


import com.example.springproj1.dao.categoryRepo;
import com.example.springproj1.dao.expenseRepo;
import com.example.springproj1.dao.userRepo;
import com.example.springproj1.model.Category;
import com.example.springproj1.model.Expense;
import com.example.springproj1.model.LoginForm;
import com.example.springproj1.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class projController {

    @Autowired
    categoryRepo crp;
    @Autowired
    expenseRepo exp;
    @Autowired
    userRepo urp;





    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    @ResponseBody
    private Boolean addUser(@RequestBody Users user){
        try{
            urp.save(user);
            System.out.println("user saved");
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }


    @RequestMapping(value="/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public Boolean loginUser(@RequestBody LoginForm login){
        String email=login.getEmail();
        String pass=login.getPass();

//        System.out.println(login.getEmail());
        Users dbUser= urp.findUserByEmail(email);
        try{
            if(Objects.equals(dbUser.getEmail(), email) && Objects.equals(dbUser.getPassword(), pass)){
               return true;

            }
        }catch(Exception e){
            System.out.println(e);
        }

        return false;
    }



    @RequestMapping(value="/addExpense",method = RequestMethod.POST)
    @ResponseBody
    private Boolean addExpense(@RequestBody Expense expense){

        System.out.println(expense);
        try{
            exp.save(expense);

            return true;

        }catch(Exception e){
            System.out.println(e);

        }
        return false;

    }


    @RequestMapping(value="/getExpense",method = RequestMethod.GET)
    @ResponseBody
    private List<Expense> getExpense(){
        List<Expense> ex=null;
        try{
           ex=exp.findAll();

        }catch (Exception e){
            System.out.println(e);
        }
        return ex;
    }


    @RequestMapping("createcategory")
    private String createCategory(){
        return "createCategory.jsp";
    }


    @RequestMapping(value="addCategory", method = RequestMethod.POST)
    @ResponseBody
    private Boolean addCategory(@RequestBody Category cat){

        System.out.println(cat);
        try{
            crp.save(cat);
            return true;
        }catch(Exception e){
            System.out.println(e);
        }

        return false;
    }

}
