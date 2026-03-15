package cesar.sistema_transcesar.edu.service;

import java.util.HashSet;
import java.util.Set;

public class FestivosService {

    private final Set<String> festivos;

    public FestivosService() {
        festivos = new HashSet<>();
        festivos.add("2026-01-01");
        festivos.add("2026-01-12");
        festivos.add("2026-03-23");
        festivos.add("2026-04-02");
        festivos.add("2026-04-03");
        festivos.add("2026-05-01");
        festivos.add("2026-05-18");
        festivos.add("2026-06-08");
        festivos.add("2026-06-29");
        festivos.add("2026-07-04");
        festivos.add("2026-07-20");
        festivos.add("2026-08-07");
        festivos.add("2026-08-17");
        festivos.add("2026-10-12");
        festivos.add("2026-11-02");
        festivos.add("2026-11-16");
        festivos.add("2026-12-08");
        festivos.add("2026-12-25");
    }

    public boolean esFestivo(String fecha) {
        return festivos.contains(fecha);
    }

    public double aplicarTarifaFestivo(double tarifa, String fecha) {
        if (esFestivo(fecha)) {
            return tarifa * 1.20;
        }
        return tarifa;
    }
}
