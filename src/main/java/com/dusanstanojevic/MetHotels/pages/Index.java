/**
 * 
 */
package com.dusanstanojevic.MetHotels.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.dusanstanojevic.MetHotels.dao.GenericDAO;
import com.dusanstanojevic.MetHotels.entities.Korisnik;
import com.dusanstanojevic.MetHotels.entities.Role;
import com.dusanstanojevic.MetHotels.entities.Soba;

/**
 * @author dusanstanojevic
 *
 */
public class Index {
	@Property
	@Persist
	private String username;
	@Property
	@Persist
	private String password;
	@Inject
	private GenericDAO<Korisnik> korisnikDAO;
	@SessionState
	private Korisnik k;
	
	public String getMD5Hash(String yourString) {
		try {
			java.security.MessageDigest md =
			java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(yourString.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
		 	return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}
	private Object onSuccess() {
		Korisnik k = new Korisnik();
		k.ime = username;
		k.pass = password;
		k.role = Role.ADMIN;
		this.k = k;
		//korisnikDAO.saveOrUpdate(k);
		
		return Sobe.class;
	}
}