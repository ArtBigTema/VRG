package vrg;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class VRGframe extends JFrame {

	public interface onInnerWindowClosed {

	}

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

		setModelForCoords(tableCars, StrUtils.TXT_GAMERS_AUTO, 4);
		jScrollPane2.setViewportView(tableCars);
		tableCars.getColumnModel().getColumn(0).setResizable(false);

		jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonAddVertex.setText(StrUtils.BTN_TXT_ADD_VERTEX);
		buttonAddVertex.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAddVertexActionPerformed(evt);
			}
		});

		buttonGenerGraph.setText(StrUtils.BTN_TXT_GENERATE_DATA);
		buttonGenerGraph.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonGenerGraphActionPerformed(evt);
			}
		});

		buttonDeleteVertex.setText(StrUtils.BTN_TXT_DELETE_VERTEX);
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
		jLabel5.setText(StrUtils.TXT_GAMERS_AUTO);
		jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		tableCoordsDP.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				null, new java.awt.Color(0, 0, 0)));
		tableCoordsDP.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] {
						StrUtils.TXT_VERTEX_LABEL, StrUtils.TXT_COORDS,
						StrUtils.TXT_DEMAND, StrUtils.TXT_PRICE }) {
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

		textCountCars.setText(StrUtils.FIELD_TXT_NUMBERS_OF_PLAYERS);
		textCountCars.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		textCountCars.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				textCountCarsMouseClicked(evt);
			}
		});
		textCountCars.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textCountCarsKey(e);
			}

		});
		buttonSaveCountCars.setText(StrUtils.BTN_TXT_GENERATE);//old is save
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
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(textCountCars)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

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

		tabbedPane.addTab(StrUtils.TAB_TXT_ENTER_COORDS, jPanel1);
		tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				clickTab(evt);
			}
		});
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

		tabbedPane.addTab(StrUtils.TAB_TXT_GRAPH, jPanel2);
		jPanel2.addFocusListener(graphFocusListener);

		tableTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tableTC.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null } },
				new String[] { StrUtils.TXT_VERTEX, "", "", "" }) {
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
								{ null, null, null, null } },
						new String[] { StrUtils.TXT_ROUTE, "", "",
								StrUtils.TXT_PROFIT }) {
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

		jLabel3.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 14));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText(StrUtils.TXT_TYPE_OF_GAME);
		jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonGenerPath.setText(StrUtils.BTN_TXT_GENERATE);
		buttonGenerPath.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonGenerPathActionPerformed(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font(StrUtils.FONT_TAHOMA, 0, 12));
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
												172,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
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
												85,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		tabbedPane.addTab(StrUtils.TXT_TRANSPORTS_COSTS, jPanel3);

		tableResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tableResult
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null, null },
								{ null, null, null, null, null },
								{ null, null, null, null, null },
								{ null, null, null, null, null } },
						new String[] { StrUtils.TXT_PLAYER_NUMBER,
								StrUtils.TXT_ROUTE_NUMBER,
								StrUtils.TXT_LENGTH_OF_ROUTE,
								StrUtils.TXT_LOAD_VEHICLE,
								StrUtils.TXT_PROFIT_LABEL }) {
					Class[] types = new Class[] { java.lang.String.class,
							java.lang.String.class, java.lang.Float.class,
							java.lang.Integer.class, java.lang.Double.class };
					boolean[] canEdit = new boolean[] { false, true, false,
							false, false };

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
		jLabel6.setText("������ �� ����������������");
		jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonAnSolve.setText(StrUtils.BTN_TXT_ANOTHER_SOLUTION);
		buttonAnSolve.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAnSolveActionPerformed(evt);
			}
		});

		buttonBestSolve.setText(StrUtils.BTN_TXT_BEST_SOLUTION);
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

		tabbedPane.addTab(StrUtils.TAB_TXT_RESULT, jPanel9);

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
			dtm.addColumn(new TableColumn(dtm.getColumnCount()));
		}
	}

	private void buttonAddVertexActionPerformed(java.awt.event.ActionEvent evt) {
		int k = StrUtils.getIntFromDialog(StrUtils.FIELD_TXT_NUMBER_OF_ROWS);

		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();

		setRowCount(k, dtm);

		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt("x[" + i + "]", i, 0);
		}
	}

	private void buttonDeleteVertexActionPerformed(
			java.awt.event.ActionEvent evt) {

	}

	private void textCountCarsMouseClicked(java.awt.event.MouseEvent evt) {
		textCountCars.setText("");
		setModelForCoords(tableCars, StrUtils.TXT_GAMERS_AUTO, 4);
	}

	private void textCountCarsKey(java.awt.event.KeyEvent evt) {// FIXME
		int k = StrUtils.getIntFromText(textCountCars.getText().trim());

		TableColumnModel dtm = tableCars.getColumnModel();

		VRG.countCars = k;

		setModelForCars(tableCars, StrUtils.TXT_GAMERS_AUTO, k);
		fillCarsArray(dtm.getColumnCount());
	}

	private void buttonGenerGraphActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();

		fillArrays(dtm.getRowCount());

		fillCoordsTable(dtm);

	}

	private void fillArrays(int n) {
		VRG.generateCoordinates(n);
		VRG.generateCars(n);
		VRG.generateDemand(n);
		VRG.generatePrice(n);
		VRG.generateRoutes();
		GraphFrame.constructVertexes();
	}

	private void fillCarsArray(int n) {
		VRG.generateCars(n);
	}

	private void fillFirstStrokeCoordsTable(DefaultTableModel dtm) {
		dtm.setValueAt("x[" + (0) + "]", 0, 0);
		dtm.setValueAt(StrUtils.OPENEDBKT + VRG.coordinates.get(0).x
				+ StrUtils.SEMICOLON + VRG.coordinates.get(0).y
				+ StrUtils.CLOSEDBKT, 0, 1);
		dtm.setValueAt("0", 0, 2);
		dtm.setValueAt("0", 0, 3);
	}

	private void fillCoordsTable(DefaultTableModel dtm) {
		fillFirstStrokeCoordsTable(dtm);

		for (int i = 1; i < dtm.getRowCount(); i++) {
			dtm.setValueAt("x[" + i + "]", i, 0);
			dtm.setValueAt(StrUtils.OPENEDBKT + VRG.coordinates.get(i).x
					+ StrUtils.SEMICOLON + VRG.coordinates.get(i).y
					+ StrUtils.CLOSEDBKT, i, 1);
			dtm.setValueAt(VRG.demand.get(i), i, 2);
			dtm.setValueAt(VRG.price.get(i), i, 3);
		}
	}

	private void buttonSaveCountCarsActionPerformed(
			java.awt.event.ActionEvent evt) {
		saveCars();
	}

	public void saveAllData() {

	}

	public void saveCars() {
		fillCarsTable();
	}

	private void fillCarsTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableCars.getModel();
		fillCarsArray(dtm.getColumnCount());
		dtm.setValueAt(StrUtils.TXT_PLAYER_LABEL + (dtm.getColumnCount() - 1),
				0, 0);

		for (int i = 1; i < dtm.getColumnCount(); i++) {
			dtm.setValueAt(VRG.cars.get(i), 0, i);
		}
	}

	private void buttonGenerPathActionPerformed(java.awt.event.ActionEvent evt) {
		setRoutesTable();
	}

	private void setRoutesTable() {
		setModelForRoutes(tablePath, StrUtils.TXT_ROUTE, VRG.coordinates.size());
		fillTCcostsTable();
	}

	private void fillTCcostsTable() {
		Integer[][] s = VRG.getRoutes();
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				tablePath.setValueAt(s[i][j], i, j);
			}
		}
	}

	private void buttonAnSolveActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void buttonBestSolveActionPerformed(java.awt.event.ActionEvent evt) {

	}

	public static void main(String args[]) {
		VRG.main(new String[] { "" });
	}

	private boolean graphIsFirstOpened = true;
	private FocusListener graphFocusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent paramFocusEvent) {
			tabbedPane.setSelectedIndex(0);
			graphIsFirstOpened = true;
		}

		@Override
		public void focusGained(FocusEvent paramFocusEvent) {
			openGraphFrame();
		}
	};

	private void openGraphFrame() {
		if (graphIsFirstOpened) {
			GraphFrame frame = new GraphFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		graphIsFirstOpened = false;
	}

	private void fillValueToTransportTable() {
		if (GraphFrame.vrgVertexes == null
				|| GraphFrame.vrgVertexes.size() == 0) {
			showErrorMess(StrUtils.MSG_ERR_TITLE, StrUtils.MSG_ERR_BODY_TC);
			return;
		}

		DefaultTableModel dtm = (DefaultTableModel) tableTC.getModel();
		for (int i = 1; i < dtm.getColumnCount(); i++) {
			for (int j = 0; j < dtm.getRowCount(); j++) {
				String s = VRGvertexes.getDistanceText(
						GraphFrame.vrgVertexes.get(i - 1).vertexCoords,
						GraphFrame.vrgVertexes.get(j).vertexCoords);
				dtm.setValueAt(s, j, i);
			}
		}
	}

	private void clickTab(java.awt.event.MouseEvent evt) {
		if (VRG.coordinates == null || GraphFrame.vrgVertexes == null
				|| GraphFrame.vrgVertexes.size() == 0) {
			showErrorMess(StrUtils.MSG_ERR_TITLE, StrUtils.MSG_ERR_BODY_NULL);
			if (VRG.coordinates != null && VRG.routes == null) {
				tabbedPane.setSelectedIndex(2);
			} else {
				tabbedPane.setSelectedIndex(0);
			}
			return;
		}
		switch (tabbedPane.getSelectedIndex()) {
		case 1: {
			tabbedPane.setSelectedIndex(2);
			openGraphFrame();
			break;
		}
		case 2: {// Transports costs
			setModelForTC(tableTC, StrUtils.TXT_VERTEX, VRG.coordinates.size());
			fillValueToTransportTable();
			setRoutesTable();
			graphIsFirstOpened = true;
			break;
		}
		}
	}

	private void showErrorMess(String title, String body) {
		JOptionPane.showMessageDialog(this, body, title,
				JOptionPane.ERROR_MESSAGE);
	}

	private void setModelForRoutes(JTable table, String textColumn, int n) {
		String[][] rowVertexLabels = new String[n][n];
		String[] columnVertexLabels = new String[n + 1];
		columnVertexLabels[0] = textColumn;

		for (int i = 1; i < n + 1; i++) {
			columnVertexLabels[i] = String.valueOf(i);
			Arrays.fill(rowVertexLabels[i - 1], "");
		}

		Class[] stypes = new Class[n + 1];
		for (int i = 0; i < n + 1; i++) {
			stypes[i] = java.lang.String.class;
		}

		final Class[] type = Arrays.copyOf(stypes, stypes.length);
		boolean[] canEdits = new boolean[n + 1];
		Arrays.fill(canEdits, true);
		final boolean[] canEdit = Arrays.copyOf(canEdits, canEdits.length);

		table.setModel(new javax.swing.table.DefaultTableModel(rowVertexLabels,
				columnVertexLabels) {

			public Class getColumnClass(int columnIndex) {
				return type[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
	}

	private void setModelForCoords(JTable table, String textColumn, int n) {
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null } }, new String[] {
						textColumn, "", "", "" }) {
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

	private void setModelForCars(JTable table, String textColumn, int n) {
		String[][] rowVertexLabels = new String[1][n];
		String[] columnVertexLabels = new String[n + 1];
		columnVertexLabels[0] = textColumn;

		Arrays.fill(rowVertexLabels[0], "");
		for (int i = 1; i < n + 1; i++) {
			columnVertexLabels[i] = String.valueOf(i);
		}

		Class[] stypes = new Class[n + 1];
		for (int i = 0; i < n + 1; i++) {
			stypes[i] = java.lang.String.class;
		}

		final Class[] type = Arrays.copyOf(stypes, stypes.length);
		boolean[] canEdits = new boolean[n + 1];
		Arrays.fill(canEdits, true);
		final boolean[] canEdit = Arrays.copyOf(canEdits, canEdits.length);

		table.setModel(new javax.swing.table.DefaultTableModel(rowVertexLabels,
				columnVertexLabels) {

			public Class getColumnClass(int columnIndex) {
				return type[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
	}

	private void setModelForTC(JTable table, String textColumn, int n) {
		String[][] rowVertexLabels = new String[n][n];
		String[] columnVertexLabels = new String[n + 1];
		columnVertexLabels[0] = textColumn;
		columnVertexLabels[n] = (StrUtils.X + "[" + (n - 1) + "]");
		rowVertexLabels[0][0] = (StrUtils.X + "[" + 0 + "]");

		for (int i = 1; i < n; i++) {
			columnVertexLabels[i] = (StrUtils.X + "[" + (i - 1) + "]");
			rowVertexLabels[i][0] = (StrUtils.X + "[" + i + "]");
		}

		Class[] stypes = new Class[n + 1];
		for (int i = 0; i < n + 1; i++) {
			stypes[i] = java.lang.String.class;
		}

		final Class[] type = Arrays.copyOf(stypes, stypes.length);
		boolean[] canEdits = new boolean[n + 1];
		Arrays.fill(canEdits, true);
		final boolean[] canEdit = Arrays.copyOf(canEdits, canEdits.length);

		table.setModel(new javax.swing.table.DefaultTableModel(rowVertexLabels,
				columnVertexLabels) {

			public Class getColumnClass(int columnIndex) {
				return type[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
	}

	private javax.swing.JButton buttonAddVertex;
	private javax.swing.JButton buttonAnSolve;
	private javax.swing.JButton buttonBestSolve;
	private javax.swing.JButton buttonDeleteVertex;
	private javax.swing.JButton buttonGenerGraph;
	private javax.swing.JButton buttonGenerPath;
	private javax.swing.JButton buttonSaveCountCars;
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
}
