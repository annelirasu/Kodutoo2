package p4.view;

import java.util.List;

import javax.validation.Valid;

import p4.domain.*;

public class ayView {
	
	
	@Valid //uus, loodav
    private AdminYksus current;
	
	private List<AdminYksLiik> liigid;
    //rippmenyys, algab tyhjusega, sest ylemust pole tingimata tarvis
    private List<AdminYksus> ylemad;
    //ainult need, kellel veel ylemust ei ole
    private List<AdminYksus> alluvad;
    //vahepeal väljavalitud alluvad, eemaldatakse ühtlasi väljakuvatavate alluvate listist
    private List<AdminYksus> valitudalluvad;
	//ylemuse_id kinnipyydmiseks
    private String yl_id;
    //veateadeteks
    private String errormessage;
	public AdminYksus getCurrent() {
		return current;
	}
	public void setCurrent(AdminYksus current) {
		this.current = current;
	}
	public List<AdminYksLiik> getLiigid() {
		return liigid;
	}
	public void setLiigid(List<AdminYksLiik> liigid) {
		this.liigid = liigid;
	}
	public List<AdminYksus> getYlemad() {
		return ylemad;
	}
	public void setYlemad(List<AdminYksus> ylemad) {
		this.ylemad = ylemad;
	}
	public List<AdminYksus> getAlluvad() {
		return alluvad;
	}
	public void setAlluvad(List<AdminYksus> alluvad) {
		this.alluvad = alluvad;
	}
	public List<AdminYksus> getValitudalluvad() {
		return valitudalluvad;
	}
	public void setValitudalluvad(List<AdminYksus> valitudalluvad) {
		this.valitudalluvad = valitudalluvad;
	}
	public String getYl_id() {
		return yl_id;
	}
	public void setYl_id(String yl_id) {
		this.yl_id = yl_id;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

    

}
