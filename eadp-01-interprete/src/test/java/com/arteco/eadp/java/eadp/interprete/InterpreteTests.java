package com.arteco.eadp.java.eadp.interprete;

import com.arteco.eadp.java.eadp.interprete.comando.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
@RunWith(JUnit4.class)
public class InterpreteTests {

    @Test
    public void creacionInterprete() {
        Interprete i = new Interprete();
        Assert.assertEquals(
                new File(".").getAbsolutePath(),
                i.getDirectory().getAbsolutePath());
    }

    @Test
    public void procesoEntrada() {
        Interprete i = new Interprete();
        Comando comando = i.procesaLinea("ls");
        Assert.assertTrue(comando instanceof LsComando);

        comando = i.procesaLinea("MkDiR prueba");
        Assert.assertTrue(comando instanceof MkDirComando);

        comando = i.procesaLinea("cP prueba.txt destino.txt");
        Assert.assertTrue(comando instanceof CpComando);

        comando = i.procesaLinea("cAt prueba.txt");
        Assert.assertTrue(comando instanceof CatComando);

        comando = i.procesaLinea("cd prueba");
        Assert.assertTrue(comando instanceof CdComando);

        comando = i.procesaLinea("replace prueba.txt buscado reemplazo");
        Assert.assertTrue(comando instanceof ReplaceComando);

        comando = i.procesaLinea("RM prueba.txt");
        Assert.assertTrue(comando instanceof RmComando);

        comando = i.procesaLinea("append prueba.txt contenido 1 y 2");
        Assert.assertTrue(comando instanceof AppendComando);

    }


    @Test
    public void comprobarComandoMkdir() throws Exception {
        String result;
        Path tempDirectory = Files.createTempDirectory(null);
        Interprete i = new Interprete(tempDirectory.toString());

        i.procesaLinea("mkdir prueba").ejecutar();
        result = i.procesaLinea("ls").ejecutar();
        Assert.assertTrue(result.contains("prueba"));

        i.procesaLinea("cd prueba").ejecutar();
        result = i.procesaLinea("ls").ejecutar();
        Assert.assertEquals(0, result.length());

        i.procesaLinea("append prueba.txt contenido 1 y 2").ejecutar();
        result = i.procesaLinea("ls").ejecutar();
        Assert.assertTrue(result.contains("prueba.txt"));

        result = i.procesaLinea("cat prueba.txt").ejecutar();
        Assert.assertTrue(result.contains("contenido 1 y 2"));

        i.procesaLinea("replace prueba.txt 2 3").ejecutar();
        result = i.procesaLinea("cat prueba.txt").ejecutar();
        Assert.assertTrue(result.contains("contenido 1 y 3"));

        i.procesaLinea("cp prueba.txt otro.txt").ejecutar();
        result = i.procesaLinea("cat otro.txt").ejecutar();
        Assert.assertTrue(result.contains("contenido 1 y 3"));

        i.procesaLinea("rm prueba.txt").ejecutar();
        i.procesaLinea("rm otro.txt").ejecutar();
        result = i.procesaLinea("ls").ejecutar();
        Assert.assertFalse(result.contains("prueba.txt"));
        Assert.assertFalse(result.contains("otro.txt"));

        i.procesaLinea("cd ..").ejecutar();
        i.procesaLinea("rm prueba").ejecutar();
        result = i.procesaLinea("ls").ejecutar();
        Assert.assertEquals(0, result.length());
    }
}
