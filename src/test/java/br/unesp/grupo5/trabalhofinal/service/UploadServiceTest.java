package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadServiceTest {

    @Autowired
    private UploadService service;

    @Test
    @DisplayName("UploadService.save")
    void testSave() {
        MultipartFile file = mock(MultipartFile.class);
        Conteudo conteudo = mock(Conteudo.class);

        when(file.getOriginalFilename()).thenReturn("imagem.jpg");
        try {
            when(file.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        } catch (Exception ex) {
        }
        when(conteudo.getIdConteudo()).thenReturn((long) 16);

        service.saveThumb(conteudo, file);

        verify(conteudo).setThumbnailFile("16.jpg");
    }
}
