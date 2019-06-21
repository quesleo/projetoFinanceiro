package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		
		System.out.println("=== TEST 1: Usuario findById =====");

		List<Usuario> list = usuarioDao.findAll();
		for (Usuario obj : list) {
			System.out.println(obj);
		}
		
		sc.close();
	}
}
