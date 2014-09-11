package vrg;



import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class BasicPaint {

	@SuppressWarnings("unused")
	private BufferedImage originalImage;
	private BufferedImage canvasImage;
	private JPanel gui;

	private Color color = Color.gray;
	private Color backcolor = Color.WHITE;
	private JLabel output = new JLabel("Ваш рисунок!");
	private Rectangle p = new Rectangle(750, 500);
	private BufferedImage colorSample = new BufferedImage(16, 16,
			BufferedImage.TYPE_INT_RGB);
	private JLabel imageLabel;
	public int activeTool;
	public static final int SELECTION_TOOL = 0;
	public static final int DRAW_TOOL = 1;
	public static final int TEXT_TOOL = 2;
	public static final int CLEARS_TOOL = 3;
	private Point selectionStart;
	private Rectangle selection;
	private boolean err = false;
	private Stroke stroke = new BasicStroke(3, BasicStroke.CAP_ROUND,
			BasicStroke.JOIN_ROUND, 1.7f);
	private RenderingHints renderingHints;

	public JComponent getGui() {
		if (gui == null) {
			Map<Key, Object> hintsMap = new HashMap<RenderingHints.Key, Object>();
			hintsMap.put(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			hintsMap.put(RenderingHints.KEY_DITHERING,
					RenderingHints.VALUE_DITHER_ENABLE);
			hintsMap.put(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			renderingHints = new RenderingHints(hintsMap);

			setImage(new BufferedImage(p.width, p.height,
					BufferedImage.TYPE_INT_RGB));
			gui = new JPanel(new BorderLayout(4, 4));
			gui.setBorder(new EmptyBorder(5, 3, 5, 3));

			JPanel imageView = new JPanel(new GridBagLayout());
			imageView.setPreferredSize(new Dimension(p.width,p.height));
			imageLabel = new JLabel(new ImageIcon(canvasImage));
			JScrollPane imageScroll = new JScrollPane(imageView);
			imageView.add(imageLabel);
			imageLabel.addMouseMotionListener(new ImageMouseMotionListener());
			imageLabel.addMouseListener(new ImageMouseListener());
			gui.add(imageScroll, BorderLayout.CENTER);
			backcolor = color;
			JToolBar tb = new JToolBar();
			tb.setFloatable(false);
			JButton colorButton = new JButton("Цвет |");
			colorButton.setMnemonic('o');
			colorButton.setToolTipText("Выберите цвет");
			ActionListener colorListener = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color c = JColorChooser.showDialog(gui, "Выберите цвет",
							color);
					if (c != null) {
						setColor(c);
					}
				}
			};
			colorButton.addActionListener(colorListener);
			colorButton.setIcon(new ImageIcon(colorSample));
			tb.add(colorButton);
			// backcolor=color;
			setColor(color);

			final SpinnerNumberModel strokeModel = new SpinnerNumberModel(3, 1,
					20, 1); // new SpinnerNumberModel(3,1,16,1);
			JSpinner strokeSize = new JSpinner(strokeModel);
			ChangeListener strokeListener = new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					Object o = strokeModel.getValue();
					Integer i = (Integer) o;
					stroke = new BasicStroke(i.intValue(),
							BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.7f);
				}
			};
			strokeSize.addChangeListener(strokeListener);
			strokeSize.setMaximumSize(strokeSize.getPreferredSize());
			JLabel strokeLabel = new JLabel("Размер ");
			strokeLabel.setLabelFor(strokeSize);
			strokeLabel.setDisplayedMnemonic('t');
			tb.add(strokeLabel);
			tb.add(strokeSize);

			tb.addSeparator();

			ActionListener clearListener = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int result = JOptionPane.OK_OPTION;
					backcolor = color;
					if (err) {
						result = JOptionPane.showConfirmDialog(gui,
								"Стереть всё?");
					}
					if (result == JOptionPane.OK_OPTION) {
						clear(canvasImage);
					}

				}
			};
			JButton clearButton = new JButton("Очистить");
			tb.add(clearButton);
			clearButton.addActionListener(clearListener);
			// backcolor=color;
			gui.add(tb, BorderLayout.PAGE_START);

			JToolBar tools = new JToolBar(JToolBar.VERTICAL);
			tools.setFloatable(false);
			JButton crop = new JButton("Инструмент");
			final JRadioButton select = new JRadioButton("Выбрать т.", true);
			final JRadioButton draw = new JRadioButton("Рисовать");
			final JRadioButton clears = new JRadioButton("Ластик");
			final JRadioButton text = new JRadioButton("Текст");

			tools.add(crop);
			tools.add(select);
			tools.add(draw);
			tools.add(clears);
			tools.add(text);

			ButtonGroup bg = new ButtonGroup();
			bg.add(select);
			bg.add(text);
			bg.add(draw);
			bg.add(clears);
			ActionListener toolGroupListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (ae.getSource() == select) {
						activeTool = SELECTION_TOOL;
					} else if (ae.getSource() == draw) {
						activeTool = DRAW_TOOL;
					} else if (ae.getSource() == text) {
						activeTool = TEXT_TOOL;
					} else if (ae.getSource() == clears) {
						activeTool = CLEARS_TOOL;
					}
				}
			};
			select.addActionListener(toolGroupListener);
			draw.addActionListener(toolGroupListener);
			text.addActionListener(toolGroupListener);
			clears.addActionListener(toolGroupListener);

			gui.add(tools, BorderLayout.LINE_END);

			gui.add(output, BorderLayout.PAGE_END);
			clear(colorSample);
			clear(canvasImage);
		}
		return gui;
	}

	/** Clears the entire image area by painting it with the current color. */
	public void clear(BufferedImage bi) {
		Graphics2D g = bi.createGraphics();
		g.setRenderingHints(renderingHints);
		// backcolor=color;
		g.setColor(color);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());

		g.dispose();
		imageLabel.repaint();
	}

	public void setImage(BufferedImage image) {
		this.originalImage = image;
		int w = image.getWidth();
		int h = image.getHeight();
		canvasImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = this.canvasImage.createGraphics();
		g.setRenderingHints(renderingHints);
		g.drawImage(image, 0, 0, gui);
		g.dispose();

		selection = new Rectangle(0, 0, w, h);
		if (this.imageLabel != null) {
			imageLabel.setIcon(new ImageIcon(canvasImage));
			this.imageLabel.repaint();
		}
		if (gui != null) {
			gui.invalidate();
		}
	}

	public void setColor(Color col) {
		this.color = col;
		clear(colorSample);
	}

	private JMenu getFileMenu(boolean webstart) {
		JMenu file = new JMenu("Файл");
		file.setMnemonic('f');

		JMenuItem newImageItem = new JMenuItem("Новый рис.");
		newImageItem.setMnemonic('n');

		ActionListener newImage = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage bi = new BufferedImage(360, 300,
						BufferedImage.TYPE_INT_ARGB);
				clear(bi);
				backcolor = color;
				setImage(bi);
			}
		};
		newImageItem.addActionListener(newImage);
		file.add(newImageItem);

		if (!webstart) {
			file.addSeparator();
			ActionListener openListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (!err) {
						JFileChooser ch = getFileChooser();
						int result = ch.showOpenDialog(gui);
						if (result == JFileChooser.APPROVE_OPTION) {
							try {
								BufferedImage bi = ImageIO.read(ch
										.getSelectedFile());
								setImage(bi);
							} catch (IOException e) {
								showError(e);
								e.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(gui,
								"Сохранить изображение ");
					}
				}
			};
			JMenuItem openItem = new JMenuItem("Открыть");
			openItem.setMnemonic('o');
			openItem.addActionListener(openListener);
			file.add(openItem);

			ActionListener saveListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser ch = getFileChooser();
					int result = ch.showSaveDialog(gui);
					if (result == JFileChooser.APPROVE_OPTION) {
						try {
							File f = ch.getSelectedFile();
							ImageIO.write(BasicPaint.this.canvasImage, "png", f);
							BasicPaint.this.originalImage = BasicPaint.this.canvasImage;
							err = false;
						} catch (IOException ioe) {
							showError(ioe);
							ioe.printStackTrace();
						}
					}
				}
			};
			JMenuItem saveItem = new JMenuItem("Сохранить");
			saveItem.addActionListener(saveListener);
			saveItem.setMnemonic('s');
			file.add(saveItem);
		}

		if (canExit()) {
			ActionListener exit = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			};
			JMenuItem exitItem = new JMenuItem("Выход");
			exitItem.setMnemonic('x');
			file.addSeparator();
			exitItem.addActionListener(exit);
			file.add(exitItem);
		}

		return file;
	}

	private void showError(Throwable t) {
		JOptionPane.showMessageDialog(gui, t.getMessage(), t.toString(),
				JOptionPane.ERROR_MESSAGE);
	}

	JFileChooser chooser = null;

	public JFileChooser getFileChooser() {
		if (chooser == null) {
			chooser = new JFileChooser();
			FileFilter ff = new FileNameExtensionFilter("Файлы изображений",
					ImageIO.getReaderFileSuffixes());
			chooser.setFileFilter(ff);
		}
		return chooser;

	}

	public boolean canExit() {
		boolean canExit = false;
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			canExit = true;
		} else {
			try {
				sm.checkExit(0);
				canExit = true;
			} catch (Exception stayFalse) {
			}
		}

		return canExit;
	}

	public JMenuBar getMenuBar(boolean webstart) {
		JMenuBar mb = new JMenuBar();
		mb.add(this.getFileMenu(webstart));
		return mb;
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					// use default
				}
				/*
				 * BasicPaint bp = new BasicPaint();
				 * 
				 * JFrame f = new JFrame("Paint!");
				 * f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 * f.setLocationByPlatform(true);
				 * 
				 * f.setContentPane(bp.getGui());
				 * f.setJMenuBar(bp.getMenuBar(false));
				 * 
				 * f.pack(); f.setMinimumSize(f.getSize()); f.setVisible(true);
				 */
			}
		};
		SwingUtilities.invokeLater(r);
	}

	public void text(Point point) {
		String text = JOptionPane.showInputDialog(gui, "Добавить текст",
				"текст");
		if (text != null) {
			Graphics2D g = this.canvasImage.createGraphics();
			g.setRenderingHints(renderingHints);
			g.setColor(this.color);
			g.setStroke(stroke);
			g.drawString(text, point.x, point.y);
			g.dispose();
			this.imageLabel.repaint();
		}
	}

	public void str(int x, int y, Color c, String str) {

		Graphics2D g = this.canvasImage.createGraphics();
		g.setRenderingHints(renderingHints);
		g.setColor(c);
		g.setStroke(stroke);
		g.drawString(str, x, y);
		g.dispose();
		this.imageLabel.repaint();
	}

	public void paint(int n, int x, int y, Color c, String s) {
		Graphics2D g = this.canvasImage.createGraphics();
		g.setRenderingHints(renderingHints);
		g.setColor(c);
		g.setStroke(stroke);
		g.drawLine(x, n, y, n);
		str(y + 5, n + 5, c, s);
		g.dispose();
		this.imageLabel.repaint();
	}

	public void draw(Point point) {
		Graphics2D g = this.canvasImage.createGraphics();
		g.setRenderingHints(renderingHints);
		g.setColor(this.color);
		g.setStroke(stroke);
		int n = 0;
		g.drawLine(point.x, point.y, point.x + n, point.y + n);
		g.dispose();
		this.imageLabel.repaint();
	}

	public void clears(Point point) {
		Graphics2D g = this.canvasImage.createGraphics();
		g.setRenderingHints(renderingHints);
		g.setColor(this.backcolor);
		g.setStroke(stroke);
		int n = 0;
		g.drawLine(point.x, point.y, point.x + n, point.y + n);
		g.dispose();
		this.imageLabel.repaint();
	}

	class ImageMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent arg0) {
			if (activeTool == BasicPaint.SELECTION_TOOL) {
				selectionStart = arg0.getPoint();
			} else if (activeTool == BasicPaint.DRAW_TOOL) {
				draw(arg0.getPoint());
			} else if (activeTool == BasicPaint.TEXT_TOOL) {
				text(arg0.getPoint());
			} else if (activeTool == BasicPaint.CLEARS_TOOL) {
				clears(arg0.getPoint());
			} else {
				JOptionPane.showMessageDialog(gui, "Ошибка приложения",
						"Ошибка!", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (activeTool == BasicPaint.SELECTION_TOOL) {
				selection = new Rectangle(selectionStart.x, selectionStart.y,
						arg0.getPoint().x, arg0.getPoint().y);
			}
		}
	}

	class ImageMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			reportPositionAndColor(arg0);
			if (activeTool == BasicPaint.SELECTION_TOOL) {
				selection = new Rectangle(selectionStart.x, selectionStart.y,
						arg0.getPoint().x - selectionStart.x, arg0.getPoint().y
								- selectionStart.y);
			} else if (activeTool == BasicPaint.DRAW_TOOL) {
				draw(arg0.getPoint());
			} else if (activeTool == BasicPaint.CLEARS_TOOL) {
				clears(arg0.getPoint());
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			reportPositionAndColor(arg0);
		}

	}

	public void setRect(int w, int h) {
		p.height = h;
		p.width = w;
	}

	private void reportPositionAndColor(MouseEvent me) {
		String text = "";
		if (activeTool == BasicPaint.SELECTION_TOOL) {
			text += "Выбрано (X,Y:WxH): " + (int) selection.getX() + ","
					+ (int) selection.getY() + ":" + (int) selection.getWidth()
					+ "x" + (int) selection.getHeight();
		} else {
			text += "X,Y: " + (me.getPoint().x + 1) + ","
					+ (me.getPoint().y + 1);
		}
		output.setText(text);
	}
}