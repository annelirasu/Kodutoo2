/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.view;

import java.util.List;
import p4.domain.AdminYksus;

/**
 *
 * @author reget.kalamees
 */
public class AdminYksusView {
    private long id;
    private String name;
    private List<AdminYksus> yksused;

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
    
}
