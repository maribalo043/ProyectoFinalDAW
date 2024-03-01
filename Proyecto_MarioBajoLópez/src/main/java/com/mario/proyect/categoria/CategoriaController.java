package com.mario.proyect.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.equipo.Equipo;
import com.mario.proyect.equipo.EquipoDAO;
import com.mario.proyect.partido.PartidoDAO;



@Controller
public class CategoriaController {

    @Autowired
    CategoriaDAO categoriaDao;
    @Autowired
    EquipoDAO equipoDao;
    @Autowired 
    PartidoDAO partidoDao;

    @GetMapping("/categorias")
    public ModelAndView getCategorias() {
        
        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaHTML/categorias");
        model.addObject("categorias", categoriaDao.findAll());
        
        return model;
    }
    @GetMapping("/categoria/{id}")
    public ModelAndView getCategoria(@PathVariable long id) {
        
        ModelAndView model = new ModelAndView();

        model.setViewName("categoriaHTML/categoria");
        model.addObject("categoria",categoriaDao.findById(id).get());
        
        return model;
    }
    @GetMapping("/categoria/add")
    public ModelAndView addCategoria() {

        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaHTML/categoriaForm");
        model.addObject("categoriaNueva", new Categoria());

        return model;
    }
    @SuppressWarnings("null")
    @GetMapping("/categoria/del/{id}")
    public ModelAndView deleteCategoria(@PathVariable long id) {
    ModelAndView model = new ModelAndView();
    model.setViewName("redirect:/categorias");

    Optional<Categoria> categoriaOptional = categoriaDao.findById(id);

    if (categoriaOptional.isPresent()) {
        Categoria categoria = categoriaOptional.get();

        // Obtener equipos de la categoría
        List<Equipo> equipos = categoria.getEquipos();

        // Desvincular la categoría en los equipos de la categoría
        equipos.forEach(equipo -> equipo.setCategoria(null));
        equipoDao.saveAll(equipos);
        
        // Eliminar la categoría
        categoriaDao.deleteById(id);
    }

    return model;
}

    @SuppressWarnings("unused")
    @PostMapping("categoria/save")
    public ModelAndView saveCategoria(@ModelAttribute Categoria categoriaNueva){

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/categorias");
        Optional<Categoria> categoriaOpcional = categoriaDao.findById(categoriaNueva.getId());

        categoriaDao.save(categoriaNueva);
        
        return model;
    }
    @GetMapping("categoria/edit/{id}")
    public ModelAndView editCategoria(@PathVariable long id) {
        
        ModelAndView model = new ModelAndView();
        Optional<Categoria> categoria = categoriaDao.findById(id);
        if (categoria.isPresent()) {
            model.addObject("categoriaNueva", categoria.get());
        }
        model.setViewName("categoriaHTML/categoriaForm");
        return model;
    }
    
    
    
}
