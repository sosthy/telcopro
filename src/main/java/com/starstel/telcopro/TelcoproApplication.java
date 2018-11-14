package com.starstel.telcopro;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.starstel.telcopro.accounts.entities.AppMenu;
import com.starstel.telcopro.accounts.entities.AppRole;
import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.accounts.entities.AppUserModel;
import com.starstel.telcopro.accounts.services.AccountService;
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.entities.WorkSpace;
import com.starstel.telcopro.rh.services.EmployeeService;
import com.starstel.telcopro.rh.services.WorkSpaceService;
import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Camera;
import com.starstel.telcopro.stocks.entities.Cpu;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.MeasureUnit;
import com.starstel.telcopro.stocks.entities.Memory;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.PointOfSale;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.PortableCategory;
import com.starstel.telcopro.stocks.entities.PortableItem;
import com.starstel.telcopro.stocks.entities.ProductCategory;
import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;
import com.starstel.telcopro.stocks.entities.State;
import com.starstel.telcopro.stocks.entities.SystemOS;
import com.starstel.telcopro.stocks.services.AppColorService;
import com.starstel.telcopro.stocks.services.EntrepotService;
import com.starstel.telcopro.stocks.services.MouvmentService;
import com.starstel.telcopro.stocks.services.PointOfSaleService;
import com.starstel.telcopro.stocks.services.PortableService;
import com.starstel.telcopro.stocks.services.ProductService;
import com.starstel.telcopro.stocks.services.RecipientService;
import com.starstel.telcopro.storage.Storageable;

@SpringBootApplication
public class TelcoproApplication extends SpringBootServletInitializer implements CommandLineRunner
{
	@Autowired
	private AccountService accountService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WorkSpaceService workSpaceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private RecipientService recipientService;
	@Autowired
	private MouvmentService mouvmentService;
	@Autowired
	private EntrepotService entrepotService;
	@Autowired
	private PortableService portableService;
	@Autowired
	private PointOfSaleService pointOfSaleService;
	
	@Autowired
	private AppColorService appColorService;
	
	@Autowired
	private Storageable storageable;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(TelcoproApplication.class, args);
	}
	
	@Bean // Instanciation de BCryptPasswordEncoder
	public BCryptPasswordEncoder getBCPE() 
	{	
		return new BCryptPasswordEncoder();
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TelcoproApplication.class);
    }
	
	@Override
	public void run(String... args) throws Exception 
	{	
		
		AppMenu menuProduct= new AppMenu("Inventory", "fa-building-o", "Manage stocks");
		AppMenu menuRh= new AppMenu("Resources", "fa-tachometer", "Manage resources");
		menuRh=accountService.createAppMenu(menuRh);
		menuProduct=accountService.createAppMenu(menuProduct);
		
		AppRole admin = new AppRole();
		admin.setRoleName("ADMIN");
		admin.setDescription("No Description");

		AppRole magasinier = new AppRole();
		magasinier.setRoleName("MAGASINIER");
		magasinier.setDescription("employé s'occupant d'un magasin de l'entreprise");
		magasinier.getMenus().add(menuProduct);
		
		AppRole humanRessource = new AppRole();
		humanRessource.setRoleName("RH");
		humanRessource.setDescription("employé travaillant aux ressources humaines de l'entreprise");
		humanRessource.getMenus().add(menuRh);
		
		AppRole simple = new AppRole();
		simple.setRoleName("SIMPLE");
		simple.setDescription("No Description");
		
		admin = accountService.saveRole(admin);
		magasinier = accountService.saveRole(magasinier);
		humanRessource = accountService.saveRole(humanRessource);
		simple = accountService.saveRole(simple);

		AppUserModel user = new AppUserModel("admin","admin","sosthenegolden@gmail.com",Boolean.FALSE);
		user.getRoles().add(admin);
		user.getRoles().add(simple);
		
		AppUserModel user2 = new AppUserModel("admin2","admin2","romeo@gmail.com",Boolean.FALSE);
		user2.getRoles().add(magasinier);
		
		AppUserModel user3 = new AppUserModel("admin3","admin3","fabien@gmail.com",Boolean.FALSE);
		user3.getRoles().add(humanRessource);
		
		PointOfSale boutique = new PointOfSale("Boutique 2B","Douala Marché central");
		PointOfSale boutique2 = new PointOfSale("Boutique 14M","Douala Marché Dakar");
		PointOfSale boutique3 = new PointOfSale("Boutique 75FG","Douala Marché Bonamoussadi");
		
		boutique= pointOfSaleService.save(boutique);
		boutique2= pointOfSaleService.save(boutique2);
		boutique3= pointOfSaleService.save(boutique3);
		
		WorkSpace space = new WorkSpace("Startel Deido","Douala Deido rue kotto à 100m d'ecoMarche");
		WorkSpace space2 = new WorkSpace("Startel Nyalla","Douala Nyalla à 100m de total Nyalla");
		
		Employee employee = new Employee(null,"NOUEBISSI NGHEMNIN","Sosthene","78221242","rsosthenegolden@gmail.com",
				"M","KIT272","XXXX",new Date(),new Date(),Integer.valueOf(4),space);
		
		Employee employee2 = new Employee(null,"TCHECHE","Romeo","693936236","romeo.@gmail.com","M","475JHk5",
				"romeo.png",new Date(),new Date(),Integer.valueOf(1),space2);
		
		Employee employee3 = new Employee(null,"NZUKO","QUEVIN","52481234","fabien.@gmail.com","M","785427JKDF3",
				"romeo.png",new Date(),new Date(),Integer.valueOf(1),space);
		
		Employee employee4 = new Employee(null,"unknow","FRANCIS","6952478625","francis.@gmail.com","M","8966J245M",
				"francis.png",new Date(),new Date(),Integer.valueOf(1),boutique);
		
		Employee employee5 = new Employee(null,"unknow","GAELLE","52244452524","gaelle.@gmail.com","F","4513GJDJ7",
				"gaelle.png",new Date(),new Date(),Integer.valueOf(1),boutique2);
		
		Employee employee6 = new Employee(null,"EBOUA","OSE","5282283","ose.@gmail.com","M","782LLM3",
				"ose.png",new Date(),new Date(),Integer.valueOf(1),boutique3);
		
		employee=employeeService.createEmployee(employee);
		employee2=employeeService.createEmployee(employee2);
		employee3=employeeService.createEmployee(employee3);
		employee4=employeeService.createEmployee(employee4);
		employee5=employeeService.createEmployee(employee5);
		employee6=employeeService.createEmployee(employee6);
		
		user.setEmployee(employee);
		user2.setEmployee(employee2);
		user3.setEmployee(employee3);
		
		AppUser cuser=accountService.saveUser(user);
		AppUser cuser2=accountService.saveUser(user2);
		AppUser cuser3=accountService.saveUser(user3);
		
		RecipientGroupe groupe1 = new RecipientGroupe(null, "FOURNISSEURS", null);
		RecipientGroupe groupe2 = new RecipientGroupe(null, "CLIENTS", null);
		groupe1 = recipientService.createRecipientGroupe(groupe1);
		groupe2 = recipientService.createRecipientGroupe(groupe2);
		
		Recipient recipient1 = new Recipient("Citoyen", "SOREPCO", "Maintenance", "Surveillant", null, null);
		recipient1.setGroupe(groupe1);
		recipient1 = recipientService.createRecipient(recipient1);
		
		Recipient recipient2 = new Recipient("Etranger", "Orange", "Reseau", "Adminitrateur", null, null);
		recipient2.setGroupe(groupe2);
		recipient2 = recipientService.createRecipient(recipient2);
		
		Recipient recipient3 = new Recipient("Etranger", "Nokia", "Telecom", "Technicien", null, null);
		recipient3.setGroupe(groupe2);
		recipient3 = recipientService.createRecipient(recipient3);
		
		MouvmentType mouvmentType1 = new MouvmentType(null, "APPROVISIONNEMENT", null, "No Description");
		MouvmentType mouvmentType2 = new MouvmentType(null, "RETOUR", null, "No Description");
		MouvmentType mouvmentType3 = new MouvmentType(null, "LIVRAISON", null, "No Description");
		MouvmentType mouvmentType4 = new MouvmentType(null, "TRANSFERT", null, "No Description");
		
		mouvmentType1 = mouvmentService.saveMouvmentType(mouvmentType1);
		mouvmentType2 = mouvmentService.saveMouvmentType(mouvmentType2);
		mouvmentType3 = mouvmentService.saveMouvmentType(mouvmentType3);
		mouvmentType4 = mouvmentService.saveMouvmentType(mouvmentType4);
		
		Entrepot entrepot1 = new Entrepot("Entrepot Douala", "Rond point 4ème", 0.0, 0.0, 100.0, 70.0);
		Entrepot entrepot2 = new Entrepot("Entrepot Douala2", "Maképe Saint tropèze", 0.0, 0.0, 100.0, 70.0);
		entrepot1 = entrepotService.saveEntrepot(entrepot1);
		entrepot2 = entrepotService.saveEntrepot(entrepot2);
		
		Emplacement emplacement1 = new Emplacement(null, "Magasin Sabba", entrepot1, null);
		Emplacement emplacement2 = new Emplacement(null, "Etage", entrepot1, null);
		emplacement1 = entrepotService.saveEmplacement(emplacement1);
		emplacement2 = entrepotService.saveEmplacement(emplacement2);
		

		Camera camera = new Camera(null, 8.0, 13.0);
		Camera camera2 = new Camera(null, 10.0, 13.0);
		Camera camera3 = new Camera(null, 10.0, 16.0);
		

		State state = new State("Disponible");
		State state2 = new State("Indisponible");
		State state3= new State("En attente de livraison");

		Cpu cpu = new Cpu("MT1658M Quad-core", 13.0);
		Cpu cpu2 = new Cpu("MHZ", 13.0);
		Cpu cpu3 = new Cpu("KHZ", 16.0);

		Memory memory = new Memory(12.5,12.5,"GHZ");
		Memory memory2 = new Memory(14.5,75.5,"MHZ");
		Memory memory3 = new Memory(12.0,12.5,"KHZ");

		MeasureUnit measureUnit = new MeasureUnit("dm");
		MeasureUnit measureUnit2 = new MeasureUnit("mm");
		MeasureUnit measureUnit3 = new MeasureUnit("m");
		
		SystemOS os = new SystemOS("Android Oreo","7.0");

		ProductCategory productCategory = new ProductCategory("Téléphone","RAS");
		
		AppColor redColor = new AppColor("RED");
		AppColor blueColor = new AppColor("BLUE");
		AppColor blackColor = new AppColor("BLACK");
		AppColor whiteColor = new AppColor("WHITE"); 

		PortableCategory portableCategory = new PortableCategory(null,"SMART FAMILLY","Un pack complet pour la famille");
		PortableCategory portableCategory2 = new PortableCategory(null,"XTouch Robot Family","Un pack complet pour les futuristes");
		PortableCategory portableCategory3 = new PortableCategory(null,"SMART FAMILLY","GHZ");
		PortableCategory portableCategory4 = new PortableCategory(null,"XTouch Robot Family","GHZ");
		
		
		Portable portable= new Portable(null, 2.0, new Date(), "XTOUCH X9", "XTOUCH_X9.jpeg", 85000.0, 75000.0, 800000.0, 
				20.0, 50.0, 20.0, "FACE ID UNLOCK", state, emplacement1, measureUnit, productCategory, null,"C",5.5,"2400mAh", 
				"Dual Sim 3G NetWork",960.0,138.0,"Ip64",false,false,false,false, null,memory,camera,cpu,os,
				portableCategory,blueColor);
		
		Portable portable1= new Portable(null, 0.0, new Date(), "Xbot Senior", "XTOUCH_Xbot_Senior.jpeg", 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", 
				state, emplacement2, measureUnit3, productCategory, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",false,false,false,
				true,null,memory2,camera2,cpu2,os,portableCategory2,redColor);
		
		Portable portable2= new Portable(null, 0.0, new Date(), "XTOUCH X10", "XTOUCH_X10.jpeg", 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", 
				state2, emplacement2, measureUnit, productCategory, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",false,true,false,
				true,null,memory3,camera2,cpu,os,portableCategory3,blueColor);
		
		Portable portable3= new Portable(null, 0.0, new Date(), "Xbot Senior", "XTOUCH_Xbot_Senior.jpeg", 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", 
				state2, emplacement1, measureUnit2, productCategory, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",true,false,true,
				false,null,memory2,camera3,cpu3,os,portableCategory4,blackColor);
		
		Portable portable4= new Portable(null, 0.0, new Date(), "XTOUCH E4", "XTOUCH_E4.jpeg", 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", state3,
				emplacement1, measureUnit3, productCategory, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",false,true,true,false,
				null,memory2,camera,cpu2,os,portableCategory4,whiteColor);
		
		
		System.err.println("ready to save first product");
		
		portableService.save(portable);
		portableService.save(portable1);
		portableService.save(portable2);
		portableService.save(portable3);
		portableService.save(portable4);


		Mouvment mouvment1 = new Mouvment(null, "ML752P", new Date(), 0.0, 0.0, entrepot1, null, null, mouvmentType1, employee, recipient1);
		Mouvment mouvment2 = new Mouvment(null, "OZ986E", new Date(), 0.0, 0.0, entrepot1, null, null, mouvmentType1, employee, recipient1);

		mouvment1 = mouvmentService.saveMouvment(mouvment1);
		mouvment2 = mouvmentService.saveMouvment(mouvment2);
		
		MouvmentLine mouvmentLine1 = new MouvmentLine(null, 10D, 60000D, 600000D, mouvment1, portable,"");
		MouvmentLine mouvmentLine2 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment2, portable2,"");
		MouvmentLine mouvmentLine3 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment2, portable3,"");
		MouvmentLine mouvmentLine4 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment1, portable4,"");
		MouvmentLine mouvmentLine5 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment2, portable4,"");
		
		mouvmentService.saveMouvmentLine(mouvmentLine1);	
		mouvmentService.saveMouvmentLine(mouvmentLine2);	
		mouvmentService.saveMouvmentLine(mouvmentLine3);	
		mouvmentService.saveMouvmentLine(mouvmentLine4);	
		mouvmentService.saveMouvmentLine(mouvmentLine5);
	}
}
