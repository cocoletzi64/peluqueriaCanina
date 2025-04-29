package org.example.logica;


import org.example.percistencia.ControladoraPersistencia;

import java.util.List;
import java.util.Random;

public class ControladoraLogica {
    ControladoraPersistencia controlPersis=new ControladoraPersistencia();


    public void guardar(String nombremasco, String raza,
                        String color, String nombreDue, String celDue, String obser,
                        String alergico, String atencion) {
        Random ran=new Random();
        int valor= ran.nextInt(100000);
        Duenio duenio=new Duenio();
        duenio.setId_duenio(valor);
        duenio.setNombre(nombreDue);
        duenio.setCel(celDue);

        Mascota masco=new Mascota();
        masco.getNum_cliente();
        masco.setNum_cliente(2);
        masco.setNombre(nombremasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setEspecial(atencion);
        masco.setObservaciones(obser);
        masco.setNum_cliente(valor);
        masco.setDuenio(duenio);
        controlPersis.guardar(duenio,masco);
    }

    public List<Mascota> traerMasoctas() {
        return controlPersis.traerMascotas();
    }
}
