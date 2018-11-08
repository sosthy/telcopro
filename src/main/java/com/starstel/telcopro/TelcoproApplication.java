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
import com.starstel.telcopro.rh.services.EmployeeService;
import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Camera;
import com.starstel.telcopro.stocks.entities.Cpu;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.Memory;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.PortableItem;
import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;
import com.starstel.telcopro.stocks.services.AppColorService;
import com.starstel.telcopro.stocks.services.EntrepotService;
import com.starstel.telcopro.stocks.services.MouvmentService;
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
		storageable.readEmployesFile(false);
		
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
		
		Employee employee = new Employee(null,"NOUEBISSI NGHEMNIN","Sosthene","693936236","rsosthenegolden@gmail.com",
				"M","KIT272","655321007","XXXX",new Date(),new Date(),Integer.valueOf(4));
		
		Employee employee2 = new Employee(null,"TCHECHE","Romeo","693936236","romeo.@gmail.com","M","475JHk5","78221242",
				"romeo.png",new Date(),new Date(),Integer.valueOf(1));
		
		Employee employee3 = new Employee(null,"Fabien","DUBUISSON","52481234","fabien.@gmail.com","M","475JHk5","78221242",
				"romeo.png",new Date(),new Date(),Integer.valueOf(1));
		
		employee=employeeService.createEmployee(employee);
		employee2=employeeService.createEmployee(employee2);
		employee3=employeeService.createEmployee(employee3);
		
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
		
		Entrepot entrepot1 = new Entrepot(null, "Deido Plage", 0.0, 0.0, 100.0, 70.0, null, null);
		Entrepot entrepot2 = new Entrepot(null, "Akwa", 0.0, 0.0, 170.0, 100.0, null, null);
		entrepot1 = entrepotService.saveEntrepot(entrepot1);
		entrepot2 = entrepotService.saveEntrepot(entrepot2);
		
		Emplacement emplacement1 = new Emplacement(null, "Magasin Sabba", entrepot1, null);
		Emplacement emplacement2 = new Emplacement(null, "Etage", entrepot1, null);
		emplacement1 = entrepotService.saveEmplacement(emplacement1);
		emplacement2 = entrepotService.saveEmplacement(emplacement2);
		

		Camera camera = new Camera(null, 8.0, 13.0);
		Camera camera2 = new Camera(null, 10.0, 13.0);
		Camera camera3 = new Camera(null, 10.0, 16.0);

		Cpu cpu = new Cpu("GHZ", 13.0);
		Cpu cpu2 = new Cpu("MHZ", 13.0);
		Cpu cpu3 = new Cpu("KHZ", 16.0);

		Memory memory = new Memory(12.5,12.5,"GHZ");
		Memory memory2 = new Memory(14.5,75.5,"MHZ");
		Memory memory3 = new Memory(12.0,12.5,"KHZ");

		AppColor redColor = new AppColor("RED");
		AppColor blueColor = new AppColor("BLUE");
		AppColor blackColor = new AppColor("BLACK");
		AppColor whiteColor = new AppColor("WHITE");
		
		
		Portable portable= new Portable(null, 0.0, new Date(), "TECHNO Y2", null, 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", null, 
				emplacement1, null, null, null,"C",2D,"B","Sim",2D,2D,"Ip",true,true,true,true,
				null,memory,camera,cpu,null,null,blueColor);
		
		Portable portable1= new Portable(null, 0.0, new Date(), "ALCATEL Z3", null, 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", 
				null, emplacement2, null, null, null,"C",2D,"B","Sim",2D,2D,"Ip",false,false,false,
				true,null,memory2,camera2,cpu2,null,null,redColor);
		
		Portable portable2= new Portable(null, 0.0, new Date(), "SAMSUNG S2", null, 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", 
				null, emplacement2, null, null, null,"C",2D,"B","Sim",2D,2D,"Ip",false,true,false,
				true,null,memory3,camera2,cpu,null,null,blueColor);
		
		Portable portable3= new Portable(null, 0.0, new Date(), "INFINIX", null, 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", 
				null, emplacement1, null, null, null,"C",2D,"B","Sim",2D,2D,"Ip",true,false,true,
				false,null,memory2,camera3,cpu3,null,null,blackColor);
		
		Portable portable4= new Portable(null, 0.0, new Date(), "TECHNO Y3", null, 0.0, 0.0, 0.0, 100.0, 20.0, 2000.0, "No", null,
				emplacement1, null, null, null,"C",2D,"B","Sim",2D,2D,"Ip",false,true,true,false,
				null,memory2,camera,cpu2,null,null,whiteColor);
		
		
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
