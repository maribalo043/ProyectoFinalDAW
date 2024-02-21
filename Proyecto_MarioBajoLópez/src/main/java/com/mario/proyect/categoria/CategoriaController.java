package com.mario.proyect.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



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
    @GetMapping("/categoria/{id}")
    public ModelAndView getJugador(@PathVariable long id) {
        
        ModelAndView model = new ModelAndView();

        model.setViewName("categoria");
        model.addObject("categoria",categoriaDao.findById(id).get());
        
        return model;
    }
    @GetMapping("/categoria/add")
    public ModelAndView addCategoria() {

        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaForm");
        model.addObject("categoriaNueva", new Categoria());

        return model;
    }
    
    
    
    
}
