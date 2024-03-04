package com.mario.proyect.partido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.equipo.EquipoDAO;

import jakarta.validation.Valid;

@Controller
public class PartidoController {

    @Autowired
    PartidoDAO partidoDao;
    @Autowired
    CategoriaDAO categoriaDao;
    @Autowired
    EquipoDAO equipoDao;

    @GetMapping("/partidos")
    public ModelAndView getPartidos() {
       
        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidos");
        model.addObject("partidos", partidoDao.findAll());

        return model;
    }

    @GetMapping("/partido/{idLocal}/{idVisitante}")
    public ModelAndView getPartido(@PathVariable long idLocal,@PathVariable long idVisitante){

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partido");
        Partido partido = new Partido();

        PartidoKey partidoKey = new PartidoKey();
        partidoKey.setIdEquipoLocal(idLocal);
        partidoKey.setIdEquipoVisitante(idVisitante);

        partido.setId(partidoKey);
        partido.setEquipoLocal(equipoDao.findById(idLocal).get());
        partido.setEquipoVisitante(equipoDao.findById(idVisitante).get());

        model.addObject("partido", partido);
        
        return model;
    }
    @SuppressWarnings("null")
    @GetMapping("/partido/del/{idLocal}/{idVisitante}")
public ModelAndView deletePartido(@PathVariable long idLocal, @PathVariable long idVisitante) {
    ModelAndView model = new ModelAndView();
    model.setViewName("redirect:/partidos");

    PartidoKey partidoKey = new PartidoKey();
    partidoKey.setIdEquipoLocal(idLocal);
    partidoKey.setIdEquipoVisitante(idVisitante);

    Optional<Partido> partidoOptional = partidoDao.findById(partidoKey);

    if (partidoOptional.isPresent()) {
        Partido partido = partidoOptional.get();
        partidoDao.delete(partido);
    }
    return model;
}

    @GetMapping("/partido/add")
    public ModelAndView addPartido() {
    	
    	ModelAndView model = new ModelAndView();
    	model.setViewName("partidoHTML/partidoForm");
    	model.addObject("partidoNuevo",new Partido());
        model.addObject("equipos",equipoDao.findAll());

    	return model;	
    }

    @SuppressWarnings("null")
    @PostMapping("/partido/save")
    public ModelAndView savePartido(@ModelAttribute("partidoNuevo") @Valid Partido partidoNuevo, BindingResult bindingResult) {

        
    ModelAndView model = new ModelAndView();
    /*, BindingResult bindingResult */
    /*Revisar que todo se setea o ver si se guarda etc*/
    if(bindingResult.hasErrors()){
        model.setViewName("partidoForm");

        model.setViewName("partidoHTML/partidoForm");
    	model.addObject("partidoNuevo",partidoNuevo);
        model.addObject("equipos",equipoDao.findAll());

        return model;

    }
    model.setViewName("redirect:/partidos");

    if (partidoNuevo.getId() != null) {
        Partido existente = partidoDao.findById(partidoNuevo.getId()).orElse(null);
        if (existente != null) {
            existente.setGolesLocal(partidoNuevo.getGolesLocal());
            existente.setGolesVisitante(partidoNuevo.getGolesVisitante());
            existente.setPista(partidoNuevo.getPista());
            partidoDao.save(existente);
        }
    } else {
        PartidoKey partidoKey = new PartidoKey();
        partidoKey.setIdEquipoLocal(partidoNuevo.getEquipoLocal().getId());
        partidoKey.setIdEquipoVisitante(partidoNuevo.getEquipoVisitante().getId());
        partidoNuevo.setId(partidoKey);
        partidoDao.save(partidoNuevo);
    }
    return model;
}



    @GetMapping("/partido/edit/{idLocal}/{idVisitante}")
    public ModelAndView editPartido(@PathVariable long idLocal,@PathVariable long idVisitante){

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidoForm");
        
        Partido partido = new Partido();
        PartidoKey key = new PartidoKey();
        key.setIdEquipoLocal(idLocal);
        key.setIdEquipoVisitante(idVisitante);
        partido.setId(key);

        model.addObject("partidoNuevo",partido);
        model.addObject("equipos",equipoDao.findAll());

        return model;
    }
}
