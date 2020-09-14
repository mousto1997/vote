package com.example.demo;

import java.io.File;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.Depute;
import com.example.demo.repository.DeputeRpository;

@SpringBootApplication
@ComponentScan({"com.example.demo", "controller"})
public class VoteApplication implements CommandLineRunner {
	@Autowired
	DeputeRpository metier;	

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
		//new File(ControllerImage.uploadDirectory).mkdir();
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		// 1
		Depute d1 = new Depute("moustapha", "BARRY", "Mamadou Moustapha", "barry09g@gmail.com", "Mamou", "Agriculteur", "Sonfonia C/RATOMA",
				"president", "PTA", "Agronomie, eau et forêt", "Sertificat en agronomie", "Sécretaire", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d1);
		// 2
		Depute d2 = new Depute("mariame", "SOW", "Mariame D", "bmariame@gmail.com", "Macenta", "Journaliste", "Géssia C/Matoto",
				"president", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d2);
		// 3
		Depute d3 = new Depute("wodi", "TOURE", "wodia", "wodi@gmail.com", "Dubréka", "Controleur général", "Kalouma",
				"president", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d3);
		// 4
		Depute d = new Depute("yarad", "YARADOUNO", "Manssa Moussa", "yarad@gmail.com", "N'Zérékoré", "Magistra", "Kaghelen C/Dubréka",
				"president", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d);
		// 5
		Depute d5 = new Depute("dores", "DORE", "JEAN ZAKELINE", "dores@gmail.com", "Kissidougou", "Avocate", "Kaghelen C/Dubréka",
				"president", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d5);
		// 6
		Depute d6 = new Depute("teea", "TEA", "Aniess", "teea@gmail.com", "Kissidougou", "Avocate", "Kaghelen C/Dubréka",
				"secretaire", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d6);
		// 7
		Depute d7 = new Depute("keit", "KEITA", "Ibrahima", "keit@gmail.com", "Guékédou", "Avocate", "Cosa C/Ratoma",
				"secretaire", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d7);
		// 8
		Depute d8 = new Depute("diane", "DIANE", "Aly", "diane@gmail.com", "Siguiri", "Avocate", "Koloma C/Ratoma",
				"secretaire", "PTA", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d8);
		// 9
		Depute d9 = new Depute("bangou", "BANGOURA", "Oumou Hawa", "dbangourao@gmail.com", "Kissidougou", "Avocate", "Kaghelen C/Dubréka",
				"secretaire", "USR", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d9);
		// 10
		Depute d10 = new Depute("daisya", "SY", "Amara", "daisya@gmail.com", "Kissidougou", "Avocate", "Kaghelen C/Dubréka",
				"membre", "USR", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d10);
		// 11
		Depute d11 = new Depute("dcamaraa", "CAMARA", "Alseny", "dcamaraa@gmail.com", "Kindia", "Avocate", "Sonfonia C/Ratoma",
				"membre", "USR", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d11);
		// 12
		Depute d12 = new Depute("dsoumahk", "SOUMAH", "Kallil Ibrahima", "dsoumahk@gmail.com", "Labé", "Avocate", "Kaghelen C/Dubréka",
				"membre", "USR", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d12);
		// 13
		Depute d13 = new Depute("dbarrio", "BARRY", "Oumar", "dbarrio@gmail.com", "Kissidougou", "Avocate", "Kaghelen C/Dubréka",
				"membre", "USR", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d13);
		// 14
		Depute d14 = new Depute("ddiallod", "DIALLO", "Elhadj Mamoudou", "ddiallod@gmail.com", "Mamou", "Avocate", "Kaghelen C/Dubréka",
				"membre", "USR", "Droit Civil", "Docteur en droit civil", "Vice presidente", "+224634374838", "", "Nationale",
				"Le parlement doit jouer sont role de representation nationale, de contrôler l'action gouvernementale et voter les lois de la république. Il doit jouer le rôle du premier contre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre lescontre poids au pouvoir executif. Le parlement en toute circonstance, doit vehiculer et defendre les veritables intérêt des population tout en restant objectif et réaliste.",
				true, bc.encode("12345"));
//		metier.save(d14);		
		System.out.println("bonjour !");
		
//		Users u =new Users("admin", bc.encode("123"),true);
//		Users v =new Users("user", bc.encode("1234"),true);
//		usersRepository.save(u);
//		usersRepository.save(v);
			
		
	}

}
