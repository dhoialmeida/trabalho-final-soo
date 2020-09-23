package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    @Test
    @DisplayName("ConteudoService.save")
    void testSave() {
        Usuario usuario = new Usuario("Teste", "teste@teste", "senhateste", "12345678801", LocalDate.of(2000, Month.MARCH, 12));
        usuario = service.save(usuario);
        assertNotNull(usuario);
        assertEquals(usuario.getNome(), "Teste");
    }
    
    @Test
    @DisplayName("ConteudoService.findByCpf")
    void testFindByCPF() {
        Usuario usuario = service.findByCpf("12345678901");
        assertNotNull(usuario);
    }
    
    @Test
    @DisplayName("ConteudoService.findByEmail")
    void testFindByEmail() {
        Usuario usuario = service.findByEmail("teste@teste");
        assertNotNull(usuario);
    }
    
    @Test
    @DisplayName("ConteudoService.delete")
    void testDelete() {
        Usuario usuario = service.findByEmail("teste@teste");
        assertNotNull(usuario);
        service.delete(usuario);
        usuario = service.findByEmail("teste@teste");
        assertNull(usuario);
    }
}
