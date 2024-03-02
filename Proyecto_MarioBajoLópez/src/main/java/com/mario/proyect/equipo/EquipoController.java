package com.mario.proyect.equipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.jugador.JugadorDAO;
import com.mario.proyect.partido.Partido;
import com.mario.proyect.partido.PartidoDAO;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping; 

@Controller
public class EquipoController {
    @Autowired
    JugadorDAO jugadorDao;
    @Autowired
    EquipoDAO equipoDao;
    @Autowired
    CategoriaDAO categoriaDao;
    @Autowired
    PartidoDAO partidoDao;

    @GetMapping(value = {"/equipos", "/equipos/{filtro}"})
    public ModelAndView getEquipos(@PathVariable(required = false) String filtro) {

    ModelAndView model = new ModelAndView();
    model.setViewName("equipoHTML/equipos");
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
            model.addObject("partidos", partidoDao.obtenerPartidosPorEquipo(id));
            model.addObject("partidoNuevo", new Partido());
            model.addObject("equipos", equipoDao.obtenerEquiposExceptoPorId(id));
            model.setViewName("equipoHTML/equipo");
        }else{
            model.setViewName("equipoHTML/equipos");
        }
        
        return model;
    }

    @SuppressWarnings("null")
    @GetMapping("/equipo/del/{id}")
@Transactional
public ModelAndView deleteEquipo(@PathVariable long id) {
    ModelAndView model = new ModelAndView();
    
    Optional<Equipo> equipoOptional = equipoDao.findById(id);

    if (equipoOptional.isPresent()) {
        Equipo equipo = equipoOptional.get();

        List<Partido> partidosAsociados = partidoDao.findByEquipoLocalOrEquipoVisitante(equipo, equipo);
        partidoDao.deleteAll(partidosAsociados);

        equipoDao.deleteById(id);
    }

    model.setViewName("redirect:/equipos");
    return model;
}

    @GetMapping("equipo/add")
    public ModelAndView addJugador() {

        ModelAndView model = new ModelAndView();
        model.addObject("equipoNuevo",new Equipo());
        model.addObject("categorias",categoriaDao.categoriasActive());
        model.setViewName("equipoHTML/equipoForm");

        return model;
    }

    @PostMapping("/equipo/save")
    public ModelAndView saveEquipo(@ModelAttribute Equipo equipo) {

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/equipos");
        Optional<Equipo> existingEquipoOptional = equipoDao.findById(equipo.getId());
        if (existingEquipoOptional.isPresent()) {
            equipoDao.save(equipo);
        }
        model.addObject("equipos", equipoDao.findAll());
        return model;
    }
    

    @GetMapping("equipo/edit/{id}")
    public ModelAndView editEquipo(@PathVariable long id) {
        
        ModelAndView model = new ModelAndView();
        Optional<Equipo> equipo = equipoDao.findById(id);
        if (equipo.isPresent()) {
            model.addObject("equipoNuevo", equipo.get());
            model.addObject("categorias",categoriaDao.categoriasActive());
        }
        model.setViewName("equipoHTML/equipoForm");
        return model;
    }
}
