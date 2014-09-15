package vrg;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ru.amse.smyshlyaev.grapheditor.ui.JGraphComponent;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class VRGframe extends JFrame {
	public static boolean isEnabled = false;
	private java.util.Timer timer;
	public static boolean isNeedToUpdate = false;
	private boolean graphIsFirstOpened = true;
	JTable[] tables = new JTable[5];

	public interface onInnerWindowClosed {

	}

	public VRGframe() {
		initComponents();
	}

	private void initComponents() {
		final int P_SIZE = GroupLayout.PREFERRED_SIZE;
		final int D_SIZE = GroupLayout.DEFAULT_SIZE;
		final short S_MAX = Short.MAX_VALUE;
		final java.awt.Font font = new java.awt.Font(VRGUtils.FONT_TAHOMA, 0,
				14);

		tabbedPane = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		tableCars = new javax.swing.JTable();
		jPanel8 = new javax.swing.JPanel();
		buttonAddVertex = new javax.swing.JButton();
		buttonGenerGraph = new javax.swing.JButton();
		buttonDeleteVertex = new javax.swing.JButton();
		buttonStandartData = new javax.swing.JButton();
		jScrollPane4 = new javax.swing.JScrollPane();
		tableCoordsDP = new javax.swing.JTable();
		textCountCars = new javax.swing.JTextField();
		buttonSaveCountCars = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableTC = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		tableRoutes = new javax.swing.JTable();
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
		jMenu1 = new JMenu();
		jMenu2 = new JMenu();

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				reSize();
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		setModelForCar(tableCars, VRGUtils.TXT_GAMERS_AUTO, 4);
		jScrollPane2.setViewportView(tableCars);
		tableCars.getColumnModel().getColumn(0).setResizable(false);
		tableCars.addMouseListener(carsSelectionListener);

		jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonAddVertex.setText(VRGUtils.BTN_TXT_ADD_VERTEX);
		buttonAddVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonAddVertexActionPerformed(evt);
			}
		});

		buttonGenerGraph.setText(VRGUtils.BTN_TXT_GENERATE_DATA);
		buttonGenerGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonGenerGraphActionPerformed(evt);
			}
		});

		buttonDeleteVertex.setText(VRGUtils.BTN_TXT_DELETE_VERTEX);
		buttonDeleteVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonDeleteVertexActionPerformed(evt);
			}
		});

		GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel8Layout.createSequentialGroup()
						.addComponent(buttonAddVertex).addGap(18, 18, 18)
						.addComponent(buttonGenerGraph, D_SIZE, D_SIZE, S_MAX)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(buttonDeleteVertex)));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel8Layout
						.createSequentialGroup()
						.addContainerGap(D_SIZE, S_MAX)
						.addGroup(
								jPanel8Layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(buttonAddVertex)
										.addComponent(buttonGenerGraph)
										.addComponent(buttonDeleteVertex))));

		buttonStandartData.setFont(font);
		buttonStandartData.setHorizontalAlignment(0);
		buttonStandartData.setText(VRGUtils.TXT_GENERATE_STANDARD_DATA);
		buttonStandartData.setBorder(javax.swing.BorderFactory
				.createEtchedBorder());
		buttonStandartData.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonStandartDataActionPerformed(evt);
			}
		});

		tableCoordsDP.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				null, new java.awt.Color(0, 0, 0)));
		setAllModel(tableCoordsDP, new String[] { VRGUtils.TXT_VERTEX_LABEL,
				VRGUtils.TXT_COORDS, VRGUtils.TXT_DEMAND, VRGUtils.TXT_PRICE });
		jScrollPane4.setViewportView(tableCoordsDP);
		tableCoordsDP.getColumnModel().getColumn(0).setResizable(false);
		tableCoordsDP.addMouseListener(coordsSelectionListener);

		textCountCars.setText(VRGUtils.FIELD_TXT_NUMBERS_OF_PLAYERS);
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
		buttonSaveCountCars.setText(VRGUtils.BTN_TXT_GENERATE);
		buttonSaveCountCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSaveCountCarsActionPerformed(evt);
			}
		});
		GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel8, D_SIZE, D_SIZE, S_MAX)
				.addComponent(jScrollPane2, D_SIZE, 506, S_MAX)
				.addGroup(
						jPanel4Layout
								.createSequentialGroup()
								.addComponent(buttonStandartData, P_SIZE, 212,
										P_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textCountCars)
								.addPreferredGap(ComponentPlacement.UNRELATED)

								.addComponent(buttonSaveCountCars)
								.addContainerGap())
				.addGroup(
						jPanel4Layout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addComponent(
								jScrollPane4, D_SIZE, 502, S_MAX)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap(189, S_MAX)
										.addComponent(jPanel8, P_SIZE, D_SIZE,
												P_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonStandartData)
														.addComponent(
																textCountCars,
																P_SIZE, D_SIZE,
																P_SIZE)
														.addComponent(
																buttonSaveCountCars))
										.addGap(18, 18, 18)
										.addComponent(jScrollPane2, P_SIZE, 50,
												P_SIZE).addContainerGap())
						.addGroup(
								jPanel4Layout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addGroup(
												jPanel4Layout
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(
																jScrollPane4,
																P_SIZE, 179,
																P_SIZE)
														.addContainerGap(158,
																S_MAX))));
		jLabel2.setFont(font);
		jLabel2.setHorizontalAlignment(0);
		jLabel2.setText(VRGUtils.TXT_COORDS_DEMAND_PRICE);
		jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel4, D_SIZE, D_SIZE, S_MAX)
				.addGroup(
						jPanel1Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel2, D_SIZE, D_SIZE, S_MAX)
								.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addGap(5, 5, 5)
						.addComponent(jLabel2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel4, P_SIZE, D_SIZE, P_SIZE)
						.addContainerGap(D_SIZE, S_MAX)));

		tabbedPane.addTab(VRGUtils.TAB_TXT_ENTER_COORDS, jPanel1);
		tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				clickTab(evt);
			}
		});
		graph = new VRGgraph();
		graphComponent = new JGraphComponent(graph.getGraph(), this.getWidth(),
				this.getHeight()) {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				VRGUtils.paintCarcass(g.create());
			}

		};
		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(graphComponent));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(graphComponent));

		tabbedPane.addTab(VRGUtils.TAB_TXT_GRAPH, jPanel2);

		tableTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		setAllModel(tableTC, new String[] { VRGUtils.TXT_VERTEX, "", "", "" });
		jScrollPane1.setViewportView(tableTC);
		tableTC.getColumnModel().getColumn(0).setResizable(false);

		jLabel1.setFont(font);
		jLabel1.setHorizontalAlignment(0);
		jLabel1.setText(VRGUtils.TXT_DISTANCES);
		jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		tableRoutes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		setAllModel(tableRoutes, new String[] { VRGUtils.TXT_ROUTE, "", "",
				VRGUtils.TXT_PROFIT });

		jScrollPane3.setViewportView(tableRoutes);
		tableRoutes.getColumnModel().getColumn(0).setResizable(false);

		jLabel3.setFont(font);
		jLabel3.setHorizontalAlignment(0);
		jLabel3.setText(VRGUtils.TXT_TYPE_OF_GAME);
		jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonGenerPath.setText(VRGUtils.BTN_TXT_GENERATE);
		buttonGenerPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonGenerRoutesActionPerformed(evt);
			}
		});

		jLabel4.setFont(font);
		jLabel4.setText(VRGUtils.TXT_EXAMPLE);
		jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1)
						.addComponent(jScrollPane3)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(jLabel1,
																D_SIZE, D_SIZE,
																S_MAX)
														.addComponent(jLabel3,
																D_SIZE, D_SIZE,
																S_MAX)
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
																				D_SIZE,
																				D_SIZE,
																				S_MAX)))
										.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel3Layout
						.createSequentialGroup()
						.addComponent(jLabel1, P_SIZE, 25, P_SIZE)
						.addGap(18, 18, 18)
						.addComponent(jScrollPane1, P_SIZE, 172, P_SIZE)
						.addGap(18, 18, 18)
						.addComponent(jLabel3)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								jPanel3Layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(buttonGenerPath)
										.addComponent(jLabel4))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jScrollPane3, P_SIZE, 85, P_SIZE)
						.addContainerGap()));
		tabbedPane.addTab(VRGUtils.TXT_TRANSPORTS_COSTS, jPanel3);

		tableResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tableResult.getTableHeader().setReorderingAllowed(false);
		setAllModel(tableResult, new String[] { VRGUtils.TXT_PLAYER_NUMBER,
				VRGUtils.TXT_ROUTE_NUMBER, VRGUtils.TXT_LENGTH_OF_ROUTE,
				VRGUtils.TXT_LOAD_VEHICLE, VRGUtils.TXT_PROFIT_LABEL });
		tableResult.getColumnModel().getColumn(1).setMinWidth(213);
		jScrollPane5.setViewportView(tableResult);

		jLabel6.setFont(font);
		jLabel6.setHorizontalAlignment(0);
		jLabel6.setText(VRGUtils.TXT_ANALYS);
		jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		buttonAnSolve.setText(VRGUtils.BTN_TXT_ANOTHER_SOLUTION);
		buttonAnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonAnSolveActionPerformed(evt);
			}
		});

		buttonBestSolve.setText(VRGUtils.BTN_TXT_BEST_SOLUTION);
		buttonBestSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonBestSolveActionPerformed(evt);
			}
		});

		GroupLayout jPanel9Layout = new GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane5, D_SIZE, 506, S_MAX)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(jLabel6,
																D_SIZE, D_SIZE,
																S_MAX)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addComponent(
																				buttonAnSolve)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				D_SIZE,
																				S_MAX)
																		.addComponent(
																				buttonBestSolve)))
										.addContainerGap()));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel9Layout
						.createSequentialGroup()
						.addGap(4, 4, 4)
						.addComponent(jLabel6)
						.addGap(18, 18, 18)
						.addGroup(
								jPanel9Layout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(buttonAnSolve)
										.addComponent(buttonBestSolve))
						.addPreferredGap(ComponentPlacement.RELATED, 13, S_MAX)
						.addComponent(jScrollPane5, P_SIZE, 302, P_SIZE)));

		tabbedPane.addTab(VRGUtils.TAB_TXT_RESULT, jPanel9);
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(tabbedPane));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(tabbedPane, P_SIZE,
				409, S_MAX));

		constructMenuBar();
		fillArrayOfTable();
		addKeyListener();
		pack();
	}

	private void addKeyListener() {
		for (Component component : this.getContentPane().getComponents()) {
			component.addKeyListener(keyListener);
		}
		for (Component component : this.getComponents()) {
			component.addKeyListener(keyListener);
		}
		for (JTable table : tables) {
			table.addKeyListener(keyListener);
		}
	}

	private void closeProgram() {
		this.dispose();
	}

	private void fillArrayOfTable() {
		tableCoordsDP.setName(VRGUtils.TXT_COORDS_DEMAND_PRICE);
		tableCars.setName(VRGUtils.TXT_GAMERS_AUTO);
		tableTC.setName(VRGUtils.TXT_TRANSPORTS_COSTS);
		tableRoutes.setName(VRGUtils.TXT_ROUTES);
		tableResult.setName(VRGUtils.TAB_TXT_RESULT);

		tables[0] = tableCoordsDP;
		tables[1] = tableCars;
		tables[2] = tableTC;
		tables[3] = tableRoutes;
		tables[4] = tableResult;
	}

	private void constructMenuBar() {// FIXME
		jMenu1.setText("File");
		menuBar.add(jMenu1);

		jMenu2.setText("Edit");
		menuBar.add(jMenu2);
		setJMenuBar(menuBar);

		JMenu menu;
		{
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText("Новый");
			jMenu1.add(menuItem);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					buttonDeleteVertexActionPerformed(evt);
					buttonGenerGraphActionPerformed(evt);
				}
			});

			menu = new JMenu();
			menu.setText("Экспорт таблиц");
			jMenu1.add(menu);
		}

		{
			JMenuItem menuTable = new JMenuItem();
			menuTable.setText("Таблица координат, спроса, цены");
			menuTable.setToolTipText("0");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText("Таблица машин, загрузка ТС");
			menuTable.setToolTipText("1");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText("Таблица транспортных затрат");
			menuTable.setToolTipText("2");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText("Таблица маршрутов");
			menuTable.setToolTipText("3");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText("Таблица результатов");
			menuTable.setToolTipText("4");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText("Экспортировать все таблицы");
			menuTable.setToolTipText("5");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

		}

		{
			menu = new JMenu();
			menu.setText("Импорт файлов");
			jMenu1.add(menu);

			JMenuItem menuItem = new JMenuItem();
			menuItem.setText("Файл с координатами");
			menu.add(menuItem);

			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					VRG.readTableFromFile(VRGframe.this);
					fillAllStandart();
				}
			});
		}
		jMenu1.addSeparator();
		{
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText("Закрыть программу");
			jMenu1.add(menuItem);

			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent paramActionEvent) {
					closeProgram();
				}
			});
		}
	}

	protected void generateAllStandart() {
		clearAll();
		isNeedToUpdate = true;

		VRG.generateAllStandart();
		fillAllStandart();
	}

	protected void fillAllStandart() {
		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();

		addRowCount(VRG.countCoords - 1, dtm);
		setValueInFirstColumn(dtm);
		fillCoordsTable(dtm);

		setModelForCars(tableCars, VRGUtils.TXT_GAMERS_AUTO, VRG.countCars);
		fillCarsTable();
		setRoutesTable();
	}

	public void addRowCount(int k, DefaultTableModel dtm) {
		addRow(k, dtm, "");
	}

	public void addRow(int k, DefaultTableModel dtm, String s) {
		for (int i = 0; i < k; i++) {
			Vector newRow = new Vector();
			newRow.add(s);
			dtm.addRow(newRow);
		}
	}

	public void setColumnCount(int k, TableColumnModel dtm) {
		for (int i = 0; i < k; i++) {
			dtm.addColumn(new TableColumn(dtm.getColumnCount()));
		}
	}

	private void buttonAddVertexActionPerformed(ActionEvent evt) {
		int k = VRGUtils.getIntFromDialog(this,
				VRGUtils.FIELD_TXT_NUMBER_OF_ROWS, VRG.countCoords);

		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();

		addRowCount(k, dtm);

		fillArrays(dtm.getRowCount());
		fillCoordsTable(dtm);
		buttonSaveCountCarsActionPerformed(evt);
	}

	private void setValueInFirstColumn(DefaultTableModel dtm) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt("x[" + i + "]", i, 0);
		}
	}

	private void buttonDeleteVertexActionPerformed(ActionEvent evt) {
		clearAll();
	}

	private void clearAll() {
		VRG.clearAll();
		setAllModel(tableCoordsDP, new String[] { VRGUtils.TXT_VERTEX_LABEL,
				VRGUtils.TXT_COORDS, VRGUtils.TXT_DEMAND, VRGUtils.TXT_PRICE });
		setAllModel(tableCars, new String[] { VRGUtils.TXT_GAMERS_AUTO, "1",
				"2", "3" });
		setAllModel(tableRoutes, new String[] { VRGUtils.TXT_ROUTE });
		setAllModel(tableTC, new String[] { VRGUtils.TXT_VERTEX });
		setAllModel(tableResult, new String[] { VRGUtils.TXT_PLAYER_NUMBER,
				VRGUtils.TXT_ROUTE_NUMBER, VRGUtils.TXT_LENGTH_OF_ROUTE,
				VRGUtils.TXT_LOAD_VEHICLE, VRGUtils.TXT_PROFIT_LABEL });
	}

	private void textCountCarsMouseClicked(java.awt.event.MouseEvent evt) {
		textCountCars.setText("");
		setModelForCar(tableCars, VRGUtils.TXT_GAMERS_AUTO, 4);
	}

	private void textCountCarsKey(java.awt.event.KeyEvent evt) {
		int k = VRGUtils.getIntFromText(textCountCars.getText().trim());

		isNeedToUpdate = true;
		VRG.countCars = k;

		setModelForCars(tableCars, VRGUtils.TXT_GAMERS_AUTO, k);
		fillCarsArray(k + 1);
		fillCarsTable();
		VRG.generateGraphRoutes();
	}

	private void buttonGenerGraphActionPerformed(ActionEvent evt) {
		DefaultTableModel dtm = (DefaultTableModel) tableCoordsDP.getModel();
		if (dtm.getRowCount() < 3) {
			buttonAddVertexActionPerformed(evt);
		}
		fillArrays(dtm.getRowCount());

		fillCoordsTable(dtm);
		buttonSaveCountCarsActionPerformed(evt);
	}

	private void buttonStandartDataActionPerformed(ActionEvent evt) {
		generateAllStandart();
	}

	private void fillArrays(int n) {
		isNeedToUpdate = true;
		VRG.generateAll(n);
	}

	private void fillCarsArray(int n) {
		VRG.generateCars(n);
	}

	private void fillFirstStrokeCoordsTable(DefaultTableModel dtm) {
		dtm.setValueAt(VRGUtils.X + "[" + (0) + "]", 0, 0);
		dtm.setValueAt(VRGUtils.OPENEDBKT + VRG.coordinates.get(0).x
				+ VRGUtils.SEMICOLON + VRG.coordinates.get(0).y
				+ VRGUtils.CLOSEDBKT, 0, 1);
		dtm.setValueAt("0", 0, 2);
		dtm.setValueAt("0", 0, 3);
	}

	private void fillCoordsTable(DefaultTableModel dtm) {
		fillFirstStrokeCoordsTable(dtm);

		setValueInFirstColumn(dtm);

		for (int i = 1; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(VRGUtils.OPENEDBKT + VRG.coordinates.get(i).x
					+ VRGUtils.SEMICOLON + VRG.coordinates.get(i).y
					+ VRGUtils.CLOSEDBKT, i, 1);
			dtm.setValueAt(VRG.demand.get(i), i, 2);
			dtm.setValueAt(VRG.price.get(i), i, 3);
		}
	}

	private void buttonSaveCountCarsActionPerformed(ActionEvent evt) {
		VRG.countCars = tableCars.getColumnCount() - 1;
		fillCarsArray(VRG.countCars + 1);
		fillCarsTable();
		VRG.generateGraphRoutes();
		setRoutesTable();
	}

	private void fillCarsTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableCars.getModel();
		dtm.setValueAt(VRGUtils.TXT_PLAYER_LABEL + (dtm.getColumnCount() - 1),
				0, 0);

		for (int i = 1; i < dtm.getColumnCount(); i++) {
			dtm.setValueAt(VRG.cars.get(i), 0, i);
		}
	}

	private void buttonGenerRoutesActionPerformed(ActionEvent evt) {
		isNeedToUpdate = true;
		VRG.countCars = tableCars.getColumnCount() - 1;
		setRoutesTable();
		VRG.generateGraphRoutes();
	}

	private void setRoutesTable() {
		setModelForRoutes(tableRoutes, VRGUtils.TXT_ROUTE,
				VRG.coordinates.size(), VRG.countCars);
		VRG.createTableOfRoutes();
		fillRoutesTable();
	}

	private void fillRoutesTable() {
		int[][] s = VRG.getRoutes();
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				tableRoutes.setValueAt(s[i][j], i, j);
			}
		}
	}

	private void buttonAnSolveActionPerformed(ActionEvent evt) {
		if (VRG.isValid()) {
			VRG.generateGraphRoutes();
			fillValueToResultTable();
		}
	}

	private void buttonBestSolveActionPerformed(ActionEvent evt) {// FIXME
		turnOnTimer(evt);
	}

	private void turnOnTimer(final ActionEvent evt) {
		if (turnOffTimer()) {
			return;
		} else {
			buttonBestSolve.setText(VRGUtils.BTN_TXT_BEST_SOLUTION
					+ VRGUtils.SPACE + VRGUtils.SYMBOLS_ON);
		}

		timer = new java.util.Timer();
		isEnabled = true;
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				buttonAnSolveActionPerformed(evt);
			}
		}, VRGUtils.START, VRGUtils.DELAY);
	}

	private boolean turnOffTimer() {
		// if ((isEnabled || isNeedToUpdate) && (timer != null)) {
		if ((isEnabled) && (timer != null)) {
			buttonBestSolve.setText(VRGUtils.BTN_TXT_BEST_SOLUTION
					+ VRGUtils.SPACE + VRGUtils.SYMBOLS_OFF);
			timer.cancel();
			isEnabled = false;
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		VRG.main(new String[] { "" });
	}

	private void openGraphFrame() {
		if (graphIsFirstOpened) {
			GraphFrame frame = new GraphFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		graphIsFirstOpened = false;
	}

	private void fillValueToTransportTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableTC.getModel();

		for (int i = 1; i < dtm.getColumnCount(); i++) {
			for (int j = 0; j < dtm.getRowCount(); j++) {
				String s = VRG.getDistanceText(VRG.coordinates.get(i - 1),
						VRG.coordinates.get(j));
				dtm.setValueAt(VRGUtils.get(s), j, i);
			}
		}
	}

	private void clickTab(java.awt.event.MouseEvent evt) {
		turnOffTimer();
		isNeedToUpdate = false;
		if (VRG.coordinates == null || VRG.cars == null
				|| tableCoordsDP.getRowCount() < 3) {
			VRGUtils.showErrorMess(this, VRGUtils.MSG_ERR_TITLE,
					VRGUtils.MSG_ERR_BODY_NULL);
			if (VRG.cars == null
					|| (VRG.coordinates != null && VRG.routes == null)) {
				tabbedPane.setSelectedIndex(2);
			} else {
				tabbedPane.setSelectedIndex(0);
			}
			return;
		}

		switch (tabbedPane.getSelectedIndex()) {
		case 0: {// Coords
			isNeedToUpdate = true;
			break;
		}
		case 1: {// GraphFrame
			tabbedPane.setSelectedIndex(3);
			fillResultTable();
			graphIsFirstOpened = true;
			openGraphFrame();
			break;
		}
		case 2: {// Transports costs
			setModelForTC(tableTC, VRGUtils.TXT_VERTEX, VRG.coordinates.size());
			fillValueToTransportTable();
			setRoutesTable();
			break;
		}
		case 3: {// Result
			fillResultTable();
			if (graphIsFirstOpened) {
				graphIsFirstOpened = false;
				VRGUtils.showInfoMess(this, VRGUtils.MSG_ATTENTION,
						VRGUtils.MSG_BODY_ATTENTION);
				return;
			}
			break;
		}
		}
	}

	private void fillResultTable() {
		if (VRG.isValid()) {
			fillColumnValueToResultTable();
			fillValueToResultTable(VRGUtils.TXT_IS_ALL);
		}
	}

	private void fillValueToResultTable() {
		fillValueToResultTable("");
	}

	private void fillValueToResultTable(String s) {
		DefaultTableModel dtm = (DefaultTableModel) tableResult.getModel();

		int n = dtm.getRowCount();
		if (!s.equals("")
				|| dtm.getValueAt(n - 1, 0).equals(VRGUtils.TXT_IS_ALL)) {
			if (!dtm.getValueAt(n - 1, 0).equals(VRGUtils.TXT_IS_ALL)) {
				addRow(1, dtm, s);
			} else {
				n--;
			}
		}

		VRG.createTableOfRoutes();
		for (int j = 0; j < n; j++) {
			// Second column is routes
			dtm.setValueAt(VRG.routes.get(j).toString(), j, 1);
		}

		for (int j = 0; j < n; j++) {
			// Third column is length of routes
			dtm.setValueAt(VRGUtils.get(VRG.getLengthOfRoutes(j + 1)), j, 2);// getTextLengthOfRoutes
		}

		for (int j = 0; j < n; j++) {
			// Fourth column is weight of routes
			dtm.setValueAt(VRG.getRoutesWeight(j), j, 3);
		}

		for (int j = 0; j < n; j++) {
			// Fiveth column is benefit of routes
			dtm.setValueAt(VRGUtils.get(VRG.getBenefit(j)), j, 4);
		}

		setLastStroke(dtm);
	}

	private void setLastStroke(DefaultTableModel dtm) {
		int n = dtm.getRowCount() - 1;
		if (!dtm.getValueAt(n, 0).equals(VRGUtils.TXT_IS_ALL)) {
			return;
		}
		Double result = 0D;
		dtm.setValueAt(
				VRGUtils.TXT_GRAPH + VRG.getStringDifferenceBetweenSets(),
				dtm.getRowCount() - 1, 1);

		for (int j = 0; j < n; j++) {
			result += VRGUtils.getDouble(dtm.getValueAt(j, 2));
		}
		dtm.setValueAt(VRGUtils.get(result), dtm.getRowCount() - 1, 2);

		result = 0D;
		for (int j = 0; j < n; j++) {
			result += VRGUtils.getIntFromObject(dtm.getValueAt(j, 3));
		}
		dtm.setValueAt(result.intValue(), dtm.getRowCount() - 1, 3);

		result = 0D;
		for (int j = 0; j < n; j++) {
			result += VRGUtils.getDouble(dtm.getValueAt(j, 4));
		}
		dtm.setValueAt(VRGUtils.get(result), dtm.getRowCount() - 1, 4);
	}

	private void fillColumnValueToResultTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableResult.getModel();

		addRowCount(Math.max(VRG.countCars - dtm.getRowCount(), 0), dtm);
		int n = dtm.getRowCount();
		if (dtm.getValueAt(n - 1, 0).equals(VRGUtils.TXT_IS_ALL)) {
			n--;
		}
		for (int j = 0; j < n; j++) {
			dtm.setValueAt(VRGUtils.SPACE + (j + 1) + VRGUtils.COMMA
					+ VRGUtils.SPACE + VRGUtils.LABEL_WEIGHT + VRGUtils.SPACE
					+ VRG.cars.get(j + 1), j, 0);
		}
	}

	private void setAllModel(JTable table, String[] titles) {
		final boolean[] canEdit = new boolean[titles.length];
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[1][1], titles) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
	}

	private void setModelForRoutes(JTable table, String textColumn, int n, int m) {
		String[][] rowVertexLabels = new String[m][n];
		String[] columnVertexLabels = new String[n + 1];
		columnVertexLabels[0] = textColumn;

		for (int i = 1; i < n + 1; i++) {
			columnVertexLabels[i] = String.valueOf(i);
		}

		for (int i = 0; i < m; i++) {
			Arrays.fill(rowVertexLabels[i], "");
		}

		Class[] stypes = new Class[n + 1];
		for (int i = 0; i < n + 1; i++) {
			stypes[i] = java.lang.String.class;
		}

		final Class[] type = Arrays.copyOf(stypes, stypes.length);
		boolean[] canEdits = new boolean[n + 1];
		Arrays.fill(canEdits, false);
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

	private void setModelForCar(JTable table, String textColumn, int n) {
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null } }, new String[] {
						textColumn, "", "", "" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Integer.class, java.lang.Integer.class,
					java.lang.Integer.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };

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
		Arrays.fill(canEdits, false);
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
		columnVertexLabels[n] = (VRGUtils.X + "[" + (n - 1) + "]");
		rowVertexLabels[0][0] = (VRGUtils.X + "[" + 0 + "]");

		for (int i = 1; i < n; i++) {
			columnVertexLabels[i] = (VRGUtils.X + "[" + (i - 1) + "]");
			rowVertexLabels[i][0] = (VRGUtils.X + "[" + i + "]");
		}

		Class[] stypes = new Class[n + 1];
		for (int i = 0; i < n + 1; i++) {
			stypes[i] = java.lang.String.class;
		}

		final Class[] type = Arrays.copyOf(stypes, stypes.length);
		boolean[] canEdits = new boolean[n + 1];
		Arrays.fill(canEdits, false);
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

	public void generateCoordsValue(int row, int column) {
		VRG.generateCoordTableAtIndex(row, column);
		fillCoordsTable((DefaultTableModel) tableCoordsDP.getModel());
	}

	java.awt.event.MouseAdapter coordsSelectionListener = new java.awt.event.MouseAdapter() {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			int row = tableCoordsDP.rowAtPoint(evt.getPoint());
			int col = tableCoordsDP.columnAtPoint(evt.getPoint());
			if (row >= 1 && col >= 1) {
				generateCoordsValue(row, col);
			}
			if (row > 0 && col == 0) {
				generateCoordsValue(row, 1);
				generateCoordsValue(row, 2);
				generateCoordsValue(row, 3);
			}
		}
	};

	public void generateCarsValue(int row, int column) {
		VRG.generateCarsTableAtIndex(row, column);
		fillCarsTable();
	}

	java.awt.event.MouseAdapter carsSelectionListener = new java.awt.event.MouseAdapter() {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			int row = tableCars.rowAtPoint(evt.getPoint());
			int col = tableCars.columnAtPoint(evt.getPoint());
			if (row >= 0 && col >= 1 && tableCars.getValueAt(row, col) != null) {
				generateCarsValue(row, col);
			}
		}
	};

	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			int index = VRGUtils.getIntFromText(((JMenuItem) actionEvent
					.getSource()).getToolTipText());
			if (index < 5) {
				exportTable(new JTable[] { tables[index] });
			} else {
				exportTable(tables);
			}
		}
	};

	private void exportTable(JTable[] tables) {
		VRGTableExporter.exportTableToXLS(tables);
	}

	public void reSize() {
		repaint();
	}

	public KeyListener keyListener = new KeyListener() {
		@Override
		public void keyReleased(KeyEvent paramKeyEvent) {
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_ALT) {
				VRGUtils.takeScreenShotOfWindow(VRGframe.this);
			}
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_CONTROL) {
				VRGUtils.takeScreenCapture(VRGframe.this);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	};

	private javax.swing.JButton buttonAddVertex;
	private javax.swing.JButton buttonAnSolve;
	private javax.swing.JButton buttonBestSolve;
	private javax.swing.JButton buttonDeleteVertex;
	private javax.swing.JButton buttonGenerGraph;
	private javax.swing.JButton buttonGenerPath;
	private javax.swing.JButton buttonSaveCountCars;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JButton buttonStandartData;
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
	private javax.swing.JTable tableRoutes;
	private javax.swing.JTable tableResult;
	private javax.swing.JTable tableTC;
	private javax.swing.JTextField textCountCars;
	private VRGgraph graph;
	private JGraphComponent graphComponent;
}
