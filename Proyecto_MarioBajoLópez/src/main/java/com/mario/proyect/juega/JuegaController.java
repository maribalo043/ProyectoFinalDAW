package com.mario.proyect.juega;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.equipo.EquipoDAO;
import com.mario.proyect.jugador.JugadorDAO;
import com.mario.proyect.partido.PartidoDAO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

 

@Controller
public class JuegaController {
    @Autowired
    EquipoDAO equipoDao;
    @Autowired
    PartidoDAO partidoDAO;
    @Autowired
    JuegaDAO juegaDao;
    
    /*Metodos implementados pero no provados, tambien faltan los HTML */
    @GetMapping("/juegas")
    public ModelAndView getJuegas(){

        ModelAndView model = new ModelAndView();
        model.setViewName("juegaHTML/juegas");
        model.addObject("juegas", juegaDao.findAll());

        return model;
    }
    @GetMapping("/juega/{id}")
    public ModelAndView getJuega(@PathVariable long id){

        ModelAndView model = new ModelAndView();
        model.setViewName("juegaHTML/juega");
        model.addObject("juega", juegaDao.findById(id).get());

        return model;
    }
    /*
    @GetMapping("/juega/add")
    public ModelAndView addJuega() {
        ModelAndView model = new ModelAndView();
        
        return model;
    }
    
    @GetMapping("")
    public ModelAndView deleteJuega(){

        return null;
    }
    @GetMapping("")
    public ModelAndView editJuega() {

        return null;
    }
    @PostMapping("path")
    public ModelAndView postMethodName(@ModelAttribute Juega juega) {
        
        ModelAndView model = new ModelAndView();
        
        return model;
    }
    */
    
}
