package com.mario.proyect.partido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;


@Controller
public class PartidoController {

    @Autowired
    PartidoDAO partidoDao;
    @Autowired
    CategoriaDAO categoriaDao;

    @GetMapping("/partidos")
    public ModelAndView getPartidos() {
       
        ModelAndView model = new ModelAndView();
        model.setViewName("partidos");
        model.addObject("partidos", partidoDao.findAll());

        return model;
    }

    @GetMapping("/partido/{id}")
    public ModelAndView getPartido(@PathVariable long id){

        ModelAndView model = new ModelAndView();
        model.setViewName("partido");
        model.addObject("partido", partidoDao.findById(id).get());

        return model;
    }
    @GetMapping("/partido/del/{id}")
    public ModelAndView deletePartido(@PathVariable long id) {
    	
    	ModelAndView model = new ModelAndView();
    	model.setViewName("redirect:/partidos");
    	Partido partido = partidoDao.findById(id).get();

    	if(partido!= null) {
    		partidoDao.delete(partido);
    	}
    	
    	return model;
    }
    @GetMapping("/partido/add")
    public ModelAndView addPartidos() {
    	
    	ModelAndView model = new ModelAndView();
    	model.setViewName("partidoForm");
    	model.addObject("partidoNuevo",new Partido());
        model.addObject("categorias",categoriaDao.categoriasActive());

    	return model;  	
    }

    @PostMapping("/partido/save")
    public ModelAndView savePartido(@ModelAttribute Partido partidoNuevo) {

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/partidos");
        Optional<Partido> partidoOpcional =  partidoDao.findById(partidoNuevo.getId());
        if (partidoOpcional.isPresent()) {
            Partido existingPartido = partidoOpcional.get();
            existingPartido.setId(partidoNuevo.getId());
            existingPartido.setResultado(partidoNuevo.getResultado());
            existingPartido.setPista(partidoNuevo.getPista());
            existingPartido.setCategoria(partidoNuevo.getCategoria());
    
            partidoDao.save(existingPartido);
        } else {
            partidoDao.save(partidoNuevo);
        }
        return model;
    }
    @GetMapping("/partido/edit/{id}")
    public ModelAndView editPartido(@PathVariable long id){

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoForm");
        model.addObject("partidoNuevo",partidoDao.findById(id));
        model.addObject("categorias",categoriaDao.categoriasActive());

        return model;
    }
}
