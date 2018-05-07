package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;

//functionalitati
//i.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//ii.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//iii.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "intrebari.txt";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		AppController appController = new AppController();
		
		boolean activ = true;
		String optiune = null;
		
		while(activ){
			
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();

			switch(optiune){
			case "1" :
				 System.out.println("Introduceti enunt:");
				 String enunt = console.readLine();
				 System.out.println("Introduceti varianta1:");
				 String v1 = console.readLine();
				 System.out.println("Introduceti varianta2:");
				 String v2 = console.readLine();
				 System.out.println("Introduceti varianta corecta:");
				 String vc = console.readLine();
				 System.out.println("Introduceti domeniu:");
				 String d = console.readLine();
				 try{
					 appController.addNewIntrebare(new Intrebare(enunt,v1,v2,vc,d));
				 }catch(InputValidationFailedException x){
				 	System.out.println(x.getMessage());
				 }catch(DuplicateIntrebareException x){
					 System.out.println(x.getMessage());
				 }
				case "2" :
					try{
						appController.createNewTest();
					}catch(NotAbleToCreateTestException x){
						System.out.println(x);
					}
			case "3" :
				appController.loadIntrebariFromFile(file);
				Statistica statistica;
				try {
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					System.out.println(e);
				}
				
				break;
			case "4" :
				activ = false;
				break;
			default:
				break;
			}
		}
		
	}

}
