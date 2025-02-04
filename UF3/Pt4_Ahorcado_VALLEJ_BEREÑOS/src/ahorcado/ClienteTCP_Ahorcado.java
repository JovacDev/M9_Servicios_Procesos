package ahorcado;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteTCP_Ahorcado {

	public static void main(String[] args) throws RemoteException {

		Scanner sc = new Scanner(System.in);
		String letra = new String();

		RMIAhorcInterface ahorcado = null;
		try {
			System.out.println("Localizando registro de objetos remotos...");
			Registry registry = LocateRegistry.getRegistry("192.168.40.47", 5555);
			System.out.println("Obteniendo el stub del objeto remoto...");
			ahorcado = (RMIAhorcInterface) registry.lookup("Ahorcado");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ahorcado != null) {
			System.out.println("Realizando operaciones con el objeto remoto...");
			try {
				System.out.println("La palabra tiene " + ahorcado.largoPalabra() + " letras.");
				boolean repetir = true;
				while (repetir) {
					System.out.println("Introduce una letra:");
					letra = sc.next();
					letra = String.valueOf(letra.charAt(0));
					System.out.println(ahorcado.devolverPalabra(letra) + "\n");
					repetir = ahorcado.finalizar();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Terminado");
		}
	}
}

