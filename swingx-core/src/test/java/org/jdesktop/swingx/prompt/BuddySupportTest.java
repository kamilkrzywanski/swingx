package org.jdesktop.swingx.prompt;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTextField;


import org.jdesktop.swingx.plaf.BuddyLayoutAndBorder;
import org.jdesktop.swingx.plaf.BuddyTextFieldUI;
import org.jdesktop.swingx.prompt.BuddySupport.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

public class BuddySupportTest {
	private JTextField tf = new JTextField();

	@Test
	public void testAdd() throws Exception {
		JButton button = new JButton();
		BuddySupport.addLeft(button, tf);
		Assertions.assertSame(BuddyLayoutAndBorder.class, tf.getLayout().getClass());
		Assertions.assertSame(1, tf.getComponentCount());
		Assertions.assertSame(button, tf.getComponent(0));
	}

	@Test
	public void testDoubleAdd() throws Exception {
		JButton button = new JButton();
		BuddySupport.addLeft(button, tf);
		try {
			BuddySupport.addLeft(button, tf);
			fail();
		} catch (IllegalStateException e) {
			
		}
		try {
			BuddySupport.addRight(button, tf);
			fail();
		} catch (IllegalStateException e) {
			
		}
	}

	@Test
	public void testGet() throws Exception {
		Assertions.assertSame(0, BuddySupport.getLeft(tf).size());
		JButton button = new JButton();
		BuddySupport.addLeft(button, tf);
		Assertions.assertSame(1, BuddySupport.getLeft(tf).size());
	}
	
	@Test
	public void testRemove() throws Exception {
		JButton button = new JButton();
		BuddySupport.addLeft(button, tf);
		BuddySupport.remove(button, tf);
		Assertions.assertSame(0, BuddySupport.getLeft(tf).size());
		Assertions.assertSame(0, tf.getComponentCount());
	}
	
	@Test
	public void testRemoveAll() throws Exception {
		JButton button1 = new JButton();
		JButton button2 = new JButton();
		BuddySupport.addLeft(button1, tf);
		BuddySupport.addRight(button2, tf);
		BuddySupport.removeAll(tf);
		Assertions.assertSame(0, BuddySupport.getLeft(tf).size());
		Assertions.assertSame(0, BuddySupport.getRight(tf).size());
		Assertions.assertSame(0, tf.getComponentCount());
	}
	
	@Test
	public void testEnsureBuddiesInComponentHierarchy() throws Exception {
		JButton button = new JButton();
		BuddySupport.addLeft(button, tf);
		tf.remove(button);
		BuddySupport.ensureBuddiesAreInComponentHierarchy(tf);
		Assertions.assertSame(1, BuddySupport.getLeft(tf).size());
		Assertions.assertSame(1, tf.getComponentCount());
	}
	
	@Test
	public void testGetAfterPlainRemove() throws Exception {
		JButton button = new JButton();
		BuddySupport.addLeft(button, tf);
		tf.remove(button);
	}
	
	@Test
	public void testIsBuddy() throws Exception {
		JButton button = new JButton();
		assertFalse(BuddySupport.isBuddy(button, tf));
		tf.add(button);
		assertFalse(BuddySupport.isBuddy(button, tf));
		BuddySupport.addLeft(button, tf);
		assertTrue(BuddySupport.isBuddy(button, tf));
		tf.remove(button);
	}

	@Test
	public void testInstall() throws Exception {
		BuddySupport.add(new JButton(), Position.LEFT, tf);

		Assertions.assertSame(BuddyTextFieldUI.class, tf.getUI().getClass());
		Assertions.assertSame(BuddyLayoutAndBorder.class, tf.getBorder().getClass());
		Assertions.assertSame(BuddyLayoutAndBorder.class, tf.getLayout().getClass());
	}

	@Test
	public void testLookAndFeelChange() throws Exception {
		BuddySupport.addLeft(new BuddyButton(), tf);
		BuddySupport.addRight(new BuddyButton(), tf);

		Assertions.assertSame(2, tf.getComponentCount());
		tf.updateUI();
		Assertions.assertSame(2, tf.getComponentCount());
	}

	@Test
	public void testCreateNegativeGap() throws Exception {
		Component c = BuddySupport.createGap(-1);
		Assertions.assertSame(-1, c.getPreferredSize().width);
	}

}
