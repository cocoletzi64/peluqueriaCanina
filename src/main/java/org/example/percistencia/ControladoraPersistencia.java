package org.example.percistencia;

import org.example.logica.Duenio;
import org.example.logica.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ControladoraPersistencia {
    ControllerMascota masc=new ControllerMascota();
    ControllerDuenio duen=new ControllerDuenio();

    public void guardar(Duenio duenio, Mascota masco) {
        duen.create(duenio);
        masc.create(masco);
    }

    public List<Mascota> traerMascotas() {
        return masc.listarTodos();
    }

    public void borrarMascotas(int numCliente) {
        try {
            masc.destroy(numCliente);
            duen.destroy(numCliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Mascota traerMascotas(int numCliente) {
        return masc.find(numCliente);
    }

    public void modificarMascota(Mascota masco) {
        masc.edit(masco);

    }

    public Duenio traerduenio(int idDuenio) {
        return duen.find(idDuenio);
    }

    public void modificarDuenio(Duenio duenio) {
        duen.edit(duenio);
    }
}
