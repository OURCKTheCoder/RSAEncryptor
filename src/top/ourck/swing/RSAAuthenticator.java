package top.ourck.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import top.ourck.processor.RSADecryptor;
import top.ourck.processor.RSAEncryptor;
import top.ourck.processor.RSAKeyGenerator;
import top.ourck.utils.MD5Utils;
import top.ourck.utils.TypeConvert;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class RSAAuthenticator {

	private JFrame frame;
	private JTextField messageTextField;
	private JLabel lblCipher;
	private JTextField cipherTextField;
	private JTextField SKTextField;
	private JTextField PKTextField;
	private JTextField decMessageTextField;
	private JButton btnClear;
	private JButton button;
	private JButton btnGenerate;

	private List<BigInteger> kList;
	private RSAEncryptor enc = new RSAEncryptor();
	private RSADecryptor dec = new RSADecryptor();
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JTextField senderMsgTextField;
	private JTextField senderDigestTextField;
	private JTextField senderCipherTextField;
	private JTextField receiverCipherTextField;
	private JTextField receiverDgTextField;
	private JTextField receiverDgGottenTextField;
	private JTextField receiverMsgTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RSAAuthenticator window = new RSAAuthenticator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RSAAuthenticator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 843, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 819, 525);
		frame.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("RSA encrypting", null, panel, null);
		panel.setLayout(null);
		
		messageTextField = new JTextField();
		messageTextField.setBounds(288, 18, 507, 31);
		panel.add(messageTextField);
		messageTextField.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(47, 24, 99, 25);
		panel.add(lblMessage);
		
		cipherTextField = new JTextField();
		cipherTextField.setBounds(288, 67, 507, 31);
		panel.add(cipherTextField);
		cipherTextField.setColumns(10);
		
		lblCipher = new JLabel("Cipher");
		lblCipher.setBounds(47, 73, 99, 25);
		panel.add(lblCipher);
		
		JLabel lblEncryptedMessage = new JLabel("Decrypted message");
		lblEncryptedMessage.setBounds(47, 122, 213, 25);
		panel.add(lblEncryptedMessage);
		
		decMessageTextField = new JTextField();
		decMessageTextField.setBounds(288, 116, 507, 31);
		panel.add(decMessageTextField);
		decMessageTextField.setColumns(10);
		
		// TODO The show begins!
		JButton btnExecute = new JButton("Execute");
		btnExecute.setBounds(495, 162, 143, 33);
		panel.add(btnExecute);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(176, 162, 143, 33);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageTextField.setText("");
				cipherTextField.setText("");
				decMessageTextField.setText("");
			}
		});
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(kList == null) {
					kList = RSAKeyGenerator.generate();
					SKTextField.setText(kList.get(0).toString());
					PKTextField.setText(kList.get(1).toString());
				}
				
				String m = messageTextField.getText();
				BigInteger sk = kList.get(0); //new BigInteger(SKTextField.getText());
				BigInteger n = kList.get(2);
				String c = enc.encrypt(m, sk, n);
				cipherTextField.setText(c); // Encrypt!
				
				BigInteger pk = kList.get(1); //new BigInteger(PKTextField.getText());
				decMessageTextField.setText(dec.decrypt(c, pk, n)); // Decrypt!!
			}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("MD5(Hash) signing", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Message");
		label_1.setBounds(47, 61, 99, 25);
		panel_1.add(label_1);
		
		senderMsgTextField = new JTextField();
		senderMsgTextField.setColumns(10);
		senderMsgTextField.setBounds(288, 55, 507, 31);
		panel_1.add(senderMsgTextField);
		
		JLabel lblDigestCipher = new JLabel("Digest");
		lblDigestCipher.setBounds(47, 104, 222, 25);
		panel_1.add(lblDigestCipher);
		
		senderDigestTextField = new JTextField();
		senderDigestTextField.setColumns(10);
		senderDigestTextField.setBounds(288, 101, 507, 31);
		panel_1.add(senderDigestTextField);
		
		senderCipherTextField = new JTextField();
		senderCipherTextField.setColumns(10);
		senderCipherTextField.setBounds(288, 147, 507, 31);
		panel_1.add(senderCipherTextField);
		
		JLabel lblSenders = new JLabel("Sender's");
		lblSenders.setBounds(19, 18, 99, 25);
		panel_1.add(lblSenders);
		
		JLabel lblCipher_1 = new JLabel("Cipher");
		lblCipher_1.setBounds(47, 150, 222, 25);
		panel_1.add(lblCipher_1);
		
		JLabel lblReceivers = new JLabel("Receiver's");
		lblReceivers.setBounds(19, 199, 187, 25);
		panel_1.add(lblReceivers);
		
		JLabel lblReceivedCipher = new JLabel("Received cipher");
		lblReceivedCipher.setBounds(47, 242, 222, 25);
		panel_1.add(lblReceivedCipher);
		
		receiverCipherTextField = new JTextField();
		receiverCipherTextField.setColumns(10);
		receiverCipherTextField.setBounds(288, 236, 507, 31);
		panel_1.add(receiverCipherTextField);
		
		JLabel lblDigestCalculated = new JLabel("Digest calculated");
		lblDigestCalculated.setBounds(47, 285, 222, 25);
		panel_1.add(lblDigestCalculated);
		
		receiverDgTextField = new JTextField();
		receiverDgTextField.setColumns(10);
		receiverDgTextField.setBounds(288, 282, 507, 31);
		panel_1.add(receiverDgTextField);
		
		JLabel lblRestoredMessgae = new JLabel("Digest gotten");
		lblRestoredMessgae.setBounds(47, 331, 222, 25);
		panel_1.add(lblRestoredMessgae);
		
		receiverDgGottenTextField = new JTextField();
		receiverDgGottenTextField.setColumns(10);
		receiverDgGottenTextField.setBounds(288, 328, 507, 31);
		panel_1.add(receiverDgGottenTextField);
		
		JCheckBox isValidCheckBox = new JCheckBox("Valid!");
		isValidCheckBox.setEnabled(false);
		isValidCheckBox.setBounds(696, 370, 99, 33);
		panel_1.add(isValidCheckBox);
		
		JButton button_3 = new JButton("Execute");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(kList == null) {
					kList = RSAKeyGenerator.generate();
					SKTextField.setText(kList.get(0).toString());
					PKTextField.setText(kList.get(1).toString());
				}
				
				/**
				 * Alice side.
				 */
				// 1. Fetching data
				String m = senderMsgTextField.getText();
				byte[] message = TypeConvert.str2Bytes(m);
				BigInteger sk = kList.get(0);
				BigInteger n = kList.get(2);
				// 2. Use MD5 to get digest
				byte[] dg = MD5Utils.getDigest(TypeConvert.str2Bytes(m)); // Fixed 16 bytes!
				// 3. Encrypt digest
				byte[] encDg = enc.encrypt(dg, sk.toByteArray(), n);
				// 4. Refresh views.
				senderDigestTextField.setText(TypeConvert.bytes2Str(encDg));
				byte[] cipher = MD5Utils.merge(message, encDg);
				senderCipherTextField.setText(TypeConvert.bytes2Str(cipher));
				
				/**
				 * Bob side.
				 */
				// 0. Receive data from Alice.
				receiverCipherTextField.setText(TypeConvert.bytes2Str(cipher));
				// 1. Fetching digest.
				// TODO In real world, the message.length should be transfered along with cipher.
				byte[] encedDg = new byte[cipher.length - message.length]; // Fixed 16 bytes!
				System.arraycopy(cipher, message.length, encedDg, 0, cipher.length - message.length); 
				// 2. Decrypt digest.
				BigInteger pk = kList.get(1);
				byte[] decedDg = dec.decrypt(encedDg, pk.toByteArray(), n);
				// 3. Calculate digest.
				byte[] msgGotten = new byte[message.length];
				System.arraycopy(cipher, 0, msgGotten, 0, message.length);
				byte[] dgCalculated = MD5Utils.getDigest(msgGotten);
				// 4. Refresh views
				receiverDgGottenTextField.setText(TypeConvert.bytes2Str(decedDg));
				receiverDgTextField.setText(TypeConvert.bytes2Str(dgCalculated));
				receiverMsgTextField.setText(TypeConvert.bytes2Str(msgGotten));
				isValidCheckBox.setSelected(MD5Utils.compareDigest(dgCalculated, decedDg));
			}
		});
		button_3.setBounds(497, 435, 143, 33);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("Clear");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senderCipherTextField.setText("");
				senderMsgTextField.setText("");
				senderDigestTextField.setText("");
				receiverCipherTextField.setText("");
				receiverMsgTextField.setText("");
				receiverDgTextField.setText("");
				receiverDgGottenTextField.setText("");
				isValidCheckBox.setSelected(false);
			}
		});
		button_4.setBounds(178, 435, 143, 33);
		panel_1.add(button_4);
		
		receiverMsgTextField = new JTextField();
		receiverMsgTextField.setColumns(10);
		receiverMsgTextField.setBounds(288, 371, 393, 31);
		panel_1.add(receiverMsgTextField);
		
		JLabel lblMessageGotten = new JLabel("Message gotten");
		lblMessageGotten.setBounds(47, 374, 222, 25);
		panel_1.add(lblMessageGotten);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 819, 712);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUsers = new JLabel("Sender's");
		lblUsers.setBounds(19, 535, 99, 25);
		panel_2.add(lblUsers);
		
		PKTextField = new JTextField();
		PKTextField.setBounds(288, 571, 507, 31);
		panel_2.add(PKTextField);
		PKTextField.setEditable(false);
		PKTextField.setColumns(10);
		
		JLabel lblPublicKey = new JLabel("Public key");
		lblPublicKey.setBounds(47, 577, 159, 25);
		panel_2.add(lblPublicKey);
		
		JLabel label = new JLabel("Private key");
		label.setBounds(47, 621, 159, 25);
		panel_2.add(label);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(497, 661, 143, 33);
		panel_2.add(btnGenerate);
		
		SKTextField = new JTextField();
		SKTextField.setBounds(288, 615, 507, 31);
		panel_2.add(SKTextField);
		SKTextField.setEditable(false);
		SKTextField.setColumns(10);
		
		button = new JButton("Clear");
		button.setBounds(177, 661, 143, 33);
		panel_2.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PKTextField.setText("");
				SKTextField.setText("");
				kList = null;
			}
		});
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kList = RSAKeyGenerator.generate();
				SKTextField.setText(kList.get(0).toString());
				PKTextField.setText(kList.get(1).toString());
			}
		});
	}
}
