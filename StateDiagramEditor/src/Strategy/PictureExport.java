package Strategy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import AbstractFactory_and_Prototype.StateDiagram;

public class PictureExport extends Export {

	private StateDiagram sd;

	public PictureExport(StateDiagram sd) {
		this.sd = sd;
	}

	@Override
	public void saveDocument() {
		String FileName = super.pickLocation();
		FileName = FileName + ".png";
		DiagramPanel dp = new DiagramPanel(sd);
		dp.repaint();
		BufferedImage bi = new BufferedImage(dp.getSize().width,
				dp.getSize().height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		dp.paint(g); // this == JComponent
		g.dispose();
		try {
			ImageIO.write(bi, "png", new File(FileName));
		} catch (Exception e) {
		}
	}

}
