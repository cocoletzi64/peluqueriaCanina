package org.example.percistencia;

import org.example.logica.Duenio;
import org.example.logica.Mascota;

public class ControladoraPersistencia {
    ControllerMascota masc=new ControllerMascota();
    ControllerDuenio duen=new ControllerDuenio();

    public void guardar(Duenio duenio, Mascota masco) {
        duen.create(duenio);
        masc.create(masco);
    }
}
