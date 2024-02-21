package com.mario.proyect.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.equipo.Equipo;



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
    @GetMapping("/categoria/del/{id}")
    public ModelAndView deleteCategoria(@PathVariable long id){

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/categorias");
        if(categoriaDao.findById(id).isPresent()){
            categoriaDao.deleteById(id);
        }
        return model;
    }
    @PostMapping("categoria/save")
    public ModelAndView saveCategoria(@ModelAttribute Categoria categoriaNueva){

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/categorias");
        Optional<Categoria> categoriaOpcional = categoriaDao.findById(categoriaNueva.getId());
        if(categoriaOpcional.isPresent()){
            Categoria categoria = categoriaOpcional.get();
            categoria.setId(categoriaNueva.getId());
            categoria.setNombre(categoriaNueva.getNombre());
            categoria.setActiva(categoriaNueva.isActiva());
            categoria.setEquipos(categoriaNueva.getEquipos());
            categoria.setPartidos(categoriaNueva.getPartidos());

            categoriaDao.save(categoria);
        } else {
            categoriaDao.save(categoriaNueva);
        }
        return model;
    }
    @GetMapping("categoria/edit/{id}")
    public ModelAndView editCategoria(@PathVariable long id) {
        
        ModelAndView model = new ModelAndView();
        Optional<Categoria> categoria = categoriaDao.findById(id);
        if (categoria.isPresent()) {
            model.addObject("categoriaNueva", categoria.get());
        }
        model.setViewName("categoriaForm");
        return model;
    }
    
    
    
}
