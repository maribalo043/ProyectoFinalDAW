package com.mario.proyect.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategoriaController {

    @Autowired
    CategoriaDAO categoriaDao;

    @GetMapping("/categorias")
    public ModelAndView getMethodName() {
        
        ModelAndView model = new ModelAndView();
        model.setViewName("categorias");
        model.addObject("categorias", categoriaDao.findAll());
        
        return model;
    }
    
    
}
