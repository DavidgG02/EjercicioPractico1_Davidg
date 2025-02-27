package EjercicioPractico.demo.service;

import EjercicioPractico.demo.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
   
    public List<Categoria> getCategorias(boolean activos);
    
    
    void save(Categoria categoria);
    
   
    void delete(Long idCategoria);
    
    
    Categoria getCategoriaById(Long idCategoria);
}
