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
        List<Mascota> listita=new ArrayList<Mascota>();
        ArrayList<Mascota>listaMascotas=new ArrayList<Mascota>(listita);
        return listaMascotas;
    }
}
