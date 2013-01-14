package p4.view;

import java.util.List;
import p4.domain.*;

public class AdminYksLiikView {
    //esialgseks katseks
    private String kood;
    private String nimetus;
    private String kommentaar;
    
    
    //uus, parasjagu loodav
    private AdminYksLiik current;
    //rippmenyys, algab tyhjusega, sest ylemust pole tingimata tarvis
    private List<AdminYksLiik> ylemad;
    //ainult need, kellel veel ylemust ei ole
    private List<AdminYksLiik> alluvad;

    public String getKood() {

        return kood;
    }

    public void setKood(String kood) {
        this.kood = kood;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public String getKommentaar() {
        return kommentaar;
    }

    public void setKommentaar(String kommentaar) {
        this.kommentaar = kommentaar;
    }

    /**
     * @return the current
     */
    public AdminYksLiik getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(AdminYksLiik current) {
        this.current = current;
    }

    /**
     * @return the ylemad
     */
    public List<AdminYksLiik> getYlemad() {
        return ylemad;
    }

    /**
     * @param ylemad the ylemad to set
     */
    public void setYlemad(List<AdminYksLiik> ylemad) {
        this.ylemad = ylemad;
    }

    /**
     * @return the alluvad
     */
    public List<AdminYksLiik> getAlluvad() {
        return alluvad;
    }

    /**
     * @param alluvad the alluvad to set
     */
    public void setAlluvad(List<AdminYksLiik> alluvad) {
        this.alluvad = alluvad;
    }
}
