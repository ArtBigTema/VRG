package vrg;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ru.amse.smyshlyaev.grapheditor.ui.JGraphComponent;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class VRGframe extends JFrame {
	public static boolean isEnabled = false;
	public static boolean isNeedToUpdate = false;
	private java.util.Timer timer;
	private boolean graphIsFirstOpened = true;
	JTable[] tables = new JTable[5];

	public VRGframe() {
		initComponents();
	}

	private void initComponents() {
		final int P_SIZE = GroupLayout.PREFERRED_SIZE;
		final int D_SIZE = GroupLayout.DEFAULT_SIZE;
		final short S_MAX = Short.MAX_VALUE;
		final javax.swing.GroupLayout.Alignment LEADING = javax.swing.GroupLayout.Alignment.LEADING;
		final java.awt.Font font = new java.awt.Font(VRGUtils.FONT_TAHOMA, 0,
				14);

		tabbedPane = new javax.swing.JTabbedPane();
		tableCoordsDP = new javax.swing.JTable();
		buttonAddVertex = new javax.swing.JButton();
		buttonGenerGraph = new javax.swing.JButton();
		buttonDeleteVertex = new javax.swing.JButton();
		textCountCars = new javax.swing.JTextField();
		buttonSaveCountCars = new javax.swing.JButton();
		tableCars = new javax.swing.JTable();
		tableRoutes = new javax.swing.JTable();
		buttonGenerPath = new javax.swing.JButton();
		tableTC = new javax.swing.JTable();
		tableResult = new javax.swing.JTable();
		buttonAnSolve = new javax.swing.JButton();
		buttonBestSolve = new javax.swing.JButton();
		buttonStandartData = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		javax.swing.JPanel p1 = new javax.swing.JPanel();
		javax.swing.JPanel p2 = new javax.swing.JPanel();
		javax.swing.JPanel p3 = new javax.swing.JPanel();
		javax.swing.JPanel p4 = new javax.swing.JPanel();
		javax.swing.JPanel p5 = new javax.swing.JPanel();
		javax.swing.JPanel p6 = new javax.swing.JPanel();
		javax.swing.JPanel p7 = new javax.swing.JPanel();
		javax.swing.JPanel p8 = new javax.swing.JPanel();
		javax.swing.JPanel p9 = new javax.swing.JPanel();
		javax.swing.JPanel p10 = new javax.swing.JPanel();
		javax.swing.JPanel p11 = new javax.swing.JPanel();
		javax.swing.JPanel p12 = new javax.swing.JPanel();
		javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
		javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
		javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
		javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
		javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
		javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		javax.swing.JInternalFrame internalFrame = new javax.swing.JInternalFrame();

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				reSize();
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		tableCoordsDP.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				null, new java.awt.Color(0, 0, 0)));
		setAllModel(tableCoordsDP, new String[] { VRGUtils.TXT_VERTEX_LABEL,
				VRGUtils.TXT_COORDS, VRGUtils.TXT_DEMAND, VRGUtils.TXT_PRICE });

		jScrollPane4.setViewportView(tableCoordsDP);
		tableCoordsDP.getColumnModel().getColumn(0).setResizable(false);
		tableCoordsDP.addMouseListener(coordsSelectionListener);

		p3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(p3);
		p3.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addComponent(buttonAddVertex)
										.addGap(18, 18, 18)
										.addComponent(buttonGenerGraph, D_SIZE,
												D_SIZE, S_MAX)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(buttonDeleteVertex))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel8Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(buttonStandartData,
												P_SIZE, 212, P_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(textCountCars, D_SIZE,
												197, S_MAX)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(buttonSaveCountCars)
										.addContainerGap()));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonAddVertex)
														.addComponent(
																buttonGenerGraph)
														.addComponent(
																buttonDeleteVertex))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonStandartData)
														.addComponent(
																textCountCars,
																P_SIZE, D_SIZE,
																P_SIZE)
														.addComponent(
																buttonSaveCountCars))
										.addGap(0, 11, S_MAX)));

		setModelForCar(tableCars, VRGUtils.TXT_GAMERS_AUTO, 4);
		jScrollPane2.setViewportView(tableCars);
		tableCars.getColumnModel().getColumn(0).setResizable(false);
		tableCars.addMouseListener(carsSelectionListener);

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(p11);
		p11.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				LEADING).addComponent(jScrollPane2,
				javax.swing.GroupLayout.Alignment.TRAILING));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel5Layout.createSequentialGroup().addGap(0, 0, S_MAX)
						.addComponent(jScrollPane2, P_SIZE, 58, P_SIZE)));

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(p2);
		p2.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout
				.createParallelGroup(LEADING)
				.addComponent(p11, D_SIZE, D_SIZE, S_MAX)
				.addComponent(p3, D_SIZE, D_SIZE, S_MAX));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap(D_SIZE, S_MAX)
										.addComponent(p3, P_SIZE, D_SIZE,
												P_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(p11, P_SIZE, D_SIZE,
												P_SIZE)));

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(p6);
		p6.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout
				.createParallelGroup(LEADING)
				.addComponent(p2, D_SIZE, D_SIZE, S_MAX)
				.addComponent(jScrollPane4));
		jPanel10Layout
				.setVerticalGroup(jPanel10Layout
						.createParallelGroup(LEADING)
						.addGroup(
								jPanel10Layout
										.createSequentialGroup()
										.addComponent(jScrollPane4, D_SIZE,
												199, S_MAX)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(p2, P_SIZE, D_SIZE,
												P_SIZE)));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(p5);
		p5.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				LEADING).addComponent(p6,
				javax.swing.GroupLayout.Alignment.TRAILING, D_SIZE, D_SIZE,
				S_MAX));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addContainerGap()
						.addComponent(p6, D_SIZE, D_SIZE, S_MAX)
						.addContainerGap()));

		jLabel2.setFont(font);
		jLabel2.setHorizontalAlignment(0);
		jLabel2.setText(VRGUtils.TXT_COORDS_DEMAND_PRICE);
		jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(p4);
		p4.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(LEADING)
				.addComponent(p5, D_SIZE, D_SIZE, S_MAX)
				.addGroup(
						jPanel1Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel2, D_SIZE, D_SIZE, S_MAX)
								.addContainerGap()));

		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addGap(5, 5, 5)
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(p5, D_SIZE, D_SIZE, S_MAX)
										.addContainerGap()));

		tabbedPane.addTab(VRGUtils.TAB_TXT_ENTER_COORDS, p4);
		tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				clickTab(evt);
			}

			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				pressTab(paramMouseEvent);
			}

		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(p8);
		p8.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(
				LEADING).addGap(0, 100, S_MAX));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(
				LEADING).addGap(0, 100, S_MAX));
		graph = new VRGgraph();
		graphComponent = new JGraphComponent(graph.getGraph(),
				VRGUtils.MAX_SIZE, VRGUtils.MAX_SIZE) {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				VRGUtils.paintCarcass(g.create());
			}
		};
		internalFrame.setVisible(true);
		javax.swing.JScrollPane jScrollPane = new javax.swing.JScrollPane();
		jScrollPane.setViewportView(graphComponent);

		javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(
				internalFrame.getContentPane());
		internalFrame.getContentPane().setLayout(jInternalFrame2Layout);
		jInternalFrame2Layout.setHorizontalGroup(jInternalFrame2Layout
				.createParallelGroup(LEADING).addComponent(jScrollPane, D_SIZE,
						0, S_MAX));
		jInternalFrame2Layout.setVerticalGroup(jInternalFrame2Layout
				.createParallelGroup(LEADING).addComponent(jScrollPane, D_SIZE,
						0, S_MAX));

		tabbedPane.addTab(VRGUtils.TAB_TXT_GRAPH, internalFrame);

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

		javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(
				p10);
		p10.setLayout(jPanel14Layout);
		jPanel14Layout
				.setHorizontalGroup(jPanel14Layout
						.createParallelGroup(LEADING)
						.addComponent(jScrollPane3)
						.addGroup(
								jPanel14Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel14Layout
														.createParallelGroup(
																LEADING)
														.addComponent(jLabel3,
																D_SIZE, D_SIZE,
																S_MAX)
														.addGroup(
																jPanel14Layout
																		.createSequentialGroup()
																		.addComponent(
																				buttonGenerPath)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jLabel4,
																				D_SIZE,
																				D_SIZE,
																				S_MAX)))
										.addContainerGap()));
		jPanel14Layout
				.setVerticalGroup(jPanel14Layout
						.createParallelGroup(LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel14Layout
										.createSequentialGroup()
										.addGap(0, 0, S_MAX)
										.addComponent(jLabel3)
										.addGap(18, 18, 18)
										.addGroup(
												jPanel14Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																buttonGenerPath)
														.addComponent(jLabel4))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jScrollPane3, P_SIZE, 83,
												P_SIZE)));

		tableTC.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		setAllModel(tableTC, new String[] { VRGUtils.TXT_VERTEX, "", "", "" });

		jScrollPane1.setViewportView(tableTC);
		tableTC.getColumnModel().getColumn(0).setResizable(false);

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(p1);
		p1.setLayout(jPanel11Layout);
		jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(
				LEADING).addComponent(jScrollPane1, D_SIZE, 506, S_MAX));
		jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(
				LEADING).addComponent(jScrollPane1, D_SIZE, 203, S_MAX));

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(p9);
		p9.setLayout(jPanel13Layout);
		jPanel13Layout.setHorizontalGroup(jPanel13Layout
				.createParallelGroup(LEADING)
				.addGroup(
						jPanel13Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(p1, D_SIZE, D_SIZE, S_MAX)
								.addContainerGap())
				.addComponent(p10, D_SIZE, D_SIZE, S_MAX));
		jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(
				LEADING).addGroup(
				jPanel13Layout.createSequentialGroup()
						.addComponent(p1, D_SIZE, D_SIZE, S_MAX)
						.addGap(18, 18, 18)
						.addComponent(p10, P_SIZE, D_SIZE, P_SIZE)
						.addContainerGap()));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(p12);
		p12.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout
				.createParallelGroup(LEADING)
				.addGroup(
						jPanel3Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel1, D_SIZE, D_SIZE, S_MAX)
								.addContainerGap())
				.addComponent(p9, javax.swing.GroupLayout.Alignment.TRAILING,
						D_SIZE, D_SIZE, S_MAX));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(jLabel1, P_SIZE, 25,
												P_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(p9, D_SIZE, D_SIZE, S_MAX)
										.addContainerGap()));

		tabbedPane.addTab(VRGUtils.TXT_TRANSPORTS_COSTS, p12);

		tableResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tableResult.getTableHeader().setReorderingAllowed(false);
		setAllModel(tableResult, new String[] { VRGUtils.TXT_PLAYER_NUMBER,
				VRGUtils.TXT_ROUTE_NUMBER, VRGUtils.TXT_LENGTH_OF_ROUTE,
				VRGUtils.TXT_LOAD_VEHICLE, VRGUtils.TXT_PROFIT_LABEL });
		tableResult.getColumnModel().getColumn(1).setMinWidth(213);

		tableResult.setColumnSelectionAllowed(true);
		tableResult.getTableHeader().setReorderingAllowed(false);
		jScrollPane5.setViewportView(tableResult);
		tableResult
				.getColumnModel()
				.getSelectionModel()
				.setSelectionMode(
						javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableResult.getColumnModel().getColumn(0).setResizable(false);
		tableResult.getColumnModel().getColumn(1).setMinWidth(213);
		tableResult.getColumnModel().getColumn(2).setResizable(false);

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

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(p7);
		p7.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(LEADING)
						.addComponent(jScrollPane5, D_SIZE, 526, S_MAX)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																LEADING)
														.addComponent(jLabel6,
																D_SIZE, D_SIZE,
																S_MAX)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addComponent(
																				buttonAnSolve)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				D_SIZE,
																				S_MAX)
																		.addComponent(
																				buttonBestSolve)))
										.addContainerGap()));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
						.createParallelGroup(LEADING)
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
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jScrollPane5, D_SIZE,
												353, S_MAX)));

		tabbedPane.addTab(VRGUtils.TAB_TXT_RESULT, p7);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
				.addComponent(tabbedPane));
		layout.setVerticalGroup(layout.createParallelGroup(LEADING)
				.addComponent(tabbedPane));

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
		graphComponent.addKeyListener(keyListener);
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
		JMenu jMenu1 = new JMenu();
		JMenu jMenu2 = new JMenu();

		jMenu1.setText(VRGUtils.MENU_FILE);
		menuBar.add(jMenu1);

		jMenu2.setText(VRGUtils.MENU_EDIT);
		menuBar.add(jMenu2);
		setJMenuBar(menuBar);

		JMenu menu;
		{
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(VRGUtils.MENU_NEW);
			jMenu1.add(menuItem);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					buttonDeleteVertexActionPerformed(evt);
					buttonGenerGraphActionPerformed(evt);
				}
			});

			menu = new JMenu();
			menu.setText(VRGUtils.MENU_EXPORT);
			jMenu1.add(menu);
		}

		{
			JMenuItem menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_COORDS_D_P);
			menuTable.setToolTipText("0");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_CARS);
			menuTable.setToolTipText("1");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_COSTS);
			menuTable.setToolTipText("2");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_ROUTES);
			menuTable.setToolTipText("3");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_RESULT);
			menuTable.setToolTipText("4");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_EXPORT_ALL);
			menuTable.setToolTipText("5");
			menuTable.addActionListener(actionListener);
			menu.add(menuTable);

			menuTable = new JMenuItem();
			menuTable.setText(VRGUtils.MENU_EXPORT_GRAPH);
			menuTable.setAutoscrolls(true);
			menu.add(menuTable);
			menuTable.addActionListener(actionGraph);

		}

		{
			menu = new JMenu();
			menu.setText(VRGUtils.MENU_IMPORT);
			jMenu1.add(menu);

			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(VRGUtils.MENU_COORDS);
			menu.add(menuItem);

			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					VRG.readTableFromFile(VRGframe.this);
					fillAllStandart();
				}
			});

			menuItem = new JMenuItem();
			menuItem.setText(VRGUtils.MENU_EXPORT_GRAPH);
			menuItem.setAutoscrolls(false);
			menu.add(menuItem);
			menuItem.addActionListener(actionGraph);
		}

		jMenu1.addSeparator();
		{
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(VRGUtils.MENU_CLOSE);
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
			VRG.generateOptimalRoutes();
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

	private void openGraph(boolean isNewStyle) {
		if (isNewStyle) {
			graph = new VRGgraph(this);
			graphComponent.setGraph(graph.getGraph());
			repaint();
		} else {
			if (graphIsFirstOpened) {
				tabbedPane.setSelectedIndex(3);
				GraphFrame frame = new GraphFrame();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
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

	public void pressTab(MouseEvent paramMouseEvent) {
		clickTab(null);
	}

	private long ms = 0;

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
			fillResultTable();
			graphIsFirstOpened = true;

			openGraph(!((ms + 300) > System.currentTimeMillis()));// DoubleClick

			ms = System.currentTimeMillis();
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

	ActionListener actionGraph = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent evt) {
			VRGUtils.IOGraph(graphComponent,
					((JMenuItem) evt.getSource()).getAutoscrolls());
		}
	};

	public void reSize() {
		repaint();
		VRGUtils.windowWidth = getWidth();
		VRGUtils.windowHeight = getHeight();
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
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
				if (spaceListener != null) {
					spaceListener.spacePressed();
					reSize();
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	};

	public interface onSpacePressed {
		public void spacePressed();
	}

	public void setListener(onSpacePressed listener) {
		this.spaceListener = listener;
	}

	public onSpacePressed spaceListener;
	private javax.swing.JButton buttonAddVertex;
	private javax.swing.JButton buttonAnSolve;
	private javax.swing.JButton buttonBestSolve;
	private javax.swing.JButton buttonDeleteVertex;
	private javax.swing.JButton buttonGenerGraph;
	private javax.swing.JButton buttonGenerPath;
	private javax.swing.JButton buttonSaveCountCars;
	private javax.swing.JButton buttonStandartData;
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
