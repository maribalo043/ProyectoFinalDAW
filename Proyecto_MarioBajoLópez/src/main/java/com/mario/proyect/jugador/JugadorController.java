package com.mario.proyect.jugador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mario.proyect.equipo.EquipoDAO;
import com.mario.proyect.partido.Partido;

import jakarta.validation.Valid;


@Controller
public class JugadorController {

    @Autowired
    JugadorDAO jugadorDao;
    @Autowired
    EquipoDAO equipoDao;

    @GetMapping(value = {"/jugadores", "/jugadores/{filtro}"})
    public ModelAndView getJugadores(@PathVariable(required = false) String filtro) {

    ModelAndView model = new ModelAndView("jugadorHTML/jugadores");
    if(filtro != null){
        if(filtro.equals("tallaCamiseta")){
            model.addObject("jugadores", jugadorDao.findAllPorTallaCamiseta());
        }else if(filtro.equals("equipo")){
            model.addObject("jugadores", jugadorDao.findAllPorEquipo());
        }else{
            model.addObject("jugadores", jugadorDao.findAll());
        }
    }else{
        model.addObject("jugadores", jugadorDao.findAll());
    }
    return model;
}


    @GetMapping("/jugador/{dni}")
	public ModelAndView getJugador(@PathVariable String dni) {
		Jugador jugador = jugadorDao.findById(dni).get();
		ModelAndView model = new ModelAndView();
		model.setViewName("jugadorHTML/Jugador");
		model.addObject("jugador",jugador);
		return model;
	}

    @GetMapping("/jugador/del/{dni}")
    public ModelAndView deleteJugador(@PathVariable String dni){

        ModelAndView model = new ModelAndView();
        Optional<Jugador> jugador = jugadorDao.findById(dni);
        if(jugador.isPresent()){
            jugadorDao.deleteById(dni);
        }
        model.setViewName("redirect:/jugadores");
        
        return model;
    }
    
    @GetMapping("/jugador/add")
    public ModelAndView addJugador(){
        ModelAndView model = new ModelAndView();
        model.addObject("jugadorNuevo", new Jugador());
        model.addObject("equipos", equipoDao.findAll());
        model.addObject("equipoItem",equipoDao.findAll());
        model.setViewName("jugadorHTML/jugadoresForm");

        return model;
    }

    @SuppressWarnings("null")
    @PostMapping("/jugador/save")
    public ModelAndView saveJugador(@ModelAttribute("jugadorNuevo") @Valid Jugador jugadorNuevo, BindingResult bindingResult) {
    ModelAndView model = new ModelAndView();

    if (bindingResult.hasErrors()) {
        model.addObject("jugadorNuevo", jugadorNuevo);
        model.addObject("equipos", equipoDao.findAll());
        model.addObject("equipoItem",equipoDao.findAll());
        model.setViewName("jugadorHTML/jugadoresForm");
        return model;
    }

    jugadorDao.save(jugadorNuevo);

    model.setViewName("redirect:/jugadores");
    return model;
    }

    @GetMapping("/jugador/edit/{dni}")
    public ModelAndView editJugador(@PathVariable String dni){
        ModelAndView model = new ModelAndView();
        Optional <Jugador> jugOpt=jugadorDao.findById(dni);
        if(jugOpt.isPresent()) {
			model.addObject("jugadorNuevo",jugOpt.get());
            model.addObject("equipos",equipoDao.findAll());
			model.setViewName("jugadorHTML/jugadoresForm");
		}else model.setViewName("redirect:/jugadores");
		return model;
    } 
}