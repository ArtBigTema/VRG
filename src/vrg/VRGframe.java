package vrg;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class VRGframe extends JFrame {

	public VRGframe() {
		initComponents();
	}

	private void initComponents() {

		tabbedPane = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		tableCars = new javax.swing.JTable();
		jPanel8 = new javax.swing.JPanel();
		buttonAddVertex = new javax.swing.JButton();
		buttonGenerGraph = new javax.swing.JButton();
		buttonDeleteVertex = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jScrollPane4 = new javax.swing.JScrollPane();
		tableCoordsDP = new javax.swing.JTable();
		textCountCars = new javax.swing.JTextField();
		buttonSaveCountCars = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		frameCanvas = new javax.swing.JInternalFrame();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableTC = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		tablePath = new javax.swing.JTable();
		jLabel3 = new javax.swing.JLabel();
		buttonGenerPath = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		jScrollPane5 = new javax.swing.JScrollPane();
		tableResult = new javax.swing.JTable();
		jLabel6 = new javax.swing.JLabel();
		buttonAnSolve = new javax.swing.JButton();
		buttonBestSolve = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		setModel(tableCars);
		jScrollPane2.setViewportView(tableCars);
		tableCars.getColumnModel().getColumn(0).setResizable(false);

		jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonAddVertex.setText("Добавить вершины");
		buttonAddVertex.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAddVertexActionPerformed(evt);
			}
		});

		buttonGenerGraph.setText("Генерировать данные");
		buttonGenerGraph.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonGenerGraphActionPerformed(evt);
			}
		});

		buttonDeleteVertex.setText("Удалить вершины");
		buttonDeleteVertex
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						buttonDeleteVertexActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addComponent(buttonAddVertex)
										.addGap(18, 18, 18)
										.addComponent(
												buttonGenerGraph,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(buttonDeleteVertex)));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonAddVertex)
														.addComponent(
																buttonGenerGraph)
														.addComponent(
																buttonDeleteVertex))));

		jLabel5.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 14)); // NOI18N
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel5.setText("Игроки, автомобили");
		jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		tableCoordsDP.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				null, new java.awt.Color(0, 0, 0)));
		tableCoordsDP.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] {
						"Вершина, x[j]", "Координаты", "d(x[j])", "p(x[j])" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Integer.class };
			boolean[] canEdit = new boolean[] { false, true, true, true };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane4.setViewportView(tableCoordsDP);
		tableCoordsDP.getColumnModel().getColumn(0).setResizable(false);

		textCountCars.setText("Введите количество игроков");
		textCountCars.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		textCountCars.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				textCountCarsMouseClicked(evt);
			}
		});
		textCountCars.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				textCountCarsKeyReleased(evt);
			}
		});
		buttonSaveCountCars.setText(StrUtils.TXT_SAVE_COUNT);
		buttonSaveCountCars
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						buttonSaveCountCarsActionPerformed(evt);
					}
				});
		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel8,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.DEFAULT_SIZE, 506,
								Short.MAX_VALUE)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(
												jLabel5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												212,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												textCountCars,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(buttonSaveCountCars)
										.addContainerGap())
						.addGroup(
								jPanel4Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												502, Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap(189, Short.MAX_VALUE)
										.addComponent(
												jPanel8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																textCountCars,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonSaveCountCars))
										.addGap(18, 18, 18)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
						.addGroup(
								jPanel4Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												jPanel4Layout
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(
																jScrollPane4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																179,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap(158,
																Short.MAX_VALUE))));
		jLabel2.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 14));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText(StrUtils.TXT_COORDS_DEMAND_PRICE);
		jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addGap(5, 5, 5)
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		tabbedPane.addTab("Ввод координат/авто", jPanel1);

		frameCanvas.setVisible(true);

		javax.swing.GroupLayout frameCanvasLayout = new javax.swing.GroupLayout(
				frameCanvas.getContentPane());
		frameCanvas.getContentPane().setLayout(frameCanvasLayout);
		frameCanvasLayout.setHorizontalGroup(frameCanvasLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 490, Short.MAX_VALUE));
		frameCanvasLayout.setVerticalGroup(frameCanvasLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 355, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				frameCanvas));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				frameCanvas));

		tabbedPane.addTab("Граф", jPanel2);
		jPanel2.addFocusListener(graphFocusListener);

		tableTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tableTC.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null } },
				new String[] { "x[j]/x[j]", "", "", "" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.String.class,
					java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(tableTC);
		tableTC.getColumnModel().getColumn(0).setResizable(false);

		jLabel1.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 14)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText(StrUtils.TXT_DISTANCES);
		jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		tablePath.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tablePath
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null },
								{ null, null, null, null },
								{ null, null, null, null } }, new String[] {
								"r[i]", "", "", "k[i](r[i])" }) {
					Class[] types = new Class[] { java.lang.String.class,
							java.lang.String.class, java.lang.String.class,
							java.lang.String.class };
					boolean[] canEdit = new boolean[] { false, false, false,
							false };

					public Class getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});

		jScrollPane3.setViewportView(tablePath);
		tablePath.getColumnModel().getColumn(0).setResizable(false);

		jLabel3.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 14)); // NOI18N
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText(StrUtils.TXT_TYPE_OF_GAME);
		jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonGenerPath.setText("Gener");
		buttonGenerPath.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonGenerPathActionPerformed(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 12)); // NOI18N
		jLabel4.setText(StrUtils.TXT_EXAMPLE);
		jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1)
						.addComponent(jScrollPane3)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				buttonGenerPath)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel4,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(
												jLabel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												25,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												135,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(55, 55, 55)
										.addComponent(jLabel3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonGenerPath)
														.addComponent(jLabel4))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												96,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		tabbedPane.addTab(StrUtils.TXT_TRANSPORTS_COSTS, jPanel3);

		tableResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tableResult.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null } }, new String[] {
						"Игрок, i", "Маршрут, r[i]", "Длина маршрута r[i]",
						"Загрузка ТС, Sum(d(x[j]))", "Прибыль, k[i](h[i])" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.Float.class,
					java.lang.Integer.class, java.lang.Double.class };
			boolean[] canEdit = new boolean[] { false, true, false, false,
					false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane5.setViewportView(tableResult);

		jLabel6.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 14)); // NOI18N
		jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel6.setText("Анализ на чувствительность");
		jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonAnSolve.setText("Другое решение");
		buttonAnSolve.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAnSolveActionPerformed(evt);
			}
		});

		buttonBestSolve.setText("Лучшее решение");
		buttonBestSolve.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonBestSolveActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(
				jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane5,
								javax.swing.GroupLayout.DEFAULT_SIZE, 506,
								Short.MAX_VALUE)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addComponent(
																				buttonAnSolve)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				buttonBestSolve)))
										.addContainerGap()));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel9Layout
										.createSequentialGroup()
										.addGap(4, 4, 4)
										.addComponent(jLabel6)
										.addGap(18, 18, 18)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonAnSolve)
														.addComponent(
																buttonBestSolve))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												13, Short.MAX_VALUE)
										.addComponent(
												jScrollPane5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												302,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		tabbedPane.addTab("Результаты", jPanel9);

		jMenu1.setText("File");
		menuBar.add(jMenu1);

		jMenu2.setText("Edit");
		menuBar.add(jMenu2);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				tabbedPane));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 409,
				Short.MAX_VALUE));

		pack();
	}

	public void setRowCount(int k, DefaultTableModel dtm) {
		for (int i = 0; i < k; i++) {
			Vector newRow = new Vector();
			newRow.add("");
			dtm.addRow(newRow);
		}
	}
	
	public void setColumnCount(int k, TableColumnModel dtm) {
		for (int i = 0; i < k; i++) {
			dtm.addColumn(new TableColumn(0));//FIXME
		}
	}

	private void buttonAddVertexActionPerformed(java.awt.event.ActionEvent evt) {
		int k = StrUtils.getIntFromDialog(StrUtils.TXT_ENTER_COUNT_ROWS);

		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();

		setRowCount(k, dtm);

		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt("x[" + (i + 1) + "]", i, 0);
		}
	}

	private void buttonDeleteVertexActionPerformed(
			java.awt.event.ActionEvent evt) {

	}

	private void textCountCarsMouseClicked(java.awt.event.MouseEvent evt) {
		textCountCars.setText("");
		setModel(tableCars);
	}

	private void textCountCarsKeyReleased(java.awt.event.KeyEvent evt) {
		//tableCars.setModel(new javax.swing.JTable().getModel());
		TableColumnModel dtm = tableCars.getColumnModel();
		
		VRG.countCars = StrUtils.getIntFromText(textCountCars.getText().trim()); 
		setColumnCount(dtm.getColumnCount()-VRG.countCars, dtm);
		//tableCars.setModel(new javax.swing.JTabl);
	}

	private void setModel(JTable table) {
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null } }, new String[] {
						"Игроки (Авто)", "", "", "" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Integer.class, java.lang.Integer.class,
					java.lang.Integer.class };
			boolean[] canEdit = new boolean[] { false, true, true, true };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
	}

	private void buttonGenerGraphActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();

		{
			dtm.setValueAt("x[" + (0) + "]", 0, 0);
			dtm.setValueAt(StrUtils.OPENEDBKT + VRG.CORDINATES[0][0]
					+ StrUtils.SEMICOLON + VRG.CORDINATES[0][1]
					+ StrUtils.CLOSEDBKT, 0, 1);
			dtm.setValueAt("0", 0, 2);
			dtm.setValueAt("0", 0, 3);
		}

		for (int i = 1; i < dtm.getRowCount(); i++) {
			dtm.setValueAt("x[" + (i + 1) + "]", i, 0);
			dtm.setValueAt(StrUtils.OPENEDBKT + VRG.CORDINATES[i][0]
					+ StrUtils.SEMICOLON + VRG.CORDINATES[i][1]
					+ StrUtils.CLOSEDBKT, i, 1);
			dtm.setValueAt(VRG.DEMAND[i], i, 2);
			dtm.setValueAt(VRG.PRICE[i], i, 3);
		}
	}

	private void buttonSaveCountCarsActionPerformed(
			java.awt.event.ActionEvent evt) {
		saveCars();
	}

	public void saveAllData() {

	}

	public void saveCars() {
		DefaultTableModel dtm = (DefaultTableModel) tableCars.getModel();
		VRG.cars.clear();

		for (int i = 0; i < dtm.getColumnCount(); i++) {
			VRG.cars.add(StrUtils.getIntFromObject(dtm.getValueAt(0, i)));
		}
	}

	private void buttonGenerPathActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void buttonAnSolveActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void buttonBestSolveActionPerformed(java.awt.event.ActionEvent evt) {

	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(VRGframe.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VRGframe().setVisible(true);
			}
		});
	}

	private boolean isFirstOpened = true;
	private FocusListener graphFocusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent paramFocusEvent) {

		}

		@Override
		public void focusGained(FocusEvent paramFocusEvent) {
			if (isFirstOpened) {
				GraphFrame frame = new GraphFrame();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			isFirstOpened = false;
		}
	};

	private javax.swing.JButton buttonAddVertex;
	private javax.swing.JButton buttonAnSolve;
	private javax.swing.JButton buttonBestSolve;
	private javax.swing.JButton buttonDeleteVertex;
	private javax.swing.JButton buttonGenerGraph;
	private javax.swing.JButton buttonGenerPath;
	public javax.swing.JInternalFrame frameCanvas;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JTabbedPane tabbedPane;
	private javax.swing.JTable tableCars;
	private javax.swing.JTable tableCoordsDP;
	private javax.swing.JTable tablePath;
	private javax.swing.JTable tableResult;
	private javax.swing.JTable tableTC;
	private javax.swing.JTextField textCountCars;
	private javax.swing.JButton buttonSaveCountCars;
	// End of variables declaration
}
