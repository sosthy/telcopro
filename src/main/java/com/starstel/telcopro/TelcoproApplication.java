package com.starstel.telcopro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.starstel.telcopro.stocks.entities.Commande;
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
import com.starstel.telcopro.stocks.services.CommandeService;
import com.starstel.telcopro.stocks.services.EntrepotService;
import com.starstel.telcopro.stocks.services.MouvmentService;
import com.starstel.telcopro.stocks.services.PointOfSaleService;
import com.starstel.telcopro.stocks.services.PortableItemService;
import com.starstel.telcopro.stocks.services.PortableService;
import com.starstel.telcopro.stocks.services.ProductService;
import com.starstel.telcopro.stocks.services.RecipientService;
import com.starstel.telcopro.storage.entities.Storage;
import com.starstel.telcopro.storage.services.ReportManager;
import com.starstel.telcopro.storage.services.Reportable;
import com.starstel.telcopro.storage.services.Storageable;

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
	private PortableItemService portableItemService;
	@Autowired
	private PointOfSaleService pointOfSaleService;
	@Autowired
	private AppColorService appColorService;
	@Autowired
	private CommandeService commandeService;
	
	@Autowired
	private Storageable storageable;
	@Autowired
	private Reportable reporter;
	
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
		storageable.init();

		AppMenu menuProduct= new AppMenu("Inventory", "fa-building-o", "/inventories", "bg-brown", "Stock Management");
		AppMenu menuAccount= new AppMenu("Accounts", "fa-user-circle-o", "/accounts/users", "bg-green", "Users Accounts");
		AppMenu menuRh= new AppMenu("Resources", "fa-user-o", "/rh", "bg-deep-purple", "Humans Resources Management");
		menuRh=accountService.createAppMenu(menuRh);
		menuProduct=accountService.createAppMenu(menuProduct);
		menuAccount=accountService.createAppMenu(menuAccount);
		
		AppRole admin = new AppRole();
		admin.setRoleName("ADMIN");
		admin.setDescription("No Description");
		admin.getMenus().add(menuProduct);
		admin.getMenus().add(menuRh);
		admin.getMenus().add(menuAccount);

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
		
		PointOfSale boutique = new PointOfSale("Boutique 2B","Douala Marché central","+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com");
		PointOfSale boutique2 = new PointOfSale("Boutique 14M","Douala Marché Dakar","+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com");
		PointOfSale boutique3 = new PointOfSale("Boutique 75FG","Douala Marché Bonamoussadi","+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com");
		
		boutique= pointOfSaleService.save(boutique);
		boutique2= pointOfSaleService.save(boutique2);
		boutique3= pointOfSaleService.save(boutique3);
		
		WorkSpace space = new WorkSpace("Startel Deido","Douala Deido EcoMarche","+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com");
		WorkSpace space2 = new WorkSpace("Startel Nyalla","Douala Nyalla à 100m de total Nyalla","+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com");

		space= workSpaceService.saveWorkSpace(space);
		space2= workSpaceService.saveWorkSpace(space2);
		
		Entrepot entrepot1 = new Entrepot("Akwa", "Rond point 4ème", "+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com", 0.0, 0.0, 60.0, 70.0);
		Entrepot entrepot2 = new Entrepot("Deido", "Maképe Saint tropèze", "+237 695 71 53 54", "cameroon@xtouchdevice.com", "www.cameroon.xtouchdevice.com", 0.0, 0.0, 63.0, 100.0);
		entrepot1 = entrepotService.saveEntrepot(entrepot1);
		entrepot2 = entrepotService.saveEntrepot(entrepot2);
		
		Emplacement emplacement1 = new Emplacement(null, "ZONE A", entrepot1, null);
		Emplacement emplacement2 = new Emplacement(null, "ZONE B", entrepot1, null);
		Emplacement emplacement3 = new Emplacement(null, "RED PLACE", entrepot2, null);
		Emplacement emplacement4 = new Emplacement(null, "BLACK PLACE", entrepot2, null);
		
		emplacement1 = entrepotService.saveEmplacement(emplacement1);
		emplacement2 = entrepotService.saveEmplacement(emplacement2);
		emplacement3 = entrepotService.saveEmplacement(emplacement3);
		emplacement4 = entrepotService.saveEmplacement(emplacement4);
		
		Employee employee = new Employee(null,"Sosthene","N.","78221242","sosthenegolden@gmail.com",
				"M","KIT272","XXXX",new Date(),new Date(),Integer.valueOf(4), "Chef équipe DEV",entrepot2);
		
		Employee employee2 = new Employee(null,"TCHECHE","Romeo","693936236","romeo.@gmail.com","M","475JHk5",
				"romeo.png",new Date(),new Date(),Integer.valueOf(1), "Developpeur",space2);
		
		Employee employee3 = new Employee(null,"NZUKO","QUEVIN","52481234","fabien.@gmail.com","M","785427JKDF3",
				"romeo.png",new Date(),new Date(),Integer.valueOf(1), "Developpeur",space);
		
		Employee employee4 = new Employee(null,"SIAKA","FRANCIS","6952478625","francis.@gmail.com","M","8966J245M",
				"francis.png",new Date(),new Date(),Integer.valueOf(1), "Marketeur",boutique);
		
		Employee employee5 = new Employee(null,"Yimga","GAELLE","52244452524","gaelle.@gmail.com","F","4513GJDJ7",
				"gaelle.png",new Date(),new Date(),Integer.valueOf(1), "Marketeuse",boutique2);
		
		Employee employee6 = new Employee(null,"EBOUA","OSE","5282283","ose.@gmail.com","M","782LLM3",
				"ose.png",new Date(),new Date(),Integer.valueOf(1), "Superviseur équipe DEV",boutique3);
		
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
		
		RecipientGroupe groupe1 = new RecipientGroupe(null, "FOURNISSEUR", null);
		RecipientGroupe groupe2 = new RecipientGroupe(null, "CLIENT", null);
		groupe1 = recipientService.createRecipientGroupe(groupe1);
		groupe2 = recipientService.createRecipientGroupe(groupe2);
		
		Recipient recipient1 = new Recipient("SOREPCO", "Bepanda double balle", "sorepco-bepanda.com", "22452389");
		recipient1.setGroupe(groupe2);
		recipient1 = recipientService.createRecipient(recipient1);
		
		Recipient recipient2 = new Recipient("ORANGE CAMEROUN", "Akwa boulevard de la liberté", "orangecameroon.com", "699548147");
		recipient2.setGroupe(groupe2);
		recipient2 = recipientService.createRecipient(recipient2);
		
		Recipient recipient3 = new Recipient("NOKIA", "Allemagne", "nokia.fr", "+023 0234875632");
		recipient3.setGroupe(groupe1);
		recipient3 = recipientService.createRecipient(recipient3);
		
		Recipient recipient4 = new Recipient("XTOUCH", "DUBAÏ", "xtouchdevice.com", "+730 6533478");
		recipient4.setGroupe(groupe1);
		recipient4 = recipientService.createRecipient(recipient4);
		
		Recipient recipient5 = new Recipient("TCHECHE ROMEO", "Logpom andem", "romeo@gmail.com", "693936236");
		recipient5.setGroupe(groupe2);
		recipient5 = recipientService.createRecipient(recipient5);
		
		MouvmentType mouvmentType1 = new MouvmentType(null, "APPROVISIONNEMENT", null, "No Description");
		MouvmentType mouvmentType2 = new MouvmentType(null, "RETOUR", null, "No Description");
		MouvmentType mouvmentType3 = new MouvmentType(null, "LIVRAISON", null, "No Description");
		MouvmentType mouvmentType4 = new MouvmentType(null, "TRANSFERT", null, "No Description");
		
		mouvmentType1 = mouvmentService.saveMouvmentType(mouvmentType1);
		mouvmentType2 = mouvmentService.saveMouvmentType(mouvmentType2);
		mouvmentType3 = mouvmentService.saveMouvmentType(mouvmentType3);
		mouvmentType4 = mouvmentService.saveMouvmentType(mouvmentType4);

		Camera camera = new Camera(null, 8.0, 13.0);
		Camera camera2 = new Camera(null, 10.0, 13.0);
		Camera camera3 = new Camera(null, 10.0, 16.0);
		Camera camera4 = new Camera(null, 0.0, 0.6);
		
		camera = portableService.saveCamera(camera);
		camera2 = portableService.saveCamera(camera2);
		camera3 = portableService.saveCamera(camera3);
		camera4 = portableService.saveCamera(camera4);

		State state = new State("Disponible");
		State state2 = new State("Indisponible");
		State state3= new State("En attente de livraison");

		state = productService.saveState(state);
		state2 = productService.saveState(state2);
		state3 = productService.saveState(state3);

		Cpu cpu = new Cpu("MT1658M Quad-core", 13.0);
		Cpu cpu2 = new Cpu("MT6261D", 13.0);
		Cpu cpu3 = new Cpu("F785JK63X", 16.0);
		
		cpu = portableService.saveCpu(cpu);
		cpu2 = portableService.saveCpu(cpu2);
		cpu3 = portableService.saveCpu(cpu3);

		Memory memory = new Memory(12.5,12.5,"GB");
		Memory memory2 = new Memory(14.5,75.5,"MB");
		Memory memory3 = new Memory(12.0,12.5,"KB");
		
		memory = portableService.saveMemory(memory);
		memory2 = portableService.saveMemory(memory2);
		memory3 = portableService.saveMemory(memory3);
		
		MeasureUnit carton = new MeasureUnit("Carton(s)");
		MeasureUnit paquet = new MeasureUnit("Paquet(s)");
		MeasureUnit paquet10 = new MeasureUnit("Paquet(s) de 10");
		MeasureUnit cargaison = new MeasureUnit("Cargaison(s)");
		MeasureUnit unite = new MeasureUnit("Unité(s)");
		
		carton = productService.saveMeasureUnit(carton);
		paquet = productService.saveMeasureUnit(paquet);
		paquet10 = productService.saveMeasureUnit(paquet10);
		cargaison = productService.saveMeasureUnit(cargaison);
		unite = productService.saveMeasureUnit(unite);
		
		SystemOS os = new SystemOS("Android Oreo","7.0");
		
		os = portableService.saveSystemOS(os);

		ProductCategory telephone = new ProductCategory("Téléphone","RAS");
		
		telephone = productService.saveProductCategory(telephone);
		
		AppColor redColor = new AppColor("RED");
		AppColor blueColor = new AppColor("BLUE");
		AppColor blackColor = new AppColor("BLACK");
		AppColor whiteColor = new AppColor("WHITE"); 
		
		redColor = appColorService.saveAppColor(redColor);
		blueColor = appColorService.saveAppColor(blueColor);
		blackColor = appColorService.saveAppColor(blackColor);
		whiteColor = appColorService.saveAppColor(whiteColor);

		PortableCategory portableCategory = new PortableCategory(null,"SMART FAMILLY","Un pack complet pour la famille");
		PortableCategory portableCategory2 = new PortableCategory(null,"XTouch Robot Family","Un pack complet pour les futuristes");
		PortableCategory portableCategory3 = new PortableCategory(null,"XTouch Tiny Tots","Un pack complet pour les simplistes");
		PortableCategory portableCategory4 = new PortableCategory(null,"XTouch Humanoïd Family","Un pack complet avec IA");

		portableCategory = portableService.savePortableCategory(portableCategory);
		portableCategory2 = portableService.savePortableCategory(portableCategory2);
		portableCategory3 = portableService.savePortableCategory(portableCategory3);
		portableCategory4 = portableService.savePortableCategory(portableCategory4);
		
		Portable portable= new Portable(null, 1.0, new Date(), "XTOUCH A4", "XTOUCH_A4.png", 85000.0, 75000.0, 800000.0, 
				20.0, 50.0, 20.0, "FACE ID UNLOCK", state, emplacement1, unite, telephone, null,"C",5.5,"2400mAh", 
				"Dual Sim 3G NetWork",960.0,138.0,"Ip64", null,memory,camera,cpu,os,
				portableCategory,blueColor);
	
		Portable portable1= new Portable(null, 0.0, new Date(), "Xbot Senior", "Xbot_Senior.png", 131000.0, 100000.0, 
				110000.0, 100.0, 2000.0, 20.0, "No", state, emplacement4, cargaison, telephone, null,"C",2D,"B",
				"Dual Sim 3G NetWork",2D,2D,"Ip",null,memory2,camera2,cpu2,os,portableCategory2,redColor);
		
		Portable portable2= new Portable(null, 1.0, new Date(), "XTOUCH X", "XTOUCH_X.png", 100000.0, 90000.0, 95000.0, 100.0, 
				20.0, 25.0, "No", state2, emplacement2, unite, telephone, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",
				null,memory3,camera2,cpu,os,portableCategory,blueColor);
		
		Portable portable3= new Portable(null, 1.0, new Date(), "Xbot Junior", "Xbot_Junior.png", 115000.0, 110000.0, 0.0, 11300.0, 
				20.0, 20.0, "No", state2, emplacement3, paquet10, telephone, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",
				null,memory2,camera3,cpu3,os,portableCategory2,blackColor);
		
		Portable portable4= new Portable(null, 0.0, new Date(), "XTOUCH E4", "XTOUCH_E4.png", 60000.0, 50000.0, 55000.0, 100.0, 
				20.0, 18.0, "No", state3,emplacement1, cargaison, telephone, null,"C",2D,"B","Dual Sim 3G NetWork",2D,2D,"Ip",
				null,memory2,camera,cpu2,os,portableCategory,whiteColor);
		
		Portable portable5= new Portable(null, 3.0, new Date(), "L4 Bar Phone", "L4_Bar_Phone.png", 7000.0, 5000.0, 6000.0, 
				100.0, 20.0, 10.0, "No", state2, emplacement4, unite, telephone, null,"C",2D,"B","Dual Sim 3G NetWork",2D,
				2D,"Ip", null,memory3,camera4,cpu2,os,portableCategory,whiteColor);
		
		portable = portableService.save(portable);
		portable1 = portableService.save(portable1);
		portable2 = portableService.save(portable2);
		portable3 = portableService.save(portable3);
		portable4 = portableService.save(portable4);
		portable5 = portableService.save(portable5);

		PortableItem item = new PortableItem(null, "££@3&dk&ld", "123456646789", "417854552112/4455778522147", "PIO", true, portable5, null);
		PortableItem item2 = new PortableItem(null, "££@3&dk&ld", "1234114456789", "417854552112/4455778522147", "PIO", true, portable5, null);
		PortableItem item3 = new PortableItem(null, "££@3&dk&ld", "123451126789", "417854552112/4455778522147", "PIO", true, portable5, null);
		PortableItem item4 = new PortableItem(null, "££@3&dk&ld", "123456646789", "4178285757112/45275225222147", "PIO", true, portable, null);
		PortableItem item5 = new PortableItem(null, "££@3&dk&ld", "1234114456789", "415727522/445272727", "PIO", true, portable2, null);
		PortableItem item6 = new PortableItem(null, "££@3&dk&ld", "123451126789", "417854552527112/442758522147", "PIO", true, portable3, null);
		
		item = portableItemService.save(item);
		item2 = portableItemService.save(item2);
		item3 = portableItemService.save(item3);
		item4 = portableItemService.save(item4);
		item5 = portableItemService.save(item5);
		item6 = portableItemService.save(item6);
		
		Set<PortableItem> items = new HashSet<>();
		items.add(item);
		items.add(item2);
		Set<PortableItem> items2 = new HashSet<>();
		items2.add(item3);
		items2.add(item4);
		Set<PortableItem> items3 = new HashSet<>();
		items3.add(item5);
		items3.add(item6);
		
		
		Mouvment mouvment1 = new Mouvment(null, new Date(), 0.0, 0.0, null, null, mouvmentType1, employee, null, recipient3);
		Mouvment mouvment2 = new Mouvment(null, new Date(), 0.0, 0.0, entrepot2, null, mouvmentType1, employee, null, recipient4);

		mouvment1 = mouvmentService.saveMouvment(mouvment1);
		mouvment2 = mouvmentService.saveMouvment(mouvment2);
		MouvmentLine mouvmentLine1 = new MouvmentLine(null, 10D, 60000D, 600000D, mouvment1, portable, items, "");
		MouvmentLine mouvmentLine2 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment2, portable2, items, "");
		MouvmentLine mouvmentLine3 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment2, portable3, items2, "");
		MouvmentLine mouvmentLine4 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment1, portable4, items2, "");
		MouvmentLine mouvmentLine5 = new MouvmentLine(null, 10D, 80000D, 800000D, mouvment2, portable4, items3, "");
		
		mouvmentLine1 = mouvmentService.saveMouvmentLine(mouvmentLine1);	
		mouvmentLine2 = mouvmentService.saveMouvmentLine(mouvmentLine2);	
		mouvmentLine3 = mouvmentService.saveMouvmentLine(mouvmentLine3);	
		mouvmentLine4 = mouvmentService.saveMouvmentLine(mouvmentLine4);	
		mouvmentLine5 = mouvmentService.saveMouvmentLine(mouvmentLine5);
		
		Commande commande = new Commande(mouvment1, true, new Date());
		Commande commande2 = new Commande(mouvment1, false, new Date());
		Commande commande3 = new Commande(mouvment2, true, new Date());
		Commande commande4 = new Commande(mouvment2, false, new Date());

		commandeService.saveCommande(commande);
		commandeService.saveCommande(commande2);
		commandeService.saveCommande(commande3);
		commandeService.saveCommande(commande4);

		
		commande.setMouvmentLines(new ArrayList<>());
		commande2.setMouvmentLines(new ArrayList<>());
		commande3.setMouvmentLines(new ArrayList<>());
		commande4.setMouvmentLines(new ArrayList<>());
		
		commande.getMouvmentLines().add(mouvmentLine1);
		commande.getMouvmentLines().add(mouvmentLine4);
		commande2.getMouvmentLines().add(mouvmentLine1);
		commande2.getMouvmentLines().add(mouvmentLine4);

		commande3.getMouvmentLines().add(mouvmentLine1);
		commande3.getMouvmentLines().add(mouvmentLine4);
		commande4.getMouvmentLines().add(mouvmentLine1);
		commande4.getMouvmentLines().add(mouvmentLine4);

		mouvment1.setMouvmentLines(new ArrayList<>());
		mouvment2.setMouvmentLines(new ArrayList<>());
		mouvment1.getMouvmentLines().add(mouvmentLine1);
		mouvment1.getMouvmentLines().add(mouvmentLine4);
		mouvment2.getMouvmentLines().add(mouvmentLine2);
		mouvment2.getMouvmentLines().add(mouvmentLine3);
		mouvment2.getMouvmentLines().add(mouvmentLine5);
		
		reporter.reportCommande(commande);
		reporter.reportCommande(commande2);
		reporter.reportCommande(commande3);
		reporter.reportCommande(commande4);
		
		reporter.reportOutPut(mouvment1);
		reporter.reportOutPut(mouvment2);
	}
}
