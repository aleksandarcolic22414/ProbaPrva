package korisnici;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Users implements Serializable {

	private String name;
	private String email;
	private char[] password;
	
	public Users(String name, String email, char[] password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Users() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
//	Redefinisanje equals metode tako da se poredjenje vrsi 
//	preko name vrednosti.
//	Boli nas uvo za sifru.
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public static LinkedList<Users>vratiListuKorisnika(){
		LinkedList<Users>korisnici = new LinkedList<>();
		try {
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("Users.out")));
			try {
				while(true){
				Users u = (Users)in.readObject();
				korisnici.add(u);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (EOFException eof){
				
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return korisnici;
	}

	
	
	
	
}
