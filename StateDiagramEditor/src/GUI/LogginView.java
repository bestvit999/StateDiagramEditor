package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Proxy.ProxyController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;

public class LogginView extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField accountField;
	private JTextField passwordField;
	private JLabel lebel;

	public LogginView(String account,String password,ProxyController proxyController) {
		setTitle("Loggin");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setBounds(114, 86, 63, 15);
		contentPanel.add(lblAccount);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(114, 128, 63, 15);
		contentPanel.add(lblPassword);
		
		accountField = new JTextField();
		accountField.setBounds(185, 83, 122, 21);
		contentPanel.add(accountField);
		accountField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(185, 125, 122, 21);
		contentPanel.add(passwordField);
		
		lebel = new JLabel("Loggin Error, please reloggin.");
		lebel.setForeground(Color.RED);
		lebel.setBounds(128, 10, 190, 52);
		lebel.setVisible(false);
		contentPanel.add(lebel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("UML Version 1.");
		comboBox.addItem("UML Version 2.");
		comboBox.setBounds(186, 166, 121, 21);
		
		contentPanel.add(comboBox);
		
		JLabel lblUmlVersion = new JLabel("UML Version:");
		lblUmlVersion.setBounds(92, 169, 85, 15);
		contentPanel.add(lblUmlVersion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(account.equals(accountField.getText()) && password.equals(passwordField.getText())){
							proxyController.setValidation(true);
							proxyController.setSelectVerion(comboBox.getSelectedItem().toString());
							proxyController.startUp();
							setVisible(false);
						}else {
							proxyController.setValidation(false);
							lebel.setVisible(true);
							proxyController.setSelectVerion(comboBox.getSelectedItem().toString());					
							proxyController.startUp();
							
						}
							
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
