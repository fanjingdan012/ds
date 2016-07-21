package graphicUserInterface;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


interface accordionListener {
	public void accordionStateChanged(accordionEvent e);
}

abstract class AccordionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	abstract public JPanel makePanel();

	private final String _title;

	private final JLabel label;

	private final JPanel panel;

	private boolean openFlag = false;

	public AccordionPanel(String title) {
		super(new BorderLayout());
		_title = title;
		label = new JLabel("¡ý " + title) {
			/** */
			/**
  *  
  */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				// »æÖÆ½¥±ä
				g2.setPaint(new GradientPaint(50, 0, Color.WHITE, getWidth(),
						getHeight(), new Color(199, 212, 247)));
				g2.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		label.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				openFlag = !openFlag;
				initPanel();
				fireaccordionEvent();
			}
		});
		label.setForeground(new Color(33, 93, 198));
		label.setFont(new Font("Arial", 1, 12));
		label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
		panel = makePanel();
		panel.setOpaque(true);
		Border outBorder = BorderFactory.createMatteBorder(0, 2, 2, 2,
				Color.WHITE);
		Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border border = BorderFactory.createCompoundBorder(outBorder, inBorder);
		panel.setBorder(border);
		panel.setBackground(new Color(240, 240, 255));
		add(label, BorderLayout.NORTH);
	}

	public boolean isSelected() {
		return openFlag;
	}

	public void setSelected(boolean flg) {
		openFlag = flg;
		initPanel();
	}

	protected void initPanel() {
		if (isSelected()) {
			label.setText("¡ü " + _title);
			add(panel, BorderLayout.CENTER);
			setPreferredSize(new Dimension(getSize().width,
					label.getSize().height + panel.getSize().height));
		} else {
			label.setText("¡ý " + _title);
			remove(panel);
			setPreferredSize(new Dimension(getSize().width,
					label.getSize().height));
		}
		revalidate();
	}

	@SuppressWarnings("unchecked")
	protected ArrayList accordionListenerList = new ArrayList();

	@SuppressWarnings("unchecked")
	public void addaccordionListener(accordionListener listener) {
		if (!accordionListenerList.contains(listener))
			accordionListenerList.add(listener);
	}

	public void removeaccordionListener(accordionListener listener) {
		accordionListenerList.remove(listener);
	}

	@SuppressWarnings("unchecked")
	public void fireaccordionEvent() {
		List list = (List) accordionListenerList.clone();
		Iterator it = list.iterator();
		accordionEvent e = new accordionEvent(this);
		while (it.hasNext()) {
			accordionListener listener = (accordionListener) it.next();
			listener.accordionStateChanged(e);
		}
	}
}


class accordionEvent extends java.util.EventObject {
	/** */
	/**
  *  
  */
	private static final long serialVersionUID = 1L;

	public accordionEvent(Object source) {
		super(source);
	}
}
