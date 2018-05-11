package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Pivara;
import jwd.wafepa.model.Pivo;
import jwd.wafepa.model.VrstaPiva;
import jwd.wafepa.service.PivaraService;
import jwd.wafepa.service.PivoService;
import jwd.wafepa.service.VrstaPivaService;

@Component
public class TestData {

	
	@Autowired
	private PivaraService pivaraService;
	
	@Autowired
	private PivoService pivoService;
	
	@Autowired
	private VrstaPivaService vrstaPivaService;
	
	
	@PostConstruct
	public void init(){
//	       for (int i = 1; i <= 100; i++) {
//	            User user = new User();
//	            user.setFirstName("First name " + i);
//	            user.setLastName("Last name " + i);
//	            user.setEmail("email" + i + "@example.com");
//	            user.setPassword("123");
//	            userService.save(user);
//
//	            for (int j = 1; j <= 3; j++) {
//	                Address address = new Address();
//	                address.setNumber(j + "c/7");
//	                address.setStreat("Narodnog fronta");
//
//	                address.setUser(user);
//	                user.addAddress(address);
//	                addressService.save(address);
//	            }
//	       }
//		for (int j = 0; j <= 3; j++) {
//            Activity activity = new Activity();            
//            activity.setName("activity" + j);
//            activityService.save(activity);
//        }
		
	
		
		
		Pivara p1 = new Pivara();
		p1.setDrzava("Srbija");
		p1.setNaziv("Apatinska");
		p1.setPib("100962933");
		pivaraService.save(p1);
		
		Pivara p2 = new Pivara();
		p2.setDrzava("Srbija");
		p2.setNaziv("Jagodinska");
		p2.setPib("100922222");
		pivaraService.save(p2);
		
		Pivara p3 = new Pivara();
		p3.setDrzava("Srbija");
		p3.setNaziv("Zajecarska");
		p3.setPib("101335688");
		pivaraService.save(p3);
		
		Pivara p4 = new Pivara();
		p4.setDrzava("Holandija");
		p4.setNaziv("Amstel pivara");
		p4.setPib("100000000");
		pivaraService.save(p4);
		
		Pivara p5 = new Pivara();
		p5.setDrzava("Nemacka");
		p5.setNaziv("Paulaner pivara");
		p5.setPib("1098765654");
		pivaraService.save(p5);
		
		VrstaPiva v1 = new VrstaPiva();
		v1.setNaziv("svetlo");
		vrstaPivaService.save(v1);
		
		VrstaPiva v2 = new VrstaPiva();
		v2.setNaziv("tamno");
		vrstaPivaService.save(v2);
		
		VrstaPiva v3 = new VrstaPiva();
		v3.setNaziv("psenicno");
		vrstaPivaService.save(v3);
		
		Pivo pi1 = new Pivo();
		pi1.setNaziv("Jelen");
		pi1.setIbu(10.3);
		pi1.setKolicina(356);
		pi1.setAlkohol(4.5);
		pi1.setPivara(p1);
		pi1.setVrstaPiva(v1);
		pivoService.save(pi1);
		
		Pivo pi2 = new Pivo();
		pi2.setNaziv("Lav");
		pi2.setIbu(15.3);
		pi2.setKolicina(654);
		pi2.setAlkohol(4.6);
		pi2.setPivara(p1);
		pi2.setVrstaPiva(v1);
		pivoService.save(pi2);
		
		Pivo pi3 = new Pivo();
		pi3.setNaziv("Zajecarsko");
		pi3.setIbu(12.9);
		pi3.setKolicina(333);
		pi3.setAlkohol(4.4);
		pi3.setPivara(p3);
		pi3.setVrstaPiva(v1);
		pivoService.save(pi3);
		
		Pivo pi4 = new Pivo();
		pi4.setNaziv("Amstel");
		pi4.setIbu(9.9);
		pi4.setKolicina(200);
		pi4.setAlkohol(4.2);
		pi4.setPivara(p4);
		pi4.setVrstaPiva(v1);
		pivoService.save(pi4);
		
		Pivo pi5 = new Pivo();
		pi5.setNaziv("Paulaner");
		pi5.setIbu(20.5);
		pi5.setKolicina(150);
		pi5.setAlkohol(4.7);
		pi5.setPivara(p5);
		pi5.setVrstaPiva(v3);
		pivoService.save(pi5);
		
	
		
	}
}
