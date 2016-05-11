package strategyModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UI extends JFrame {
	private JTextField txtNum;
	private JTextField txtPrice;
	final String[] str = new String[] { "请选择...", "正常收费", "打八折", "满300返100" };

	public UI() {
		getContentPane().setLayout(null);

		JLabel lbNum = new JLabel("数量:");
		lbNum.setBounds(24, 36, 41, 28);
		getContentPane().add(lbNum);

		txtNum = new JTextField();
		txtNum.setBounds(75, 34, 137, 28);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);

		JLabel lbPrice = new JLabel("价格:");
		lbPrice.setBounds(24, 74, 41, 34);
		getContentPane().add(lbPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(75, 80, 137, 28);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lbSelect = new JLabel("总计:");
		lbSelect.setBounds(24, 118, 41, 26);
		getContentPane().add(lbSelect);
		
		
		final JTextArea totalArea = new JTextArea();
		Font f = new Font("宋体", Font.PLAIN, 24);
		totalArea.setFont(new Font("宋体", Font.PLAIN, 16));
		//totalArea.setBounds(24, 151, 307, 74);
			
		JScrollPane jsp = new JScrollPane(totalArea);
		jsp.setBounds(24, 151, 307, 74);
		getContentPane().add(jsp);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(str));
		comboBox.setToolTipText("");
		comboBox.setBounds(75, 123, 137, 21);
		getContentPane().add(comboBox);
		
		final JLabel lbtp = new JLabel("");
		lbtp.setForeground(Color.RED);
		lbtp.setHorizontalAlignment(SwingConstants.TRAILING);
		lbtp.setFont(new Font("宋体", Font.PLAIN, 24));
		lbtp.setBounds(77, 264, 207, 48);
		getContentPane().add(lbtp);

		JButton btnSure = new JButton("确定");
		btnSure.setBounds(238, 36, 93, 23);
		getContentPane().add(btnSure);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num = Integer.parseInt(txtNum.getText());
				int price = Integer.parseInt(txtPrice.getText());
				int index = comboBox.getSelectedIndex();
				String types = str[index];
				double total = num * price ;
				
				CashSuper cs = null;
				switch(types) {
					case "正常收费":
						CashNormal cn = new CashNormal();
						cs = cn;
						break;
					case "打八折":
						CashRebate cb = new CashRebate("0.8");
						cs = cb;
						break;
					case "满300返100":
						CashReturn ct = new CashReturn("300", "100");
						cs = ct;
						break;
				
				}
				double sum = cs.acciptCash(total);
				totalArea.append("数量:" + num + ",  价格:" + price +"\n" );
				lbtp.setText(String.valueOf(sum));
				
				
			}
			
		});
		

		JButton btnClear = new JButton("重置");
		btnClear.setBounds(238, 86, 93, 23);
		getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNum.setText("");
				txtPrice.setText("");
				comboBox.setSelectedIndex(0);
			}

		});

		

		JLabel lbTotal = new JLabel("总计");
		lbTotal.setForeground(Color.RED);
		lbTotal.setFont(new Font("宋体", Font.PLAIN, 14));
		lbTotal.setBounds(24, 235, 41, 34);
		getContentPane().add(lbTotal);


		this.setVisible(true);
		this.setSize(400, 400);

	}

	public static void main(String[] args) {
		new UI();
	}
}
