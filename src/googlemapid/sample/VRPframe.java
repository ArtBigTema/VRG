package googlemapid.sample;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import vrg.VRGUtils;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class VRPframe extends javax.swing.JFrame {
	public VRPframe() {
		initComponents();
	}

	private void initComponents() {
		javax.swing.JPanel jPanel1;
		javax.swing.JPanel jPanel10;
		javax.swing.JPanel jPanel11;
		javax.swing.JPanel jPanel12;
		javax.swing.JPanel jPanel13;
		javax.swing.JPanel jPanel14;
		javax.swing.JPanel jPanel2;
		javax.swing.JPanel jPanel3;
		javax.swing.JPanel jPanel4;
		javax.swing.JPanel jPanel5;
		javax.swing.JPanel jPanel9;
		javax.swing.JScrollPane jScrollPane1;
		javax.swing.JScrollPane jScrollPane2;
		javax.swing.JScrollPane jScrollPane3;
		javax.swing.JScrollPane jScrollPane4;
		javax.swing.JSeparator jSeparator1;
		tabPanel = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		tfFirstAddres = new javax.swing.JTextField();
		checkBoxSave = new javax.swing.JCheckBox();
		checkBoxRoute = new javax.swing.JCheckBox();
		checkBoxWalkingDriving = new javax.swing.JCheckBox();
		jPanel9 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		tfNewAddress = new javax.swing.JTextField();
		btnAddAddres = new javax.swing.JButton();
		btnDeleteAddress = new javax.swing.JButton();
		btnGenerateAddres = new javax.swing.JButton();
		jPanel10 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		listIn = new javax.swing.JList();
		jPanel2 = new javax.swing.JPanel();
		jPanel11 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		listOut = new javax.swing.JList();
		jSeparator1 = new javax.swing.JSeparator();
		jPanel12 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel4 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jButton7 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		innerFrame = new javax.swing.JInternalFrame();
		jPanel4 = new javax.swing.JPanel();
		jPanel13 = new javax.swing.JPanel();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jPanel14 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		tableResult = new javax.swing.JTable();
		sliderZoom = new javax.swing.JSlider();
		jLabel5 = new javax.swing.JLabel();

		final int P_SIZE = GroupLayout.PREFERRED_SIZE;
		final int D_SIZE = GroupLayout.DEFAULT_SIZE;
		final short S_MAX = Short.MAX_VALUE;
		final javax.swing.GroupLayout.Alignment LEADING = javax.swing.GroupLayout.Alignment.LEADING;
		final java.awt.Font font = new java.awt.Font(VRGUtils.FONT_TAHOMA, 0, 14);
		final javax.swing.LayoutStyle.ComponentPlacement cUnR = javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
		final javax.swing.LayoutStyle.ComponentPlacement cR = javax.swing.LayoutStyle.ComponentPlacement.RELATED;
		final javax.swing.GroupLayout.Alignment trail = javax.swing.GroupLayout.Alignment.TRAILING;
		final javax.swing.GroupLayout.Alignment basel = javax.swing.GroupLayout.Alignment.BASELINE;

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		tabPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				tabPanMousePressed(evt);
			}
		});

		jLabel1.setFont(font);
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Введите начальный адрес");
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel1MouseClicked(evt);
			}
		});

		tfFirstAddres.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		tfFirstAddres.setText(VRP.getStandartFirstAddres());
		tfFirstAddres.setDisabledTextColor(new java.awt.Color(10, 10, 10));

		checkBoxSave.setText("Сохранить");
		checkBoxSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkBoxSave.setMaximumSize(new java.awt.Dimension(281, 23));
		checkBoxSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox1ActionPerformed(evt);
			}
		});

		checkBoxRoute.setText("Использовать прямые маршруты");
		checkBoxRoute.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkBoxRoute.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox2ActionPerformed(evt);
			}
		});

		checkBoxWalkingDriving.setText("Использовать часы/минуты");
		checkBoxWalkingDriving.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkBoxWalkingDriving.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
		checkBoxWalkingDriving.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout.createParallelGroup(LEADING).addGroup(
						trail,
						jPanel5Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel5Layout
												.createParallelGroup(trail)
												.addComponent(tfFirstAddres)
												.addComponent(jLabel1, LEADING, D_SIZE, D_SIZE, S_MAX)
												.addGroup(
														LEADING,
														jPanel5Layout.createSequentialGroup()
																.addComponent(checkBoxWalkingDriving, D_SIZE, D_SIZE, S_MAX)
																.addPreferredGap(cUnR)
																.addComponent(checkBoxSave, P_SIZE, 206, P_SIZE)
																.addPreferredGap(cUnR).addComponent(checkBoxRoute)))
								.addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(LEADING).addGroup(
				jPanel5Layout
						.createSequentialGroup()
						.addComponent(jLabel1, P_SIZE, 23, P_SIZE)
						.addPreferredGap(cR)
						.addComponent(tfFirstAddres, P_SIZE, D_SIZE, P_SIZE)
						.addPreferredGap(cR)
						.addGroup(
								jPanel5Layout.createParallelGroup(basel).addComponent(checkBoxSave, P_SIZE, D_SIZE, P_SIZE)
										.addComponent(checkBoxRoute).addComponent(checkBoxWalkingDriving))
						.addContainerGap(D_SIZE, S_MAX)));

		jLabel2.setFont(font);
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("Введите новый адрес");

		tfNewAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);

		tfFirstAddres.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tfFirstAddresMouseClicked(evt);
			}
		});
		tfNewAddress.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tfNewAddressMouseClicked(evt);
			}
		});

		btnAddAddres.setText("Добавить адрес");
		btnAddAddres.setMinimumSize(new java.awt.Dimension(55, 23));
		btnAddAddres.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});

		btnDeleteAddress.setText("Удалить адрес");
		btnDeleteAddress.setMinimumSize(new java.awt.Dimension(55, 23));
		btnDeleteAddress.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRemoveActionPerformed(evt);
			}
		});

		btnGenerateAddres.setText("Сгенерировать адрес");
		btnGenerateAddres.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGenerateActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(LEADING).addGroup(
				jPanel9Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel9Layout
										.createParallelGroup(LEADING)
										.addComponent(jLabel2, D_SIZE, D_SIZE, S_MAX)
										.addComponent(tfNewAddress, trail)
										.addGroup(
												trail,
												jPanel9Layout.createSequentialGroup()
														.addComponent(btnDeleteAddress, P_SIZE, 196, P_SIZE).addGap(18, 18, 18)
														.addComponent(btnGenerateAddres, D_SIZE, 154, S_MAX).addGap(18, 18, 18)
														.addComponent(btnAddAddres, P_SIZE, 210, P_SIZE))).addContainerGap()));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(LEADING)
				.addGroup(
						jPanel9Layout
								.createSequentialGroup()
								.addGap(4, 4, 4)
								.addComponent(jLabel2, P_SIZE, 23, P_SIZE)
								.addGap(7, 7, 7)
								.addComponent(tfNewAddress, P_SIZE, D_SIZE, P_SIZE)
								.addPreferredGap(cUnR)
								.addGroup(
										jPanel9Layout.createParallelGroup(basel)
												.addComponent(btnAddAddres, P_SIZE, D_SIZE, P_SIZE)
												.addComponent(btnDeleteAddress, P_SIZE, D_SIZE, P_SIZE)
												.addComponent(btnGenerateAddres)).addContainerGap(D_SIZE, S_MAX)));

		listIn.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		listIn.setModel(new DefaultListModel());
		listOut.setModel(new DefaultListModel());
		jScrollPane1.setViewportView(listIn);
		listIn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jList1MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(LEADING).addGroup(
				jPanel10Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap()));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(LEADING).addGroup(
				jPanel10Layout.createSequentialGroup().addComponent(jScrollPane1, D_SIZE, 168, S_MAX).addContainerGap()));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel1Layout.createParallelGroup(LEADING).addComponent(jPanel10, D_SIZE, D_SIZE, S_MAX)
										.addComponent(jPanel5, D_SIZE, D_SIZE, S_MAX)
										.addComponent(jPanel9, D_SIZE, D_SIZE, S_MAX)).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel5, P_SIZE, D_SIZE, P_SIZE)
						.addPreferredGap(cR).addComponent(jPanel9, P_SIZE, D_SIZE, P_SIZE).addPreferredGap(cUnR)
						.addComponent(jPanel10, D_SIZE, D_SIZE, S_MAX).addContainerGap()));

		tabPanel.addTab("Ввод данных", jPanel1);

		listOut.setFont(font);

		listOut.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jList2MouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(listOut);

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(LEADING).addComponent(jScrollPane2));
		jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(LEADING).addComponent(jScrollPane2, D_SIZE, 278,
				S_MAX));

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null, null } }, new String[] {
				"Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane3.setViewportView(jTable1);

		jLabel4.setFont(font);
		jLabel4.setText("Enter count TC");

		jLabel3.setFont(font);
		jLabel3.setText("Weight cars");

		jButton7.setText("jButton7");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout.setHorizontalGroup(jPanel12Layout
				.createParallelGroup(LEADING)
				.addComponent(jScrollPane3, D_SIZE, 616, S_MAX)
				.addGroup(
						jPanel12Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4, P_SIZE, 110, P_SIZE)
								.addGap(18, 18, 18).addComponent(jTextField3, P_SIZE, 43, P_SIZE).addGap(18, 18, 18)
								.addComponent(jLabel3, D_SIZE, D_SIZE, S_MAX).addPreferredGap(cUnR).addComponent(jButton7)));
		jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(LEADING).addGroup(
				trail,
				jPanel12Layout
						.createSequentialGroup()
						.addGroup(
								jPanel12Layout.createParallelGroup(basel).addComponent(jLabel4)
										.addComponent(jTextField3, P_SIZE, D_SIZE, P_SIZE).addComponent(jLabel3)
										.addComponent(jButton7)).addPreferredGap(cR)
						.addComponent(jScrollPane3, D_SIZE, 47, S_MAX)));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel2Layout.createParallelGroup(LEADING).addComponent(jPanel11, D_SIZE, D_SIZE, S_MAX)
										.addComponent(jSeparator1).addComponent(jPanel12, trail, D_SIZE, D_SIZE, S_MAX))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel11, D_SIZE, D_SIZE, S_MAX)
						.addPreferredGap(cR).addComponent(jSeparator1, P_SIZE, 10, P_SIZE).addPreferredGap(cR)
						.addComponent(jPanel12, P_SIZE, D_SIZE, P_SIZE).addContainerGap()));

		tabPanel.addTab("Визуализация", jPanel2);

		innerFrame.setVisible(true);

		javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(innerFrame.getContentPane());
		innerFrame.getContentPane().setLayout(jInternalFrame2Layout);

		jInternalFrame2Layout.setHorizontalGroup(jInternalFrame2Layout.createParallelGroup(LEADING)
				.addComponent(sliderZoom, D_SIZE, 654, S_MAX).addComponent(jLabel5, D_SIZE, D_SIZE, S_MAX));

		jInternalFrame2Layout.setVerticalGroup(jInternalFrame2Layout.createParallelGroup(LEADING).addGroup(
				jInternalFrame2Layout.createSequentialGroup().addComponent(sliderZoom, P_SIZE, D_SIZE, P_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel5, D_SIZE, 343, S_MAX)));

		sliderZoom.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				jSlider1MouseReleased(evt);
			}
		});
		sliderZoom.setMaximum(16);
		sliderZoom.setValue(6);
		sliderZoom.setMinimum(2);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(LEADING).addComponent(innerFrame));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(LEADING).addComponent(innerFrame));

		tabPanel.addTab("Визуализация Граф", jPanel3);

		jButton4.setText("jButton4");

		jButton5.setText("jButton5");

		jButton6.setText("jButton6");

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
		jPanel13.setLayout(jPanel13Layout);
		jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(LEADING).addGroup(
				jPanel13Layout.createSequentialGroup().addContainerGap().addComponent(jButton4, P_SIZE, 220, P_SIZE)
						.addGap(18, 18, 18).addComponent(jButton5, P_SIZE, 131, P_SIZE).addGap(18, 18, 18)
						.addComponent(jButton6, D_SIZE, 209, S_MAX).addContainerGap()));
		jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(LEADING).addGroup(
				jPanel13Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel13Layout.createParallelGroup(basel).addComponent(jButton4).addComponent(jButton5)
										.addComponent(jButton6)).addContainerGap(D_SIZE, S_MAX)));

		setModelForResultTable();
		jScrollPane4.setViewportView(tableResult);
		tableResult.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				tableResultRowClick(me);
			}

		});

		javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
		jPanel14.setLayout(jPanel14Layout);
		jPanel14Layout.setHorizontalGroup(jPanel14Layout.createParallelGroup(LEADING).addComponent(jScrollPane4));
		jPanel14Layout.setVerticalGroup(jPanel14Layout.createParallelGroup(LEADING).addComponent(jScrollPane4, D_SIZE, 325,
				S_MAX));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(LEADING).addGroup(
				trail,
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel4Layout.createParallelGroup(trail).addComponent(jPanel14, D_SIZE, D_SIZE, S_MAX)
										.addComponent(jPanel13, D_SIZE, D_SIZE, S_MAX)).addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jPanel13, P_SIZE, D_SIZE, P_SIZE)
						.addPreferredGap(cR).addComponent(jPanel14, D_SIZE, D_SIZE, S_MAX).addContainerGap()));

		tabPanel.addTab("Результаты", jPanel4);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(LEADING).addComponent(tabPanel));
		layout.setVerticalGroup(layout.createParallelGroup(LEADING).addComponent(tabPanel, D_SIZE, 426, S_MAX));

		pack();

		disableAll();
	}

	private void setModelForResultTable() {
		tableResult.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "TC", "Paths", "Delay",
				"Distance" }));
	}

	private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
		generateAllStandart();
	}

	private void jSlider1MouseReleased(java.awt.event.MouseEvent evt) {
		// VRPUtils.downLoadImage(jLabel5, sliderZoom.getValue());
		VRPUtils.downLoadImageWithMarkers(jLabel5, sliderZoom.getValue(), VRP.getArrayLatLngAddress());
	}

	private void generateAllStandart() {
		VRP.generateAllStandart();
		checkBoxSave.setSelected(true);
		tfFirstAddres.setText(VRP.getStandartFirstAddres());
		enableAll();
		DefaultListModel<String> listModel = (DefaultListModel) listIn.getModel();
		for (String s : VRP.getArrayAddress()) {
			listModel.addElement(s);
		}
	}

	private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (tfFirstAddres.getText().length() < 1) {
			tfFirstAddres.setText(VRP.getStandartFirstAddres());
		} else {
			VRP.setFirstAddres(tfFirstAddres.getText());
		}

		if (!checkBoxSave.isSelected()) {
			disableAll();
		} else {
			enableAll();

			DefaultListModel listModel = (DefaultListModel) listIn.getModel();
			listModel.addElement(VRP.getFirstAddres()); // jList1.ad
		}
	}

	private void disableAll() {
		VRP.clearAll();

		listIn.setModel(new DefaultListModel());
		listOut.setModel(new DefaultListModel());
		listOut.setCellRenderer(new CellRenderer());
		clearTextField(tfNewAddress);
		tfFirstAddres.setEnabled(true);
		tfNewAddress.setEnabled(false);
		btnAddAddres.setEnabled(false);
		btnDeleteAddress.setEnabled(false);
		btnGenerateAddres.setEnabled(false);
		checkBoxWalkingDriving.setText("Driving || Walking");
		checkBoxRoute.setSelected(false);
		checkBoxRoute.setEnabled(false);
		checkBoxWalkingDriving.setSelected(false);
		checkBoxWalkingDriving.setEnabled(false);
	}

	private void enableAll() {
		tfFirstAddres.setEnabled(false);
		tfNewAddress.setEnabled(true);
		btnAddAddres.setEnabled(true);
		btnDeleteAddress.setEnabled(true);
		btnGenerateAddres.setEnabled(true);
		checkBoxRoute.setSelected(false);
		checkBoxRoute.setEnabled(true);
		checkBoxWalkingDriving.setSelected(false);
		checkBoxWalkingDriving.setEnabled(true);
	}

	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
		if (tfNewAddress.getText().length() < 1) {// ADD btn
			VRGUtils.showErrorMess(this);
		} else {
			VRP.addAddres(tfNewAddress.getText());
			DefaultListModel listModel = (DefaultListModel) listIn.getModel();
			listModel.addElement(tfNewAddress.getText());
		}
		clearTextField(tfNewAddress);
	}

	private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
		int index = listIn.getSelectedIndex();
		deleteAtIndex(index, listIn);
	}

	private void deleteAtIndex(int index, JList l) {
		DefaultListModel listModel = (DefaultListModel) listIn.getModel();
		if (index != -1) {
			if (index == 0) {
				VRGUtils.showErrorMess(this, "Нельзя удалять базовый элемент");
			} else {
				listModel.remove(index);
			}
		} else {
			VRGUtils.showErrorMess(this, "Выберите элемент");
		}
	}

	private String getAtIndex(JList l) {
		return getAtIndex(l.getSelectedIndex(), l);
	}

	private String getAtIndex(int index, JList l) {
		if (listIn.getModel().getSize() < 1) {
			return "";
		}
		DefaultListModel<String> listModel = (DefaultListModel) listIn.getModel();
		if (index != -1) {
			return listModel.getElementAt(index);
		} else {
			VRGUtils.showErrorMess(this, "Выберите элемент");
		}
		return "";
	}

	private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {
		// gener btn
		DefaultListModel listModel = (DefaultListModel) listIn.getModel();
		listModel.addElement("Элемент списка " + System.currentTimeMillis() % 10); // jList1.ad
	}

	private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
		VRP.setShortRoute(!checkBoxRoute.isSelected());
	}

	private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
		VRP.setWalking(!checkBoxWalkingDriving.isSelected());
		checkBoxWalkingDriving.setText(!checkBoxWalkingDriving.isSelected() ? " Пешком ходим " : "Катаемся на ТС");
		checkBoxRoute.setSelected(true);
		checkBoxRoute.setEnabled(false);
		checkBoxRoute.setText("Обходные пути с Google");
	}

	private void tfFirstAddresMouseClicked(java.awt.event.MouseEvent evt) {
		if (tfFirstAddres.isEnabled()) {
			clearTextField(tfFirstAddres);
		}
	}

	private void clearTextField(JTextField tf) {
		tf.setText("");
	}

	private void tfNewAddressMouseClicked(java.awt.event.MouseEvent evt) {
		if (tfNewAddress.isEnabled()) {
			// clearTextField(jTextField2);//FIXME
		}
	}

	private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
		tfNewAddress.setText(getAtIndex(listIn));
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		tabPanel.setSelectedIndex(2);
		DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

		// VRPUtils.downLoadImage(jLabel5, sliderZoom.getValue());

		int index = listOut.getSelectedIndex();
		if (index == -1) {
			return;
		}

		DefaultListModel listModel = (DefaultListModel) listOut.getModel();
		model.addRow(new Object[] { listModel.getElementAt(index) });
		listModel.remove(index);
	}

	private void tabPanMousePressed(java.awt.event.MouseEvent evt) {
		if (!VRP.isVal()) {
			tabPanel.setSelectedIndex(0);
			VRGUtils.showErrorMess(this, VRGUtils.MSG_ERR_BODY_NULL);
			return;
		}
		VRPUtils.checkInternetConnectionWithErr(VRPframe.this);
		switch (tabPanel.getSelectedIndex()) {
		case 0: {// Coords
			break;
		}
		case 1: {// GraphFrame
			VRP.setCountCars(3);
			fillAddresArray();
			fillRoutingCostsList();
			break;
		}
		case 2: {// GraphFrame
			jSlider1MouseReleased(evt);
			break;
		}
		case 3: {// Result
			setModelForResultTable();
			fillValueToResultTable();
			break;
		}
		}
	}

	private void tableResultRowClick(MouseEvent me) {
		int index = tableResult.getSelectedRow();
		if (index == (tableResult.getRowCount() - 1)) {
			VRGUtils.showInfoMess(this, VRGUtils.MSG_ATTENTION, getLastStrokeMess());
			return;
		}
		if (index != -1) {
			VRGUtils.showInfoMess(this, VRGUtils.MSG_ATTENTION, getFullRoute(index));
			return;
		}
	}

	private String getLastStrokeMess() {
		DefaultTableModel dtm = (DefaultTableModel) tableResult.getModel();
		int n = dtm.getRowCount() - 1;

		StringBuilder sb = new StringBuilder();
		String v = VRP.getStringDifferenceBetweenSets();
		if (v.length() < 1) {
			sb.append("Учтены все пути \n");
		}
		sb.append("Общее время в пути: " + VRPUtils.getDelay(dtm.getValueAt(n, 2)));
		sb.append("\nОбщее расстояние пути: " + VRPUtils.getDistance(dtm.getValueAt(n, 3)));

		return sb.toString();
	}

	private String getFullRoute(int j) {
		return VRP.getFullRoute(j);
	}

	private void fillValueToResultTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableResult.getModel();

		VRP.constrRes();
		if (!VRP.checkResult()) {
			VRGUtils.showErrorMess(this, " нажмите на вторую вкладку");
			return;
		}
		int n = VRP.getRoutessSize();
		while ((n--) > 0) {
			Vector newRow = new Vector();
			newRow.add("");
			dtm.addRow(newRow);
		}
		n = VRP.getRoutessSize();

		for (int j = 0; j < n; j++) {
			// First column is routes
			dtm.setValueAt(String.valueOf(j + 1), j, 0);
		}
		// VRG.createTableOfRoutes();//FIXME
		for (int j = 0; j < n; j++) {
			// Second column is routes
			dtm.setValueAt(getRoutes(j).toString(), j, 1);
		}

		for (int j = 0; j < n; j++) {
			// Third column is length of routes
			dtm.setValueAt(getDelay(j), j, 2);
		}

		for (int j = 0; j < n; j++) {
			// Fourth column is weight or delay of routes
			dtm.setValueAt(getDistance(j), j, 3);
		}

		setLastStroke(dtm);
	}

	private void setLastStroke(DefaultTableModel dtm) {
		int n = dtm.getRowCount();
		Vector newRow = new Vector();
		newRow.add("");
		dtm.addRow(newRow);
		Double result = 0D;

		dtm.setValueAt(VRGUtils.TXT_GRAPH + getDiff(), n, 1);

		for (int j = 0; j < n; j++) {
			result += VRGUtils.getDouble(dtm.getValueAt(j, 2));
		}
		dtm.setValueAt(VRGUtils.get(result), n, 2);

		result = 0D;
		for (int j = 0; j < n; j++) {
			result += VRGUtils.getDouble(dtm.getValueAt(j, 3));
		}
		dtm.setValueAt(VRGUtils.get(result), n, 3);
	}

	private String getDiff() {
		return VRP.getStringDifferenceBetweenSets();
	}

	private String getDelay(int j) {
		return VRP.getDelay(j);
	}

	private String getDistance(int j) {
		return VRP.getDistance(j);
	}

	private String getRoutes(int j) {
		return VRP.getRoutes(j);
	}

	private void fillAddresArray() {
		VRPUtils.showMess(this);
		VRP.setArrayAddress(((DefaultListModel) listIn.getModel()).elements());
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		VRPUtils.closeMess();
	}

	private void fillRoutingCostsList() {
		DefaultListModel<String> listModel = (DefaultListModel) listOut.getModel();
		listModel.clear();
		for (String s : VRP.getArrayFullAddress()) {
			listModel.addElement(s);
		}
	}

	private void jList2MouseClicked(java.awt.event.MouseEvent evt) {
		VRGUtils.showErrorMess(this, getAtIndex(listOut));
	}

	private javax.swing.JButton btnAddAddres;
	private javax.swing.JButton btnDeleteAddress;
	private javax.swing.JButton btnGenerateAddres;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JCheckBox checkBoxSave;
	private javax.swing.JCheckBox checkBoxRoute;
	private javax.swing.JCheckBox checkBoxWalkingDriving;
	private javax.swing.JInternalFrame innerFrame;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JList listIn;
	private javax.swing.JList listOut;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable tableResult;
	private javax.swing.JTextField tfFirstAddres;
	private javax.swing.JTextField tfNewAddress;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTabbedPane tabPanel;
	private javax.swing.JSlider sliderZoom;
}