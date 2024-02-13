package com.mario.proyect.equipo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.jugador.JugadorDAO;

import org.springframework.web.bind.annotation.PostMapping; 

@Controller
public class EquipoController {
    @Autowired
    JugadorDAO jugadorDao;
    @Autowired
    EquipoDao equipoDao;
    @Autowired
    CategoriaDAO categoriaDao;

    @GetMapping(value = {"/equipos", "/equipos/{filtro}"})
    public ModelAndView getJugadores(@PathVariable(required = false) String filtro) {

    ModelAndView model = new ModelAndView();
    model.setViewName("equipos");
    if(filtro != null){
        if(filtro.equals("categorias")){
            model.addObject("equipos", equipoDao.findAllByCategoria());
        }else{
            model.addObject("jugadores", jugadorDao.findAll());
        }
    }else{
        model.addObject("equipos", equipoDao.findAll());
    }
    return model;
}

    @GetMapping("equipo/{id}")
    public ModelAndView getEquipo(@PathVariable long id) {

        ModelAndView model = new ModelAndView();
        if(equipoDao.findById(id).isPresent()){
            model.addObject("equipo", equipoDao.findById(id).get());
            model.setViewName("equipo");
        }else{
            model.setViewName("equipos");
        }
        
        return model;
    }

    @GetMapping("/equipo/del/{id}")
    public ModelAndView deleteJugador(@PathVariable long id){

        ModelAndView model = new ModelAndView();
        Optional<Equipo> equipo = equipoDao.findById(id);
        if(equipo.isPresent()){  
            equipoDao.deleteById(id);
        }
        model.setViewName("redirect:/equipos");
        return model;
    }
    
    @GetMapping("equipo/add")
    public ModelAndView addJugador() {

        ModelAndView model = new ModelAndView();
        model.addObject("equipoNuevo", new Equipo());
        model.addObject("categorias",categoriaDao.findAll());
        model.setViewName("equipoForm");

        return model;
    }

    @PostMapping("/equipo/save")
    public ModelAndView saveOrUpdateEquipo(@ModelAttribute Equipo equipo) {

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/equipos");
        Optional<Equipo> existingEquipoOptional = equipoDao.findById(equipo.getId());
        if (existingEquipoOptional.isPresent()) {
            Equipo existingEquipo = existingEquipoOptional.get();
            existingEquipo.setNombre(equipo.getNombre());
            existingEquipo.setEmailContacto(equipo.getEmailContacto());
            existingEquipo.setNumeroTelefonoContacto(equipo.getNumeroTelefonoContacto());
            existingEquipo.setJugadores(equipo.getJugadores());
            existingEquipo.setCategoria(equipo.getCategoria());
    
            equipoDao.save(existingEquipo);
        } else {
            equipoDao.save(equipo);
        }
        model.addObject("equipos", equipoDao.findAll());
        return model;
    }
    

    @GetMapping("equipo/edit/{id}")
    public ModelAndView getMethodName(@PathVariable long id) {
        
        ModelAndView model = new ModelAndView();
        Optional<Equipo> equipo = equipoDao.findById(id);
        if (equipo.isPresent()) {
            model.addObject("equipoNuevo", equipo.get());
            model.addObject("categorias",categoriaDao.findAll());
        }
        model.setViewName("equipoForm");
        return model;
    }
    
    
    
    
    
    
}
