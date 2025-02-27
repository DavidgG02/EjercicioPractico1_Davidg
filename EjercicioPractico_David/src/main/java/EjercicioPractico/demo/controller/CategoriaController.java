package EjercicioPractico.demo.controller;

import EjercicioPractico.demo.domain.Categoria;
import EjercicioPractico.demo.service.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        return "/categoria/listado";
    }

    @GetMapping("/agregar")
    public String agregarCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "/categoria/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificarCategoria(@PathVariable("id") Long idCategoria, Model model) {
        Categoria categoria = categoriaService.getCategoriaById(idCategoria);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "/categoria/formulario";
        }
        return "redirect:/categoria/listado";
    }

    @PostMapping("/modificar/{id}")
    public String guardarModificacion(@PathVariable("id") Long idCategoria, @ModelAttribute Categoria categoria) {
        // Verificar si la categoría existe antes de modificarla
        if (categoria != null) {
            categoria.setIdCategoria(idCategoria);  // Corregir nombre del método
            categoriaService.save(categoria);
        }
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long idCategoria) {
        categoriaService.delete(idCategoria);
        return "redirect:/categoria/listado";
    }
}
