package EjercicioPractico.demo.service.impl;

import EjercicioPractico.demo.dao.CategoriaDao;
import EjercicioPractico.demo.domain.Categoria;
import EjercicioPractico.demo.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        List<Categoria> lista = categoriaDao.findAll();
        return activos ? lista.stream().filter(Categoria::isActivo).toList() : lista;
    }

    @Override
    public void save(Categoria categoria) { 
        categoriaDao.save(categoria);
    }
    @Override
@Transactional
    public void delete(Long idCategoria){
             categoriaDao.deleteById(idCategoria);
}
@Override
    @Transactional(readOnly = true)
    public Categoria getCategoriaById(Long idCategoria) {
        return categoriaDao.findById(idCategoria).orElse(null); 
    }

}