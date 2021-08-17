package Herramientas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.StringTokenizer;

import ED.Date;
import Excepciones.AlreadyInUseException;
import Excepciones.InvalidPasswordException;
import Excepciones.NonExistingUserException;
import Principal.Company;
import Principal.Contacto;
import Principal.Evento;
import Principal.Movimiento;
import Principal.Usuario;

public class DataBaseManager {

	// Métodos - Version Alfa
	public static void RegistrarUsuario(String id, String password,Company c) throws AlreadyInUseException {
		String path = "BD"; // Ruta a la base de datos + nombre de fichero
		File user = new File(path);
		
		try {
			
			user.mkdirs();
			
			path = path +"/"+ id +".user";
			user = new File(path);
			
			// Comprobar si el fichero existe
			if (user.exists())
				throw new AlreadyInUseException("User ID already in use");

			// Crear Fichero de usuario en BD
			
			user.createNewFile();

			// Dar permisos de escritura
			user.setWritable(true);

			// Escribir Contraseña en el fichero
			// Escribir Identificador de Movimientos en el fichero
			PrintWriter writer = new PrintWriter(user);
			writer.println("C>>"+password);
			writer.println("MID>>0");
			writer.println("EID>>0");
			writer.println(c.dumpCompany());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void LogIn(String id, String Contrasena, Usuario ActiveUser) throws NonExistingUserException, InvalidPasswordException {
		String path = "BD/" + id + ".user";
		File user = new File(path);

		// Nombre de Usuario no existe
		if (!user.exists())
			throw new NonExistingUserException("Invalid User ID");

		try {
			user.setReadable(true);
			Scanner reader = new Scanner(user);

			// Leer contraseña del nombre de usuario
			String PassWord = reader.nextLine().substring(3); //Eliminar Caracteres Identificadores
			Integer movID  = Integer.parseInt(reader.nextLine().substring(5));
			Integer evntID = Integer.parseInt(reader.nextLine().substring(5));
			reader.close();

			// Si la contraseña del fichero no coincide con la del fichero lanzar error
			if (!PassWord.equals(Contrasena)) {
				throw new InvalidPasswordException("Wrong Password");
			} else {
				// Configurar Usuario con los datos leidos
				ActiveUser.setUserName(id);
				ActiveUser.setUserPassword(PassWord);
				ActiveUser.setmovId(movID);
				ActiveUser.setevntId(evntID);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void CargarDatos(Usuario activeUser) {
		String path = "BD/" + activeUser.getUserName() + ".user";
		File userFile = new File(path);

		try (Scanner sc = new Scanner(userFile);) {
			sc.useDelimiter(">>");

			while (sc.hasNextLine()) {
				StringTokenizer st = new StringTokenizer(sc.nextLine(), ">>");

				switch (st.nextToken()) {
				case "M":
					// leer campos
					double cantidad = Double.parseDouble(st.nextToken());
					Date Fecha = new Date(st.nextToken());
					String concepto = st.nextToken();
					Integer id = Integer.parseInt(st.nextToken());
					String Contacto = st.nextToken();
					String Category = st.nextToken();

					// Añadir Movimiento a usuario
					Movimiento M = new Movimiento(cantidad, Fecha, concepto, id, Contacto,Category);
					activeUser.insertarMovimiento(M);
				break;
				
				case "CT":
					// leer campos
					String dni = st.nextToken();
					String name = st.nextToken();
					String Relationship = st.nextToken();
					String tlf = st.nextToken();
					
					//Añadir Contacto a usuario
					Contacto C = new Principal.Contacto(dni,name,Relationship,tlf);
					activeUser.addContact(C);
				break;
				
				case "CMP":
					String Name = st.nextToken();
					String Phone= st.nextToken();
					String Email= st.nextToken();
					String Address= st.nextToken();
					
					Company cmp = new Company(Name,Phone,Email,Address);
					activeUser.setCompany(cmp);
					
				break;
				
				case "EVNT":
					Integer evntid = Integer.parseInt(st.nextToken());
					Date  evntFecha = new Date(st.nextToken());
					String Descripcion = st.nextToken();
					String evntContacto = st.nextToken();
					
					Evento E = new Evento(evntid,evntFecha,Descripcion,evntContacto);
					activeUser.addEvent(E);
					
					break;
				
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void VolcarDatos(Usuario activeUser) {
		String path = "BD/" + activeUser.getUserName() + ".user";
		File userFile = new File(path);

		try (PrintWriter pw = new PrintWriter(userFile);) {
			//Escribir Contraseña  e Identificador de Movimientos
			pw.println("C>>"+activeUser.getUserPassword());
			pw.println("MID>>"+activeUser.getmovID());
			pw.println("EID>>"+activeUser.getevntID());
			
			
			//Escribir Informacion de la Compañia
			pw.println(activeUser.getCompany().dumpCompany());
			
			//En caso de que no haya movimientos no se escribe nada mas
			if (activeUser.getIteradorMovimientos() != null) {
				// Guardar Movimientos
				for (Movimiento m : activeUser.getIteradorMovimientos()) {
					double cantidad = m.getCantidad();

					int dia = m.getFecha().getDay();
					int mes = m.getFecha().getMonth();
					int year = m.getFecha().getYear();
					String Fecha = dia + "/" + mes + "/" + year;

					String concepto = m.getConcepto();

					int id = m.getIdentificador();

					String Contacto = m.getContacto();
					
					String Category = m.getCategory();

					pw.println("M>>" + cantidad + ">>" + Fecha + ">>" + concepto + ">>" + id + ">>" + Contacto + ">>"+ Category);
				}
			//Guardar Contactos
			pw.print(activeUser.dumpContacts());
			
			//Guardar Calendario
			pw.print(activeUser.dumpEvents());
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void BorrarUsuario(String id) {
		String path = "BD/" + id + ".user";
		String imagepath = "BD/"+id+".jpg";
		
		File user = new File(path);		
		user.delete();
		
		File image = new File(imagepath);
		if(image.exists()) {
			image.delete();
		}
	}
	
	//Operations - version 1.3
	public static void myCreateImage(File selectedFile, String usuario, String Mode) {
		try {
			
			if(Mode.equals("Create")) {
				
					String targetPath = "BD/";
					File dir = new File(targetPath);
					
					dir.mkdirs(); //Create directory //Just to avoid problems
					
					File dataBaseImage = new File("BD/"+usuario+".jpg");
					
					if(!dataBaseImage.exists()) {
						Files.copy(selectedFile.toPath(), dataBaseImage.toPath(),StandardCopyOption.COPY_ATTRIBUTES);
					}
			
			}else if (Mode.equals("Change")) {
				File dataBaseImage = new File("BD/"+usuario+".jpg");
				
				Files.copy(selectedFile.toPath(), dataBaseImage.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
