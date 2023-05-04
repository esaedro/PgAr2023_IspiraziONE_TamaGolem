package Arnaldo;

import java.util.ArrayList;

import it.unibs.fp.mylib.MyMenu;

public class InterazioneUtenti {

    public static Pietra selezionaPietra() {
        String[] listaPietre = new String[Scontro.S];
        Pietra pietraSelezionata;

        for (int i = 0; i < Scontro.getScortaDiPietre().size(); i++) {
            listaPietre[i] = Scontro.getScortaDiPietre().get(i).getElemento().name();
        }

        MyMenu menu = new MyMenu(Frasi.TITOLO_MENU_SELEZIONE_PIETRA, listaPietre);

        pietraSelezionata = Scontro.getScortaDiPietre().get(menu.scegli());

        return pietraSelezionata;
    }

    public static void mostraDanno(int danno) {
        System.out.println(danno);
    }
}
