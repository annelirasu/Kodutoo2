/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.view;

import java.util.List;
import p4.domain.AdminYksLiik;
import p4.domain.AdminYksus;

/**
 *
 * @author reget.kalamees
 */
public class AdminYksusView {
    private long id;
    private String name;
    private List<AdminYksus> yksused;
    private List<AdminYksus> alluvad;
    private List<AdminYksLiik> liigid;
    private AdminYksLiik valitudLiik;
    private AdminYksus valitudAlluvYksus;
    private String username;
    

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the yksused
     */
    public List<AdminYksus> getYksused() {
        return yksused;
    }

    /**
     * @param yksused the yksused to set
     */
    public void setYksused(List<AdminYksus> yksused) {
        this.yksused = yksused;
    }

    /**
     * @return the alluvad
     */
    public List<AdminYksus> getAlluvad() {
        return alluvad;
    }

    /**
     * @param alluvad the alluvad to set
     */
    public void setAlluvad(List<AdminYksus> alluvad) {
        this.alluvad = alluvad;
    }

    /**
     * @return the liigid
     */
    public List<AdminYksLiik> getLiigid() {
        return liigid;
    }

    /**
     * @param liigid the liigid to set
     */
    public void setLiigid(List<AdminYksLiik> liigid) {
        this.liigid = liigid;
    }

    /**
     * @return the valitudLiik
     */
    public AdminYksLiik getValitudLiik() {
        return valitudLiik;
    }

    /**
     * @param valitudLiik the valitudLiik to set
     */
    public void setValitudLiik(AdminYksLiik valitudLiik) {
        this.valitudLiik = valitudLiik;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the valitudAlluvYksus
     */
    public AdminYksus getValitudAlluvYksus() {
        return valitudAlluvYksus;
    }

    /**
     * @param valitudAlluvYksus the valitudAlluvYksus to set
     */
    public void setValitudAlluvYksus(AdminYksus valitudAlluvYksus) {
        this.valitudAlluvYksus = valitudAlluvYksus;
    }
    
}
