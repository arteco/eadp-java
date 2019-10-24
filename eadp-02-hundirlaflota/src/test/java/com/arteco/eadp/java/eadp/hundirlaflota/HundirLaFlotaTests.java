package com.arteco.eadp.java.eadp.hundirlaflota;

import com.arteco.eadp.java.eadp.hundirlaflota.action.ActionResult;
import com.arteco.eadp.java.eadp.hundirlaflota.game.*;
import com.arteco.eadp.java.eadp.hundirlaflota.writer.NoWriter;
import com.arteco.eadp.java.eadp.hundirlaflota.writer.Writer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
@RunWith(JUnit4.class)
public class HundirLaFlotaTests {

    @Test
    public void inicializacion() {
        Game g = new Game();
        Writer w = new NoWriter();
        Parser p = new Parser(g);
        p.executeActions(w, "start");
        Assert.assertTrue(g.getBoardCPU().isAlive());
        Assert.assertTrue(g.getBoardUSR().isAlive());
        List<ActionResult> result = p.executeActions(w, "print");
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void printHelp() {
        Game g = new Game();
        Writer w = new NoWriter();
        Parser p = new Parser(g);
        List<Object> arg = p.parseLine("");
        List<ActionResult> result = p.executeActions(w, arg);
        Assert.assertEquals(0, result.size());

        String help = p.printHelp();
        Assert.assertTrue(help.contains("start"));
        Assert.assertTrue(help.contains("launch"));

    }

    @Test
    public void inicializacionSimple() {
        Game g = new Game();
        Writer w = new NoWriter();
        Parser p = new Parser(g);
        p.executeActions(w, "start", "simple");
        Assert.assertTrue(g.getBoardCPU().isAlive());
        Assert.assertTrue(g.getBoardUSR().isAlive());

        List<Boat> boatsCPU = g.getBoardCPU().getBoats();
        Assert.assertEquals(1, boatsCPU.size());

        Pos pos = boatsCPU.get(0).getPos();
        boolean horz = boatsCPU.get(0).isHorizontal();

        List<ActionResult> result = p.executeActions(w, "launch", pos.x, pos.y);
        Assert.assertTrue(result.get(0).getOutput().contains(ImpactType.TOUCHED.name()));

        if (horz) {
            pos = new Pos(pos.x + 1, pos.y);
        } else {
            pos = new Pos(pos.x, pos.y + 1);
        }
        result = p.executeActions(w, "launch", pos.x, pos.y);
        Assert.assertTrue(result.get(0).getOutput().contains(ImpactType.SUNKEN.name()));
        Assert.assertFalse(g.getBoardCPU().isAlive());

        result = p.executeActions(w, "exit");
        Assert.assertTrue(result.get(0).getOutput().contains("Terminando"));


        w.append(g.print());

    }

}
