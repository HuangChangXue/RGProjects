package priv.hcx.sender.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

public class PreviewPanel extends JPanel {
	private final JPanel panel_1 = new JPanel();
	JButton btn_preview = new JButton("预览");
	JButton btn_synSend = new JButton("同步发送");
	JButton btn_format = new JButton("格式化");
	JButton btn_clear = new JButton("清空记录");
	JButton btn_send = new JButton("异步发送");
	JTextArea txt_send = new JTextArea();
	JTextArea txt_receive = new JTextArea();
	JComboBox comboBox = new JComboBox();

	public PreviewPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new MigLayout("", "[73.00px]", "[21px,center][][]"));

		panel.add(comboBox, "cell 0 0,growx,aligny center");

		// btn_preview.addActionListener();
		panel.add(btn_preview, "cell 0 1,growx,aligny center");

		panel.add(btn_format, "cell 0 2,growx");
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new MigLayout("", "[93px,center]", "[23px,center][fill][]"));

		panel_1.add(btn_clear, "cell 0 0,growx");

		panel_1.add(btn_synSend, "cell 0 1,growx,aligny center");

		panel_1.add(btn_send, "cell 0 2,growx");

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		panel_2.add(splitPane);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		scrollPane.setViewportView(txt_send);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(txt_receive);
	}

}

class PreviewActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	}
}