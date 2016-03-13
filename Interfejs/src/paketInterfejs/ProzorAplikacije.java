package paketInterfejs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptionPackage.EMailException;
import exceptionPackage.NameException;
import exceptionPackage.PasswordException;
import korisnici.Users;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

public class ProzorAplikacije {

	private JFrame frm;
	private JPanel panelStartPanel;
	private JButton btnLogin;
	private JButton btnRegister;
	private JLabel lblSelectOneOption;
	private JPanel panelRegister;
	private JTextField txtName;
	private JTextField txtMail;
	private JTextArea txtrPasswordPom;
	private JLabel lblName;
	private JLabel lblPassword;
	private JLabel lblmail;
	private JButton btnRegisterConfirm;
	LinkedList<Users>korisnici;
	private JPasswordField passwordField;
	private JLabel lblError;
	private JTextField txtError;
	private JPanel panelRegistrationConfirmed;
	private JTextField txtRegistrationConfirmed;
	private JButton btnContinue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProzorAplikacije window = new ProzorAplikacije();
					window.frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProzorAplikacije() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame();
		frm.setTitle("Aplikacija");
		frm.setBounds(100, 100, 450, 300);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add(getPanelStartPanel(), BorderLayout.CENTER);
	}
	
	private JPanel getPanelStartPanel() {
		if (panelStartPanel == null) {
			panelStartPanel = new JPanel();
			panelStartPanel.setLayout(null);
			panelStartPanel.add(getBtnLogin());
			panelStartPanel.add(getBtnRegister());
			panelStartPanel.add(getLblSelectOneOption());
			
		}
		return panelStartPanel;
	}
	
	private JPanel getPanelRegister() {
		if (panelRegister == null) {
			panelRegister = new JPanel();
			panelRegister.setLayout(null);
			panelRegister.add(getTxtName());
			panelRegister.add(getTxtMail());
			panelRegister.add(getTxtrPasswordPom());
			panelRegister.add(getLblName());
			panelRegister.add(getLblPassword());
			panelRegister.add(getLblmail());
			panelRegister.add(getBtnRegisterConfirm());
			panelRegister.add(getPasswordField());
			panelRegister.add(getLblError());
			panelRegister.add(getTxtError());
		}
		return panelRegister;
	}
	
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("login");
			btnLogin.setBounds(60, 116, 116, 51);
		}
		return btnLogin;
	}
	private JButton getBtnRegister() {
		if (btnRegister == null) {
			btnRegister = new JButton("register");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getPanelStartPanel().setVisible(false);
					frm.getContentPane().add(getPanelRegister(), BorderLayout.CENTER);
				}
			});
			btnRegister.setBounds(229, 116, 116, 51);
		}
		return btnRegister;
	}
	private JLabel getLblSelectOneOption() {
		if (lblSelectOneOption == null) {
			lblSelectOneOption = new JLabel("Select one option:");
			lblSelectOneOption.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblSelectOneOption.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectOneOption.setBounds(123, 38, 141, 35);
		}
		return lblSelectOneOption;
	}
	
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtName.setBounds(162, 33, 144, 26);
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JTextField getTxtMail() {
		if (txtMail == null) {
			txtMail = new JTextField();
			txtMail.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtMail.setBounds(162, 164, 144, 26);
			txtMail.setColumns(10);
		}
		return txtMail;
	}
	private JTextArea getTxtrPasswordPom() {
		if (txtrPasswordPom == null) {
			txtrPasswordPom = new JTextArea();
			txtrPasswordPom.setEnabled(false);
			txtrPasswordPom.setFont(new Font("Monospaced", Font.PLAIN, 10));
			txtrPasswordPom.setText("Password must contains \r\nat least 5 caracters!");
			txtrPasswordPom.setBounds(162, 121, 144, 32);
		}
		return txtrPasswordPom;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblName.setBounds(76, 39, 59, 14);
		}
		return lblName;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPassword.setBounds(76, 101, 85, 14);
		}
		return lblPassword;
	}
	private JLabel getLblmail() {
		if (lblmail == null) {
			lblmail = new JLabel("@mail:");
			lblmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblmail.setBounds(76, 166, 76, 18);
		}
		return lblmail;
	}
	private JButton getBtnRegisterConfirm() {
		if (btnRegisterConfirm == null) {
			btnRegisterConfirm = new JButton("Register");
			btnRegisterConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char[]password = getPasswordField().getPassword();
					String name = getTxtName().getText();
					String email = getTxtMail().getText();
					Users u = new Users(name, email, password);
					try {
						LinkedList<Users> listaKorisnika = Users.vratiListuKorisnika();
						
							if(listaKorisnika.contains(u))
								throw new NameException("Already exist!");
							if(name.equals(""))
								throw new NameException("Incorect name!");
							if(password.length < 5){
								throw new PasswordException("Invalid password!");
							}
							if(!email.contains("@"))
								throw new EMailException("Incorect mail!");
						
							try {
								ObjectOutputStream out = new ObjectOutputStream(
										new BufferedOutputStream(
												new FileOutputStream("Users.out")));
								out.writeObject(u);
								out.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						listaKorisnika.clear();
						getPanelRegister().setVisible(false);
						frm.getContentPane().add(getPanelRegistrationConfirmed()
								, BorderLayout.CENTER);						
						
						getTxtRegistrationConfirmed().setText("Congratulations " 
								+ name + ", you've succesffuly registered!");
						
					}catch (PasswordException ex){
						lblError.setVisible(true);
						txtError.setVisible(true);
						txtError.setText(ex.getMessage());
					} catch (NameException e1) {
						lblError.setVisible(true);
						txtError.setVisible(true);
						txtError.setText(e1.getMessage());
					} catch (EMailException e2) {
						lblError.setVisible(true);
						txtError.setVisible(true);
						txtError.setText(e2.getMessage());
					}
					
					
				}
			});
			btnRegisterConfirm.setBounds(335, 91, 89, 39);
		}
		return btnRegisterConfirm;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(162, 94, 144, 26);
		}
		return passwordField;
	}
	private JLabel getLblError() {
		if (lblError == null) {
			lblError = new JLabel("Error:");
			lblError.setEnabled(false);
			lblError.setVisible(false);
			lblError.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblError.setBounds(76, 215, 59, 26);
		}
		return lblError;
	}
	private JTextField getTxtError() {
		if (txtError == null) {
			txtError = new JTextField();
			txtError.setEditable(false);
			txtError.setVisible(false);
			txtError.setBounds(162, 218, 186, 25);
			txtError.setColumns(10);
		}
		return txtError;
	}
	private JPanel getPanelRegistrationConfirmed() {
		if (panelRegistrationConfirmed == null) {
			panelRegistrationConfirmed = new JPanel();
			panelRegistrationConfirmed.setVisible(true);
			panelRegistrationConfirmed.setBounds(0, 0, 434, 262);
			panelRegistrationConfirmed.setLayout(null);
			panelRegistrationConfirmed.add(getTxtRegistrationConfirmed());
			panelRegistrationConfirmed.add(getBtnContinue());
		}
		return panelRegistrationConfirmed;
	}
	private JTextField getTxtRegistrationConfirmed() {
		if (txtRegistrationConfirmed == null) {
			txtRegistrationConfirmed = new JTextField();
			txtRegistrationConfirmed.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtRegistrationConfirmed.setBounds(53, 25, 316, 102);
			txtRegistrationConfirmed.setColumns(10);
		}
		return txtRegistrationConfirmed;
	}
	private JButton getBtnContinue() {
		if (btnContinue == null) {
			btnContinue = new JButton("Press the button ");
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnContinue.setBounds(132, 138, 152, 60);
		}
		return btnContinue;
	}
}
